package rbprojects.dto;

import java.util.Arrays;

public class ComparativoCelularVO {
	public String descricaoCelular1;
	public String descricaoCelular2;
	public RequisitoComparativoVo[] requisitos;
	@Override
	public String toString() {
		return "ComparativoCelularVO [descricaoCelular1=" + descricaoCelular1 + ", descricaoCelular2="
				+ descricaoCelular2 + ", requisitos=" + Arrays.toString(requisitos) + "]";
	}
	
}
