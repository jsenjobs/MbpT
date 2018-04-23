package com.jsen.test.service;

import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/14
 */
public interface DBMetaService {
    ResponseBase listColumns(String tableName);
    ResponseBase listTables(String dbName);
}
