package org.leo.rpg.domain.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Dado {

	D_4(4),
	D_6(6),
	D_8(8),
	D_10(10),
	D_12(12),
	D_20(20),
	D_100(100);

	private final int valor;

	private Dado(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public int jogar() {
		return new Random().nextInt(valor);
	}

	@Override
	public String toString() {
		return "d" + valor;
	}

	public Integer getMaximo() {
		return valor;
	}

	public static Dado parse(String representacao) {

		Pattern pattern = Pattern.compile("(d)([\\d]*)");

		Matcher matcher = pattern.matcher(representacao);

		if (matcher.find()) {
			return buscarDadoPorValor(matcher.group(2));
		}

		return null;
	}

	private static Dado buscarDadoPorValor(String group) {

		for (Dado dado : Dado.values()) {
			if (dado.getValor() == Integer.valueOf(group)) {
				return dado;
			}
		}

		return null;
	}

}
