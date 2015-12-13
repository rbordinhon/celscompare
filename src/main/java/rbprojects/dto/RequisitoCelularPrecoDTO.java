package rbprojects.dto;

public class RequisitoCelularPrecoDTO extends RequisitoCelularDTO{

	public Boolean isBetter(RequisitoCelularDTO vo){
		if(valor == null || vo.valor == null || vo.valor.equals(valor)){
			return false;
		}
		return valor.doubleValue() < vo.valor.doubleValue()  ;
	}
	
	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}
		final String display = "R$ "+ super.getValorDisplay() ;
		return display;
	}
}
