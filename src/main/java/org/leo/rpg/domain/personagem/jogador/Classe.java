package org.leo.rpg.domain.personagem.jogador;

import static org.leo.rpg.domain.personagem.PersonagemFunctions.getFunctionNivel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.leo.rpg.domain.personagem.MagiasPorDia;
import org.leo.rpg.domain.personagem.Nivel;
import org.leo.rpg.domain.personagem.NomeAtributo;
import org.leo.rpg.domain.personagem.Pericia;
import org.leo.rpg.domain.util.Dado;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

@Entity
@Table(name = "classe",schema = "rpgdb")
public class Classe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "atributo_para_magia")
	@Enumerated(EnumType.STRING)
	private NomeAtributo atributoParaMagia;

	@Column(name = "dado_vida")
	@Enumerated(EnumType.STRING)
	private Dado dadoVida;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "classe",cascade = CascadeType.ALL)
	private final Set<Nivel> niveis = new TreeSet<>();

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "classe_pericias",schema = "rpgdb",joinColumns = { @JoinColumn(name = "classe_id") },inverseJoinColumns = { @JoinColumn(name = "pericia_id") })
	private Set<Pericia> pericias = new TreeSet<>();

	@Embedded
	private ConfiguracaoPericia configuracaoPericia;

	public Classe() {
		super();
	}

	public Classe(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Set<Nivel> getNiveis() {
		return niveis;
	}

	public Set<Pericia> getPericias() {
		return pericias;
	}

	public void setPericias(Set<Pericia> pericias) {
		this.pericias = pericias;
	}

	public void addNivel(Nivel nivel) {
		niveis.add(nivel);
	}

	public Nivel getNivel(Integer value) {
		return FluentIterable.from(niveis).filter(getFunctionNivel(value)).first().orNull();
	}

	public List<Poder> getPoderesAteNivel(Integer value) {

		List<Poder> poderes = Lists.newArrayList();

		Iterator<Nivel> it = niveis.iterator();

		while (it.hasNext()) {

			Nivel n = it.next();

			if (n.getNivel() > value) {
				break;
			} else {
				poderes.addAll(n.getPoderes());
			}
		}

		return poderes;
	}

	public NomeAtributo getAtributoParaMagia() {
		return atributoParaMagia;
	}

	public void setAtributoParaMagia(NomeAtributo atributoParaMagia) {
		this.atributoParaMagia = atributoParaMagia;
	}

	public Dado getDadoVida() {
		return dadoVida;
	}

	public void setDadoVida(Dado dadoVida) {
		this.dadoVida = dadoVida;
	}

	public ConfiguracaoPericia getConfiguracaoPericia() {
		return configuracaoPericia;
	}

	public void setConfiguracaoPericia(ConfiguracaoPericia configuracaoPericia) {
		this.configuracaoPericia = configuracaoPericia;
	}

	public List<MagiasPorDia> getMagiasPorDia(Integer nivel) {
		return Lists.newArrayList();
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
		Classe other = (Classe) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
