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
	
	public HistoricoRecomendacionesService(DB_RecomendacionRepository repository) {
		this.repository = repository;
	}

    public void insertarRecomendacion(DB_Recomendacion recomendacion) {
    	if (recomendacion != null && recomendacion.esValida())
            repository.insert(recomendacion);
    }

    public List<DB_Recomendacion> consultarTodasRecomendaciones() {
        return repository.selectAll();
    } 
   
    public List<DB_Recomendacion> consultarRecomendacionesPorProductosYCriterio(List<String> productos, String criterio) {
        List<DB_Recomendacion> respuesta = consultarTodasRecomendaciones().stream()
            .filter(recomendacion -> recomendacion.getPeticionUsuario().containsAll(productos) && recomendacion.getPeticionUsuario().size() == productos.size() && recomendacion.getCriterioNombre() != null && recomendacion.getCriterioNombre().equals(criterio)).collect(Collectors.toList());
        return respuesta;
    }

}

