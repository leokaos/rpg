package org.leo.rpg.domain;

import static org.leo.rpg.domain.PersonagemFunctions.extraiPericiasDeNiveis;
import static org.leo.rpg.domain.PersonagemFunctions.getClasseFromNivel;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@Table(name = "personagem",schema = "rpgdb")
public class PersonagemJogador extends Personagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "pontos_de_vida")
	private Integer pontosDeVida = 0;

	@OneToMany(mappedBy = "personagem",cascade = CascadeType.ALL)
	private final List<RankPericia> pericias = Lists.newArrayList();

	@Transient
	private final List<Arma> armas = Lists.newArrayList();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "personagem_niveis",schema = "rpgdb",joinColumns = {@JoinColumn(name = "personagem_id")},inverseJoinColumns = {@JoinColumn(name = "nivel_id")})
	private final Set<Nivel> niveis = Sets.newHashSet();

	public PersonagemJogador() {
		super();
	}

	public PersonagemJogador(String nome) {
		super();
		this.nome = nome;
	}

	public Integer getPontosDeVida() {
		return pontosDeVida;
	}

	public void addPericia(Pericia pericia,Integer rank) throws RPGException {

		checkRankPericia(rank);

		checkPontosSobrando(rank);

		pericias.add(new RankPericia(rank,pericia));
	}

	private void checkPontosSobrando(Integer rank) throws RPGException {

		Integer totalPontosPericia = getTotalPontosPericia();

		Integer totalRank = 0;

		for (RankPericia rankPericia : pericias) {
			totalRank += rankPericia.getRank();
		}

		if (rank + totalRank > totalPontosPericia) {
			throw new RPGException("Não há pontos suficientes: " + (totalPontosPericia - totalRank));
		}

	}

	protected void checkRankPericia(Integer rank) throws RPGException {

		Integer nivelTotal = 0;

		for (Nivel nivel : niveis) {
			nivelTotal += nivel.getNivel();
		}

		if (nivelTotal + 3 < rank) {
			throw new RPGException("Rank não permitido para o seu nível.");
		}

	}

	public void addNivel(Classe classe,Integer valorNivel) {

		Nivel nivel = classe.getNivel(valorNivel);

		if (!niveis.contains(nivel)) {
			pontosDeVida += nivel.getPontosDeVidaFull(this);
			niveis.add(nivel);
		}
	}

	public void addArma(Arma arma) {
		armas.add(arma);
	}

	// SAVING THROWS
	@Override
	public Integer getReflexos() {

		Integer valorTotal = 0;

		for (Nivel nivel : niveis) {
			valorTotal += nivel.getReflexos().getModificador();
		}

		return valorTotal + getDestreza().getModifier();
	}

	@Override
	public Integer getFortitude() {

		Integer valorTotal = 0;

		for (Nivel nivel : niveis) {
			valorTotal += nivel.getFortitude().getModificador();
		}

		return valorTotal + getConstituicao().getModifier();
	}

	@Override
	public Integer getVontade() {

		Integer valorTotal = 0;

		for (Nivel nivel : niveis) {
			valorTotal += nivel.getVontade().getModificador();
		}

		return valorTotal + getInteligencia().getModifier();
	}

	// PERICIAS
	public Integer getTotalPericia(Pericia pericia) throws RPGException {

		Optional<RankPericia> promisseRankPericia = FluentIterable.from(pericias).filter(PersonagemFunctions.pericia(pericia)).first();

		if (promisseRankPericia.isPresent()) {
			return promisseRankPericia.get().getBonusTotal(this);
		} else if (pericia.isPodeUsarSemTreinamento()) {
			return getAtributo(pericia.getAtributo()).getModifier();
		}

		throw new RPGException("Pericia naum pode ser usada sem treinamento!");
	}

	// ATAQUES
	public List<Ataque> getAtaques(Arma arma) {

		List<Ataque> ataques = Lists.newArrayList();

		for (Nivel nivel : niveis) {
			ataques.addAll(nivel.getAtaques(this,arma));
		}

		return ataques;
	}

	public List<Poder> getPoderes() {

		List<Poder> poderes = Lists.newArrayList();

		for (Nivel nivel : niveis) {
			poderes.addAll(nivel.getClasse().getPoderesAteNivel(nivel.getNivel()));
		}

		return poderes;
	}

	public Integer getQuantidadeMagiasPorCirculo(int circulo,Classe classe) {

		Optional<Nivel> nivel = FluentIterable.from(niveis).filter(getClasseFromNivel(classe)).first();

		if (nivel.isPresent()) {
			return nivel.get().getQuantidadeMagiasPorCirculo(circulo) + getAtributo(classe.getAtributoParaMagia()).getModifier();
		}

		return 0;
	}

	public Integer getTotalPontosPericia() {

		Integer total = 0;

		for (Nivel nivel : niveis) {
			total += nivel.getClasse().getConfiguracaoPericia().getTotal(getInteligencia(),nivel.getNivel());
		}

		return total;
	}

	public Set<Pericia> getPericiasPorClasses() {
		return FluentIterable.from(niveis).transformAndConcat(extraiPericiasDeNiveis()).toSet();
	}

	public RankPericia getRank(Pericia pericia) {
		return FluentIterable.from(pericias).filter(PersonagemFunctions.rankPericia(pericia)).first().orNull();
	}

	@Override
	public Integer getIniciativa() {
		return getDestreza().getModifier();
	}

}
