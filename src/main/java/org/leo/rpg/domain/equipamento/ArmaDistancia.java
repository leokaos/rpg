package org.leo.rpg.domain.equipamento;

import org.leo.rpg.domain.personagem.Personagem;

public class ArmaDistancia extends Arma {

	public ArmaDistancia(String nome) {
		super(nome);
	}

	@Override
	protected Integer getModificadorHabilidade(Personagem personagem) {
		return personagem.getDestreza().getModifier();
	}

	@Override
	protected boolean isArmaDistancia() {
		return true;
	}

}
