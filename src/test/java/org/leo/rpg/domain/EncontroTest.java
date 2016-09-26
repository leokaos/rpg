package org.leo.rpg.domain;

import org.junit.Test;
import org.leo.rpg.domain.monster.Monstro;

public class EncontroTest {

	@Test
	public void test() {
		Encontro encontro = new Encontro();

		Monstro m1 = new Monstro("m1");
		m1.setIniciativa(2);

		Monstro m2 = new Monstro("m2");
		m2.setIniciativa(5);

		PersonagemJogador j1 = new PersonagemJogador("j1");
		j1.addAtributo(18,NomeAtributo.DESTREZA);

		PersonagemJogador j2 = new PersonagemJogador("j2");
		j2.addAtributo(12,NomeAtributo.DESTREZA);

		encontro.addPersonagem(m1);
		encontro.addPersonagem(m2);
		encontro.addPersonagem(j1);
		encontro.addPersonagem(j2);

		encontro.iniciar();

		encontro.addRolagemIniciativa(m1,Dado.D_20.getValor());
		encontro.addRolagemIniciativa(m2,Dado.D_20.getValor());
		encontro.addRolagemIniciativa(j1,Dado.D_20.getValor());
		encontro.addRolagemIniciativa(j2,Dado.D_20.getValor());

		System.out.println(encontro.getIniciativas());
	}

}
