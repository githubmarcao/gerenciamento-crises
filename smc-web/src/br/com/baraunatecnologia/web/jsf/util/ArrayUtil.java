package br.com.baraunatecnologia.web.jsf.util;

import java.util.Arrays;

/**
 * @author Marco
 * 	Funcoes de manipulacao de array
 */
public class ArrayUtil {

	public static <T> boolean contains(final T[] array, final T key) {
		return Arrays.asList(array).contains(key);
	}
}
