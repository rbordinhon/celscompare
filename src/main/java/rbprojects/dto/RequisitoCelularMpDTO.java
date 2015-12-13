package rbprojects.dto;

public class RequisitoCelularMpDTO extends RequisitoCelularDTO{

	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}

		final String display = super.getValorDisplay() +" Mp";
		return display;
	}
}
