package com.kojikoji.gpt.manage.domain.prompt.repository;

import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;

import java.util.List;

public interface IPromptRepository {
    List<PromptEntity> queryPromptsByMMUId(Long mmuId);

    List<Long> queryClosedPrompts(Long mmuId);
}
