package org.leo.rpg.domain.encontro;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.leo.rpg.domain.personagem.Personagem;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

public class EncontroIterator implements Iterator<Personagem> {

	private final Encontro encontro;
	private Iterator<RolagemIniciativa> it;

	public EncontroIterator(Encontro encontro) {
		super();
		this.encontro = encontro;
	}

	@Override
	public boolean hasNext() {
		Map<String,Collection<RolagemIniciativa>> mapaGrupo = FluentIterable.from(encontro.getIniciativas()).index(getFunctionAgrupar()).asMap();

		Integer retorno = 0;

		for (String grupo : mapaGrupo.keySet()) {
			retorno += FluentIterable.from(mapaGrupo.get(grupo)).filter(getPredicateVivo()).isEmpty() ? 0 : 1;
		}

		return retorno > 1;
	}

	private Function<RolagemIniciativa,String> getFunctionAgrupar() {
		return new Function<RolagemIniciativa,String>() {

			@Override
			public String apply(RolagemIniciativa input) {
				return input.getGrupo();
			}
		};
	}

	private Predicate<RolagemIniciativa> getPredicateVivo() {
		return new Predicate<RolagemIniciativa>() {

			@Override
			public boolean apply(RolagemIniciativa input) {
				return input.getPersonagem().isVivo();
			}
		};
	}

	@Override
	public Personagem next() {

		if (it == null) {
			it = encontro.getIniciativas().iterator();
		}

		if (!it.hasNext()) {
			it = encontro.getIniciativas().iterator();
		}

		while (true) {

			RolagemIniciativa rl = it.next();

			if (rl.getPersonagem().isVivo()) {
				return rl.getPersonagem();
			}
		}

	}

	@Override
	public void remove() {

	}

}
