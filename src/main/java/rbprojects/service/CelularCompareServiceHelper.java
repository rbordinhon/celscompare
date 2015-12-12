package rbprojects.service;

import rbprojects.domain.CelularInfo;
import rbprojects.dto.CelularInfoVO;
import rbprojects.dto.RequisitoCelularVO;

public interface CelularCompareServiceHelper {

	CelularInfo findInfo(long id);

	RequisitoCelularVO[] findRequisitosByIdCelular(long idCelular);

	RequisitoCelularVO createRequisito(String name, Number value);

	CelularInfoVO createCelularInfo(long idCelular);

	CelularInfoVO createCelularInfo(CelularInfo info);

}
