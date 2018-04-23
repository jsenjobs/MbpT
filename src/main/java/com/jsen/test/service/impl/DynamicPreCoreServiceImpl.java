package com.jsen.test.service.impl;

import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.service.DynamicPreCoreService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/19
 *
 * 请在这里检测sql注入！！！
 */
@Service
public class DynamicPreCoreServiceImpl implements DynamicPreCoreService {

    @Autowired
    DynamicPreCoreMapper dynamicPreCoreMapper;


    @Override
    public ResponseBase listTableData(String tableName, Integer page, Integer limit, String columns) {
        return ResponseBase.create().code(0).data(dynamicPreCoreMapper.list("select " + columns + " from " + tableName + " LIMIT " + page + "," + limit));
    }

    @Override
    public List simpleList(String tableName, Integer page, Integer limit, String columns) {
        return dynamicPreCoreMapper.list("select " + columns + " from " + tableName + " LIMIT " + page + "," + limit);
    }
}
