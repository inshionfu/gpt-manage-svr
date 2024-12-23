package com.kojikoji.gpt.manage.trigger.http;

import com.kojikoji.gpt.manage.domain.MMU.model.entity.MMUEntity;
import com.kojikoji.gpt.manage.domain.MMU.service.IMMUService;
import com.kojikoji.gpt.manage.types.common.Constants;
import com.kojikoji.gpt.manage.types.model.Response;
import com.kojikoji.gpt.manage.trigger.http.model.entity.MMUMngEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MMUController
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:23
 * @Version
 */
@Slf4j
@RestController
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/gpt/mng/mmu")
public class MMUController {

    @Resource
    private IMMUService mmuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response<List<MMUMngEntity>> list() {
        try {
            List<MMUEntity> mmuEntities = mmuService.listAll();
            List<MMUMngEntity> mmuMngEntities = new ArrayList<>();
            for (MMUEntity mmuEntity : mmuEntities) {
                MMUMngEntity entity = MMUMngEntity.builder()
                        .mmuId(mmuEntity.getMmuId())
                        .mmuName(mmuEntity.getMmuName())
                        .desc(mmuEntity.getDesc())
                        .icon(mmuEntity.getIcon())
                        .build();
                mmuMngEntities.add(entity);
            }

            return Response.<List<MMUMngEntity>>builder()
                    .data(mmuMngEntities)
                    .code(Constants.ResponseCode.SUCCESS.getCode())
                    .info(Constants.ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("查询垂类失败，异常", e);
            return Response.<List<MMUMngEntity>>builder()
                    .code(Constants.ResponseCode.MMU_ERR.getCode())
                    .info(Constants.ResponseCode.MMU_ERR.getInfo())
                    .build();
        }
    }
}
