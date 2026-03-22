package com.glaceon.util;

import feign.Response;
import org.springframework.http.ContentDisposition;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ProcessingFeignResponse {
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

}
