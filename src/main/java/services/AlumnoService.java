
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.actores.Alumno;
import repositories.AlumnoRepository;

@Service
@Transactional
public class AlumnoService {

	private final AlumnoRepository alumnoRepository;


	/// Servicios de apoyo

	@Autowired
	public AlumnoService(final AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}

	/// Métodos base
	public Alumno create() {
		Alumno result;

		result = new Alumno();

		return result;
	}

	public Collection<Alumno> findAll() {
		Collection<Alumno> result;

		result = this.alumnoRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Alumno findById(final int id) {
		Assert.isTrue(id != 0);

		final Alumno result = this.alumnoRepository.findById(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Alumno> findByNombre(final String nombre) {
		Assert.hasText(nombre);
		Collection<Alumno> result;

		result = this.alumnoRepository.findByNombre(nombre);

		return result;
	}

	public Alumno save(final Alumno alumno) {
		Assert.notNull(alumno);
		Alumno result;

		result = this.alumnoRepository.save(alumno);

		return result;
	}

	public void delete(final Alumno alumno) {
		Assert.notNull(alumno);
		Assert.isTrue(alumno.getId() != 0);
		Assert.isTrue(this.alumnoRepository.exists(alumno.getId()));

		this.alumnoRepository.delete(alumno);
	}

	public Alumno findByUserAccount(final int userAccountId) {
		Alumno result;
		result = this.alumnoRepository.findByUserAccountId(userAccountId);
		return result;
	}
}
