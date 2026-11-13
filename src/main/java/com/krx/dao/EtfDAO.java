package com.krx.dao;

import java.util.List;

import com.krx.dto.EtfDTO;

public interface EtfDAO {
    List<EtfDTO> selectEtfList();

    EtfDTO selectEtfById(long id);

    int insertEtf(EtfDTO etf);

    int updateEtf(EtfDTO etf);

    int deleteEtf(long id);
}
