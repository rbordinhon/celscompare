package rbprojects.service;

import rbprojects.dto.CelularInfoVO;
import rbprojects.dto.ComparativoCelularVO;

public interface CelularCompareService {

	static final String CAMERA_TRASEIRA_RESOLUCAO = "C�mara Traseira Resolu��o";
	static final String CAMERA_FRONTAL_RESOLUCAO = "C�mara Frontal Resolu��o";
	static final String PRECO = "Pre�o";
	static final String MEMORIA_INTERNA = "Mem�ria Interna";
	static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public CelularInfoVO[] findAllInfos();

	ComparativoCelularVO compareCels(long idCelular1, long idCelular2);

	void addFavorito(long idCelular);

	CelularInfoVO[] findAllInfosOrderByFavorito();
	

}
