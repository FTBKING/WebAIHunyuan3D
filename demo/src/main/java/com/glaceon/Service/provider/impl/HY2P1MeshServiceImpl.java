package com.glaceon.Service.provider.impl;

import com.glaceon.FeighClient.HY2P0MeshProcessingClient;
import com.glaceon.FeighClient.HY2P1MeshProcessingClient;
import com.glaceon.Service.provider.HY2P1MeshService;
import com.glaceon.pojo.dto.Hy2.Hy2WhiteMeshRequestDTO;
import com.glaceon.pojo.dto.Hy2P1.Hy2P1GenDTO;
import com.glaceon.util.ProcessingFeignResponse;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HY2P1MeshServiceImpl implements HY2P1MeshService {
    private final HY2P1MeshProcessingClient hy2P1MeshProcessingClient;
    private final ProcessingFeignResponse processingFeignResponse;
    @Override
    public String GenerateAll(Hy2P1GenDTO requestDTO) {
        try (Response response = hy2P1MeshProcessingClient.generate(requestDTO)) {
            return processingFeignResponse.getFilename(response);
        } catch (Exception e) {
            log.error("HY2P1 生成失败", e);
            throw new RuntimeException("HY2P1 生成失败", e);
        }
    }
}
