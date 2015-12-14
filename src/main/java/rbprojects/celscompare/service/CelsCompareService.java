package rbprojects.celscompare.service;

import rbprojects.celscompare.dto.CelularInfoDTO;
import rbprojects.celscompare.dto.ComparativoCelularDTO;

public interface CelsCompareService {

	public static final String CAMERA_TRASEIRA_RESOLUCAO = "C�mera Traseira Resolu��o";
	public static final String CAMERA_FRONTAL_RESOLUCAO = "C�mera Frontal Resolu��o";
	public static final String PRECO = "Pre�o";
	public static final String MEMORIA_INTERNA = "Mem�ria Interna";
	public static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public abstract CelularInfoDTO[] findAllInfos();

	public abstract ComparativoCelularDTO compareCels(long idCelular1, long idCelular2);

	public abstract void addFavorito(long idCelular);

	public abstract CelularInfoDTO[] findAllInfosOrderByFavorito();
	

}
