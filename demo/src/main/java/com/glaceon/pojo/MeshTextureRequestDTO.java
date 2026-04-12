package com.glaceon.pojo;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 用于接收3D模型贴图操作的请求参数
 */
@Data
public class MeshTextureRequestDTO {
    private MeshModelVersion modelVersion = MeshModelVersion.HY2;

    // 白模文件 (必填)
    @NotNull(message = "模型文件不能为空")
    private MultipartFile modelFile;

    // 贴图参考图片 (必填)
    @NotNull(message = "图片文件不能为空")
    private MultipartFile imageFile;

    // 是否移除图片背景，默认 false
    private Boolean checkBoxRembg = false;
}
