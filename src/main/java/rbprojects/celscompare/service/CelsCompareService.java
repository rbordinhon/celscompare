package rbprojects.celscompare.service;

import rbprojects.celscompare.dto.CelularInfoDTO;
import rbprojects.celscompare.dto.ComparativoCelularDTO;

public interface CelsCompareService {

	public static final String CAMERA_TRASEIRA_RESOLUCAO = "Câmera Traseira Resolução";
	public static final String CAMERA_FRONTAL_RESOLUCAO = "Câmera Frontal Resolução";
	public static final String PRECO = "Preço";
	public static final String MEMORIA_INTERNA = "Memória Interna";
	public static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public abstract CelularInfoDTO[] findAllInfos();

	public abstract ComparativoCelularDTO compareCels(long idCelular1, long idCelular2);

	public abstract void addFavorito(long idCelular);

	public abstract CelularInfoDTO[] findAllInfosOrderByFavorito();
	

}
