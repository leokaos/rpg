package org.leo.rpg.domain.monster;

import org.leo.rpg.domain.Personagem;
import org.leo.rpg.domain.Dado;

public class Monstro extends Personagem {

	private Tamanho tamanho;

	private Dado dadoVida;
	private Integer quantidadeDadosVida;
	private Integer pontosDeVidaBase;
	private Integer pontosDeVidaMedio;

	private Integer iniciativa;

	private String nome;

	private Integer fortitude;
	private Integer vontade;
	private Integer reflexos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Dado getDadoVida() {
		return dadoVida;
	}

	public void setDadoVida(Dado dadoVida) {
		this.dadoVida = dadoVida;
	}

	public Integer getQuantidadeDadosVida() {
		return quantidadeDadosVida;
	}

	public void setQuantidadeDadosVida(Integer quantidadeDadosVida) {
		this.quantidadeDadosVida = quantidadeDadosVida;
	}

	public Integer getPontosDeVidaMedio() {
		return pontosDeVidaMedio;
	}

	public void setPontosDeVidaMedio(Integer pontosDeVidaMedio) {
		this.pontosDeVidaMedio = pontosDeVidaMedio;
	}

	public Integer getPontosDeVidaBase() {
		return pontosDeVidaBase;
	}

	public void setPontosDeVidaBase(Integer pontosDeVidaBase) {
		this.pontosDeVidaBase = pontosDeVidaBase;
	}

	public Integer getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(Integer iniciativa) {
		this.iniciativa = iniciativa;
	}

	@Override
	public Integer getReflexos() {
		return this.reflexos;
	}

	@Override
	public Integer getFortitude() {
		return this.fortitude;
	}

	public void setFortitude(Integer fortitude) {
		this.fortitude = fortitude;
	}

	public void setVontade(Integer vontade) {
		this.vontade = vontade;
	}

	public void setReflexos(Integer reflexos) {
		this.reflexos = reflexos;
	}

	@Override
	public Integer getVontade() {
		return this.vontade;
	}

	@Override
	public String toString() {
		return nome + " - " + quantidadeDadosVida + "" + dadoVida + " " + pontosDeVidaBase + "(" + pontosDeVidaMedio + ")";
	}

}
