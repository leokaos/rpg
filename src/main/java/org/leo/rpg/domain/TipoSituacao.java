package org.leo.rpg.domain;

public abstract class TipoSituacao {

	public static final TipoSituacao TOQUE = new TipoToque();
	public static final TipoSituacao NORMAL = new TipoNormal();
	public static final TipoSituacao SURPRESO = new TipoSurpreso();

	protected abstract boolean isAdicionaArmadura();

	protected abstract boolean isAdicionaArmaduraNatural();

	protected abstract boolean isAdicionaDestreza();

}
