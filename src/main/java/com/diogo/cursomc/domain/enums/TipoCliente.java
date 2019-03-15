package com.diogo.cursomc.domain.enums;

public enum TipoCliente {
 PESSOAFISICA(1,"Pessoa Física")
 , PESSOAJURIDICA(1,"Pessoa Jurídica");
	
	private Integer id;
	private String descricao;
	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	public Integer getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null)
			return null;
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("id invalido" + cod);
	}
	
	
}
