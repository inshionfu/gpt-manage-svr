package com.kojikoji.gpt.manage.trigger.http;

import com.kojikoji.gpt.manage.domain.MMU.model.entity.MMUEntity;
import com.kojikoji.gpt.manage.domain.MMU.service.IMMUService;
import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.domain.prompt.model.req.PromptQueryReq;
import com.kojikoji.gpt.manage.domain.prompt.service.list.IPromptService;
import com.kojikoji.gpt.manage.trigger.http.model.dto.PromptRequestDTO;
import com.kojikoji.gpt.manage.trigger.http.model.entity.MMUMngEntity;
import com.kojikoji.gpt.manage.trigger.http.model.entity.PromptUserEntity;
import com.kojikoji.gpt.manage.types.common.Constants;
import com.kojikoji.gpt.manage.types.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName PromptController
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:18
 * @Version
 */

@Slf4j
@RestController
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/gpt/mng/prompt/")
public class PromptController {

    @Resource
    private IPromptService promptService;

    @Resource
    private IMMUService mmuService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Response<PromptUserEntity> getByMMUId(@RequestBody PromptRequestDTO req) {
        // 0.基础信息
        Long mmuId = req.getMmuId();
        // 1.查询对应垂类
        MMUEntity mmu = mmuService.getById(mmuId);
        if (Objects.isNull(mmu)) {
            return Response.<PromptUserEntity>builder()
                    .code(Constants.ResponseCode.MMU_ERR.getCode())
                    .info(Constants.ResponseCode.MMU_ERR.getInfo())
                    .build();
        }
        // 2.通过垂类信息随机选择prompt
        PromptQueryReq promptQueryReq = PromptQueryReq.builder()
                .mmuId(mmuId)
                .strategyMode(Constants.StrategyMode.of(mmu.getDrawStrategy().getCode()))
                .build();
        PromptEntity promptEntity = promptService.getPromptByMMUId(promptQueryReq);
        if (Objects.isNull(promptEntity)) {
            return Response.<PromptUserEntity>builder()
                    .code(Constants.ResponseCode.PROMPT_ERR.getCode())
                    .info(Constants.ResponseCode.PROMPT_ERR.getInfo())
                    .build();
        }
        return Response.<PromptUserEntity>builder()
                .code(Constants.ResponseCode.SUCCESS.getCode())
                .info(Constants.ResponseCode.SUCCESS.getInfo())
                .data(PromptUserEntity.builder()
                        .name(promptEntity.getPromptName())
                        .content(promptEntity.getContent())
                        .build())
                .build();

    }
}
