package org.leo.rpg.domain;

public class RolagemIniciativa implements Comparable<RolagemIniciativa> {

	private final Integer valorDado;
	private final Personagem personagem;

	public RolagemIniciativa(Integer valorDado,Personagem personagem) {
		super();
		this.valorDado = valorDado;
		this.personagem = personagem;
	}

	public Integer getValorDado() {
		return valorDado;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public Integer getIniciativaTotal() {
		return valorDado + personagem.getIniciativa();
	}

	@Override
	public int compareTo(RolagemIniciativa o) {
		return o.getIniciativaTotal() - this.getIniciativaTotal();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (personagem == null ? 0 : personagem.hashCode());
		result = prime * result + (valorDado == null ? 0 : valorDado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RolagemIniciativa other = (RolagemIniciativa) obj;
		if (personagem == null) {
			if (other.personagem != null) {
				return false;
			}
		} else if (!personagem.equals(other.personagem)) {
			return false;
		}
		if (valorDado == null) {
			if (other.valorDado != null) {
				return false;
			}
		} else if (!valorDado.equals(other.valorDado)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return personagem.getNome() + " = " + getIniciativaTotal();
	}

}
