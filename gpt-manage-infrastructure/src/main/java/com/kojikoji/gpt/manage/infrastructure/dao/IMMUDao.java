package com.kojikoji.gpt.manage.infrastructure.dao;

import com.kojikoji.gpt.manage.infrastructure.po.MMUPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName MMUDao
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 12:00
 * @Version
 */

@Mapper
public interface IMMUDao {
    List<MMUPO> queryMMUList();

    MMUPO queryMMUById(Long mmuId);
}
