package rbprojects.dto;

public class RequisitoComparativoVo {
	public String descricao;
	public Number valorCelular1;
	public Number valorCelular2;
	public boolean celular1emelhor;
	public boolean celular2emelhor;

	@Override
	public String toString() {
		return "RequisitoComparativoVo [descricao=" + descricao + ", valorCelular1=" + valorCelular1
				+ ", valorCelular2=" + valorCelular2 + ", celular1emelhor=" + celular1emelhor + ", celular2emelhor="
				+ celular2emelhor + "]";
	}

}