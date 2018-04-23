package com.jsen.test.controller.hc;

import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.service.HcModelShareService;
import com.jsen.test.service.TokenService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@RestController
@CrossOrigin
@RequestMapping("/hcModel/share")
public class HcModelShareController {
    @Autowired
    private HcModelShareService hcModelShareService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/model/save/{modelId}/{modelName}/{intro}")
    public ResponseBase save(HttpServletRequest request, @PathVariable("modelId") Integer modelId, @PathVariable("modelName") String modelName, @PathVariable("intro") String intro) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelShareService.save(modelId, id, modelName, intro);
    }

    @GetMapping("/model/update/{shareModelName}/{modelId}")
    public ResponseBase updateModel(HttpServletRequest request, @PathVariable("shareModelName") String shareModelName, @PathVariable("modelId") Integer modelId) {
        int id = tokenService.getUserId(request.getHeader("Authorization"));
        return hcModelShareService.updateModelBy(shareModelName, id, modelId);
    }


    @GetMapping("/model/list")
    public ResponseBase listAll() {
        return hcModelShareService.listAll();
    }

    @GetMapping("/model/delete/{shareModelId}")
    public ResponseBase deleteModel(@PathVariable("shareModelId") Integer shareModelId) {
        return hcModelShareService.deleteShareModelById(shareModelId);
    }

}
