package com.krx.service;

import java.math.BigDecimal;
import java.util.List;

import com.krx.dao.EtfDAO;
import com.krx.dao.EtfDAOImpl;
import com.krx.dto.EtfDTO;

public class EtfServiceImpl implements EtfService {
    private final EtfDAO dao = new EtfDAOImpl();

    @Override
    public List<EtfDTO> getEtfList() {
        return dao.selectEtfList();
    }

    @Override
    public EtfDTO getEtf(long id) {
        return dao.selectEtfById(id);
    }

    @Override
    public EtfDTO createEtf(EtfDTO etf) {
        validate(etf);
        dao.insertEtf(etf);
        return etf;
    }

    @Override
    public boolean modifyEtf(long id, EtfDTO etf) {
        validate(etf);
        etf.setId(id);
        return dao.updateEtf(etf) > 0;
    }

    @Override
    public boolean removeEtf(long id) {
        return dao.deleteEtf(id) > 0;
    }

    private void validate(EtfDTO e) {
        if (e.getBaseDate() == null || e.getTicker() == null || e.getTicker().trim().isEmpty()
                || e.getEtfName() == null || e.getEtfName().trim().isEmpty()) {
            throw new IllegalArgumentException("기준일, 종목코드, ETF명은 필수입니다.");
        }
        if (e.getClosingPrice() == null || e.getPriceChange() == null || e.getChangeRate() == null
                || e.getEstimatedNav() == null || e.getNetAssetTotal() == null
                || e.getTradingVolume() == null || e.getTradingValue() == null) {
            throw new IllegalArgumentException("가격·자산·거래량 항목은 숫자로 입력해야 합니다.");
        }
        if (e.getClosingPrice().compareTo(BigDecimal.ZERO) < 0
                || e.getEstimatedNav().compareTo(BigDecimal.ZERO) < 0
                || e.getNetAssetTotal().compareTo(BigDecimal.ZERO) < 0
                || e.getTradingVolume() < 0 || e.getTradingValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격, 자산, 거래량은 음수일 수 없습니다.");
        }
        e.setTicker(e.getTicker().trim());
        e.setEtfName(e.getEtfName().trim());
        if (e.getUnderlyingIndex() != null) e.setUnderlyingIndex(e.getUnderlyingIndex().trim());
    }
}
