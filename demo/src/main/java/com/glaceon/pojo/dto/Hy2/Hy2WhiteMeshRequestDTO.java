package com.glaceon.pojo.dto.Hy2;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Hy2WhiteMeshRequestDTO {

    // 文件类型参数
    private MultipartFile mv_image_front;
    private MultipartFile mv_image_back;
    private MultipartFile mv_image_left;
    private MultipartFile mv_image_right;

    // 普通表单参数，并赋予默认值
    private Integer steps = 5;
    private Float guidance_scale = 5f;
    private Integer seed = 1234;
    private Integer octree_resolution = 256;
    private Boolean check_box_rembg = true;
    private Integer num_chunks = 8000;
    private Boolean randomize_seed = true;
}
