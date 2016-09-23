package org.leo.rpg.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "pericia",schema = "rpgdb")
public class Pericia {

	@Id
	@Column(name = "nome")
	private String nome;

	@Column(name = "usar_sem_treinamento")
	private boolean podeUsarSemTreinamento;

	@Column(name = "atributo")
	@Enumerated(EnumType.STRING)
	private NomeAtributo atributo;

	public Pericia() {
		super();
	}

	public Pericia(String nome,boolean podeUsarSemTreinamento,NomeAtributo atributo) {
		super();
		this.nome = nome;
		this.podeUsarSemTreinamento = podeUsarSemTreinamento;
		this.atributo = atributo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPodeUsarSemTreinamento() {
		return podeUsarSemTreinamento;
	}

	public void setPodeUsarSemTreinamento(boolean podeUsarSemTreinamento) {
		this.podeUsarSemTreinamento = podeUsarSemTreinamento;
	}

	public NomeAtributo getAtributo() {
		return atributo;
	}

	public void setAtributo(NomeAtributo atributo) {
		this.atributo = atributo;
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
		Pericia other = (Pericia) obj;
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
		return new ToStringBuilder(this,SHORT_PREFIX_STYLE).append("Nome",nome).append("podeUsarSemTreinamento",podeUsarSemTreinamento).append("atributo",atributo).toString();
	}
}
