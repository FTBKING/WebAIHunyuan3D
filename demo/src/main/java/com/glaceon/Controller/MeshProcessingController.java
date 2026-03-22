package com.glaceon.Controller;

import com.glaceon.Service.MeshProcessingService;
import com.glaceon.pojo.Result;
import com.glaceon.pojo.WhiteMeshRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/meshProcessing")
public class MeshProcessingController {
    @Autowired
    private MeshProcessingService meshProcessingService;

    @PostMapping("/generateMvWhiteMesh")
    public Result generateMvWhiteMesh(@ModelAttribute WhiteMeshRequest whiteMeshRequest) {
        log.info("生成白模接口被调用");
        //调用逻辑层方法，返回url
        String url = meshProcessingService.generateMvWhiteMesh(whiteMeshRequest);
        //返回url
        return Result.success(url);
    }
}
