
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.actores.Academia;
import repositories.AcademiaRepository;
import repositories.CursoRepository;
import security.UserAccount;

@Service
@Transactional
public class AcademiaService {

	// Repositorio propio
	AcademiaRepository	academiaRepository;

	/// Repositorios de apoyo
	CursoRepository		cursoRepository;


	@Autowired
	public AcademiaService(final AcademiaRepository academiaRepository, final CursoRepository cursoRepository) {
		this.academiaRepository = academiaRepository;
		this.cursoRepository = cursoRepository;
	}

	/// Operaciones basicas
	public Academia create() {
		Academia result;

		result = new Academia();

		return result;
	}

	public Collection<Academia> findAll() {
		Collection<Academia> result;

		result = this.academiaRepository.findAll();
		Assert.notEmpty(result);

		return result;
	}

	public Academia findById(final int id) {
		Assert.isTrue(id != 0);
		Assert.isTrue(this.academiaRepository.exists(id));
		Academia result;

		result = this.academiaRepository.findById(id);

		return result;
	}

	public Academia save(final Academia academia) {
		Assert.notNull(academia);
		Academia result;

		result = this.academiaRepository.save(academia);

		return result;
	}

	public void delete(final Academia academia) {
		Assert.notNull(academia);
		Assert.isTrue(academia.getId() != 0);
		Assert.isTrue(this.academiaRepository.exists(academia.getId()));

		this.academiaRepository.delete(academia);
	}

	/// Otros
	public Academia findByNombreComercial(final String nombreComercial) {
		Assert.hasLength(nombreComercial);
		Academia result;

		result = this.academiaRepository.findByNombreComercial(nombreComercial);

		return result;
	}

	public Collection<Academia> findByParcialNombreComercial(final String nombreComercial) {
		Assert.hasLength(nombreComercial);
		Collection<Academia> result;

		result = this.academiaRepository.findByParcialNombreComercial(nombreComercial);

		return result;
	}

	public Collection<Academia> findByNombre(final String nombre) {
		Assert.hasText(nombre);
		Collection<Academia> result;

		result = this.academiaRepository.findByNombre(nombre);

		return result;
	}

	public Academia findByCursoId(final int id) {
		Assert.isTrue(id != 0);
		Assert.isTrue(this.cursoRepository.exists(id));
		Academia result;

		result = this.academiaRepository.findByCursoId(id);

		Assert.notNull(result);

		return result;
	}

	public Academia findByUserAccount(final UserAccount userAccount) {
		final Academia result = this.academiaRepository.findByUserAccount(userAccount);
		return result;
	}

}
