package org.leo.rpg.domain;

public abstract class Arma {

	private final String nome;
	private final Integer valorModificador = 0;

	public Arma(String nome) {
		super();
		this.nome = nome;
	}

	public Integer getModificarTotal(Personagem personagem) {
		return valorModificador + getModificadorHabilidade(personagem);
	}

	public String getNome() {
		return nome;
	}

	public Integer getValorModificador() {
		return valorModificador;
	}

	protected abstract Integer getModificadorHabilidade(Personagem personagem);

	protected abstract boolean isArmaDistancia();

}
