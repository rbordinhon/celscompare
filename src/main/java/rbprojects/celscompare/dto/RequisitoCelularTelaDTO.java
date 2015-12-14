package rbprojects.celscompare.dto;

public class RequisitoCelularTelaDTO extends RequisitoCelularDTO{

	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}
		return "" + super.valor.doubleValue();
	}
}
