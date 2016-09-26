package org.leo.rpg.domain;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Encontro {

	private final List<Personagem> personagens = Lists.newArrayList();

	private final Set<RolagemIniciativa> iniciativas = Sets.newTreeSet();

	public Encontro() {
		super();
	}

	public void addPersonagem(Personagem personagem) {
		if (!personagens.contains(personagem)) {
			this.personagens.add(personagem);
		}
	}

	public void iniciar() {

	}

	public void addRolagemIniciativa(Personagem personagem,Integer rolagemDado) {
		RolagemIniciativa rolagem = new RolagemIniciativa(rolagemDado,personagem);

		if (!iniciativas.contains(rolagem)) {
			iniciativas.add(rolagem);
		}
	}

	public Set<RolagemIniciativa> getIniciativas() {
		return iniciativas;
	}

}
