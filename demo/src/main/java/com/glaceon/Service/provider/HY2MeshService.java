package com.glaceon.Service.provider;

import com.glaceon.pojo.dto.Hy2.Hy2MeshReduceRequestDTO;
import com.glaceon.pojo.dto.Hy2.Hy2MeshTextureRequestDTO;
import com.glaceon.pojo.dto.Hy2.Hy2WhiteMeshRequestDTO;

public interface HY2MeshService {

    String generateMvWhiteMesh(Hy2WhiteMeshRequestDTO requestDTO);

    String reduceMesh(Hy2MeshReduceRequestDTO requestDTO);

    String textureMesh(Hy2MeshTextureRequestDTO requestDTO);
}
