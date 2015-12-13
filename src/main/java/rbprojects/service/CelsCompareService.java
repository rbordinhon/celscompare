package rbprojects.service;

import rbprojects.dto.CelularInfoDTO;
import rbprojects.dto.ComparativoCelularDTO;

public interface CelsCompareService {

	static final String CAMERA_TRASEIRA_RESOLUCAO = "C�mara Traseira Resolu��o";
	static final String CAMERA_FRONTAL_RESOLUCAO = "C�mara Frontal Resolu��o";
	static final String PRECO = "Pre�o";
	static final String MEMORIA_INTERNA = "Mem�ria Interna";
	static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public CelularInfoDTO[] findAllInfos();

	ComparativoCelularDTO compareCels(long idCelular1, long idCelular2);

	void addFavorito(long idCelular);

	CelularInfoDTO[] findAllInfosOrderByFavorito();
	

}
