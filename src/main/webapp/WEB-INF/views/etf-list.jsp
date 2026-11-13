<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KRX ETF 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <div>
            <h1>KRX ETF 관리</h1>
            <p>ETF 정보를 조회하고 등록·수정·삭제합니다.</p>
        </div>
        <button type="button" id="newButton" class="primary">신규 등록</button>
    </header>

    <section class="panel">
        <div class="toolbar">
            <input type="search" id="searchInput" placeholder="종목코드 또는 ETF명 검색">
            <span id="totalCount">총 0건</span>
        </div>
        <div id="message" class="message" role="status"></div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>기준일</th><th>종목코드</th><th>ETF명</th><th>종가</th>
                    <th>대비</th><th>등락률</th><th>추정 NAV</th><th>순자산총액</th>
                    <th>거래량</th><th>거래대금</th><th>기초지수</th><th>관리</th>
                </tr>
                </thead>
                <tbody id="etfTableBody">
                <tr><td colspan="12" class="empty">데이터를 불러오는 중입니다.</td></tr>
                </tbody>
            </table>
        </div>
    </section>
</div>

<div id="formModal" class="modal hidden">
    <div class="modal-card">
        <div class="modal-header">
            <h2 id="formTitle">ETF 신규 등록</h2>
            <button type="button" id="closeButton" class="close">×</button>
        </div>
        <form id="etfForm">
            <input type="hidden" id="etfId">
            <div class="form-grid">
                <label>기준일<input type="date" id="baseDate" required></label>
                <label>종목코드<input type="text" id="ticker" maxlength="20" required></label>
                <label class="wide">ETF명<input type="text" id="etfName" maxlength="200" required></label>
                <label>종가<input type="number" id="closingPrice" min="0" step="0.01" required></label>
                <label>대비<input type="number" id="priceChange" step="0.01" required></label>
                <label>등락률(%)<input type="number" id="changeRate" step="0.0001" required></label>
                <label>추정 NAV<input type="number" id="estimatedNav" min="0" step="0.01" required></label>
                <label>순자산총액<input type="number" id="netAssetTotal" min="0" step="0.01" required></label>
                <label>거래량<input type="number" id="tradingVolume" min="0" step="1" required></label>
                <label>거래대금<input type="number" id="tradingValue" min="0" step="0.01" required></label>
                <label class="wide">기초지수<input type="text" id="underlyingIndex" maxlength="200"></label>
            </div>
            <div class="form-actions">
                <button type="button" id="cancelButton" class="secondary">취소</button>
                <button type="submit" class="primary">저장</button>
            </div>
        </form>
    </div>
</div>

<script>
    window.APP_CONTEXT = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/etf.js"></script>
</body>
</html>
