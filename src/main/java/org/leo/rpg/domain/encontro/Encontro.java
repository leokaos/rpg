package org.leo.rpg.domain.encontro;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.leo.rpg.domain.personagem.Personagem;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Encontro implements Iterable<Personagem> {

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

	public void addRolagemIniciativa(Personagem personagem,Integer rolagemDado,String grupo) {
		RolagemIniciativa rolagem = new RolagemIniciativa(rolagemDado,personagem,grupo);

		if (!iniciativas.contains(rolagem)) {
			iniciativas.add(rolagem);
		}
	}

	public Set<RolagemIniciativa> getIniciativas() {
		return iniciativas;
	}

	public List<Personagem> getPersonagens() {
		return personagens;
	}

	@Override
	public Iterator<Personagem> iterator() {
		return new EncontroIterator(this);
	}

}
