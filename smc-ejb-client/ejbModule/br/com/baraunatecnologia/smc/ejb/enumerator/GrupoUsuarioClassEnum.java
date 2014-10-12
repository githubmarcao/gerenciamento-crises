package br.com.baraunatecnologia.smc.ejb.enumerator;

public class GrupoUsuarioClassEnum {

	public enum GrupoUsuarioEnum {

		ADMINISTRADOR(1, "Administrador"),

		PESSOA(2, "Pessoas comum"), 
		
		POLICIAL(3, "Policial"), 
		
		BOMBEIRO(4, "Bombeiro");

		private Integer id;
		private String nome;

		private GrupoUsuarioEnum(Integer id, String nome) {
			this.id = id;
			this.nome = nome;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public static GrupoUsuarioEnum getById(Integer id) {
			if (id == null || id == 0) {
				return null;
			}
			for(GrupoUsuarioEnum item : GrupoUsuarioEnum.values()){
				if (id.equals(item.getId())) {
					return item;
				}
			}
			return null;
		}
	}

}