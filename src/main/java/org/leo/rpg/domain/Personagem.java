package org.leo.rpg.domain;

import static org.leo.rpg.domain.PersonagemFunctions.atributo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.leo.rpg.domain.monster.Tamanho;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

@MappedSuperclass
public abstract class Personagem {

	@OneToMany(mappedBy = "personagem",cascade = CascadeType.ALL)
	private final List<Atributo> atributos = Lists.newArrayList();

	private Tamanho tamanho;

	private Integer armaduraNatural;

	public Personagem() {
		super();
	}

	public void addAtributo(Integer valor,NomeAtributo atributo) {
		atributos.add(new Atributo(valor,atributo));
	}

	public Atributo getForca() {
		return getAtributo(NomeAtributo.FORCA);
	}

	public Atributo getDestreza() {
		return getAtributo(NomeAtributo.DESTREZA);
	}

	public Atributo getConstituicao() {
		return getAtributo(NomeAtributo.CONSTITUICAO);
	}

	public Atributo getInteligencia() {
		return getAtributo(NomeAtributo.INTELIGENCIA);
	}

	public Atributo getSabedoria() {
		return getAtributo(NomeAtributo.SABEDORIA);
	}

	public Atributo getCarisma() {
		return getAtributo(NomeAtributo.CARISMA);
	}

	public Atributo getAtributo(final NomeAtributo atributo) {
		return FluentIterable.from(atributos).filter(atributo(atributo)).first().get();
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getArmaduraNatural() {
		return armaduraNatural;
	}

	public void setArmaduraNatural(Integer armaduraNatural) {
		this.armaduraNatural = armaduraNatural;
	}

	public abstract Integer getReflexos();

	public abstract Integer getFortitude();

	public abstract Integer getVontade();

	public abstract Integer getIniciativa();

}
