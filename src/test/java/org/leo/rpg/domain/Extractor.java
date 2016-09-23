package org.leo.rpg.domain;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.leo.rpg.domain.monster.Monstro;
import org.leo.rpg.domain.monster.Tamanho;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Extractor {

	private static final String FORMATO_DV = "([0-9]*)(d[0-9]*)([+,-][0-9]*)?.*\\(([0-9,\\,]*).*hp\\)";
	private static final String FORMATO_SAVINGS = "Fort.*([+,-][0-9]*),.*Ref.*([+,-][0-9]*),.*Will.*([+,-][0-9]*)";
	private static final String FORMATO_ATRIBUTOS = "Str ([0-9-]*), Dex ([0-9-]*), Con ([0-9-]*), Int ([0-9-]*), Wis ([0-9-]*), Cha ([0-9-]*)";

	private static Map<String,Tamanho> tamanhos;

	private final Set<String> sizes = Sets.newHashSet();

	static {
		tamanhos = Maps.newHashMap();

		tamanhos.put("Tiny",Tamanho.MINUSCULO);
		tamanhos.put("Medium",Tamanho.MEDIO);
		tamanhos.put("Fine",Tamanho.MIUDO);
		tamanhos.put("Large",Tamanho.GRANDE);
		tamanhos.put("Colossal",Tamanho.COLOSSAL);
		tamanhos.put("Diminutive",Tamanho.DIMINUTO);
		tamanhos.put("Gargantuan",Tamanho.ENORME);
		tamanhos.put("Huge",Tamanho.IMENSO);
		tamanhos.put("Colossal+",Tamanho.COLOSSAL);
		tamanhos.put("Small",Tamanho.PEQUENO);
	}

	@Test
	public void monsterTest() throws Exception {

		File db = new File("C:\\Temp\\db\\monster.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document documento = builder.parse(db);

		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList registros = (NodeList) xpath.evaluate("//monsters/monster",documento,XPathConstants.NODESET);

		for (int i = 0 ; i < registros.getLength() ; i++) {
			Node item = registros.item(i);

			try {
				Monstro ms = new Monstro();

				ms.setTamanho(extrairTamanho(item));
				ms.setNome(extrairTexto(item,"name"));

				configurarDadosVida(ms,item);
				configurarIniciativa(ms,item);
				configurarSavings(ms,item);
				configurarAtributos(ms,item);

				System.out.println(ms.getAtributos());

			} catch (Exception ex) {

			}
		}

	}

	private void configurarAtributos(Monstro ms,Node item) throws Exception {
		String atributos = extrairTexto(item,"abilities");

		Matcher matcher = Pattern.compile(FORMATO_ATRIBUTOS).matcher(atributos);

		if (matcher.find()) {

			String str = matcher.group(1);
			if (isStringOnlyDigit(str)) {
				ms.addAtributo(Integer.valueOf(str),NomeAtributo.FORCA);
			}

			String dex = matcher.group(2);
			if (isStringOnlyDigit(dex)) {
				ms.addAtributo(Integer.valueOf(dex),NomeAtributo.DESTREZA);
			}

			String con = matcher.group(3);
			if (isStringOnlyDigit(con)) {
				ms.addAtributo(Integer.valueOf(con),NomeAtributo.CONSTITUICAO);
			}

			String inte = matcher.group(4);
			if (isStringOnlyDigit(inte)) {
				ms.addAtributo(Integer.valueOf(inte),NomeAtributo.INTELIGENCIA);
			}

			String wis = matcher.group(5);
			if (isStringOnlyDigit(wis)) {
				ms.addAtributo(Integer.valueOf(wis),NomeAtributo.SABEDORIA);
			}

			String cha = matcher.group(6);
			if (isStringOnlyDigit(cha)) {
				ms.addAtributo(Integer.valueOf(cha),NomeAtributo.CARISMA);
			}
		}
	}

	private void configurarSavings(Monstro ms,Node item) throws Exception {
		String saves = extrairTexto(item,"saves");

		Matcher matcher = Pattern.compile(FORMATO_SAVINGS).matcher(saves);

		if (matcher.find()) {
			ms.setFortitude(Integer.valueOf(matcher.group(1)));
			ms.setReflexos(Integer.valueOf(matcher.group(2)));
			ms.setVontade(Integer.valueOf(matcher.group(3)));
		}
	}

	private void configurarIniciativa(Monstro ms,Node item) throws Exception {
		String iniciativa = extrairTexto(item,"initiative");

		Matcher matcher = Pattern.compile("([+,-][0-9]*)").matcher(iniciativa);

		if (matcher.find()) {
			ms.setIniciativa(Integer.valueOf(matcher.group(1)));
		}
	}

	private String extrairTexto(Node item,String xpathStr) throws Exception {

		XPath xpath = XPathFactory.newInstance().newXPath();
		Node node = (Node) xpath.evaluate(xpathStr,item,XPathConstants.NODE);

		return node.getTextContent();
	}

	private Tamanho extrairTamanho(Node item) throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		Node node = (Node) xpath.evaluate("size",item,XPathConstants.NODE);
		sizes.add(node.getTextContent());
		return tamanhos.get(node.getTextContent());
	}

	private void configurarDadosVida(Monstro ms,Node item) throws Exception {

		XPath xpath = XPathFactory.newInstance().newXPath();
		Node node = (Node) xpath.evaluate("hit_dice",item,XPathConstants.NODE);

		String hit_dice = node.getTextContent();

		Matcher matcher = Pattern.compile(FORMATO_DV).matcher(hit_dice);

		if (matcher.find()) {

			ms.setQuantidadeDadosVida(Integer.valueOf(matcher.group(1)));
			ms.setDadoVida(Dado.parse(matcher.group(2)));
			ms.setPontosDeVidaBase(matcher.group(3) == null ? 0 : Integer.valueOf(matcher.group(3)));
			ms.setPontosDeVidaMedio(Integer.valueOf(matcher.group(4).replaceAll("[^0-9]","")));

		} else {
			throw new Exception();
		}

	}

	private boolean isStringOnlyDigit(String str) {
		return Pattern.matches("[0-9]*",str);
	}

}
