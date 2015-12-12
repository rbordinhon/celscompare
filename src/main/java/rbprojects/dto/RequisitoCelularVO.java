package rbprojects.dto;

public class RequisitoCelularVO {
	public String descricao;
	public Number valor;
	
	public Boolean isBetter(RequisitoCelularVO vo){
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
	
	@Override
	public String toString() {
		return "RequisitoCelularVO [descricao=" + descricao + ", valor=" + valor +  "]";
	}

}
