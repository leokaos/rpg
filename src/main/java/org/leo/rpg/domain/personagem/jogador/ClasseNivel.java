package org.leo.rpg.domain.personagem.jogador;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.leo.rpg.domain.personagem.Personagem;

@Entity
@Table(name = "classe_nivel",schema = "rpgdb")
public class ClasseNivel {

	@EmbeddedId
	private ClasseNivelPK id;

	@ManyToOne
	@JoinColumn(name = "classe_id",insertable = false,updatable = false)
	private Classe classe;

	@ManyToOne
	@JoinColumn(name = "personagem_id",insertable = false,updatable = false)
	private Personagem personagem;

	@Column(name = "nivel")
	private Integer nivel;

}
