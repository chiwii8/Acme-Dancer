
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mysql.cj.xdevapi.JsonArray;

import security.UserAccount;

@Component
@Transactional
public class UserAccountToStringConverter implements Converter<UserAccount, String> {

	@Override
	public String convert(final UserAccount source) {
		String result;
		StringBuilder builder;
		final JsonArray json;
		if (source == null)
			result = null;
		else
			try {
				builder = new StringBuilder(source.getId());
				builder.append("|");
				builder.append(source.getVersion());
				builder.append("|");
				builder.append(source.getUsername());
				builder.append("|");
				builder.append(source.getPassword());
				builder.append("|");
				builder.append(source.getAuthorities());
				builder.append("|");

			} catch (final Exception e) {
				// TODO: handle exception
			}
		return null;
	}

}
