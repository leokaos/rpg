package org.leo.rpg.domain;

import java.util.Scanner;

import org.leo.rpg.domain.monster.Monstro;

public class Main {

	public static void main(String[] args) {

		Encontro encontro = new Encontro();
		encontro.addPersonagem(getPersonagem("pj1",10));
		encontro.addPersonagem(getPersonagem("pj2",18));
		encontro.addPersonagem(getMonstro("m1",3));
		encontro.addPersonagem(getMonstro("m2",5));

		Scanner scanner = new Scanner(System.in);

		for (Personagem personagem : encontro.getPersonagens()) {
			System.out.println(personagem.getNome());
			String rolagem = scanner.nextLine();
			System.out.println("grupo");
			String grupo = scanner.nextLine();
			encontro.addRolagemIniciativa(personagem,Integer.valueOf(rolagem),grupo);
		}

		for (Personagem personagem : encontro) {
			System.out.println(personagem);
		}

		scanner.close();

	}

	private static Personagem getMonstro(String nome,int i) {
		Monstro monstro = new Monstro(nome);
		monstro.setIniciativa(i);
		monstro.setPontosDeVida(10);
		return monstro;
	}

	private static Personagem getPersonagem(String string,Integer dex) {
		PersonagemJogador pj = new PersonagemJogador(string);
		pj.setPontosDeVida(50);
		pj.addAtributo(dex,NomeAtributo.DESTREZA);
		return pj;
	}

}
