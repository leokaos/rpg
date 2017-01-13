package org.leo.rpg.domain;

import static org.junit.Assert.assertThat;
import static org.leo.rpg.domain.encontro.TipoSituacao.NORMAL;
import static org.leo.rpg.domain.encontro.TipoSituacao.SURPRESO;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.leo.rpg.domain.equipamento.Armadura;
import org.leo.rpg.domain.monster.Tamanho;
import org.leo.rpg.domain.personagem.NomeAtributo;
import org.leo.rpg.domain.personagem.jogador.PersonagemJogador;

public class ArmaduraTest {

	@Test
	public void calcularComRestricaoDeDestrezaSituacaoNormalTest() {

		PersonagemJogador personagem = getPersonagemPadrao();

		Armadura armadura = new Armadura(8,1);
		personagem.setArmadura(armadura);

		assertThat(personagem.getClasseDeArmadura(NORMAL),Matchers.is(19));
	}

	@Test
	public void calcularSemRestricaoDeDestrezaSituacaoNormalTest() {

		PersonagemJogador personagem = getPersonagemPadrao();
		Armadura armadura = new Armadura(8,5);

		personagem.setArmadura(armadura);

		assertThat(personagem.getClasseDeArmadura(NORMAL),Matchers.is(22));
	}

	@Test
	public void calcularComRestricaoDeDestrezaSituacaoSurpresoTest() {

		PersonagemJogador personagem = getPersonagemPadrao();
		Armadura armadura = new Armadura(8,1);

		personagem.setArmadura(armadura);

		assertThat(personagem.getClasseDeArmadura(SURPRESO),Matchers.is(18));
	}

	@Test
	public void calcularSemRestricaoDeDestrezaSituacaoSurpresoTest() {

		PersonagemJogador personagem = getPersonagemPadrao();
		Armadura armadura = new Armadura(8,5);

		personagem.setArmadura(armadura);

		assertThat(personagem.getClasseDeArmadura(SURPRESO),Matchers.is(18));
	}

	private PersonagemJogador getPersonagemPadrao() {
		PersonagemJogador personagem = new PersonagemJogador("PERSONAGEM");

		personagem.addAtributo(18,NomeAtributo.FORCA);
		personagem.addAtributo(18,NomeAtributo.DESTREZA);
		personagem.addAtributo(15,NomeAtributo.CONSTITUICAO);
		personagem.addAtributo(15,NomeAtributo.INTELIGENCIA);
		personagem.addAtributo(15,NomeAtributo.SABEDORIA);
		personagem.addAtributo(15,NomeAtributo.CARISMA);

		personagem.setTamanho(Tamanho.MEDIO);

		return personagem;
	}

}
