package rbprojects.dto;

public class RequisitoCelularCameraMpDTO extends RequisitoCelularDTO{

	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}

		final String display = super.getValorDisplay() +" Mp";
		return display;
	}
}
