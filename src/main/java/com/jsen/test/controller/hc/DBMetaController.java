package com.jsen.test.controller.hc;

import com.jsen.test.service.ScsWorkService;
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
    private ScsWorkService scsWorkService;

    @GetMapping("/columns/{tableName}")
    public ResponseBase listTables(@PathVariable("tableName") String tableName) {
        return scsWorkService.listColumns(tableName);
    }

    @GetMapping("/tables/{dbName}")
    public ResponseBase listDbs(@PathVariable("dbName") String dbName) {
        return scsWorkService.listTables(dbName);
    }
}
