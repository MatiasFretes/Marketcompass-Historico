package main;

import java.util.Arrays;

import extensible.FiltradorPorCriterio;
import modelo.Core;
import modelo.CoreInit;
import modelo.Recomendacion;
import observador.ObservadorDeRecomendaciones;

public class Main {
	
	static ObservadorDeRecomendaciones observadorRecomendaciones;

	public static void main(String[] args) throws Exception {
		CoreInit coreInit = new CoreInit();
		Core core = coreInit.inicializar();
		observadorRecomendaciones = new ObservadorDeRecomendaciones(core.recomendador.recomendadorObservable);
		
		FiltradorPorCriterio criterio = core.criterios.stream().findFirst().get();
		Recomendacion recomendacion = core.obtenerRecomendacion(criterio, Arrays.asList("Arroz"));
		System.out.println(recomendacion);
		
		
	}

}
