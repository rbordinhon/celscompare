package rbprojects.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import rbprojects.domain.CelularInfo;
import rbprojects.dto.CelularInfoDTO;
import rbprojects.dto.RequisitoCelularMemoriaDTO;
import rbprojects.dto.RequisitoCelularMpDTO;
import rbprojects.dto.RequisitoCelularPrecoDTO;
import rbprojects.dto.RequisitoCelularTelaDTO;
import rbprojects.dto.RequisitoCelularDTO;
import static rbprojects.service.CelsCompareService.*;

@Service
@Transactional
public class CelsCompareServiceHelperImpl implements CelsCompareServiceHelper {
	@PersistenceContext
	public EntityManager manager;

	public CelsCompareServiceHelperImpl() {
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
	public RequisitoCelularDTO[] findRequisitosByIdCelular(long idCelular) {
		CelularInfo info = findInfo(idCelular);
		RequisitoCelularDTO[] requisitos = new RequisitoCelularDTO[5];
		int reqIt = 0;
		requisitos[reqIt++] = createRequisito(TAMANHO_DA_TELA, info.getNrTelaTamanho());
		requisitos[reqIt++] = createRequisito(MEMORIA_INTERNA, info.getMemoria());
		requisitos[reqIt++] = createRequisito(CAMERA_FRONTAL_RESOLUCAO, info.getResolucacoCameraFrontal());
		requisitos[reqIt++] = createRequisito(CAMERA_TRASEIRA_RESOLUCAO, info.getResolucacoCameraTraseira());
		requisitos[reqIt++] = createRequisito(PRECO, info.getPreco());
		return requisitos;
	}

	@Override
	public CelularInfoDTO createCelularInfo(long idCelular) {
		CelularInfo info = findInfo(idCelular);
		return createCelularInfo(info);

	}
	
	@Override
	public CelularInfoDTO createCelularInfo(CelularInfo info) {
		CelularInfoDTO vo = new CelularInfoDTO();
		vo.modelo = info.getModelo();
		vo.idCelular = info.getIdCelularInfo();
		vo.votacoes = info.getFavorito();
		return vo;

	}

	@Override
	public RequisitoCelularDTO createRequisito(String name, Number value) {
		final RequisitoCelularDTO requ;
		if (name.equalsIgnoreCase(CelsCompareService.PRECO)) {
			requ = new RequisitoCelularPrecoDTO();
		} else if(name.equalsIgnoreCase(CelsCompareService.CAMERA_FRONTAL_RESOLUCAO) || name.equalsIgnoreCase(CelsCompareService.CAMERA_TRASEIRA_RESOLUCAO)){
			requ = new RequisitoCelularMpDTO();
		} else if(name.equalsIgnoreCase(CelsCompareService.TAMANHO_DA_TELA)){
			requ = new RequisitoCelularTelaDTO();
		}else if(name.equalsIgnoreCase(CelsCompareService.MEMORIA_INTERNA)){
			requ = new RequisitoCelularMemoriaDTO();
		} else {
			requ = new RequisitoCelularDTO();
		}

		requ.descricao = name;
		requ.valor = value;
		return requ;
	}
}