package com.jsen.test.service;

import com.jsen.test.service.impl.SqlCreatorServiceImpl;
import com.jsen.test.utils.modelcore.model.Node;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public interface SqlCreatorService {

    /**
     *
     * @param currentModelId 数据库中模型id
     * @param uuid 模型的某个部件的UUID 这里是DataSource的ID
     * @return 可执行的SQL select
     */
    SqlCreatorServiceImpl.SimpleResult genSQL(Integer currentModelId, String uuid);
    List<Node> getWorkFlow(Integer currentModelId, String uuid);
}
