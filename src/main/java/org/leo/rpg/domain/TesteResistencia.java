package org.leo.rpg.domain;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teste_resistencia",schema = "rpgdb")
public class TesteResistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoResistencia tipo;

	@Column(name = "modificador")
	private Integer modificador;

	@ManyToOne
	@JoinColumn(name = "nivel_id")
	private Nivel nivel;

	public TesteResistencia() {
		super();
	}

	public TesteResistencia(TipoResistencia tipo,Integer modificador) {
		super();
		this.tipo = tipo;
		this.modificador = modificador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoResistencia getTipo() {
		return tipo;
	}

	public void setTipo(TipoResistencia tipo) {
		this.tipo = tipo;
	}

	public Integer getModificador() {
		return modificador;
	}

	public void setModificador(Integer modificador) {
		this.modificador = modificador;
	}

	@Override
	public String toString() {
		return tipo + " = " + new DecimalFormat("+#;-#").format(modificador);
	}

}
