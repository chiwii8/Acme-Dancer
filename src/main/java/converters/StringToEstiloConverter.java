
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Estilo;
import repositories.EstiloRepository;

@Component
@Transactional
public class StringToEstiloConverter implements Converter<String, Estilo> {

	EstiloRepository estiloRepository;


	@Autowired
	public StringToEstiloConverter(final EstiloRepository estiloRepository) {
		this.estiloRepository = estiloRepository;
	}

	@Override
	public Estilo convert(final String source) {
		Estilo result;
		int id;

		try {
			id = Integer.valueOf(source);
			result = this.estiloRepository.findById(id);

		} catch (final Throwable e) {
			throw new IllegalArgumentException(e);
		}

		return result;
	}

}
