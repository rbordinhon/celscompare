package rbprojects.dto;

public class RequisitoCelularMpVO extends RequisitoCelularVO{

	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}

		final String display = super.getValorDisplay() +" Mp";
		return display;
	}
}
