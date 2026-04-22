package com.glaceon.Controller;


import com.glaceon.Service.provider.HY2P1MeshService;
import com.glaceon.pojo.Result;
import com.glaceon.pojo.dto.Hy2P1.Hy2P1GenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/HY2.1")
@RequiredArgsConstructor
public class HY2P1MeshProcessingController {
    private final HY2P1MeshService hy2P1MeshService;
    //一键生成接口
    @PostMapping("/GenerateTexturedMesh")
    public Result GenerateTexturedMesh(@RequestBody Hy2P1GenDTO requestDTO) {
        log.info("HY2.1接口被调用");
        String url = hy2P1MeshService.GenerateAll(requestDTO);
        return Result.success(url);
    }
}
