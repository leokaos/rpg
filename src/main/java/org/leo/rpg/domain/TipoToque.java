package org.leo.rpg.domain;

public class TipoToque extends TipoSituacao {

	@Override
	protected boolean isAdicionaDestreza() {
		return false;
	}

	@Override
	protected boolean isAdicionaArmaduraNatural() {
		return false;
	}

}
