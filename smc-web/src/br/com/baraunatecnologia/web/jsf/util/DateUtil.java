/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baraunatecnologia.web.jsf.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.convert.ConverterException;

/**
 * @author Marco
 */
public class DateUtil {

	public static final String PATTERN_PADRAO = "dd/MM/yyyy HH:mm:ss";

	public static String timestampToString(Date data) {
		if (data != null) {
			Format formatter = new SimpleDateFormat(DateUtil.PATTERN_PADRAO);
			return formatter.format((Date) data);
		} else {
			return null;
		}
	}

	public static Date stringToTimestamp(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.PATTERN_PADRAO);
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			throw new ConverterException(
					"Falha ao converter de String para Data.");
		}
	}
}
