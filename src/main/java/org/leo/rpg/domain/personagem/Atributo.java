package org.leo.rpg.domain.personagem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atributo",schema = "rpgdb")
public class Atributo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "valor")
	private Integer valor;

	@Column(name = "nome")
	@Enumerated(EnumType.STRING)
	private NomeAtributo name;

	@ManyToOne
	@JoinColumn(name = "personagem_id")
	private Personagem personagem;

	public Atributo() {
		super();
	}

	public Atributo(Integer valor, NomeAtributo name) {
		super();
		this.valor = valor;
		this.name = name;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public NomeAtributo getName() {
		return name;
	}

	public void setName(NomeAtributo name) {
		this.name = name;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public Integer getModifier() {
		return (valor - 10) / 2;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(name).append("=").append(valor).toString();
	}

}
