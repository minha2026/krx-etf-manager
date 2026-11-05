package com.krx.dao;

import java.util.List;

import com.krx.dto.EtfDTO;

public interface EtfDAO {
    List<EtfDTO> selectAll();

    EtfDTO selectById(long id);

    int insert(EtfDTO dto);

    int update(EtfDTO dto);
}