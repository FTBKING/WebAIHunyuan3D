package com.glaceon.pojo.dto.Hy3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskStatusResponse {

    /**
     * processing / texturing / completed / error
     */
    private String status;

    @JsonProperty("model_base64")
    private String modelBase64;

    private String message;
}
