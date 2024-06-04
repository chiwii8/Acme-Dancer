
package converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class StringToDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(final String source) {
		Date result;

		try {
			final SimpleDateFormat format = new SimpleDateFormat("dd/mm/YYYY");
			result = format.parse(source);
		} catch (final ParseException e) {
			result = null;
		}

		return result;
	}

}
