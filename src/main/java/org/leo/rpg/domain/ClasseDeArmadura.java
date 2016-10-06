package org.leo.rpg.domain;

public class ClasseDeArmadura {

	private static final Integer BASE = 10;

	private Personagem personagem;

	public ClasseDeArmadura() {
		super();
	}

	public ClasseDeArmadura(Personagem personagem) {
		super();
		this.personagem = personagem;
	}

	public Integer calcular(TipoSituacao tipo) {

		Integer total = BASE + personagem.getTamanho().getModifier();

		if (tipo.isAdicionaDestreza()) {
			total += personagem.getDestreza().getModifier();
		}

		if (tipo.isAdicionaArmaduraNatural()) {
			total += personagem.getArmaduraNatural();
		}

		if (tipo.isAdicionaArmadura()) {
			total += personagem.calcularArmadura(tipo);
		}

		return total;
	}

}
