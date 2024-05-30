
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import security.Authority;

@Component
@Transactional
class AuthorityToStringConverter implements Converter<Authority, String> {

	@Override
	public String convert(final Authority source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getAuthority());

		return result;
	}

}
