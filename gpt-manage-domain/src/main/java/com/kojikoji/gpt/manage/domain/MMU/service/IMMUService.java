package com.kojikoji.gpt.manage.domain.MMU.service;

import com.kojikoji.gpt.manage.domain.MMU.model.entity.MMUEntity;

import java.util.List;

public interface IMMUService {
    List<MMUEntity> listAll();

    MMUEntity getById(Long id);
}
