package rbprojects.celscompare.service;

import rbprojects.celscompare.domain.CelularInfo;
import rbprojects.celscompare.dto.CelularInfoDTO;
import rbprojects.celscompare.dto.RequisitoCelularDTO;

public interface CelsCompareServiceHelper {

	public abstract CelularInfo findInfo(long id);

	public abstract RequisitoCelularDTO[] findRequisitosByIdCelular(long idCelular);

	public abstract RequisitoCelularDTO createRequisito(String name, Number value);

	public abstract CelularInfoDTO createCelularInfo(long idCelular);

	public abstract CelularInfoDTO createCelularInfo(CelularInfo info);

}
