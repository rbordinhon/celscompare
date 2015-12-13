package rbprojects.dto;

public class RequisitoCelularMemoriaVO extends RequisitoCelularVO{

	
	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}
		final String display = super.getValorDisplay() +" GB" ;
		return display;
	}
}
