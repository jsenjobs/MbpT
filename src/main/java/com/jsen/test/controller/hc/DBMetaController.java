package com.jsen.test.controller.hc;

import com.jsen.test.service.DBMetaService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/14
 */
@RestController
@CrossOrigin
@RequestMapping("/dbMeta")
public class DBMetaController {
    @Autowired
    private DBMetaService dbMetaService;

    @GetMapping("/columns/{tableName}")
    public ResponseBase listTables(@PathVariable("tableName") String tableName) {
        return dbMetaService.listColumns(tableName);
    }

    @GetMapping("/tables/{dbName}")
    public ResponseBase listDbs(@PathVariable("dbName") String dbName) {
        return dbMetaService.listTables(dbName);
    }
}
