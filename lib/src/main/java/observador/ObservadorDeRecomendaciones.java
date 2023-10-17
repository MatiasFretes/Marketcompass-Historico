package observador;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import model.DB_Recomendacion;
import observable.RecomendadorObservable;
import service.HistoricoRecomendacionesService;


public class ObservadorDeRecomendaciones implements Observer {
    public RecomendadorObservable recomendadorObservable;
    public HistoricoRecomendacionesService recomendacionesService;

    public ObservadorDeRecomendaciones(RecomendadorObservable recomendadorObservable) {
        this.recomendadorObservable = recomendadorObservable;
        recomendadorObservable.addObserver(this);
        recomendacionesService = new HistoricoRecomendacionesService();
    }

    @Override
    public void update(Observable o, Object arg) {
    	System.out.println("[Historial_Recomendaciones] - Log: Se observa una nueva recomendacion, se procede a guardardo en el historico.");
        if (o instanceof RecomendadorObservable && arg instanceof RecomendadorObservable) {
            RecomendadorObservable recomendadorObservable = (RecomendadorObservable) arg;
            String mercadoRecomendado = recomendadorObservable.mercadoRecomendado;
            List<String> peticionUsuario = recomendadorObservable.productos;
            String criterioUtilizado = recomendadorObservable.criterioUtilizado;
            DB_Recomendacion nuevaRecomendacion = new DB_Recomendacion(mercadoRecomendado, peticionUsuario, criterioUtilizado);
            recomendacionesService.insertarRecomendacion(nuevaRecomendacion);
        }
    }
}
