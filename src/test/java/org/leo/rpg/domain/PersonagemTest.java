package org.leo.rpg.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.leo.rpg.domain.equipamento.Arma;
import org.leo.rpg.domain.equipamento.ArmaBranca;
import org.leo.rpg.domain.equipamento.ArmaDistancia;
import org.leo.rpg.domain.infra.RPGException;
import org.leo.rpg.domain.personagem.Ataque;
import org.leo.rpg.domain.personagem.Nivel;
import org.leo.rpg.domain.personagem.NomeAtributo;
import org.leo.rpg.domain.personagem.Pericia;
import org.leo.rpg.domain.personagem.jogador.Classe;
import org.leo.rpg.domain.personagem.jogador.ConfiguracaoPericia;
import org.leo.rpg.domain.personagem.jogador.ModificadorAtaque;
import org.leo.rpg.domain.personagem.jogador.PersonagemJogador;
import org.leo.rpg.domain.personagem.jogador.Poder;
import org.leo.rpg.domain.util.Dado;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class PersonagemTest {

	private static final String DB = "C:\\Temp\\db";

	private static Map<String,String> properties = Maps.newHashMap();
	private static List<Pericia> pericias = Lists.newArrayList();

	static {
		try {
			extrairPericiaTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void criacaoPersonagemTest() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		Pericia pericia = new Pericia("PERICIA",true,NomeAtributo.FORCA);

		personagem.addPericia(pericia,5);

		assertEquals(9,personagem.getTotalPericia(pericia).intValue());
	}

	@Test
	public void criacaoPersonagem2Test() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		Pericia pericia = new Pericia("PERICIA",true,NomeAtributo.FORCA);

		assertEquals(4,personagem.getTotalPericia(pericia).intValue());
	}

	@Test(expected = RPGException.class)
	public void criacaoPersonagem3Test() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		Pericia pericia = new Pericia("PERICIA",false,NomeAtributo.FORCA);

		personagem.getTotalPericia(pericia);
	}

	@Test
	public void criacaoPersonagemThrowTest() throws Exception {

		PersonagemJogador personagemPadrao = getPaladinoPadrao();

		assertEquals(personagemPadrao.getReflexos().intValue(),2);
		assertEquals(personagemPadrao.getFortitude().intValue(),5);
		assertEquals(personagemPadrao.getVontade().intValue(),3);

	}

	@Test
	public void criacaoPersonagemAtaquesComApenasUmAtaqueTest() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		Arma armaBranca = new ArmaBranca("PERTO");
		personagem.addArma(armaBranca);

		List<Ataque> ataquesBranca = personagem.getAtaques(armaBranca);
		assertEquals(ataquesBranca.iterator().next().getModificador().intValue(),7);

		Arma armaDistancia = new ArmaDistancia("DISTANCIA");
		personagem.addArma(armaDistancia);

		List<Ataque> ataquesDistancia = personagem.getAtaques(armaDistancia);
		assertEquals(ataquesDistancia.iterator().next().getModificador().intValue(),4);

	}

	@Test
	public void criacaoPersonagemAtaquesComDoisAtaquesTest() throws Exception {

		PersonagemJogador personagem = getPersonagemPadrao();
		Classe classe = getPaladino();
		personagem.addNivel(classe,6);

		Arma armaBranca = new ArmaBranca("PERTO");
		personagem.addArma(armaBranca);

		List<Ataque> ataquesBranca = personagem.getAtaques(armaBranca);
		assertEquals(ataquesBranca.get(0).getModificador().intValue(),10);
		assertEquals(ataquesBranca.get(1).getModificador().intValue(),5);

		Arma armaDistancia = new ArmaDistancia("DISTANCIA");
		personagem.addArma(armaDistancia);

		List<Ataque> ataquesDistancia = personagem.getAtaques(armaDistancia);
		assertEquals(ataquesDistancia.get(0).getModificador().intValue(),7);
		assertEquals(ataquesDistancia.get(1).getModificador().intValue(),2);

	}

	@Test
	public void deveriaCriarUmPersonagemECalcularPontosDeVidaTest() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		assertEquals(36,personagem.getPontosDeVida().intValue());
	}

	@Test
	public void deveriaTer24PontosDePericiaTest() throws Exception {

		PersonagemJogador paladino = getPaladinoPadrao();

		assertEquals(paladino.getTotalPontosPericia().intValue(),24);
	}

	@Test
	public void criacaoPersonagemPoderesTest() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		List<Poder> poderes = personagem.getPoderes();

		ImmutableList<String> poderesEmString = FluentIterable.from(poderes).transform(new Function<Poder,String>() {

			@Override
			public String apply(Poder input) {
				return input.getNome();
			}

		}).toList();

		assertThat(poderesEmString.size(),Matchers.is(7));
		assertThat(poderesEmString,Matchers.hasItem("Aura of good"));
		assertThat(poderesEmString,Matchers.hasItem("detect evil"));
		assertThat(poderesEmString,Matchers.hasItem("smite evil 1/day"));
		assertThat(poderesEmString,Matchers.hasItem("Divine grace"));
		assertThat(poderesEmString,Matchers.hasItem("lay on hands"));
		assertThat(poderesEmString,Matchers.hasItem("Aura of courage"));
		assertThat(poderesEmString,Matchers.hasItem("divine health"));
	}

	@Test
	public void criacaoPersonagemMagiasPorDiaTest() throws Exception {

		Classe classe = getFeiticeiro();

		assertEquals(classe.getNivel(6).getQuantidadeMagiasPorCirculo(0).intValue(),6);

		PersonagemJogador feiticeiro = getPersonagemPadrao();

		feiticeiro.addNivel(classe,6);

		// 6th |+3 |+2|+2|+5 | |6|6|5|3|—|—|—|—|—|—|

		// +2 carisma +6 diaria = 8 magias por dia de 1º circulo

		assertEquals(feiticeiro.getQuantidadeMagiasPorCirculo(0,classe).intValue(),8);
		assertEquals(feiticeiro.getQuantidadeMagiasPorCirculo(1,classe).intValue(),8);
		assertEquals(feiticeiro.getQuantidadeMagiasPorCirculo(2,classe).intValue(),7);
		assertEquals(feiticeiro.getQuantidadeMagiasPorCirculo(3,classe).intValue(),5);

	}

	@Test
	public void deveriaRetornarSemExcessaoEDepoisComExcesaoTest() {

		try {
			getPaladinoPadrao().checkRankPericia(6);
		} catch (RPGException e) {
			fail();
		}

		try {
			getPaladinoPadrao().checkRankPericia(7);
			fail();
		} catch (RPGException e) {
		}

	}

	@Test(expected = RPGException.class)
	public void deveriaNaoPermitirAdicionarUmaPericiaComRankMaiorQue7Test() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		Pericia primeiro = personagem.getPericiasPorClasses().iterator().next();

		personagem.addPericia(primeiro,10);
	}

	@Test
	public void deveriaPermitirAdicionarUmaPericiaComRankMenorOuIguala6Test() throws Exception {

		PersonagemJogador personagem = getPaladinoPadrao();

		Pericia primeiro = new Pericia("TESTE",true,NomeAtributo.FORCA);

		personagem.addPericia(primeiro,6);

		assertEquals(10,personagem.getRank(primeiro).getBonusTotal(personagem).intValue());
	}

	private PersonagemJogador getPaladinoPadrao() {

		PersonagemJogador personagem = getPersonagemPadrao();

		Classe classe = getPaladino();

		personagem.addNivel(classe,3);

		return personagem;
	}

	private PersonagemJogador getPersonagemPadrao() {
		PersonagemJogador personagem = new PersonagemJogador("PERSONAGEM");

		personagem.addAtributo(18,NomeAtributo.FORCA);
		personagem.addAtributo(12,NomeAtributo.DESTREZA);
		personagem.addAtributo(15,NomeAtributo.CONSTITUICAO);
		personagem.addAtributo(15,NomeAtributo.INTELIGENCIA);
		personagem.addAtributo(15,NomeAtributo.SABEDORIA);
		personagem.addAtributo(15,NomeAtributo.CARISMA);

		return personagem;
	}

	private Classe getPaladino() {

		List<String> lista = Lists.newArrayList();

		lista.add("1st		|+1				|+2	|+0|+0|Aura of good, detect evil, smite evil 1/day	|—|—|—|—|");
		lista.add("2nd		|+2				|+3	|+0|+0|Divine grace, lay on hands					|—|—|—|—|");
		lista.add("3rd		|+3				|+3	|+1|+1|Aura of courage, divine health				|—|—|—|—|");
		lista.add("4th		|+4				|+4	|+1|+1|Turn undead									|0|—|—|—|");
		lista.add("5th		|+5				|+4	|+1|+1|Smite evil 2/day, special mount				|0|—|—|—|");
		lista.add("6th		|+6/+1			|+5	|+2|+2|Remove disease 1/week						|1|—|—|—|");
		lista.add("7th		|+7/+2			|+5	|+2|+2|												|1|—|—|—|");
		lista.add("8th		|+8/+3			|+6	|+2|+2|												|1|0|—|—|");
		lista.add("9th		|+9/+4			|+6	|+3|+3|Remove disease 2/week						|1|0|—|—|");
		lista.add("10th		|+10/+5			|+7	|+3|+3|Smite evil 3/day								|1|1|—|—|");
		lista.add("11th		|+11/+6/+1		|+7	|+3|+3|												|1|1|0|—|");
		lista.add("12th		|+12/+7/+2		|+8	|+4|+4|Remove disease 3/week						|1|1|1|—|");
		lista.add("13th		|+13/+8/+3		|+8	|+4|+4|												|1|1|1|—|");
		lista.add("14th		|+14/+9/+4		|+9	|+4|+4|												|2|1|1|0|");
		lista.add("15th		|+15/+10/+5		|+9	|+5|+5|Remove disease 4/week, smite evil 4/day		|2|1|1|1|");
		lista.add("16th		|+16/+11/+6/+1	|+10|+5|+5|												|2|2|1|1|");
		lista.add("17th		|+17/+12/+7/+2	|+10|+5|+5|												|2|2|2|1|");
		lista.add("18th		|+18/+13/+8/+3	|+11|+6|+6|Remove disease 5/week						|3|2|2|1|");
		lista.add("19th		|+19/+14/+9/+4	|+11|+6|+6|												|3|3|3|2|");
		lista.add("20th		|+20/+15/+10/+5	|+12|+6|+6|Smite evil 5/day								|3|3|3|3|");

		String pericias = "Concentration,Craft,Diplomacy,Handle Animal,Heal,Knowledge,Profession,Ride,Sense Motive";

		return buildClasse("Paladino",lista,Dado.D_10,NomeAtributo.SABEDORIA,pericias,new ConfiguracaoPericia(2,4));
	}

	private Classe getFeiticeiro() {

		List<String> lista = Lists.newArrayList();

		lista.add("1st		|+0		|+0|+0|+2	|Summon familiar	|5|3|—|—|—|—|—|—|—|—|");
		lista.add("2nd		|+1		|+0|+0|+3	|					|6|4|—|—|—|—|—|—|—|—|");
		lista.add("3rd		|+1		|+1|+1|+3	|					|6|5|—|—|—|—|—|—|—|—|");
		lista.add("4th		|+2		|+1|+1|+4	|					|6|6|3|—|—|—|—|—|—|—|");
		lista.add("5th		|+2		|+1|+1|+4	|					|6|6|4|—|—|—|—|—|—|—|");
		lista.add("6th		|+3		|+2|+2|+5	|					|6|6|5|3|—|—|—|—|—|—|");
		lista.add("7th		|+3		|+2|+2|+5	|					|6|6|6|4|—|—|—|—|—|—|");
		lista.add("8th		|+4		|+2|+2|+6	|					|6|6|6|5|3|—|—|—|—|—|");
		lista.add("9th		|+4		|+3|+3|+6	|					|6|6|6|6|4|—|—|—|—|—|");
		lista.add("10th		|+5		|+3|+3|+7	|					|6|6|6|6|5|3|—|—|—|—|");
		lista.add("11th		|+5		|+3|+3|+7	|					|6|6|6|6|6|4|—|—|—|—|");
		lista.add("12th		|+6/+1	|+4|+4|+8	|					|6|6|6|6|6|5|3|—|—|—|");
		lista.add("13th		|+6/+1	|+4|+4|+8	|					|6|6|6|6|6|6|4|—|—|—|");
		lista.add("14th		|+7/+2	|+4|+4|+9	|					|6|6|6|6|6|6|5|3|—|—|");
		lista.add("15th		|+7/+2	|+5|+5|+9	|					|6|6|6|6|6|6|6|4|—|—|");
		lista.add("16th		|+8/+3	|+5|+5|+10	|					|6|6|6|6|6|6|6|5|3|—|");
		lista.add("17th		|+8/+3	|+5|+5|+10	|					|6|6|6|6|6|6|6|6|4|—|");
		lista.add("18th		|+9/+4	|+6|+6|+11	|					|6|6|6|6|6|6|6|6|5|3|");
		lista.add("19th		|+9/+4	|+6|+6|+11	|					|6|6|6|6|6|6|6|6|6|4|");
		lista.add("20th		|+10/+5	|+6|+6|+12	|					|6|6|6|6|6|6|6|6|6|6|");

		String pericias = "Bluff,Concentration,Craft,Knowledge,Profession,Spellcraft";

		return buildClasse("Feiticeiro",lista,Dado.D_4,NomeAtributo.CARISMA,pericias,new ConfiguracaoPericia(2,4));
	}

	private Classe buildClasse(String nome,List<String> niveis,Dado dadoVida,NomeAtributo atributoMagia,String pericias,ConfiguracaoPericia configuracaoPericia) {

		Classe classe = new Classe(nome);
		classe.setDadoVida(dadoVida);
		classe.setAtributoParaMagia(atributoMagia);
		classe.setConfiguracaoPericia(configuracaoPericia);

		classe.setPericias(carregarPericias(pericias));

		for (String string : niveis) {
			classe.addNivel(createNivel(string,classe));
		}

		return classe;
	}

	private Set<Pericia> carregarPericias(String periciasStr) {

		Set<Pericia> set = Sets.newHashSet();

		List<String> valores = Splitter.on(",").splitToList(periciasStr);

		for (String string : valores) {

			String nomeCorreto = properties.get(string);

			for (Pericia pericia : pericias) {
				if (pericia.getNome().equals(nomeCorreto)) {
					set.add(pericia);
				}
			}
		}

		return set;
	}

	private Nivel createNivel(String string,Classe classe) {

		List<String> valores = Splitter.on("|").splitToList(string);

		// NUMERO DO NIVEL
		String nivelNumero = valores.get(0).replaceAll("[^\\d]","");

		// ATAQUES
		List<String> ataques = Splitter.on("/").splitToList(valores.get(1));

		Nivel nivel = new Nivel(classe,Integer.valueOf(nivelNumero));

		for (String ataqueString : ataques) {
			nivel.addAtaque(new ModificadorAtaque(Integer.valueOf(ataqueString.replaceAll("[^\\d]","")),nivel));
		}

		// FORT
		nivel.setFortitude(Integer.valueOf(valores.get(2).replaceAll("[^\\d]","")));
		// REFLEX
		nivel.setReflexos(Integer.valueOf(valores.get(3).replaceAll("[^\\d]","")));
		// VON
		nivel.setVontade(Integer.valueOf(valores.get(4).replaceAll("[^\\d]","")));

		// PODERES
		String poderesStr = valores.get(5);
		List<String> poderes = Splitter.on(",").trimResults().splitToList(poderesStr);

		for (String poder : poderes) {
			if (StringUtils.isNotEmpty(poder)) {
				nivel.addPoder(poder);
			}
		}

		// MAGIAS
		List<String> magias = valores.subList(6,valores.size() - 1);

		for (int x = 0; x < magias.size(); x++) {

			String valorReal = magias.get(x).replaceAll("[^\\d]","");

			if (!valorReal.isEmpty()) {
				nivel.addMagiasPorDia(x,Integer.valueOf(valorReal));
			}

		}

		return nivel;
	}

	public static void extrairPericiaTest() throws Exception {

		File arquivoPericias = new File(DB + "\\skill.xml");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(arquivoPericias);

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//skills/skill[psionic='No']");
		NodeList nl = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);

		for (int x = 0; x < nl.getLength(); x++) {
			Node node = nl.item(x);

			String nome = "";
			String nomeOriginal = "";
			boolean semTreino = false;
			NomeAtributo atributo = null;

			for (int y = 0; y < node.getChildNodes().getLength(); y++) {

				Node item = node.getChildNodes().item(y);

				if (item.getNodeName().equals("name")) {
					nomeOriginal = item.getTextContent();
				}

				if (item.getNodeName().equals("trained")) {
					semTreino = "No".equals(item.getTextContent());
				}

				if (item.getNodeName().equals("pt")) {
					nome = item.getTextContent();
				}

				if (item.getNodeName().equals("key_ability")) {
					atributo = converte(item.getTextContent());
				}

				properties.put(nomeOriginal,nome);
			}

			if (nome != "") {
				pericias.add(new Pericia(nome,semTreino,atributo));
			}
		}

	}

	@Test
	public void testName() throws Exception {

	}

	@Test
	public void bancoTest() throws Exception {

		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("derby");
		// EntityManager em = emf.createEntityManager();

		// em.getTransaction().begin();
		//
		// for (Pericia pericia : pericias) {
		// em.persist(pericia);
		// }
		//
		// em.persist(getFeiticeiro());
		// em.persist(getPaladino());
		//
		// em.getTransaction().commit();

		getPaladino();

	}

	private static NomeAtributo converte(String value) {

		switch (value) {
			case "Int":
				return NomeAtributo.INTELIGENCIA;
			case "Wis":
				return NomeAtributo.SABEDORIA;
			case "Dex":
				return NomeAtributo.DESTREZA;
			case "Str":
				return NomeAtributo.FORCA;
			case "Cha":
				return NomeAtributo.CARISMA;
			case "Con":
				return NomeAtributo.CONSTITUICAO;
		}

		return null;
	}

}
