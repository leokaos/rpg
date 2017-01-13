package org.leo.rpg.domain.personagem.jogador;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.leo.rpg.domain.personagem.Personagem;

@Embeddable
public class ClasseNivelPK implements Serializable {

	private static final long serialVersionUID = -6274942498228151044L;

	@ManyToOne
	@JoinColumn(name = "classe_id")
	private Classe classe;

	@ManyToOne
	@JoinColumn(name = "personagem_id")
	private Personagem personagem;

	public ClasseNivelPK() {
		super();
	}

	public ClasseNivelPK(Classe classe,Personagem personagem) {
		super();
		this.classe = classe;
		this.personagem = personagem;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

}
