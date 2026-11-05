<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html lang="ko"><head><meta charset="UTF-8"><title>ETF 관리</title><style>body{font-family:Arial;margin:35px}table{border-collapse:collapse;width:100%}th,td{border:1px solid #ddd;padding:8px;text-align:center}th{background:#eee}</style></head><body>
<h2>KRX ETF 정보 관리</h2>
<p><a href="${pageContext.request.contextPath}/etfs?action=form">ETF 등록</a></p>
<table>
    <tr>
        <th>기준일</th>
        <th>종목코드</th>
        <th>ETF명</th>
        <th>종가</th>
        <th>대비</th>
        <th>등락률</th>
        <th>추정NAV</th>
        <th>순자산총액</th>
        <th>상세</th>
    </tr>
    <c:forEach var="e" items="${list}">
        <tr>
            <td>${e.baseDate}</td>
            <td>${e.ticker}</td>
            <td>${e.etfName}</td>
            <td>${e.closingPrice}</td>
            <td>${e.priceChange}</td>
            <td>${e.changeRate}%</td>
            <td>${e.estimatedNav}</td>
            <td>${e.netAssetTotal}</td>
            <td><a href="${pageContext.request.contextPath}/etfs?action=detail&id=${e.id}">보기</a></td>
        </tr>
    </c:forEach></table>
</body></html>