package com.glaceon.FeighClient;

import com.glaceon.pojo.WhiteMeshRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

// 绑定 Nacos 中注册的服务名 "demo"
@FeignClient(name = "demo")
public interface MeshProcessingClient {

    /**
     * Generate white mesh from multiview images
     */
    @PostMapping(value = "/api/mv/white-mesh", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response generateMvWhiteMesh(@ModelAttribute WhiteMeshRequest whiteMeshRequest);
    /**
     * Reduce mesh face count
     */
    @PostMapping(value = "/api/mesh/reduce", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response reduceMesh(
            @RequestPart("model_file") MultipartFile modelFile,
            @RequestParam(value = "target_face_num", required = false, defaultValue = "40000") Integer targetFaceNum
    );

    /**
     * Generate textured mesh from model and image
     */
    @PostMapping(value = "/api/mesh/texture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response textureMesh(
            @RequestPart("model_file") MultipartFile modelFile,
            @RequestPart("image_file") MultipartFile imageFile,
            @RequestParam(value = "check_box_rembg", required = false, defaultValue = "false") Boolean checkBoxRembg
    );
}
