package rbprojects.dto;

public class RequisitoCelularMemoriaVO extends RequisitoCelularVO{

	public Boolean isBetter(RequisitoCelularVO vo){
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
		final String display = super.getValorDisplay() +" GB" ;
		return display;
	}
}
