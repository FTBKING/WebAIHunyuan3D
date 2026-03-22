package com.glaceon.Service.impl;

import com.glaceon.FeighClient.MeshProcessingClient;
import com.glaceon.Service.MeshProcessingService;
import com.glaceon.pojo.WhiteMeshRequest;
import com.glaceon.util.AliyunOSSOperator;
import com.glaceon.util.Model3DApiClientUtil;
import com.glaceon.util.ProcessingFeignResponse;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeshProcServiceImpl1 implements MeshProcessingService {

    private final MeshProcessingClient meshProcessingClient;
    private final ProcessingFeignResponse processingFeignResponse;
    private final AliyunOSSOperator aliyunOSSOperator;

    @Override
    public String generateMvWhiteMesh(WhiteMeshRequest whiteMeshRequest) {
        //解析文件
        // 1. 调用 FeignClient
        try (Response response = meshProcessingClient.generateMvWhiteMesh(whiteMeshRequest)) {

            // 2. 检查响应状态
            if (response.status() == 200 && response.body() != null) {

                // 3. 将输入流转换为 byte[]
                try (InputStream inputStream = response.body().asInputStream()) {
                    //获取字节数组
                    byte[] fileData = StreamUtils.copyToByteArray(inputStream);
                    //获取文件名
                    String filename = processingFeignResponse.getFilename(response);
                    //调用AliyunOSS工具类上传
                    String url = aliyunOSSOperator.upload(fileData, filename);
                }
            } else {
                // 处理错误逻辑，可以根据 response.status() 抛出异常
                log.error("API调用失败，状态码：{}", response.status());
                throw new RuntimeException("API调用失败，状态码：" + response.status());
            }

        } catch (Exception e) {
            log.error("处理3D模型文件失败", e);
            throw new RuntimeException("文件处理异常", e);
        }
        return null;

    }
}
