package com.kojikoji.gpt.manage.infrastructure.repository;

import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.domain.prompt.model.vo.PromptStatusVO;
import com.kojikoji.gpt.manage.domain.prompt.repository.IPromptRepository;
import com.kojikoji.gpt.manage.infrastructure.dao.IPromptDao;
import com.kojikoji.gpt.manage.infrastructure.po.PromptPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName PromptRepository
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 10:37
 * @Version
 */
@Repository
public class PromptRepository implements IPromptRepository {

    @Resource
    private IPromptDao promptDao;

    @Override
    public List<PromptEntity> queryPromptsByMMUId(Long mmuId) {
        List<PromptPO> promptPOList = promptDao.queryPromptsByMMUId(mmuId);
        List<PromptEntity> promptEntityList = promptPOList.stream()
                .filter(Objects::nonNull)
                .map(po -> PromptEntity.builder()
                        .promptId(po.getPromptId())
                        .promptName(po.getName())
                        .rate(po.getRate())
                        .status(PromptStatusVO.of(po.getStatus()))
                        .content(po.getContent())
                        .mmuId(po.getMmuId())
                        .createTime(po.getCreateTime())
                        .updateTime(po.getUpdateTime())
                        .build())
                .collect(Collectors.toList());
        return promptEntityList;
    }

    @Override
    public List<Long> queryClosedPrompts(Long mmuId) {
        return promptDao.queryExcludePrompts(mmuId);
    }
}
