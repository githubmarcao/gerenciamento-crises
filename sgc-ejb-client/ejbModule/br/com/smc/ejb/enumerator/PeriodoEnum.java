package br.com.smc.ejb.enumerator;

public enum PeriodoEnum {

	SEGUNDO(1, "Segundo"),

	MINUTO(2, "Minuto"),

	HORA(3, "Hora"),

	DIA(4, "Dia"),

	MES(5, "Mes"),

	ANO(6, "Ano");

	private Integer valor;
	private String descricao;

	private PeriodoEnum(Integer valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static PeriodoEnum getByValor(Integer valor) {
		if (valor == null || valor <= 0) {
			return null;
		}
		for (PeriodoEnum item : PeriodoEnum.values()) {
			if (valor.equals(item.getValor())) {
				return item;
			}
		}
		return null;
	}

	public static PeriodoEnum getByCaminho(String descricao) {
		if (descricao == null || descricao.isEmpty()) {
			return null;
		}
		for (PeriodoEnum item : PeriodoEnum.values()) {
			if (descricao.equals(item.getDescricao())) {
				return item;
			}
		}
		return null;
	}

}