package service;

import java.util.List;
import java.util.stream.Collectors;

import model.DB_Recomendacion;
import repository.DB_RecomendacionRepository;

public class DB_RecomendacionService {
   
	private DB_RecomendacionRepository repository;

    public DB_RecomendacionService(DB_RecomendacionRepository repository) {
        this.repository = repository;
    }

    public void insertarRecomendacion(DB_Recomendacion recomendacion) {
        System.out.println("[Historial_Recomendaciones] - Log: Se procede a realizar un INSERT en db_recomendacion de: " + recomendacion.toString());
        repository.insert(recomendacion);
        System.out.println("[Historial_Recomendaciones] - Log: INSERT Exitoso");
    }

    public List<DB_Recomendacion> consultarTodasRecomendaciones() {
        return repository.selectAll();
    } 
   
    public List<DB_Recomendacion> consultarRecomendacionesPorProductos(List<String> productos) {
        return consultarTodasRecomendaciones().stream()
                .filter(recomendacion -> recomendacion.getPeticionUsuario().containsAll(productos) && recomendacion.getPeticionUsuario().size() == productos.size()).collect(Collectors.toList());
    }

}

