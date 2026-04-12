package com.glaceon.pojo;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import javax.validation.constraints.NotNull; // 如果使用了spring-boot-starter-validation

/**
 * 用于接收3D模型减面操作的请求参数
 */
@Data
public class MeshReduceRequestDTO {
    private MeshModelVersion modelVersion = MeshModelVersion.HY2;

    // 原始模型文件 (必填)
    @NotNull(message = "模型文件不能为空")
    private MultipartFile modelFile;

    // 目标面数，默认 40000
    private Integer targetFaceNum = 40000;
}
