package com.glaceon.Service.impl;

import com.glaceon.Service.MeshProcessingService;
import com.glaceon.Service.provider.MeshProviderFactory;
import com.glaceon.Service.provider.MeshProviderPort;
import com.glaceon.pojo.MeshModelVersion;
import com.glaceon.pojo.MeshReduceRequestDTO;
import com.glaceon.pojo.MeshTextureRequestDTO;
import com.glaceon.pojo.WhiteMeshRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HY2P0MeshProcService implements MeshProcessingService {
    private final MeshProviderFactory meshProviderFactory;

    @Override
    public String generateMvWhiteMesh(WhiteMeshRequestDTO whiteMeshRequestDTO) {
        MeshProviderPort provider = selectProvider(whiteMeshRequestDTO.getModelVersion());
        return provider.generateMvWhiteMesh(whiteMeshRequestDTO);
    }

    @Override
    public String reduceMesh(MeshReduceRequestDTO reduceRequestDTO) {
        MeshProviderPort provider = selectProvider(reduceRequestDTO.getModelVersion());
        return provider.reduceMesh(reduceRequestDTO);
    }

    @Override
    public String textureMesh(MeshTextureRequestDTO textureRequestDTO) {
        MeshProviderPort provider = selectProvider(textureRequestDTO.getModelVersion());
        return provider.textureMesh(textureRequestDTO);
    }

    private MeshProviderPort selectProvider(MeshModelVersion version) {
        MeshModelVersion actualVersion = version == null ? MeshModelVersion.HY2 : version;
        return meshProviderFactory.getProvider(actualVersion);
    }
}
