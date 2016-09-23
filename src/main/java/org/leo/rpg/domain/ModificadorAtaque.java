package org.leo.rpg.domain;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modificador_ataque",schema = "rpgdb")
public class ModificadorAtaque {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "modificador")
	private Integer modificador;

	@ManyToOne
	@JoinColumn(name = "nivel_id")
	private Nivel nivel;

	public ModificadorAtaque() {
		super();
	}

	public ModificadorAtaque(Integer modificador,Nivel nivel) {
		super();
		this.modificador = modificador;
		this.nivel = nivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public void setModificador(Integer modificador) {
		this.modificador = modificador;
	}

	public Integer getModificador() {
		return modificador;
	}

	@Override
	public String toString() {
		return new DecimalFormat("+#;-#").format(modificador);
	}

}
