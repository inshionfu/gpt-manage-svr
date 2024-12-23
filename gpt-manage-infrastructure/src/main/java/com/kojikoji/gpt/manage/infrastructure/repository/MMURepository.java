package com.kojikoji.gpt.manage.infrastructure.repository;

import com.alibaba.fastjson.JSON;
import com.kojikoji.gpt.manage.domain.MMU.model.VO.DrawStrategyVO;
import com.kojikoji.gpt.manage.domain.MMU.model.VO.MMUStatusVO;
import com.kojikoji.gpt.manage.domain.MMU.model.entity.MMUEntity;
import com.kojikoji.gpt.manage.domain.MMU.repository.IMMURepository;
import com.kojikoji.gpt.manage.infrastructure.dao.IMMUDao;
import com.kojikoji.gpt.manage.infrastructure.po.MMUPO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName MMURepository
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:59
 * @Version
 */

@Slf4j
@Repository
public class MMURepository implements IMMURepository {

    @Resource
    private IMMUDao mmuDao;

    @Override
    public List<MMUEntity> listAll() {
        List<MMUPO> mmus = mmuDao.queryMMUList();
        List<MMUEntity> mmuEntities = new ArrayList<>();
        for (MMUPO mmu : mmus) {
            MMUEntity build = MMUEntity.builder()
                    .mmuId(mmu.getMmuId())
                    .desc(mmu.getDesc())
                    .icon(mmu.getIcon())
                    .mmuName(mmu.getMmuName())
                    .totalFlow(mmu.getTotalFlow())
                    .status(MMUStatusVO.of(mmu.getStatus()))
                    .drawStrategy(DrawStrategyVO.of(mmu.getDrawStrategy()))
                    .build();
            mmuEntities.add(build);
        }
        return mmuEntities;
    }

    @Override
    public MMUEntity getById(Long id) {
        MMUPO po = mmuDao.queryMMUById(id);
        log.info("mmuId: {} getById:{}", id, JSON.toJSONString(po));
        if (Objects.isNull(po)) {
            return null;
        }
        return MMUEntity.builder()
                .mmuId(po.getMmuId())
                .mmuName(po.getMmuName())
                .totalFlow(po.getTotalFlow())
                .status(MMUStatusVO.of(po.getStatus()))
                .desc(po.getDesc())
                .icon(po.getIcon())
                .drawStrategy(DrawStrategyVO.of(po.getDrawStrategy()))
                .build();
    }
}
