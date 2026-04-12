package com.glaceon.Service;

import com.glaceon.pojo.MeshReduceRequestDTO;
import com.glaceon.pojo.MeshTextureRequestDTO;
import com.glaceon.pojo.WhiteMeshRequestDTO;

public interface MeshProcessingService {
    String generateMvWhiteMesh(WhiteMeshRequestDTO whiteMeshRequestDTO);
    String reduceMesh(MeshReduceRequestDTO reduceRequestDTO);
    String textureMesh(MeshTextureRequestDTO textureRequestDTO);
}
