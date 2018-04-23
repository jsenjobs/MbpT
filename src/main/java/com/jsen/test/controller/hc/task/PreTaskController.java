package com.jsen.test.controller.hc.task;

import com.jsen.test.service.DynamicPreCoreService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/19
 */
@RestController
@CrossOrigin
@RequestMapping("/task/pre")
class PreTaskController {

    @Autowired
    DynamicPreCoreService dynamicPreCoreService;

    // 显示数据源中的表数据
    @GetMapping("/table/list/{tableName}/{page}/{limit}/{columns}")
    public ResponseBase listTableData(@PathVariable("tableName") String tableName, @PathVariable("columns") String columns,
                               @PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        return dynamicPreCoreService.listTableData(tableName, page, limit, columns);
    }

}
