package org.leo.rpg.domain;

public class ArmaBranca extends Arma {

	public ArmaBranca(String nome) {
		super(nome);
	}

	@Override
	protected Integer getModificadorHabilidade(Personagem personagem) {
		return personagem.getForca().getModifier();
	}

	@Override
	protected boolean isArmaDistancia() {
		return false;
	}

}
