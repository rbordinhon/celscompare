package rbprojects.dto;

public class RequisitoCelularPrecoVo extends RequisitoCelularVO{

	public Boolean isBetter(RequisitoCelularVO vo){
		if(valor == null || vo.valor == null || vo.valor.equals(valor)){
			return false;
		}
		return valor.doubleValue() < vo.valor.doubleValue()  ;
	}
}
