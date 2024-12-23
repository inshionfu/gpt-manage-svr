package com.kojikoji.gpt.manage.infrastructure.dao;

import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.infrastructure.po.MMUPO;
import com.kojikoji.gpt.manage.infrastructure.po.PromptPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPromptDao {

    List<PromptPO> queryPromptsByMMUId(Long mmuId);

    List<Long> queryExcludePrompts(Long mmuId);
}
