package org.leo.rpg.domain.encontro;

public class TipoSurpreso extends TipoSituacao {

	@Override
	public boolean isAdicionaArmaduraNatural() {
		return true;
	}

	@Override
	public boolean isAdicionaDestreza() {
		return false;
	}

	@Override
	public boolean isAdicionaArmadura() {
		return true;
	}

}
