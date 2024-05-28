
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curso;
import domain.enumeraciones.CursoNivel;
import domain.enumeraciones.DiaSemana;
import repositories.CursoRepository;

@Service
@Transactional
public class CursoService {

	/// Repositorio propio
	private final CursoRepository cursoRepository;

	/// Servicio de apoyo


	@Autowired
	public CursoService(final CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	/// Metodos base
	public Collection<Curso> findAll() {
		Collection<Curso> result;

		result = this.cursoRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Curso findById(final int id) {
		Assert.isTrue(id != 0);

		Curso result;

		result = this.cursoRepository.findById(id);

		return result;
	}

	public Curso save(final Curso curso) {
		Assert.notNull(curso);
		Curso result;
		result = this.cursoRepository.save(curso);

		return result;
	}

	public void delete(final Curso curso) {
		Assert.notNull(curso);
		Assert.isTrue(curso.getId() != 0);
		Assert.isTrue(this.cursoRepository.exists(curso.getId()));

		this.cursoRepository.delete(curso);
	}

	/// Otros metodos
	public Collection<Curso> findByTitulo(final String titulo) {
		Assert.hasText(titulo);

		Collection<Curso> result;

		result = this.cursoRepository.findByTitulo(titulo);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByFechaInicio(final Date fechaInicio) {
		Assert.notNull(fechaInicio);

		Collection<Curso> result;

		result = this.cursoRepository.findByFechaInicio(fechaInicio);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByDiaSemana(final String diaSemana) {
		Assert.hasText(diaSemana);

		Collection<Curso> result;
		final DiaSemana dia = DiaSemana.valueOf(diaSemana);
		result = this.cursoRepository.findByDiaSemana(dia);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByNivel(final String nivel) {
		Assert.hasText(nivel);

		Collection<Curso> result;
		final CursoNivel cursonivel = CursoNivel.valueOf(nivel);
		result = this.cursoRepository.findByNivel(cursonivel);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findAllByAcademiaId(final int id) {
		Assert.isTrue(id != 0);

		Collection<Curso> result;

		result = this.cursoRepository.findAllByAcademiaId(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findAllByEstiloId(final int id) {
		Assert.isTrue(id != 0);

		Collection<Curso> result;

		result = this.cursoRepository.findAllByEstiloId(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByString(final String buscar) {
		Assert.hasText(buscar);

		Collection<Curso> result;

		result = this.cursoRepository.findByString(buscar);
		Assert.notNull(result);

		return result;
	}

}
