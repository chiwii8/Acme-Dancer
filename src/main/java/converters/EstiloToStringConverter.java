
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Estilo;

@Component
@Transactional
public class EstiloToStringConverter implements Converter<Estilo, String> {

	@Override
	public String convert(final Estilo source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());
		return result;
	}

}
