package com.glaceon.util;

import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProcessingFeignResponse {

    private final AliyunOSSOperator aliyunOSSOperator;

    public String getFilename(Response response) {
        // 假设 response 是 feign.Response
        Collection<String> headerValues = response.headers().get("Content-Disposition");

        if (headerValues != null && !headerValues.isEmpty()) {
            // 1. 获取 Header 字符串
            String dispositionStr = headerValues.iterator().next();

            // 2. 使用 Spring 的解析工具
            ContentDisposition cd = ContentDisposition.parse(dispositionStr);

            // 3. 直接获取文件名
            return cd.getFilename();
        }
        return null;
    }
    private String uploadFromResponse(Response response) throws Exception {
        if (response.status() != 200 || response.body() == null) {
            throw new RuntimeException("HY2 API调用失败，状态码：" + response.status());
        }
        try (InputStream inputStream = response.body().asInputStream()) {
            byte[] fileData = StreamUtils.copyToByteArray(inputStream);
            String filename = getFilename(response);
            if (filename == null || filename.trim().isEmpty()) {
                filename = UUID.randomUUID() + ".glb";
            }
            return aliyunOSSOperator.upload(fileData, filename);
        }
    }

}
