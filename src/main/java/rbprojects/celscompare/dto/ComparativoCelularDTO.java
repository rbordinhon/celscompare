package rbprojects.celscompare.dto;

import java.util.Arrays;

public class ComparativoCelularDTO {
	public String descricaoCelular1;
	public String descricaoCelular2;
	public RequisitoComparativoDTO[] requisitos;
	@Override
	public String toString() {
		return "ComparativoCelularVO [descricaoCelular1=" + descricaoCelular1 + ", descricaoCelular2="
				+ descricaoCelular2 + ", requisitos=" + Arrays.toString(requisitos) + "]";
	}
	
}
