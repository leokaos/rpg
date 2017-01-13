package org.leo.rpg.domain.encontro;

public class TipoToque extends TipoSituacao {

	@Override
	public boolean isAdicionaDestreza() {
		return false;
	}

	@Override
	public boolean isAdicionaArmaduraNatural() {
		return false;
	}

	@Override
	public boolean isAdicionaArmadura() {
		return false;
	}

}
