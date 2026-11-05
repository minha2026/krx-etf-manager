<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="ko"><meta charset="UTF-8"><body><h2>ETF 상세정보</h2>
<p>기준일: ${etf.baseDate}</p>
<p>종목코드: ${etf.ticker}</p>
<p>ETF명: ${etf.etfName}</p>
<p>종가: ${etf.closingPrice}</p>
<p>대비: ${etf.priceChange}</p>
<p>등락률: ${etf.changeRate}%</p>
<p>추정NAV: ${etf.estimatedNav}</p>
<p>순자산총액: ${etf.netAssetTotal}</p>
<p>거래량: ${etf.tradingVolume}</p>
<p>거래대금: ${etf.tradingValue}</p>
<p>기초지수명: ${etf.underlyingIndex}</p>
<a href="${pageContext.request.contextPath}/etfs?action=form&id=${etf.id}">수정</a> | <a
        href="${pageContext.request.contextPath}/etfs">목록</a></body></html>