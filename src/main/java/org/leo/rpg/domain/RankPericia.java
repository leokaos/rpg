package org.leo.rpg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rank_pericia",schema = "rpgdb")
public class RankPericia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "rank")
	private Integer rank;

	@ManyToOne
	@JoinColumn(name = "pericia_id")
	private Pericia pericia;

	@ManyToOne
	@JoinColumn(name = "personagem_id")
	private Personagem personagem;

	public RankPericia() {
		super();
	}

	public RankPericia(Integer rank,Pericia pericia) {
		super();
		this.rank = rank;
		this.pericia = pericia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public Integer getBonusTotal(Personagem personagem) {
		return rank + personagem.getAtributo(pericia.getAtributo()).getModifier();
	}

}
