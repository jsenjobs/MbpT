package com.jsen.test.service.impl;

import com.google.common.collect.Lists;
import com.jsen.test.entity.DBMetaTable;
import com.jsen.test.mapper.DBMetaMapper;
import com.jsen.test.service.DBMetaService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/14
 */
@Service
public class DBMetaServiceImpl implements DBMetaService {
    @Autowired
    DBMetaMapper dbMetaMapper;

    @Override
    public ResponseBase listColumns(String tableName) {
        return ResponseBase.create().code(0).data(dbMetaMapper.listColumnForTable(tableName));
    }

    @Override
    public ResponseBase listTables(String dbName) {
        List<DBMetaTable> dbMetaTables = Lists.newArrayList();
        List l = dbMetaMapper.listTablesForDB(dbName);
        l.forEach(item -> {
            Map m = (Map)item;
            m.forEach((mk, mv) -> {
                dbMetaTables.add(new DBMetaTable((String) mv));
            });
        });
        return ResponseBase.create().code(0).data(dbMetaTables);
    }
}
