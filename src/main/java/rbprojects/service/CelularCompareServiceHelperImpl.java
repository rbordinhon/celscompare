package rbprojects.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import rbprojects.domain.CelularInfo;
import rbprojects.dto.CelularInfoVO;
import rbprojects.dto.RequisitoCelularMemoriaVO;
import rbprojects.dto.RequisitoCelularMpVO;
import rbprojects.dto.RequisitoCelularPrecoVo;
import rbprojects.dto.RequisitoCelularTelaVO;
import rbprojects.dto.RequisitoCelularVO;
import static rbprojects.service.CelularCompareService.*;

@Service
@Transactional
public class CelularCompareServiceHelperImpl implements CelularCompareServiceHelper {
	@PersistenceContext
	public EntityManager manager;

	public CelularCompareServiceHelperImpl() {
	}
	
	@Override
	public CelularInfo findInfo(long id) {
		CelularInfo info = manager.find(CelularInfo.class, id);
		if (info == null) {
			throw new NoResultException();
		}
		return info;
	}

	@Override
	public RequisitoCelularVO[] findRequisitosByIdCelular(long idCelular) {
		CelularInfo info = findInfo(idCelular);
		RequisitoCelularVO[] requisitos = new RequisitoCelularVO[5];
		int reqIt = 0;
		requisitos[reqIt++] = createRequisito(TAMANHO_DA_TELA, info.getNrTelaTamanho());
		requisitos[reqIt++] = createRequisito(MEMORIA_INTERNA, info.getMemoria());
		requisitos[reqIt++] = createRequisito(CAMERA_FRONTAL_RESOLUCAO, info.getResolucacoCameraFrontal());
		requisitos[reqIt++] = createRequisito(CAMERA_TRASEIRA_RESOLUCAO, info.getResolucacoCameraTraseira());
		requisitos[reqIt++] = createRequisito(PRECO, info.getPreco());
		return requisitos;
	}

	@Override
	public CelularInfoVO createCelularInfo(long idCelular) {
		CelularInfo info = findInfo(idCelular);
		return createCelularInfo(info);

	}
	
	@Override
	public CelularInfoVO createCelularInfo(CelularInfo info) {
		CelularInfoVO vo = new CelularInfoVO();
		vo.modelo = info.getModelo();
		vo.idCelular = info.getIdCelularInfo();
		vo.votacoes = info.getFavorito();
		return vo;

	}

	@Override
	public RequisitoCelularVO createRequisito(String name, Number value) {
		final RequisitoCelularVO requ;
		if (name.equalsIgnoreCase(CelularCompareService.PRECO)) {
			requ = new RequisitoCelularPrecoVo();
		} else if(name.equalsIgnoreCase(CelularCompareService.CAMERA_FRONTAL_RESOLUCAO) || name.equalsIgnoreCase(CelularCompareService.CAMERA_TRASEIRA_RESOLUCAO)){
			requ = new RequisitoCelularMpVO();
		} else if(name.equalsIgnoreCase(CelularCompareService.TAMANHO_DA_TELA)){
			requ = new RequisitoCelularTelaVO();
		}else if(name.equalsIgnoreCase(CelularCompareService.MEMORIA_INTERNA)){
			requ = new RequisitoCelularMemoriaVO();
		} else {
			requ = new RequisitoCelularVO();
		}

		requ.descricao = name;
		requ.valor = value;
		return requ;
	}
}