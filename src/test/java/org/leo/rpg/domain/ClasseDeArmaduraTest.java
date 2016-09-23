package org.leo.rpg.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.leo.rpg.domain.monster.Tamanho;

public class ClasseDeArmaduraTest {

	@Test
	public void test() {

		Personagem personagem = new PersonagemJogador();
		personagem.addAtributo(15,NomeAtributo.DESTREZA);
		personagem.setTamanho(Tamanho.ENORME);
		personagem.setArmaduraNatural(10);

		ClasseDeArmadura cl = new ClasseDeArmadura(personagem);

		assertEquals(8,cl.calcular(TipoSituacao.TOQUE).intValue());
		assertEquals(20,cl.calcular(TipoSituacao.NORMAL).intValue());
		assertEquals(18,cl.calcular(TipoSituacao.SURPRESO).intValue());
	}

}
