<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="ko"><meta charset="UTF-8"><body><h2>ETF 등록 / 수정</h2>
<form method="post" action="${pageContext.request.contextPath}/etfs"><input type="hidden" name="id" value="${etf.id}">
    <p>기준일 <input type="date" name="baseDate" value="${etf.baseDate}" required></p>
    <p>종목코드 <input name="ticker" value="${etf.ticker}" required></p>
    <p>ETF명 <input name="etfName" value="${etf.etfName}" required></p>
    <p>종가 <input type="number" name="closingPrice" value="${etf.closingPrice}" required></p>
    <p>대비 <input type="number" name="priceChange" value="${etf.priceChange}" required></p>
    <p>등락률 <input type="number" step="0.01" name="changeRate" value="${etf.changeRate}" required></p>
    <p>추정NAV <input type="number" step="0.01" name="estimatedNav" value="${etf.estimatedNav}" required></p>
    <p>순자산총액(원) <input type="number" step="0.01" name="netAssetTotal" value="${etf.netAssetTotal}" required></p>
    <p>거래량 <input type="number" name="tradingVolume" value="${etf.tradingVolume}" required></p>
    <p>거래대금(원) <input type="number" name="tradingValue" value="${etf.tradingValue}" required></p>
    <p>기초지수명 <input name="underlyingIndex" value="${etf.underlyingIndex}" required></p>
    <button type="submit">저장</button>
</form>
<a href="${pageContext.request.contextPath}/etfs">목록</a></body></html>