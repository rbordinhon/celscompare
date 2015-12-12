package rbprojects.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CELULAR_INFO")
public class CelularInfo {
	// (ID_CELULAR_INFO,DS_MODELO,NR_MEMORIA,NR_CAMERA_TRASEIRA_MP,NR_TELA_TAMANHO,NR_PRECO,NR_CAMERA_FRONTAL_MP)
	@Column(name = "ID_CELULAR_INFO")
	@Id
	private long idCelularInfo;

	@Column(name = "DS_MODELO")
	private String modelo;

	@Column(name = "NR_MEMORIA")
	private Double memoria;

	@Column(name = "NR_CAMERA_TRASEIRA_MP")
	private Double resolucacoCameraTraseira;

	@Column(name = "NR_TELA_TAMANHO")
	private Double nrTelaTamanho;

	@Column(name = "NR_CAMERA_FRONTAL_MP")
	private Double resolucacoCameraDronta;

	@Column(name = "NR_PRECO")
	private Double preco;

	@Column(name = "NR_FAVORITO")
	private Long favorito;

	public long getIdCelularInfo() {
		return idCelularInfo;
	}

	public void setIdCelularInfo(long idCelularInfo) {
		this.idCelularInfo = idCelularInfo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getMemoria() {
		return memoria;
	}

	public void setMemoria(Double memoria) {
		this.memoria = memoria;
	}

	public Double getResolucacoCameraTraseira() {
		return resolucacoCameraTraseira;
	}

	public void setResolucacoCameraTraseira(Double resolucacoCameraTraseira) {
		this.resolucacoCameraTraseira = resolucacoCameraTraseira;
	}

	public Double getNrTelaTamanho() {
		return nrTelaTamanho;
	}

	public void setNrTelaTamanho(Double nrTelaTamanho) {
		this.nrTelaTamanho = nrTelaTamanho;
	}

	public Double getResolucacoCameraDronta() {
		return resolucacoCameraDronta;
	}

	public void setResolucacoCameraDronta(Double resolucacoCameraDronta) {
		this.resolucacoCameraDronta = resolucacoCameraDronta;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Long getFavorito() {
		return favorito == null ? 0 : favorito;
	}

	public void setFavorito(Long favorito) {
		this.favorito = favorito;
	}

	@Override
	public String toString() {
		return "CelularInfo [idCelularInfo=" + idCelularInfo + ", modelo=" + modelo + ", memoria=" + memoria
				+ ", resolucacoCameraTraseira=" + resolucacoCameraTraseira + ", nrTelaTamanho=" + nrTelaTamanho
				+ ", resolucacoCameraDronta=" + resolucacoCameraDronta + ", preco=" + preco + "]";
	}

}
