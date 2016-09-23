package org.leo.rpg.domain;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class AtributosUtil {

	public static Integer gerar() {

		List<Integer> valores = Lists.newArrayList();

		for (int x = 0 ; x < 4 ; x++) {
			valores.add(new Random().nextInt(6) + 1);
		}

		valores.remove(Ordering.natural().min(valores));

		Integer total = 0;

		for (Integer integer : valores) {
			total += integer;
		}

		return total;
	}

}
