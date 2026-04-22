package com.glaceon.FeighClient;

import com.glaceon.pojo.dto.Hy2P1.Hy2P1GenDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("HY2P1Service")
public interface HY2P1MeshProcessingClient {

    /**
     * 阻塞式调用，直到 Hunyuan3D 生成完成并返回模型文件（GLB/OBJ二进制流）
     */
    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    Response generate(@RequestBody Hy2P1GenDTO request);
}