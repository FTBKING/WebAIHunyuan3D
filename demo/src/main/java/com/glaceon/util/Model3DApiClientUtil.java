package com.glaceon.util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Model3DApiClientUtil {

    private final RestTemplate restTemplate;

    /**
     * 1. 生成带纹理网格接口
     * 上传网格文件与参考图片，生成并导出带纹理的 GLB 模型
     *
     * @param meshFile  上传的网格文件 (支持 obj/glb 等)
     * @param imageFile 上传的参考图片
     * @return 包含生成的带纹理 GLB 文件流的 ResponseEntity
     */
    public ResponseEntity<byte[]> generateTexturedMesh(MultipartFile meshFile, MultipartFile imageFile) throws IOException {
        //转换格式
        Resource meshResource = convertMultipartFileToResource(meshFile);
        Resource imageResource = convertMultipartFileToResource(imageFile);
        String url = "/generate/textured_mesh";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("mesh_file", meshResource);
        body.add("image_file", imageResource);

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
    public ResponseEntity<byte[]> generateWhiteMesh(MultipartFile front, MultipartFile back, MultipartFile left, MultipartFile right,
                                                    Integer steps, Float guidanceScale, Integer seed,
                                                    Integer octreeResolution, Boolean removeBg, Boolean randomSeed) throws IOException {
        //转换格式
        Resource frontResource = convertMultipartFileToResource(front);
        Resource backResource = convertMultipartFileToResource(back);
        Resource leftResource = convertMultipartFileToResource(left);
        Resource rightResource = convertMultipartFileToResource(right);

        String url = "/generate/whitemesh";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // 只有当图片存在时才加入表单
        if (front != null) body.add("front", frontResource);
        if (back != null) body.add("back", backResource);
        if (left != null) body.add("left", leftResource);
        if (right != null) body.add("right", rightResource);

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
    public ResponseEntity<byte[]> reduceFaces(MultipartFile meshFile, Integer targetFaceCount) throws IOException {

        //转换格式
        Resource meshResource = convertMultipartFileToResource(meshFile);

        String url ="/process/reduce_faces";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("mesh_file", meshResource);
        body.add("target_face_count", targetFaceCount != null ? targetFaceCount : 10000);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    }
    private Resource convertMultipartFileToResource(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // 必须重写 getFilename，这是让 RestTemplate 正确构建 multipart/form-data 的关键所在
        return new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
    }
}