package rbprojects.service;

import rbprojects.domain.CelularInfo;
import rbprojects.dto.CelularInfoDTO;
import rbprojects.dto.RequisitoCelularDTO;

public interface CelsCompareServiceHelper {

	CelularInfo findInfo(long id);

	RequisitoCelularDTO[] findRequisitosByIdCelular(long idCelular);

	RequisitoCelularDTO createRequisito(String name, Number value);

	CelularInfoDTO createCelularInfo(long idCelular);

	CelularInfoDTO createCelularInfo(CelularInfo info);

}
