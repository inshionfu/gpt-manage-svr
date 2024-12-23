package com.kojikoji.gpt.manage.domain.MMU.repository;

import com.kojikoji.gpt.manage.domain.MMU.model.entity.MMUEntity;

import java.util.List;

public interface IMMURepository {

    List<MMUEntity> listAll();

    MMUEntity getById(Long id);
}
