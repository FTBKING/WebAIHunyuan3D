package com.glaceon.pojo.dto.Hy3;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerationRequest {

    @NotBlank
    private String image;

    @JsonProperty("remove_background")
    @Builder.Default
    private boolean removeBackground = true;

    @Builder.Default
    private boolean texture = false;

    @Min(0)
    @Max(4294967295L)
    @Builder.Default
    private long seed = 1234L;

    @JsonProperty("octree_resolution")
    @Min(64)
    @Max(512)
    @Builder.Default
    private int octreeResolution = 256;

    @JsonProperty("num_inference_steps")
    @Min(1)
    @Max(20)
    @Builder.Default
    private int numInferenceSteps = 5;

    @JsonProperty("guidance_scale")
    @DecimalMin("0.1")
    @DecimalMax("20.0")
    @Builder.Default
    private double guidanceScale = 5.0;

    @JsonProperty("num_chunks")
    @Min(1000)
    @Max(20000)
    @Builder.Default
    private int numChunks = 8000;

    @JsonProperty("face_count")
    @Min(1000)
    @Max(100000)
    @Builder.Default
    private int faceCount = 40000;
}
