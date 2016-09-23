package org.leo.rpg.domain.monster;

public enum Tamanho {

	MINUSCULO(8),
	DIMINUTO(4),
	MIUDO(2),
	PEQUENO(1),
	MEDIO(0),
	GRANDE(-1),
	ENORME(-2),
	IMENSO(-4),
	COLOSSAL(-8);

	private Integer modifier;

	private Tamanho(Integer modifier) {
		this.modifier = modifier;
	}

	public Integer getModifier() {
		return modifier;
	}

}
