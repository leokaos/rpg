package org.leo.rpg.domain;

public abstract class TipoSituacao {

	private static final Integer BASE = 10;

	public static final TipoSituacao TOQUE = new TipoToque();
	public static final TipoSituacao NORMAL = new TipoNormal();
	public static final TipoSituacao SURPRESO = new TipoSurpreso();

	public Integer calcular(Personagem personagem) {

		Integer total = BASE + personagem.getTamanho().getModifier();

		if (isAdicionaDestreza()) {
			total += personagem.getDestreza().getModifier();
		}

		if (isAdicionaArmaduraNatural()) {
			total += personagem.getArmaduraNatural();
		}

		return total;
	}

	protected abstract boolean isAdicionaArmaduraNatural();

	protected abstract boolean isAdicionaDestreza();

}
