package org.leo.rpg.domain.personagem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "magias_por_dia",schema = "rpgdb")
public class MagiasPorDia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "nivel_id")
	private Nivel nivel;

	@Column(name = "circulo")
	private Integer circulo;

	@Column(name = "quantidade")
	private Integer quantidade;

	public MagiasPorDia() {
		super();
	}

	public MagiasPorDia(Nivel nivel,Integer circulo,Integer quantidade) {
		super();
		this.nivel = nivel;
		this.circulo = circulo;
		this.quantidade = quantidade;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public Integer getCirculo() {
		return circulo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).append("Circulo",circulo).append("Qt",quantidade).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (circulo == null ? 0 : circulo.hashCode());
		result = prime * result + (nivel == null ? 0 : nivel.hashCode());
		result = prime * result + (quantidade == null ? 0 : quantidade.hashCode());
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
		MagiasPorDia other = (MagiasPorDia) obj;
		if (circulo == null) {
			if (other.circulo != null) {
				return false;
			}
		} else if (!circulo.equals(other.circulo)) {
			return false;
		}
		if (nivel == null) {
			if (other.nivel != null) {
				return false;
			}
		} else if (!nivel.equals(other.nivel)) {
			return false;
		}
		if (quantidade == null) {
			if (other.quantidade != null) {
				return false;
			}
		} else if (!quantidade.equals(other.quantidade)) {
			return false;
		}
		return true;
	}

}
