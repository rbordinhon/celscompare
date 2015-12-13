package rbprojects.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class RequisitoCelularDTO {
	public String descricao;
	public Number valor;
	
	public Boolean isBetter(RequisitoCelularDTO vo){
		if(valor == null){
			if(vo.valor != null){
				return false;
			}
			return false;
		}
		if(vo.valor != null){
			if(vo.valor.equals(valor)){
				return false;
			}
			return valor.doubleValue() > vo.valor.doubleValue()  ;
		}
		return true;
	}
	
	public String getValorDisplay(){
		if(valor == null){
			return "";
		}
		Locale locale  = new Locale("pt", "BR");
		DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		return decimalFormat.format(valor.doubleValue());
		
	}
	
	@Override
	public String toString() {
		return "RequisitoCelularVO [descricao=" + descricao + ", valor=" + valor +  "]";
	}

}
