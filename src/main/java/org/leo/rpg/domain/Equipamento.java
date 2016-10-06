package org.leo.rpg.domain;

public abstract class Equipamento {

	private String nome;

	public Equipamento() {
		super();
	}

	public Equipamento(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
