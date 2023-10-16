package observador;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import db.DatabaseConnector;
import model.DB_Recomendacion;
import observable.RecomendadorObservable;
import repository.DB_RecomendacionRepository;
import service.DB_RecomendacionService;


public class ObservadorDeRecomendaciones implements Observer {
    private RecomendadorObservable recomendadorObservable;
    private DatabaseConnector dbConnector;
    private DB_RecomendacionService recomendacionesService;

    public ObservadorDeRecomendaciones(RecomendadorObservable recomendadorObservable) {
        this.recomendadorObservable = recomendadorObservable;
        recomendadorObservable.addObserver(this);
        
        dbConnector = new DatabaseConnector();
        DB_RecomendacionRepository recomendacionRepository = new DB_RecomendacionRepository(dbConnector.getConnection());
        recomendacionesService = new DB_RecomendacionService(recomendacionRepository);
    }

    @Override
    public void update(Observable o, Object arg) {
    	System.out.println("[Historial_Recomendaciones] - Log: Se observa una nueva recomendacion, se procede a guardardo en el historico.");
        if (o instanceof RecomendadorObservable && arg instanceof RecomendadorObservable) {
            RecomendadorObservable recomendadorObservable = (RecomendadorObservable) arg;
            String mercadoRecomendado = recomendadorObservable.getMercadoRecomendado().getNombre();
            List<String> peticionUsuario = recomendadorObservable.getProductos();
            String criterioUtilizado = recomendadorObservable.getCriterioUtilizado().getClass().getSimpleName();
            DB_Recomendacion nuevaRecomendacion = new DB_Recomendacion(mercadoRecomendado, peticionUsuario, criterioUtilizado);
            recomendacionesService.insertarRecomendacion(nuevaRecomendacion);
        }
    }
}
