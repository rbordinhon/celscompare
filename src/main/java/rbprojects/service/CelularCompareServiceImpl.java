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
import rbprojects.dto.CelularInfoVO;
import rbprojects.dto.ComparativoCelularVO;
import rbprojects.dto.RequisitoCelularVO;
import rbprojects.dto.RequisitoComparativoVo;

@Service
@Transactional()
public class CelularCompareServiceImpl implements CelularCompareService {
	@Autowired
	private CelularCompareServiceHelper helper;

	@PersistenceContext
	public EntityManager manager;

	@Override
	public CelularInfoVO[] findAllInfos() {
		javax.persistence.Query qury = manager.createQuery("select o from CelularInfo o", CelularInfo.class);

		List<CelularInfo> infos = qury.getResultList();
		CelularInfoVO[] returnValue = new CelularInfoVO[infos.size()];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i] = helper.createCelularInfo(infos.get(i));
		}
		return returnValue;

	}

	@Override
	public ComparativoCelularVO compareCels(long idCelular1, long idCelular2) {
		final ComparativoCelularVO comparativo = new ComparativoCelularVO();
		CelularInfoVO info1 = helper.createCelularInfo(idCelular1);
		CelularInfoVO info2 = helper.createCelularInfo(idCelular2);
		comparativo.descricaoCelular1 = info1.modelo;
		comparativo.descricaoCelular2 = info2.modelo;
		RequisitoCelularVO[] requisitosCel1 = helper.findRequisitosByIdCelular(idCelular1);
		final Map<String, RequisitoCelularVO> requMap = new HashMap();
		for (int i = 0; i < requisitosCel1.length; i++) {
			requMap.put(requisitosCel1[i].descricao, requisitosCel1[i]);
		}
		RequisitoCelularVO[] requisitosCel2 = helper.findRequisitosByIdCelular(idCelular2);

		List<RequisitoComparativoVo> requs = new ArrayList();
		for (RequisitoCelularVO requisitoInfo2 : requisitosCel2) {
			final RequisitoComparativoVo vo = new RequisitoComparativoVo();
			vo.descricao = requisitoInfo2.descricao;
			requs.add(vo);
			RequisitoCelularVO requInfo1 = requMap.get(requisitoInfo2.descricao);
			vo.valorCelular1 = requInfo1.valor;
			vo.valorCelular2 = requisitoInfo2.valor;
			vo.celular1emelhor = requInfo1.isBetter(requisitoInfo2);
			vo.celular2emelhor = requisitoInfo2.isBetter(requInfo1);
		}

		comparativo.requisitos = requs.toArray(new RequisitoComparativoVo[requs.size()]);
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
	public CelularInfoVO[] findAllInfosOrderByFavorito() {
		javax.persistence.Query qury = manager.createQuery("select o from CelularInfo o order by o.favorito desc",
				CelularInfo.class);

		List<CelularInfo> infos = qury.getResultList();
		CelularInfoVO[] returnValue = new CelularInfoVO[infos.size()];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i] = helper.createCelularInfo(infos.get(i));
		}
		return returnValue;

	}

}
