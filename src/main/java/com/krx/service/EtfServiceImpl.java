package com.krx.service;

import java.util.List;

import com.krx.dao.EtfDAO;
import com.krx.dao.EtfDAOImpl;
import com.krx.dto.EtfDTO;

public class EtfServiceImpl implements EtfService {
    private final EtfDAO dao = new EtfDAOImpl();

    public List<EtfDTO> getList() {
        return dao.selectAll();
    }

    public EtfDTO getOne(long id) {
        return dao.selectById(id);
    }

    public int register(EtfDTO d) {
        return dao.insert(d);
    }

    public int modify(EtfDTO d) {
        return dao.update(d);
    }
}