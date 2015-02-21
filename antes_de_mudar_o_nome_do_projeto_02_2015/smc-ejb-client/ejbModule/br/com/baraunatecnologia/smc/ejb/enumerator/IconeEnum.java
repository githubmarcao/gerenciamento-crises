package br.com.baraunatecnologia.smc.ejb.enumerator;

public enum IconeEnum {

	PADRAO("Padrão", "resources/images/usuarios/icone.png"),

	ADMINISTRADOR("Administrador", "resources/images/usuarios/administrador_36.png"),

	PESSOA("Pessoa", "resources/images/usuarios/pessoa_36.png"),

	POLICIA("Policia", "resources/images/usuarios/policia_36.png"),

	BOMBEIRO("Bombeiro", "resources/images/usuarios/bombeiro_36.png");

	private String nome;
	private String caminho;

	private IconeEnum(String nome, String caminho) {
		this.nome = nome;
		this.caminho = caminho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public static IconeEnum getByNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			return null;
		}
		for (IconeEnum item : IconeEnum.values()) {
			if (nome.equals(item.getNome())) {
				return item;
			}
		}
		return null;
	}

	public static IconeEnum getByCaminho(String caminho) {
		if (caminho == null || caminho.isEmpty()) {
			return null;
		}
		for (IconeEnum item : IconeEnum.values()) {
			if (caminho.equals(item.getCaminho())) {
				return item;
			}
		}
		return null;
	}

}