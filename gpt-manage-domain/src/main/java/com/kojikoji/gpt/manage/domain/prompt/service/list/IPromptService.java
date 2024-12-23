package com.kojikoji.gpt.manage.domain.prompt.service.list;

import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.domain.prompt.model.req.PromptQueryReq;

/**
 * @ClassName IPromptService
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 16:00
 * @Version
 */

public interface IPromptService {
    PromptEntity getPromptByMMUId(PromptQueryReq req);
}
