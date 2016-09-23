package org.leo.rpg.domain;

public class ClasseDeArmadura {

	private Personagem personagem;

	public ClasseDeArmadura() {
		super();
	}

	public ClasseDeArmadura(Personagem personagem) {
		super();
		this.personagem = personagem;
	}

	public Integer calcular(TipoSituacao tipo) {
		return tipo.calcular(personagem);
	}

}
