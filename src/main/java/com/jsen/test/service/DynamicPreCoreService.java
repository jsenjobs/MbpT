package com.jsen.test.service;

import com.jsen.test.utils.ResponseBase;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/19
 * 注意数据源的切换
 */
public interface DynamicPreCoreService {
    ResponseBase listTableData(String tableName, Integer page, Integer limit, String columns);

    List simpleList(String tableName, Integer page, Integer limit, String columns);
}
