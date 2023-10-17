package service;

import java.util.List;
import java.util.stream.Collectors;

import model.DB_Recomendacion;
import repository.DB_RecomendacionRepository;

public class HistoricoRecomendacionesService{
   
	private DB_RecomendacionRepository repository;
	
	public HistoricoRecomendacionesService() {
		repository = new DB_RecomendacionRepository();
	}

    public void insertarRecomendacion(DB_Recomendacion recomendacion) {
        System.out.println("[Historial_Recomendaciones] - Log: Se procede a realizar un INSERT en db_recomendacion de: " + recomendacion.toString());
        repository.insert(recomendacion);
        System.out.println("[Historial_Recomendaciones] - Log: INSERT Exitoso");
    }

    public List<DB_Recomendacion> consultarTodasRecomendaciones() {
        return repository.selectAll();
    } 
   
    public List<DB_Recomendacion> consultarRecomendacionesPorProductosYCriterio(List<String> productos, String criterio) {
        System.out.println("[Historial_Recomendaciones] - Log: Se procede a realizar un SELECT en db_recomendacion para buscar los productos: " + productos.toString() + " y el criterio: " + criterio);
        List<DB_Recomendacion> respuesta = consultarTodasRecomendaciones().stream()
            .filter(recomendacion -> recomendacion.getPeticionUsuario().containsAll(productos) && recomendacion.getPeticionUsuario().size() == productos.size() && recomendacion.getCriterioNombre() != null && recomendacion.getCriterioNombre().equals(criterio)).collect(Collectors.toList());
        System.out.println("[Historial_Recomendaciones] - Log: Se encontraron las recomendaciones: " + respuesta.toString());
        return respuesta;
    }

	public void setRepository(DB_RecomendacionRepository repository) {
		this.repository = repository;
	}
}

