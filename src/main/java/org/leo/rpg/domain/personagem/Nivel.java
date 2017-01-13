package org.leo.rpg.domain.personagem;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.leo.rpg.domain.equipamento.Arma;
import org.leo.rpg.domain.personagem.jogador.Classe;
import org.leo.rpg.domain.personagem.jogador.ModificadorAtaque;
import org.leo.rpg.domain.personagem.jogador.Poder;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

@Entity
@Table(name = "nivel",schema = "rpgdb")
public class Nivel implements Comparable<Nivel> {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "classe_id")
	private Classe classe;

	@Column(name = "nivel")
	private Integer nivel;

	@OneToMany(mappedBy = "nivel",cascade = CascadeType.ALL)
	private List<ModificadorAtaque> modificadorAtaques = Lists.newArrayList();

	@OneToMany(mappedBy = "nivel",cascade = CascadeType.ALL)
	private List<TesteResistencia> testes = Lists.newArrayList();

	@OneToMany(mappedBy = "nivel",cascade = CascadeType.ALL)
	private List<Poder> poderes = Lists.newArrayList();

	@OneToMany(mappedBy = "nivel",cascade = CascadeType.ALL)
	private List<MagiasPorDia> magias = Lists.newArrayList();

	public Nivel() {
		super();
	}

	public Nivel(Classe classe, Integer nivel) {
		super();
		this.classe = classe;
		this.nivel = nivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public List<ModificadorAtaque> getModificadorAtaques() {
		return modificadorAtaques;
	}

	public void setModificadorAtaques(List<ModificadorAtaque> modificadorAtaques) {
		this.modificadorAtaques = modificadorAtaques;
	}

	public List<TesteResistencia> getTestes() {
		return testes;
	}

	public void setTestes(List<TesteResistencia> testes) {
		this.testes = testes;
	}

	public List<MagiasPorDia> getMagias() {
		return magias;
	}

	public void setMagias(List<MagiasPorDia> magias) {
		this.magias = magias;
	}

	public void setPoderes(List<Poder> poderes) {
		this.poderes = poderes;
	}

	public void addAtaque(ModificadorAtaque ataque) {
		modificadorAtaques.add(ataque);
	}

	public void addPoder(String poder) {
		poderes.add(new Poder(poder,this));
	}

	public TesteResistencia getFortitude() {
		return getTestePorTipo(TipoResistencia.FORTITUDE);
	}

	public void setFortitude(Integer modificador) {
		testes.add(new TesteResistencia(TipoResistencia.FORTITUDE,modificador));
	}

	public TesteResistencia getReflexos() {
		return getTestePorTipo(TipoResistencia.REFLEXOS);
	}

	public void setReflexos(Integer modificador) {
		testes.add(new TesteResistencia(TipoResistencia.REFLEXOS,modificador));
	}

	public TesteResistencia getVontade() {
		return getTestePorTipo(TipoResistencia.VONTADE);
	}

	public void setVontade(Integer modificador) {
		testes.add(new TesteResistencia(TipoResistencia.VONTADE,modificador));
	}

	private TesteResistencia getTestePorTipo(TipoResistencia tipo) {

		List<TesteResistencia> filtrado = FluentIterable.from(testes).filter(predicateTeste(tipo)).toList();

		if (filtrado.isEmpty()) {
			return null;
		} else {
			return filtrado.get(0);
		}

	}

	private Predicate<TesteResistencia> predicateTeste(final TipoResistencia tipo) {
		return new Predicate<TesteResistencia>() {

			@Override
			public boolean apply(TesteResistencia input) {
				return tipo.equals(input.getTipo());
			}

		};
	}

	public List<Ataque> getAtaques(Personagem personagem,Arma arma) {

		List<Ataque> ataques = Lists.newArrayList();

		for (ModificadorAtaque modificadorAtaque : modificadorAtaques) {
			ataques.add(new Ataque(arma.getModificarTotal(personagem) + modificadorAtaque.getModificador()));
		}

		return ataques;
	}

	public List<Poder> getPoderes() {
		return poderes;
	}

	public Integer getPontosDeVida(Personagem personagem) {

		Integer total = 0;

		for (int x = 0; x < nivel; x++) {
			total += classe.getDadoVida().getValor() + personagem.getConstituicao().getModifier();
		}

		return total;
	}

	public Integer getPontosDeVidaFull(Personagem personagem) {
		return nivel * (classe.getDadoVida().getMaximo() + personagem.getConstituicao().getModifier());
	}

	public void addMagiasPorDia(Integer circulo,Integer quantidade) {
		magias.add(new MagiasPorDia(this,circulo,quantidade));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (classe == null ? 0 : classe.hashCode());
		result = prime * result + (nivel == null ? 0 : nivel.hashCode());
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
		Nivel other = (Nivel) obj;
		if (classe == null) {
			if (other.classe != null) {
				return false;
			}
		} else if (!classe.equals(other.classe)) {
			return false;
		}
		if (nivel == null) {
			if (other.nivel != null) {
				return false;
			}
		} else if (!nivel.equals(other.nivel)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Nivel o) {
		return nivel - o.nivel;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(classe.getNome()).append(" ").append(nivel);
		builder.append(" ").append(getFortitude());
		builder.append(" ").append(getReflexos());
		builder.append(" ").append(getVontade());

		builder.append(" ");

		Iterator<ModificadorAtaque> iterator = modificadorAtaques.iterator();

		while (iterator.hasNext()) {
			ModificadorAtaque ataque = iterator.next();
			builder.append(ataque);

			if (iterator.hasNext()) {
				builder.append("/");
			}
		}

		return builder.toString();
	}

	public Integer getQuantidadeMagiasPorCirculo(int circulo) {

		for (MagiasPorDia magiasPorDia : magias) {
			if (magiasPorDia.getCirculo() == circulo) {
				return magiasPorDia.getQuantidade();
			}
		}

		return null;
	}

}
