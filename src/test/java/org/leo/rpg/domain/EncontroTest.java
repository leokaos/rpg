package org.leo.rpg.domain;

import org.junit.Test;
import org.leo.rpg.domain.encontro.Encontro;
import org.leo.rpg.domain.monster.Monstro;
import org.leo.rpg.domain.personagem.NomeAtributo;
import org.leo.rpg.domain.personagem.Personagem;
import org.leo.rpg.domain.personagem.jogador.PersonagemJogador;

public class EncontroTest {

	@Test
	public void test() {

		Encontro encontro = new Encontro();

		Monstro m1 = new Monstro("m1");
		m1.setIniciativa(2);
		m1.setPontosDeVida(50);

		Monstro m2 = new Monstro("m2");
		m2.setIniciativa(5);
		m2.setPontosDeVida(50);

		PersonagemJogador j1 = new PersonagemJogador("j1");
		j1.addAtributo(18,NomeAtributo.DESTREZA);
		j1.setPontosDeVida(50);

		PersonagemJogador j2 = new PersonagemJogador("j2");
		j2.addAtributo(12,NomeAtributo.DESTREZA);
		j2.setPontosDeVida(50);

		encontro.addPersonagem(m1);
		encontro.addPersonagem(m2);
		encontro.addPersonagem(j1);
		encontro.addPersonagem(j2);

		encontro.addRolagemIniciativa(m1,10,"Monstros"); // 12
		encontro.addRolagemIniciativa(m2,10,"Monstros"); // 15
		encontro.addRolagemIniciativa(j1,10,"Jogadores"); // 14
		encontro.addRolagemIniciativa(j2,10,"Jogadores"); // 11

		encontro.iniciar();

		for (Personagem personagem : encontro) {
			System.out.println(personagem.getNome());
		}
	}

}
