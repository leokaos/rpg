package org.leo.rpg.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DadoTest {

	@Test
	public void test() {

		assertEquals(Dado.parse("d4"),Dado.D_4);
		assertEquals(Dado.parse("d6"),Dado.D_6);
		assertEquals(Dado.parse("d8"),Dado.D_8);
		assertEquals(Dado.parse("d10"),Dado.D_10);
		assertEquals(Dado.parse("d12"),Dado.D_12);
		assertEquals(Dado.parse("d20"),Dado.D_20);
		assertEquals(Dado.parse("d100"),Dado.D_100);
	}

}
