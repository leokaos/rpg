package org.leo.rpg.domain;

import java.util.List;

import com.google.common.collect.Lists;

public class Encontro {

	private final List<Personagem> personagens = Lists.newArrayList();

	public Encontro() {
		super();
	}

	public void addPersonagem(Personagem personagem) {
		if (!personagens.contains(personagem)) {
			this.personagens.add(personagem);
		}
	}

}
