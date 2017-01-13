package org.leo.rpg.domain.encontro;

public abstract class TipoSituacao {

	public static final TipoSituacao TOQUE = new TipoToque();
	public static final TipoSituacao NORMAL = new TipoNormal();
	public static final TipoSituacao SURPRESO = new TipoSurpreso();

	public abstract boolean isAdicionaArmadura();

	public abstract boolean isAdicionaArmaduraNatural();

	public abstract boolean isAdicionaDestreza();

}
