package com.glaceon.Service.provider.impl;

import com.glaceon.FeighClient.HY2P0MeshProcessingClient;
import com.glaceon.Service.provider.HY2MeshService;
import com.glaceon.pojo.dto.Hy2.Hy2MeshReduceRequestDTO;
import com.glaceon.pojo.dto.Hy2.Hy2MeshTextureRequestDTO;
import com.glaceon.pojo.dto.Hy2.Hy2WhiteMeshRequestDTO;
import com.glaceon.util.AliyunOSSOperator;
import com.glaceon.util.ProcessingFeignResponse;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class HY2MeshServiceImpl implements HY2MeshService {
    private final HY2P0MeshProcessingClient hy2P0MeshProcessingClient;
    private final ProcessingFeignResponse processingFeignResponse;

    @Override
    public String generateMvWhiteMesh(Hy2WhiteMeshRequestDTO requestDTO) {
        try (Response response = hy2P0MeshProcessingClient.generateMvWhiteMesh(requestDTO)) {
            return processingFeignResponse.getFilename(response);
        } catch (Exception e) {
            log.error("HY2 白模生成失败", e);
            throw new RuntimeException("HY2 白模生成失败", e);
        }
    }

    @Override
    public String reduceMesh(Hy2MeshReduceRequestDTO requestDTO) {
        try (Response response = hy2P0MeshProcessingClient.reduceMesh(requestDTO)) {
            return processingFeignResponse.getFilename(response);
        } catch (Exception e) {
            log.error("HY2 减面失败", e);
            throw new RuntimeException("HY2 减面失败", e);
        }
    }

    @Override
    public String textureMesh(Hy2MeshTextureRequestDTO requestDTO) {
        try (Response response = hy2P0MeshProcessingClient.textureMesh(requestDTO)) {
            return processingFeignResponse.getFilename(response);
        } catch (Exception e) {
            log.error("HY2 贴图失败", e);
            throw new RuntimeException("HY2 贴图失败", e);
        }
    }

}
