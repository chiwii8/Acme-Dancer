
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Curso;

@Component
@Transactional
public class CursoToStringConverter implements Converter<Curso, String> {

	@Override
	public String convert(final Curso source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());
		return result;
	}

}
