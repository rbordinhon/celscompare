package rbprojects.dto;

public class RequisitoCelularTelaVO extends RequisitoCelularVO{

	@Override
	public String getValorDisplay() {
		if(valor == null){
			return "";
		}
		return "" + super.valor.doubleValue();
	}
}
