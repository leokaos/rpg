package org.leo.rpg.domain.encontro;

public class TipoNormal extends TipoSituacao {

	@Override
	public boolean isAdicionaArmaduraNatural() {
		return true;
	}

	@Override
	public boolean isAdicionaDestreza() {
		return true;
	}

	@Override
	public boolean isAdicionaArmadura() {
		return true;
	}

}
