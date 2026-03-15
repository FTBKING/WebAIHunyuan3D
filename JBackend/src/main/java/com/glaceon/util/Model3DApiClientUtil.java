package com.glaceon.util;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class Model3DApiClientUtil {

    private final RestTemplate restTemplate;
    // 实际项目中建议将 baseUrl 提取到 application.yml 中，通过 @Value 注入
    private final String baseUrl = "http://your-api-domain.com";

    public Model3DApiClientUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 1. 生成带纹理网格接口
     * 上传网格文件与参考图片，生成并导出带纹理的 GLB 模型
     *
     * @param meshFile  上传的网格文件 (支持 obj/glb 等)
     * @param imageFile 上传的参考图片
     * @return 包含生成的带纹理 GLB 文件流的 ResponseEntity
     */
    public ResponseEntity<byte[]> generateTexturedMesh(Resource meshFile, Resource imageFile) {
        String url = baseUrl + "/generate/textured_mesh";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("mesh_file", meshFile);
        body.add("image_file", imageFile);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    }

    /**
     * 2. 白模生成接口 (多视角)
     * 通过前、后、左、右视角的图片生成白模网格
     *
     * @param front            前视角图片
     * @param back             后视角图片
     * @param left             左视角图片
     * @param right            右视角图片
     * @param steps            步数 (可传 null，默认 30)
     * @param guidanceScale    引导比例 (可传 null，默认 5.0)
     * @param seed             随机种子 (可传 null，默认 1234)
     * @param octreeResolution 八叉树分辨率 (可传 null，默认 256)
     * @param removeBg         是否移除背景 (可传 null，默认 true)
     * @param randomSeed       是否随机化种子 (可传 null，默认 false)
     * @return 包含 GLB 文件流及 Header (X-Stats) 的 ResponseEntity
     */
    public ResponseEntity<byte[]> generateWhiteMesh(Resource front, Resource back, Resource left, Resource right,
                                                    Integer steps, Float guidanceScale, Integer seed,
                                                    Integer octreeResolution, Boolean removeBg, Boolean randomSeed) {
        String url = baseUrl + "/generate/whitemesh";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // 只有当图片存在时才加入表单
        if (front != null) body.add("front", front);
        if (back != null) body.add("back", back);
        if (left != null) body.add("left", left);
        if (right != null) body.add("right", right);

        // 设置默认值与参数装载
        body.add("steps", steps != null ? steps : 30);
        body.add("guidance_scale", guidanceScale != null ? guidanceScale : 5.0f);
        body.add("seed", seed != null ? seed : 1234);
        body.add("octree_resolution", octreeResolution != null ? octreeResolution : 256);
        body.add("remove_bg", removeBg != null ? removeBg : true);
        body.add("random_seed", randomSeed != null ? randomSeed : false);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    }

    /**
     * 3. 减面接口
     * 对上传的网格模型进行减面处理
     *
     * @param meshFile        待减面的网格文件
     * @param targetFaceCount 目标面数 (可传 null，默认 10000)
     * @return 包含减面后的 GLB 文件流及 Header (X-Process-Time) 的 ResponseEntity
     */
    public ResponseEntity<byte[]> reduceFaces(Resource meshFile, Integer targetFaceCount) {
        String url = baseUrl + "/process/reduce_faces";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("mesh_file", meshFile);
        body.add("target_face_count", targetFaceCount != null ? targetFaceCount : 10000);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    }
}