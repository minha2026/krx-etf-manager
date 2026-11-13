package com.krx.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Map;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krx.dto.EtfDTO;
import com.krx.service.EtfService;
import com.krx.service.EtfServiceImpl;

@WebServlet("/api/etfs/*")
public class EtfApiServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final EtfService service = new EtfServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String path = req.getPathInfo();
            if (path == null || "/".equals(path)) {
                writeJson(resp, HttpServletResponse.SC_OK, service.getEtfList());
                return;
            }
            EtfDTO etf = service.getEtf(parseId(path));
            if (etf == null) {
                writeJson(resp, HttpServletResponse.SC_NOT_FOUND, message("해당 ETF를 찾을 수 없습니다."));
            } else {
                writeJson(resp, HttpServletResponse.SC_OK, etf);
            }
        } catch (Exception e) {
            handleError(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            EtfDTO etf = readEtf(req);
            EtfDTO created = service.createEtf(etf);
            writeJson(resp, HttpServletResponse.SC_CREATED, created);
        } catch (Exception e) {
            handleError(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long id = parseId(req.getPathInfo());
            EtfDTO etf = readEtf(req);
            if (!service.modifyEtf(id, etf)) {
                writeJson(resp, HttpServletResponse.SC_NOT_FOUND, message("수정할 ETF를 찾을 수 없습니다."));
            } else {
                writeJson(resp, HttpServletResponse.SC_OK, message("수정되었습니다."));
            }
        } catch (Exception e) {
            handleError(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long id = parseId(req.getPathInfo());
            if (!service.removeEtf(id)) {
                writeJson(resp, HttpServletResponse.SC_NOT_FOUND, message("삭제할 ETF를 찾을 수 없습니다."));
            } else {
                writeJson(resp, HttpServletResponse.SC_OK, message("삭제되었습니다."));
            }
        } catch (Exception e) {
            handleError(resp, e);
        }
    }

    private EtfDTO readEtf(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Map<?, ?> data = mapper.readValue(req.getInputStream(), Map.class);
        EtfDTO e = new EtfDTO();
        e.setBaseDate(Date.valueOf(required(data, "baseDate")));
        e.setTicker(required(data, "ticker"));
        e.setEtfName(required(data, "etfName"));
        e.setClosingPrice(decimal(data, "closingPrice"));
        e.setPriceChange(decimal(data, "priceChange"));
        e.setChangeRate(decimal(data, "changeRate"));
        e.setEstimatedNav(decimal(data, "estimatedNav"));
        e.setNetAssetTotal(decimal(data, "netAssetTotal"));
        e.setTradingVolume(Long.valueOf(required(data, "tradingVolume")));
        e.setTradingValue(decimal(data, "tradingValue"));
        e.setUnderlyingIndex(string(data.get("underlyingIndex")));
        return e;
    }

    private String required(Map<?, ?> data, String key) {
        Object value = data.get(key);
        if (value == null || String.valueOf(value).trim().isEmpty()) {
            throw new IllegalArgumentException(key + " 값은 필수입니다.");
        }
        return String.valueOf(value).trim();
    }

    private BigDecimal decimal(Map<?, ?> data, String key) {
        return new BigDecimal(required(data, key));
    }

    private String string(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private long parseId(String path) {
        if (path == null || !path.matches("/\\d+")) throw new IllegalArgumentException("잘못된 ETF ID입니다.");
        return Long.parseLong(path.substring(1));
    }

    private Map<String, String> message(String message) {
        return Collections.singletonMap("message", message);
    }

    private void writeJson(HttpServletResponse resp, int status, Object body) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        mapper.writeValue(resp.getWriter(), body);
    }

    private void handleError(HttpServletResponse resp, Exception e) throws IOException {
        int status = (e instanceof IllegalArgumentException) ? HttpServletResponse.SC_BAD_REQUEST
                : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        String msg = e.getMessage() == null ? "서버 처리 중 오류가 발생했습니다." : e.getMessage();
        writeJson(resp, status, message(msg));
        if (status == 500) e.printStackTrace();
    }
}
