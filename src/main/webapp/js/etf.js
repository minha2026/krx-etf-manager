(() => {
    'use strict';

    const API = `${window.APP_CONTEXT}/api/etfs`;
    const $ = (id) => document.getElementById(id);
    const tbody = $('etfTableBody');
    const modal = $('formModal');
    const form = $('etfForm');
    let etfRows = [];

    const numberFormat = new Intl.NumberFormat('ko-KR', { maximumFractionDigits: 2 });
    const rateFormat = new Intl.NumberFormat('ko-KR', { minimumFractionDigits: 2, maximumFractionDigits: 4 });

    function showMessage(message, isError = false) {
        const box = $('message');
        box.textContent = message || '';
        box.classList.toggle('error', isError);
    }

    async function request(url, options = {}) {
        const response = await fetch(url, {
            headers: { 'Content-Type': 'application/json' },
            ...options
        });
        const raw = await response.text();
        let data = null;
        try { data = raw ? JSON.parse(raw) : null; } catch (_) { data = raw; }
        if (!response.ok) {
            throw new Error(data && data.message ? data.message : `요청 실패 (${response.status})`);
        }
        return data;
    }

    function formatNumber(value) {
        if (value === null || value === undefined || value === '') return '-';
        return numberFormat.format(Number(value));
    }

    // 서버에서 받은 값을 화면 표시용 문자열로 가공합니다.
    function renderRows(rows) {
        const keyword = $('searchInput').value.trim().toLowerCase();
        const filtered = rows.filter(row =>
            String(row.ticker || '').toLowerCase().includes(keyword) ||
            String(row.etfName || '').toLowerCase().includes(keyword)
        );

        $('totalCount').textContent = `총 ${filtered.length}건`;
        tbody.replaceChildren();

        if (filtered.length === 0) {
            const tr = document.createElement('tr');
            const td = document.createElement('td');
            td.colSpan = 12;
            td.className = 'empty';
            td.textContent = '조회된 데이터가 없습니다.';
            tr.appendChild(td);
            tbody.appendChild(tr);
            return;
        }

        filtered.forEach(row => {
            const tr = document.createElement('tr');
            const values = [
                row.baseDate, row.ticker, row.etfName,
                formatNumber(row.closingPrice), formatNumber(row.priceChange),
                `${rateFormat.format(Number(row.changeRate || 0))}%`,
                formatNumber(row.estimatedNav), formatNumber(row.netAssetTotal),
                formatNumber(row.tradingVolume), formatNumber(row.tradingValue),
                row.underlyingIndex || '-'
            ];
            values.forEach(value => {
                const td = document.createElement('td');
                td.textContent = value == null ? '' : String(value);
                tr.appendChild(td);
            });

            const action = document.createElement('td');
            const edit = document.createElement('button');
            edit.type = 'button';
            edit.className = 'small secondary';
            edit.textContent = '수정';
            edit.addEventListener('click', () => openEdit(row.id));
            const remove = document.createElement('button');
            remove.type = 'button';
            remove.className = 'small danger';
            remove.textContent = '삭제';
            remove.addEventListener('click', () => deleteEtf(row.id));
            action.append(edit, remove);
            tr.appendChild(action);
            tbody.appendChild(tr);
        });
    }

    async function loadEtfs() {
        try {
            etfRows = await request(API);
            renderRows(etfRows);
        } catch (error) {
            showMessage(error.message, true);
            tbody.innerHTML = '<tr><td colspan="12" class="empty">목록을 불러오지 못했습니다.</td></tr>';
        }
    }

    function openNew() {
        form.reset();
        $('etfId').value = '';
        $('formTitle').textContent = 'ETF 신규 등록';
        $('baseDate').value = new Date().toISOString().slice(0, 10);
        modal.classList.remove('hidden');
    }

    async function openEdit(id) {
        try {
            const row = await request(`${API}/${id}`);
            $('etfId').value = row.id;
            $('formTitle').textContent = 'ETF 정보 수정';
            Object.keys(row).forEach(key => {
                const field = $(key);
                if (field && row[key] != null) field.value = row[key];
            });
            modal.classList.remove('hidden');
        } catch (error) {
            showMessage(error.message, true);
        }
    }

    function closeModal() {
        modal.classList.add('hidden');
    }

    // 폼 입력을 읽어 공백 제거 및 숫자형 변환 후 JSON payload를 만듭니다.
    function buildPayload() {
        const text = id => $(id).value.trim();
        const numeric = id => {
            const value = text(id);
            if (value === '') throw new Error(`${id} 항목을 입력하세요.`);
            const parsed = Number(value);
            if (!Number.isFinite(parsed)) throw new Error(`${id} 항목은 숫자여야 합니다.`);
            return parsed;
        };

        return {
            baseDate: text('baseDate'),
            ticker: text('ticker'),
            etfName: text('etfName'),
            closingPrice: numeric('closingPrice'),
            priceChange: numeric('priceChange'),
            changeRate: numeric('changeRate'),
            estimatedNav: numeric('estimatedNav'),
            netAssetTotal: numeric('netAssetTotal'),
            tradingVolume: Math.trunc(numeric('tradingVolume')),
            tradingValue: numeric('tradingValue'),
            underlyingIndex: text('underlyingIndex')
        };
    }

    async function saveEtf(event) {
        event.preventDefault();
        try {
            const payload = buildPayload();
            if (!payload.baseDate || !payload.ticker || !payload.etfName) {
                throw new Error('기준일, 종목코드, ETF명은 필수입니다.');
            }
            if (payload.closingPrice < 0 || payload.estimatedNav < 0 ||
                payload.netAssetTotal < 0 || payload.tradingVolume < 0 || payload.tradingValue < 0) {
                throw new Error('가격, 자산, 거래량은 음수일 수 없습니다.');
            }

            const id = $('etfId').value;
            const isEdit = Boolean(id);
            await request(isEdit ? `${API}/${id}` : API, {
                method: isEdit ? 'PUT' : 'POST',
                body: JSON.stringify(payload)
            });
            closeModal();
            showMessage(isEdit ? 'ETF 정보가 수정되었습니다.' : 'ETF가 등록되었습니다.');
            await loadEtfs();
        } catch (error) {
            showMessage(error.message, true);
        }
    }

    async function deleteEtf(id) {
        const target = etfRows.find(row => String(row.id) === String(id));
        if (!window.confirm(`${target ? target.etfName : '선택한 ETF'} 정보를 삭제할까요?`)) return;
        try {
            await request(`${API}/${id}`, { method: 'DELETE' });
            showMessage('ETF 정보가 삭제되었습니다.');
            await loadEtfs();
        } catch (error) {
            showMessage(error.message, true);
        }
    }

    $('newButton').addEventListener('click', openNew);
    $('closeButton').addEventListener('click', closeModal);
    $('cancelButton').addEventListener('click', closeModal);
    $('searchInput').addEventListener('input', () => renderRows(etfRows));
    form.addEventListener('submit', saveEtf);

    loadEtfs();
})();
