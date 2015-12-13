package rbprojects.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rbprojects.domain.CelularInfo;
import rbprojects.dto.CelularInfoDTO;
import rbprojects.dto.ComparativoCelularDTO;
import rbprojects.dto.RequisitoCelularDTO;
import rbprojects.dto.RequisitoComparativoDTO;

@Service
@Transactional()
public class CelsCompareServiceImpl implements CelsCompareService {
	@Autowired
	private CelsCompareServiceHelper helper;

	@PersistenceContext
	public EntityManager manager;

	@Override
	public CelularInfoDTO[] findAllInfos() {
		javax.persistence.Query qury = manager.createQuery("select o from CelularInfo o", CelularInfo.class);

		List<CelularInfo> infos = qury.getResultList();
		CelularInfoDTO[] returnValue = new CelularInfoDTO[infos.size()];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i] = helper.createCelularInfo(infos.get(i));
		}
		return returnValue;

	}

	@Override
	public ComparativoCelularDTO compareCels(long idCelular1, long idCelular2) {
		final ComparativoCelularDTO comparativo = new ComparativoCelularDTO();
		CelularInfoDTO info1 = helper.createCelularInfo(idCelular1);
		CelularInfoDTO info2 = helper.createCelularInfo(idCelular2);
		comparativo.descricaoCelular1 = info1.modelo;
		comparativo.descricaoCelular2 = info2.modelo;
		RequisitoCelularDTO[] requisitosCel1 = helper.findRequisitosByIdCelular(idCelular1);
		final Map<String, RequisitoCelularDTO> requMap = new HashMap();
		for (int i = 0; i < requisitosCel1.length; i++) {
			requMap.put(requisitosCel1[i].descricao, requisitosCel1[i]);
		}
		RequisitoCelularDTO[] requisitosCel2 = helper.findRequisitosByIdCelular(idCelular2);

		List<RequisitoComparativoDTO> requs = new ArrayList();
		for (RequisitoCelularDTO requisitoInfo2 : requisitosCel2) {
			final RequisitoComparativoDTO vo = new RequisitoComparativoDTO();
			vo.descricao = requisitoInfo2.descricao;
			requs.add(vo);
			RequisitoCelularDTO requInfo1 = requMap.get(requisitoInfo2.descricao);
			vo.valorCelular1 = requInfo1.getValorDisplay();
			vo.valorCelular2 = requisitoInfo2.getValorDisplay();
			vo.celular1emelhor = requInfo1.isBetter(requisitoInfo2);
			vo.celular2emelhor = requisitoInfo2.isBetter(requInfo1);
		}

		comparativo.requisitos = requs.toArray(new RequisitoComparativoDTO[requs.size()]);
		return comparativo;

	}

	@Override
	public void addFavorito(long idCelular) {
		CelularInfo info = helper.findInfo(idCelular);
		info.setFavorito(info.getFavorito() + 1);
		manager.merge(info);
		manager.flush();
	}

	@Override
	public CelularInfoDTO[] findAllInfosOrderByFavorito() {
		javax.persistence.Query qury = manager.createQuery("select o from CelularInfo o order by o.favorito desc",
				CelularInfo.class);

		List<CelularInfo> infos = qury.getResultList();
		CelularInfoDTO[] returnValue = new CelularInfoDTO[infos.size()];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i] = helper.createCelularInfo(infos.get(i));
		}
		return returnValue;

	}

}
