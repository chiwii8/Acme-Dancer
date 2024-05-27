
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.actores.Actor;

@Component
@Transactional
public class ActorToStringConverter implements Converter<Actor, String> {

	@Override
	public String convert(final Actor source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}

}
