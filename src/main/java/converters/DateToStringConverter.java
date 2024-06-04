
package converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DateToStringConverter implements Converter<Date, String> {

	@Override
	public String convert(final Date source) {
		String result;
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		try {
			result = format.format(source);
		} catch (final Exception e) {
			result = "";
		}
		return result;
	}

}
