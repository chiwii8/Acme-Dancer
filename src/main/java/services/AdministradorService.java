package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comentario;
import domain.Curso;
import domain.Solicitud;
import domain.Tutorial;
import domain.actores.Academia;
import domain.actores.Actor;
import domain.actores.Administrador;
import repositories.AdministradorRepository;

@Service
@Transactional
public class AdministradorService {

    /// Metricas que realiza
    private enum Metrica {
        MAXIMO, MINIMO, MEDIA, DESVIACION_TIPICA
    }

    /// Repositorio propio
    AdministradorRepository administradorRepository;

    /// Servicios de apoyo
    AcademiaService academiaService;
    CursoService cursoService;
    TutorialService tutorialService;
    ActorService actorService;

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository, AcademiaService academiaService,
            CursoService cursoService, TutorialService tutorialService, ActorService actorService) {
        this.administradorRepository = administradorRepository;
        this.academiaService = academiaService;
        this.cursoService = cursoService;
        this.tutorialService = tutorialService;
        this.actorService = actorService;
    }

    /// METODOS BASICOS
    public Administrador create() {
        Administrador result;

        result = new Administrador();

        return result;
    }

    public Collection<Administrador> findAll() {
        Collection<Administrador> result;

        result = administradorRepository.findAll();

        Assert.notEmpty(result);

        return result;
    }

    public Administrador findById(int id) {
        Assert.isTrue(id != 0);
        Assert.isTrue(administradorRepository.exists(id));

        Administrador result;

        result = administradorRepository.findById(id);
        Assert.notNull(result);

        return result;
    }

    public Administrador save(Administrador administrador) {
        Assert.notNull(administrador);
        Administrador result;

        result = administradorRepository.save(administrador);

        return result;
    }

    public void delete(Administrador administrador) {
        Assert.notNull(administrador);
        Assert.isTrue(administrador.getId() != 0);
        Assert.isTrue(administradorRepository.exists(administrador.getId()));

        administradorRepository.delete(administrador);
    }

    /// METODOS EXTRAS

    public Map<String, Map<String, Double>> calcularEstadisticas() {
        Map<String, Map<String, Double>> result = new HashMap<>();

        /// Tier C
        result.put("CursoPorAcademia", calcularCursosPorAcademia());
        result.put("SolicitudesPorCurso", calcularSolicitudesPorCurso());

        /// Tier B
        result.put("TutorialesPorAcademia", calcularTutorialesPorAcademia());
        result.put("TutorialesMostrados", calcularTutorialesMostrados());

        /// Tier C
        result.put("ComentariosPorActor", calcularNumeroMedioComentariosPorActor());
        result.put("SuscriptoresPorActor", calcularNumeroMedioComentariosPorSuscriptor());

        return result;
    }

    /// METODOS PARA EL CALCULO DE ESTADISTICAS

    // ********************************** TIER C **********************************

    private Map<String, Double> calcularCursosPorAcademia() {
        Map<String, Double> result = new HashMap<>();
        Collection<Academia> academias;
        List<Integer> conteoCursos = Collections.synchronizedList(new ArrayList<>());

        /// Obtenemos todas las academias
        academias = academiaService.findAll();

        /// Verificamos si es nulo o esta vacio
        if (academias == null || academias.isEmpty()) {
            return result;
        }

        /// Realizamos un conteo del número de cursos
        for (Academia academia : academias) {
            Collection<Curso> cursos = academia.getCursos();
            if (cursos != null) {
                conteoCursos.add(cursos.size());
            } else {
                conteoCursos.add(0);
            }
        }

        /// Realizamos los cálculos necesarios
        double media = calcularMedia(conteoCursos);
        double max = calcularMaximo(conteoCursos);
        double min = calcularMinimo(conteoCursos);
        double desviacionTipica = calcularDesviacionTipica(conteoCursos);

        /// Los insertamos en el diccionario correspondiente
        result.put(Metrica.MEDIA.toString(), media);
        result.put(Metrica.MAXIMO.toString(), max);
        result.put(Metrica.MINIMO.toString(), min);
        result.put(Metrica.DESVIACION_TIPICA.toString(), desviacionTipica);

        return result;

    }

    private Map<String, Double> calcularSolicitudesPorCurso() {
        Map<String, Double> result = new HashMap<>();
        Collection<Curso> cursos;
        List<Integer> conteoSolicitudes = Collections.synchronizedList(new ArrayList<>());

        /// Obtenemos todas los cursos
        cursos = cursoService.findAll();

        /// Verificamos si es nulo o esta vacio
        if (cursos == null || cursos.isEmpty()) {
            return result;
        }

        for (Curso curso : cursos) {
            Collection<Solicitud> solicitudes = curso.getSolicitudes();
            if (solicitudes != null) {
                conteoSolicitudes.add(solicitudes.size());
            } else {
                conteoSolicitudes.add(0);
            }
        }

        /// Realizamos los cálculos necesarios
        double media = calcularMedia(conteoSolicitudes);
        double max = calcularMaximo(conteoSolicitudes);
        double min = calcularMinimo(conteoSolicitudes);
        double desviacionTipica = calcularDesviacionTipica(conteoSolicitudes);

        /// Los insertamos en el diccionario correspondiente
        result.put(Metrica.MEDIA.toString(), media);
        result.put(Metrica.MAXIMO.toString(), max);
        result.put(Metrica.MINIMO.toString(), min);
        result.put(Metrica.DESVIACION_TIPICA.toString(), desviacionTipica);

        return result;
    }

    // ********************************** TIER B **********************************

    private Map<String, Double> calcularTutorialesPorAcademia() {
        Map<String, Double> result = new HashMap<>();
        Collection<Academia> academias;
        List<Integer> conteoTutoriales = Collections.synchronizedList(new ArrayList<>());

        /// Obtenemos todas los cursos
        academias = academiaService.findAll();

        /// Verificamos si es nulo o no hay academias
        if (academias == null || academias.isEmpty()) {
            return result;
        }

        /// Verificamos si es null o esta vacio
        for (Academia academia : academias) {
            Collection<Tutorial> tutoriales = academia.getTutoriales();
            if (tutoriales != null) {
                conteoTutoriales.add(tutoriales.size());
            } else {
                conteoTutoriales.add(0);
            }
        }

        /// Realizamos los calculos necesarios
        double media = calcularMedia(conteoTutoriales);
        double max = calcularMaximo(conteoTutoriales);
        double min = calcularMinimo(conteoTutoriales);

        /// Los insertamos en el diccionario correspondiente
        result.put(Metrica.MEDIA.toString(), media);
        result.put(Metrica.MAXIMO.toString(), max);
        result.put(Metrica.MINIMO.toString(), min);

        return result;
    }

    private Map<String, Double> calcularTutorialesMostrados() {
        Map<String, Double> result = new HashMap<>();
        Collection<Tutorial> tutoriales;
        List<Integer> visualizacionesTutorial = Collections.synchronizedList(new ArrayList<>());

        /// Obtenemos todas los cursos
        tutoriales = tutorialService.findAll();

        /// Verificamos si es nulo o esta vacio
        if (tutoriales == null || tutoriales.isEmpty()) {
            return result;
        }

        /// Verificamos si es null o esta vacio
        for (Tutorial tutorial : tutoriales) {
            int visualizaciones = tutorial.getVisualizaciones();
            visualizacionesTutorial.add(visualizaciones);
        }

        /// Realizamos los calculos necesarios
        double media = calcularMedia(visualizacionesTutorial);
        double max = calcularMaximo(visualizacionesTutorial);
        double min = calcularMinimo(visualizacionesTutorial);

        /// Los insertamos en el diccionario correspondiente
        result.put(Metrica.MEDIA.toString(), media);
        result.put(Metrica.MAXIMO.toString(), max);
        result.put(Metrica.MINIMO.toString(), min);

        return result;
    }

    // ********************************** TIER A **********************************

    private Map<String, Double> calcularNumeroMedioComentariosPorActor() {
        Map<String, Double> result = new HashMap<>();
        Collection<Actor> actores;
        List<Integer> conteoComentarios = Collections.synchronizedList(new ArrayList<>());

        /// Obtenemos todas los cursos
        actores = actorService.findAll();

        /// Verificamos si es nulo o esta vacio
        if (actores == null || actores.isEmpty()) {
            return result;
        }

        /// Verificamos si es null o esta vacio
        for (Actor actor : actores) {
            Collection<Comentario> comentarios = actor.getComentarios();
            if (comentarios != null) {
                conteoComentarios.add(comentarios.size());
            } else {
                conteoComentarios.add(0);
            }

        }

        /// Realizamos los calculos necesarios
        double media = (int) calcularMedia(conteoComentarios);

        /// Los insertamos en el diccionario correspondiente
        result.put(Metrica.MEDIA.toString(), media);

        return result;
    }

    private Map<String, Double> calcularNumeroMedioComentariosPorSuscriptor() {
        Map<String, Double> result = new HashMap<>();
        Collection<Actor> actores;
        List<Integer> conteoSuscriptores = Collections.synchronizedList(new ArrayList<>());

        /// Obtenemos todas los cursos
        actores = actorService.findAll();

        /// Verificamos si es nulo o esta vacio
        if (actores == null || actores.isEmpty()) {
            return result;
        }

        /// Verificamos si es null o esta vacio
        for (Actor actor : actores) {
            Collection<Actor> suscriptores = actor.getSuscriptores();
            if (suscriptores != null) {
                conteoSuscriptores.add(suscriptores.size());
            } else {
                conteoSuscriptores.add(0);
            }

        }

        /// Realizamos los calculos necesarios
        double media = (int) calcularMedia(conteoSuscriptores);

        /// Los insertamos en el diccionario correspondiente
        result.put(Metrica.MEDIA.toString(), media);

        return result;
    }

    // CALCULO DE METRICAS
    private double calcularMedia(List<Integer> conteo) {
        double result;

        double sum = conteo.stream()
                .mapToInt(Integer::intValue)
                .sum();
        double numElementos = conteo.size();

        try {
            result = sum / numElementos;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    private int calcularMinimo(List<Integer> conteo) {
        int result;

        result = conteo.stream()
                .mapToInt(Integer::intValue)
                .min()
                .getAsInt();

        return result;
    }

    private int calcularMaximo(List<Integer> conteo) {
        int result;

        result = conteo.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

        return result;
    }

    @SuppressWarnings("unused")
    private double calcularDesviacionTipica(List<Integer> conteo, double media) {
        double sumaDiferenciasCuadrado = 0;
        int numElementos = conteo.size();

        for (int elemento : conteo) {
            double calculoaux = elemento - media;
            sumaDiferenciasCuadrado += Math.pow(calculoaux, 2);
        }

        double varianza = sumaDiferenciasCuadrado / numElementos;

        return Math.sqrt(varianza);
    }

    private double calcularDesviacionTipica(List<Integer> conteo) {
        double media = calcularMedia(conteo);
        double sumaDiferenciasCuadrado = 0;
        int numElementos = conteo.size();

        for (int elemento : conteo) {
            double calculoaux = elemento - media;
            sumaDiferenciasCuadrado += Math.pow(calculoaux, 2);
        }

        double varianza = sumaDiferenciasCuadrado / numElementos;

        return Math.sqrt(varianza);
    }
}
