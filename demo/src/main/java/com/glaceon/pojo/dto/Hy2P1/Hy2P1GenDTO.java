package com.glaceon.pojo.dto.Hy2P1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Hy2P1GenDTO {

    /**
     * Base64 编码图片（不带 data:image/png;base64, 前缀更稳妥）
     */
    private String image;

    @JsonProperty("remove_background")
    private Boolean removeBackground = true;

    /**
     * 是否生成纹理
     */
    private Boolean texture = true;

    private Integer seed = 1234;

    @JsonProperty("octree_resolution")
    private Integer octreeResolution = 256;

    @JsonProperty("num_inference_steps")
    private Integer numInferenceSteps = 5;

    @JsonProperty("guidance_scale")
    private Double guidanceScale = 5.0;

    @JsonProperty("num_chunks")
    private Integer numChunks = 8000;

    @JsonProperty("face_count")
    private Integer faceCount = 40000;
}