package com.krx.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class EtfDTO {
    private Long id;
    private Date baseDate;
    private String ticker;
    private String etfName;
    private Long closingPrice;
    private Long priceChange;
    private BigDecimal changeRate;
    private BigDecimal estimatedNav;
    private BigDecimal netAssetTotal;
    private Long tradingVolume;
    private Long tradingValue;
    private String underlyingIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        id = v;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date v) {
        baseDate = v;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String v) {
        ticker = v;
    }

    public String getEtfName() {
        return etfName;
    }

    public void setEtfName(String v) {
        etfName = v;
    }

    public Long getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Long v) {
        closingPrice = v;
    }

    public Long getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Long v) {
        priceChange = v;
    }

    public BigDecimal getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(BigDecimal v) {
        changeRate = v;
    }

    public BigDecimal getEstimatedNav() {
        return estimatedNav;
    }

    public void setEstimatedNav(BigDecimal v) {
        estimatedNav = v;
    }

    public BigDecimal getNetAssetTotal() {
        return netAssetTotal;
    }

    public void setNetAssetTotal(BigDecimal v) {
        netAssetTotal = v;
    }

    public Long getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(Long v) {
        tradingVolume = v;
    }

    public Long getTradingValue() {
        return tradingValue;
    }

    public void setTradingValue(Long v) {
        tradingValue = v;
    }

    public String getUnderlyingIndex() {
        return underlyingIndex;
    }

    public void setUnderlyingIndex(String v) {
        underlyingIndex = v;
    }
}