package org.leo.rpg.domain.personagem;

import java.util.Set;

import org.leo.rpg.domain.personagem.jogador.Classe;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class PersonagemFunctions {

	public static Predicate<Atributo> atributo(final NomeAtributo atributo) {

		return new Predicate<Atributo>() {

			@Override
			public boolean apply(Atributo input) {
				return input.getName().equals(atributo);
			}

		};
	}

	public static Predicate<RankPericia> pericia(final Pericia pericia) {

		return new Predicate<RankPericia>() {

			@Override
			public boolean apply(RankPericia input) {
				return pericia.equals(input.getPericia());
			}
		};
	}

	public static Predicate<Nivel> getFunctionNivel(final Integer nivel) {

		return new Predicate<Nivel>() {

			@Override
			public boolean apply(Nivel input) {
				return input.getNivel().equals(nivel);
			}
		};
	}

	public static Predicate<Nivel> getClasseFromNivel(final Classe classe) {
		return new Predicate<Nivel>() {

			@Override
			public boolean apply(Nivel input) {
				return input.getClasse().equals(classe);
			}
		};
	}

	public static Function<Nivel,Set<Pericia>> extraiPericiasDeNiveis() {

		return new Function<Nivel,Set<Pericia>>() {

			@Override
			public Set<Pericia> apply(Nivel input) {
				return input.getClasse().getPericias();
			}

		};
	}

	public static Predicate<RankPericia> rankPericia(final Pericia pericia) {

		return new Predicate<RankPericia>() {

			@Override
			public boolean apply(RankPericia input) {
				return input.getPericia().equals(pericia);
			}
		};
	}

}
