
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comentario;
import repositories.ComentarioRepository;

@Service
@Transactional
public class ComentarioService {

	/// Repositorio propio
	private final ComentarioRepository	comentarioRepository;

	/// Servicio apoyo
	private final ActorService			actorService;


	@Autowired
	public ComentarioService(final ComentarioRepository comentarioRepository, final ActorService actorService) {
		this.comentarioRepository = comentarioRepository;
		this.actorService = actorService;
	}

	/// Métodos base
	public Collection<Comentario> findAll() {
		Collection<Comentario> result;

		result = this.comentarioRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Comentario findById(final int id) {
		Assert.isTrue(id != 0);

		Comentario result;

		result = this.comentarioRepository.findById(id);

		return result;
	}

	public Comentario save(final Comentario comentario) {
		Assert.notNull(comentario);
		Comentario result;
		result = this.comentarioRepository.save(comentario);

		return result;
	}

	public void delete(final Comentario comentario) {
		Assert.notNull(comentario);
		Assert.isTrue(comentario.getId() != 0);
		Assert.isTrue(this.comentarioRepository.exists(comentario.getId()));

		this.comentarioRepository.delete(comentario);
	}

	//// Otros metodos
	public Collection<Comentario> findByFechaRealizacion(final Date fechaRealizacion) {
		Assert.notNull(fechaRealizacion);

		Collection<Comentario> result;
		result = this.comentarioRepository.findByFechaRealizacion(fechaRealizacion);
		Assert.notNull(result);

		return result;
	}

	public Collection<Comentario> findAllComentariosByActorId(final int idActor) {
		Assert.isTrue(idActor != 0);

		/// Realizamos una busqueda por id para verificar que existe dicho cliente
		this.actorService.findById(idActor);

		final Collection<Comentario> result = this.comentarioRepository.findAllComentariosByActorId(idActor);
		Assert.notNull(result);

		return result;
	}
}
