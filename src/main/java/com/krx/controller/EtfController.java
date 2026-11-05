package com.krx.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.krx.dto.EtfDTO;
import com.krx.service.EtfService;
import com.krx.service.EtfServiceImpl;

@WebServlet("/etfs")
public class EtfController extends HttpServlet {
    private EtfService service;

    public void init() {
        service = new EtfServiceImpl();
    }

    protected void doGet(HttpServletRequest q, HttpServletResponse p) throws ServletException, IOException {
        q.setCharacterEncoding("UTF-8");
        String a = q.getParameter("action");
        if ("detail".equals(a)) {
            q.setAttribute("etf", service.getOne(Long.parseLong(q.getParameter("id"))));
            q.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(q, p);
        } else if ("form".equals(a)) {
            String id = q.getParameter("id");
            if (id != null) q.setAttribute("etf", service.getOne(Long.parseLong(id)));
            q.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(q, p);
        } else {
            q.setAttribute("list", service.getList());
            q.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(q, p);
        }
    }

    protected void doPost(HttpServletRequest q, HttpServletResponse p) throws ServletException, IOException {
        q.setCharacterEncoding("UTF-8");
        EtfDTO d = new EtfDTO();
        String id = q.getParameter("id");
        d.setBaseDate(Date.valueOf(q.getParameter("baseDate")));
        d.setTicker(q.getParameter("ticker"));
        d.setEtfName(q.getParameter("etfName"));
        d.setClosingPrice(Long.valueOf(q.getParameter("closingPrice")));
        d.setPriceChange(Long.valueOf(q.getParameter("priceChange")));
        d.setChangeRate(new BigDecimal(q.getParameter("changeRate")));
        d.setEstimatedNav(new BigDecimal(q.getParameter("estimatedNav")));
        d.setNetAssetTotal(new BigDecimal(q.getParameter("netAssetTotal")));
        d.setTradingVolume(Long.valueOf(q.getParameter("tradingVolume")));
        d.setTradingValue(Long.valueOf(q.getParameter("tradingValue")));
        d.setUnderlyingIndex(q.getParameter("underlyingIndex"));
        if (id == null || id.trim().isEmpty()) service.register(d);
        else {
            d.setId(Long.valueOf(id));
            service.modify(d);
        }
        p.sendRedirect(q.getContextPath() + "/etfs");
    }
}