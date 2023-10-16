package main;

import java.util.Arrays;
import java.util.List;

import extensible.FiltradorPorCriterio;
import model.DB_Recomendacion;
import modelo.Core;
import modelo.CoreInit;
import modelo.Recomendacion;
import observador.ObservadorDeRecomendaciones;
import service.HistoricoRecomendacionesService;

public class Main {
	
	static ObservadorDeRecomendaciones observadorRecomendaciones;

	public static void main(String[] args) throws Exception {
		
		//Prueba nueva recomendacion
		CoreInit coreInit = new CoreInit();
		Core core = coreInit.inicializar();
		observadorRecomendaciones = new ObservadorDeRecomendaciones(core.recomendador.recomendadorObservable);
		
		FiltradorPorCriterio criterio = core.criterios.stream().findFirst().get();
		Recomendacion recomendacion = core.obtenerRecomendacion(criterio, Arrays.asList("Arroz"));
		System.out.println(recomendacion);
		
		//Prueba buscar recomendacion ya existente
		HistoricoRecomendacionesService service = new HistoricoRecomendacionesService();
		List<DB_Recomendacion> recomendaciones = service.consultarRecomendacionesPorProductosYCriterio(Arrays.asList("Arroz"), "DistanciaLejana");
		System.out.println(recomendaciones);
		
	}

}
