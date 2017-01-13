package org.leo.rpg.domain.equipamento;

import org.leo.rpg.domain.encontro.TipoSituacao;
import org.leo.rpg.domain.personagem.Personagem;

public class Armadura extends Equipamento {

	private Integer bonusArmadura;
	private Integer bonusMaximoDestreza;

	public Armadura() {
		super();
	}

	public Armadura(Integer bonusArmadura,Integer bonusMaximoDestreza) {
		super();
		this.bonusArmadura = bonusArmadura;
		this.bonusMaximoDestreza = bonusMaximoDestreza;
	}

	public Integer getBonusArmadura() {
		return bonusArmadura;
	}

	public void setBonusArmadura(Integer bonusArmadura) {
		this.bonusArmadura = bonusArmadura;
	}

	public Integer getBonusMaximoDestreza() {
		return bonusMaximoDestreza;
	}

	public void setBonusMaximoDestreza(Integer bonusMaximoDestreza) {
		this.bonusMaximoDestreza = bonusMaximoDestreza;
	}

	public Integer calcular(Personagem personagem,TipoSituacao tipo) {

		Integer total = 0;

		Integer modificadorDestreza = personagem.getDestreza().getModifier();

		if (tipo.isAdicionaDestreza() && modificadorDestreza > bonusMaximoDestreza) {
			total += bonusMaximoDestreza - modificadorDestreza;
		}

		total += bonusArmadura;

		return total;
	}

}
