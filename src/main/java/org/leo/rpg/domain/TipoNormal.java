package org.leo.rpg.domain;

public class TipoNormal extends TipoSituacao {

	@Override
	protected boolean isAdicionaArmaduraNatural() {
		return true;
	}

	@Override
	protected boolean isAdicionaDestreza() {
		return true;
	}

	@Override
	protected boolean isAdicionaArmadura() {
		return true;
	}

}
