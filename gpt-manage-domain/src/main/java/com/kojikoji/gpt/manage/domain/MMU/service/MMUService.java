package com.kojikoji.gpt.manage.domain.MMU.service;

import com.kojikoji.gpt.manage.domain.MMU.model.entity.MMUEntity;
import com.kojikoji.gpt.manage.domain.MMU.repository.IMMURepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MMUService
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:35
 * @Version
 */

@Service
public class MMUService implements IMMUService {

    @Resource
    private IMMURepository mmuRepository;

    @Override
    public List<MMUEntity> listAll() {
        return mmuRepository.listAll();
    }

    @Override
    public MMUEntity getById(Long id) {
        return mmuRepository.getById(id);
    }

}
