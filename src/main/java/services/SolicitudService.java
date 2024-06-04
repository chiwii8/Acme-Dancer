
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curso;
import domain.Solicitud;
import domain.actores.Alumno;
import domain.enumeraciones.SolicitudEstado;
import repositories.SolicitudRepository;

@Service
@Transactional
public class SolicitudService {

	/// Repositorio propio
	SolicitudRepository	solicitudRepository;

	/// Servicios de apoyo
	AlumnoService		alumnoService;
	CursoService		cursoService;


	@Autowired
	public SolicitudService(final SolicitudRepository solicitudRepository, final AlumnoService alumnoService, final CursoService cursoService) {
		this.solicitudRepository = solicitudRepository;
		this.alumnoService = alumnoService;
		this.cursoService = cursoService;
	}

	/// Metodos base
	public Collection<Solicitud> findAll() {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Solicitud findById(final int id) {
		Assert.isTrue(id != 0);

		Solicitud result;

		result = this.solicitudRepository.findById(id);

		return result;
	}

	public Solicitud create() {
		Solicitud result;

		result = new Solicitud();

		return result;
	}

	public Solicitud save(final Solicitud solicitud) {
		Assert.notNull(solicitud);
		Solicitud result;
		result = this.solicitudRepository.save(solicitud);

		return result;
	}

	public void delete(final Solicitud solicitud) {
		Assert.notNull(solicitud);
		Assert.isTrue(solicitud.getId() != 0);
		Assert.isTrue(this.solicitudRepository.exists(solicitud.getId()));

		this.solicitudRepository.delete(solicitud);
	}

	/// Otros
	public Collection<Solicitud> findAllByAlumnoId(final int id) {
		final Alumno alumno = this.alumnoService.findById(id);

		Collection<Solicitud> result;
		result = this.solicitudRepository.findAllByAlumnoId(alumno.getId());

		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAllByCursoId(final int id) {
		final Curso curso = this.cursoService.findById(id);

		Collection<Solicitud> result;
		result = this.solicitudRepository.findAllByCursoId(curso.getId());

		Assert.notNull(result);
		return result;
	}

	public Collection<Solicitud> findAllByCursoIdAndEstado(final int id, final SolicitudEstado estado) {
		Collection<Solicitud> solicitud;

		solicitud = this.solicitudRepository.findAllByCursoIdAndState(id, estado);

		return solicitud;
	}

}
