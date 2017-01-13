package org.leo.rpg.domain.personagem.jogador;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.leo.rpg.domain.personagem.Atributo;

@Embeddable
public class ConfiguracaoPericia {

	@Column(name = "modificador")
	private Integer modificador;

	@Column(name = "multiplicador_primeiro_nivel")
	private Integer multiplicadorPrimeiroNivel;

	public ConfiguracaoPericia() {
		super();
	}

	public ConfiguracaoPericia(Integer modificador, Integer multiplicadorPrimeiroNivel) {
		super();
		this.modificador = modificador;
		this.multiplicadorPrimeiroNivel = multiplicadorPrimeiroNivel;
	}

	public Integer getModificador() {
		return modificador;
	}

	public void setModificador(Integer modificador) {
		this.modificador = modificador;
	}

	public Integer getMultiplicadorPrimeiroNivel() {
		return multiplicadorPrimeiroNivel;
	}

	public void setMultiplicadorPrimeiroNivel(Integer multiplicadorPrimeiroNivel) {
		this.multiplicadorPrimeiroNivel = multiplicadorPrimeiroNivel;
	}

	public Integer getTotal(Atributo inteligencia,Integer nivel) {

		Integer total = 0;

		total += (modificador + inteligencia.getModifier()) * multiplicadorPrimeiroNivel;

		for (int x = 2; x <= nivel; x++) {
			total += modificador + inteligencia.getModifier();
		}

		return total;
	}

}
