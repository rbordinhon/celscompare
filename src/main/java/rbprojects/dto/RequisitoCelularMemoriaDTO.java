package rbprojects.dto;

public class RequisitoCelularMemoriaDTO extends RequisitoCelularDTO{

	
	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}
		final String display = super.getValorDisplay() +" GB" ;
		return display;
	}
}
