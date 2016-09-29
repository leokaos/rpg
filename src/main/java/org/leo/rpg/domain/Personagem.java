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
	protected final List<Atributo> atributos = Lists.newArrayList();

	protected Tamanho tamanho;

	protected Integer armaduraNatural;

	protected String nome;

	protected Integer pontosDeVida;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}

	public void setPontosDeVida(Integer pontosDeVida) {
		this.pontosDeVida = pontosDeVida;
	}

	public boolean isVivo() {
		return pontosDeVida != null && pontosDeVida > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (nome == null ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Personagem other = (Personagem) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	public abstract Integer getReflexos();

	public abstract Integer getFortitude();

	public abstract Integer getVontade();

	public abstract Integer getIniciativa();

}
