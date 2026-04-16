package com.glaceon.Controller;

import com.glaceon.Service.provider.HY2MeshService;
import com.glaceon.pojo.dto.Hy2.Hy2MeshReduceRequestDTO;
import com.glaceon.pojo.dto.Hy2.Hy2MeshTextureRequestDTO;
import com.glaceon.pojo.Result;
import com.glaceon.pojo.dto.Hy2.Hy2WhiteMeshRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/HY2.0")
@RequiredArgsConstructor
public class HY2MeshProcessingController {
    //注入HY2MeshService
    private final HY2MeshService hy2MeshService;

    //生成白模API
    @PostMapping("/generateMvWhiteMesh")
    public Result generateMvWhiteMesh(@ModelAttribute Hy2WhiteMeshRequestDTO hy2WhiteMeshRequestDTO) {
        log.info("生成白模接口被调用");
        //调用逻辑层方法，返回url
        String url = hy2MeshService.generateMvWhiteMesh(hy2WhiteMeshRequestDTO);
        //返回url
        return Result.success(url);
    }
    //减面API'
    @PostMapping("/reduceMesh")
    public Result reduceMesh(@ModelAttribute Hy2MeshReduceRequestDTO hy2MeshReduceRequestDTO) {
        log.info("减面接口被调用");
        //调用逻辑层方法，返回url
        String url = hy2MeshService.reduceMesh(hy2MeshReduceRequestDTO);
        //返回url
        return Result.success(url);
    }
    //生成纹理API
    @PostMapping("/generateTexture")
    public Result generateTexture(@ModelAttribute Hy2MeshTextureRequestDTO hy2MeshTextureRequestDTO) {
        log.info("生成纹理接口被调用");
        //调用逻辑层方法，返回url
        String url = hy2MeshService.textureMesh(hy2MeshTextureRequestDTO);
        //返回url
        return Result.success(url);
    }

}
