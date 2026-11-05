package com.krx.service;

import java.util.List;

import com.krx.dto.EtfDTO;

public interface EtfService {
    List<EtfDTO> getList();

    EtfDTO getOne(long id);

    int register(EtfDTO dto);

    int modify(EtfDTO dto);
}