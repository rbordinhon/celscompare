package rbprojects.service;

import rbprojects.dto.CelularInfoVO;
import rbprojects.dto.ComparativoCelularVO;
import rbprojects.dto.RequisitoCelularVO;

public interface CelularCompareService {

	static final String CAMERA_TRASEIRA_RESOLUCAO = "Camera Traseira Resolucao";
	static final String CAMERA_FRONTAL_RESOLUCAO = "Camera Frontal Resolucao";
	static final String PRECO = "Preco";
	static final String MEMORIA_INTERNA = "Memoria Interna";
	static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public CelularInfoVO[] findAllInfos();

	ComparativoCelularVO compareCels(long idCelular1, long idCelular2);

	void addFavorito(long idCelular);

	CelularInfoVO[] findAllInfosOrderByFavorito();
	

}
