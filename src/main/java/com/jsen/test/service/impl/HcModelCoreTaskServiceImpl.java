package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.HcModel;
import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.mapper.HcModelMapper;
import com.jsen.test.service.DynamicPreCoreService;
import com.jsen.test.service.HcModelCoreTaskService;
import com.jsen.test.service.SqlCreatorService;
import com.jsen.test.utils.ResponseBase;
import com.jsen.test.utils.modelcore.ModuleColumnFilter;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.help.Finder;
import com.jsen.test.utils.modelcore.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/20
 */
@Service
public class HcModelCoreTaskServiceImpl implements HcModelCoreTaskService {

    public enum MODEL_TYPE {
        DataSource, Calc, Union
    }

    private static final Logger logger = LoggerFactory.getLogger(HcModelCoreTaskServiceImpl.class);

    @Autowired
    HcModelMapper hcModelMapper;
    @Autowired
    DynamicPreCoreService dynamicPreCoreService;

    @Autowired
    DynamicPreCoreMapper dynamicPreCoreMapper;

    @Autowired
    Finder finder;

    @Autowired
    SqlCreatorService sqlCreatorService;


    @Override
    public ResponseBase execPart(Integer id, String uuid) {
        List<Node> workFlow = sqlCreatorService.getWorkFlow(id, uuid);
        if (workFlow == null) {
            return ConstantResponse.FAIL.msg("创建任务树失败");
        }
        if (workFlow.size() == 0) {
            return ConstantResponse.FAIL.msg("没有任务");
        }
        int size = workFlow.size();
        for (int i = 0; i < size; i++) {
            System.out.println(workFlow.get(i).getSql());
            if (i < workFlow.size() - 1) {
                dynamicPreCoreMapper.execSqlReturnView(workFlow.get(i).genViewSQL());
            } else {
                return ConstantResponse.SUCCESS.data(dynamicPreCoreMapper.list(workFlow.get(i).getSql()));
            }
        }
        return ConstantResponse.FAIL.msg("执行失败");
    }

    @Override
    public ResponseBase execAll(Integer id) {
        return null;
    }
}
