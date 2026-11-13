package com.krx.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class EtfDTO {
    private Long id;
    private Date baseDate;
    private String ticker;
    private String etfName;
    private BigDecimal closingPrice;
    private BigDecimal priceChange;
    private BigDecimal changeRate;
    private BigDecimal estimatedNav;
    private BigDecimal netAssetTotal;
    private Long tradingVolume;
    private BigDecimal tradingValue;
    private String underlyingIndex;
    private Timestamp createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getEtfName() {
        return etfName;
    }

    public void setEtfName(String etfName) {
        this.etfName = etfName;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(BigDecimal changeRate) {
        this.changeRate = changeRate;
    }

    public BigDecimal getEstimatedNav() {
        return estimatedNav;
    }

    public void setEstimatedNav(BigDecimal estimatedNav) {
        this.estimatedNav = estimatedNav;
    }

    public BigDecimal getNetAssetTotal() {
        return netAssetTotal;
    }

    public void setNetAssetTotal(BigDecimal netAssetTotal) {
        this.netAssetTotal = netAssetTotal;
    }

    public Long getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(Long tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public BigDecimal getTradingValue() {
        return tradingValue;
    }

    public void setTradingValue(BigDecimal tradingValue) {
        this.tradingValue = tradingValue;
    }

    public String getUnderlyingIndex() {
        return underlyingIndex;
    }

    public void setUnderlyingIndex(String underlyingIndex) {
        this.underlyingIndex = underlyingIndex;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
