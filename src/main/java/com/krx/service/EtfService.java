package com.krx.service;

import java.util.List;

import com.krx.dto.EtfDTO;

public interface EtfService {
    List<EtfDTO> getEtfList();

    EtfDTO getEtf(long id);

    EtfDTO createEtf(EtfDTO etf);

    boolean modifyEtf(long id, EtfDTO etf);

    boolean removeEtf(long id);
}
