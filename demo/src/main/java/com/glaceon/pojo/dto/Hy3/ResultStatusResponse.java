package com.glaceon.pojo.dto.Hy3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultStatusResponse {

    /**
     * processing / texturing
     * completed 时 /result/{uid} 返回文件流
     */
    private String status;
    private String message;
}
