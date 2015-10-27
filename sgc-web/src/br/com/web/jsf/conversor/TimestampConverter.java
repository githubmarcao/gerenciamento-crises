package br.com.web.jsf.conversor;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

import br.com.web.jsf.util.DateUtil;

@FacesConverter("converteTimestamp")
public class TimestampConverter extends DateTimeConverter implements Converter {

	public TimestampConverter() {
		setPattern(DateUtil.PATTERN_PADRAO);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim() == "") {
			return null;
		}

		if (value != null && value.length() != getPattern().length()) {
			throw new ConverterException("Formato inválido da Data.");
		}

		return (Object) DateUtil.stringToTimestamp(value);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			return DateUtil.timestampToString((Date) value);
		} else {
			return "";
		}
	}
}
