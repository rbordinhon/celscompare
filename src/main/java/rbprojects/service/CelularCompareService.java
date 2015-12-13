package rbprojects.service;

import rbprojects.dto.CelularInfoVO;
import rbprojects.dto.ComparativoCelularVO;

public interface CelularCompareService {

	static final String CAMERA_TRASEIRA_RESOLUCAO = "Câmara Traseira Resolução";
	static final String CAMERA_FRONTAL_RESOLUCAO = "Câmara Frontal Resolução";
	static final String PRECO = "Preço";
	static final String MEMORIA_INTERNA = "Memória Interna";
	static final String TAMANHO_DA_TELA = "Tamanho da Tela";
	
	public CelularInfoVO[] findAllInfos();

	ComparativoCelularVO compareCels(long idCelular1, long idCelular2);

	void addFavorito(long idCelular);

	CelularInfoVO[] findAllInfosOrderByFavorito();
	

}
