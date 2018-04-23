package com.jsen.test.controller.hc.task;

import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.service.HcModelCoreTaskService;
import com.jsen.test.service.SqlCreatorService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/20
 */
@RestController
@CrossOrigin
@RequestMapping("/task/core")
class CoreTaskController {
    @Autowired
    HcModelCoreTaskService hcModelCoreTaskService;

    @Autowired
    DynamicPreCoreMapper dynamicPreCoreMapper;

    @Autowired
    SqlCreatorService sqlCreatorService;


    @GetMapping("/part/exec")
    public ResponseBase execPart(@RequestParam("id") Integer id, @RequestParam("uuid") String uuid) {
        return hcModelCoreTaskService.execPart(id, uuid);
    }
    @GetMapping("/exec")
    public ResponseBase execAll(@RequestParam("id") String id) {
        return null;
    }



    // test
    @GetMapping("/view/create")
    public ResponseBase creteView() {
        return ResponseBase.create().code(0).data(dynamicPreCoreMapper.testView());
    }

    @GetMapping("/view/delete")
    public ResponseBase deleteView() {
        return ResponseBase.create().code(0).data(dynamicPreCoreMapper.testDeleteView());
    }

    @GetMapping("/run/{currentModelID}/{UUID}")
    public ResponseBase genSqls(@PathVariable("currentModelID") Integer currentModelID, @PathVariable("UUID") String UUID) {
        return ResponseBase.create().code(0).data(sqlCreatorService.genSQL(currentModelID, UUID).getData());
    }
}
