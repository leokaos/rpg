package org.leo.rpg.domain;

public class TipoSurpreso extends TipoSituacao {

	@Override
	protected boolean isAdicionaArmaduraNatural() {
		return true;
	}

	@Override
	protected boolean isAdicionaDestreza() {
		return false;
	}

}
