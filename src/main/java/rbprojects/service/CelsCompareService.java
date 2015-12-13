package rbprojects.service;

import rbprojects.dto.CelularInfoDTO;
import rbprojects.dto.ComparativoCelularDTO;

public interface CelsCompareService {

	static final String CAMERA_TRASEIRA_RESOLUCAO = "Câmera Traseira Resolução";
	static final String CAMERA_FRONTAL_RESOLUCAO = "Câmera Frontal Resolução";
	static final String PRECO = "Preço";
	static final String MEMORIA_INTERNA = "Memória Interna";
	static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public CelularInfoDTO[] findAllInfos();

	ComparativoCelularDTO compareCels(long idCelular1, long idCelular2);

	void addFavorito(long idCelular);

	CelularInfoDTO[] findAllInfosOrderByFavorito();
	

}
