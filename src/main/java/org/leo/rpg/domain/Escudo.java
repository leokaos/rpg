package org.leo.rpg.domain;

public class Escudo extends Equipamento {

	private Integer bonusDeClasseArmadura;

	public Escudo() {
		super();
	}

	public Escudo(Integer bonusDeClasseArmadura) {
		super();
		this.bonusDeClasseArmadura = bonusDeClasseArmadura;
	}

	public Integer getBonusDeClasseArmadura() {
		return bonusDeClasseArmadura;
	}

	public void setBonusDeClasseArmadura(Integer bonusDeClasseArmadura) {
		this.bonusDeClasseArmadura = bonusDeClasseArmadura;
	}

	public Integer calcular(Personagem personagem,TipoSituacao tipo) {

		if (tipo.isAdicionaArmadura()) {
			return bonusDeClasseArmadura;
		}

		return 0;
	}

}
