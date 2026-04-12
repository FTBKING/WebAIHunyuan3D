package com.glaceon.FeighClient;

import com.glaceon.pojo.MeshReduceRequestDTO;
import com.glaceon.pojo.MeshTextureRequestDTO;
import com.glaceon.pojo.WhiteMeshRequestDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// 绑定 Nacos 中注册的服务名 "demo"
@FeignClient(name = "HunyuanService")
public interface HY2P0MeshProcessingClient {

    /**
     * Generate white mesh from multiview images
     */
    @PostMapping(value = "/api/mv/white-mesh", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response generateMvWhiteMesh(@ModelAttribute WhiteMeshRequestDTO whiteMeshRequestDTO);
    /**
     * Reduce mesh face count
     */
    @PostMapping(value = "/api/mesh/reduce", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response reduceMesh(@ModelAttribute MeshReduceRequestDTO meshReduceRequestDTO);

    /**
     * Generate textured mesh from model and image
     */
    @PostMapping(value = "/api/mesh/texture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response textureMesh(@ModelAttribute MeshTextureRequestDTO textureRequestDTO);
}
