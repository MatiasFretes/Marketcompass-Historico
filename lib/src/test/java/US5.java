

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import extensible.FiltradorPorCriterio;
import modelo.Core;
import modelo.CoreInit;
import observador.ObservadorDeRecomendaciones;
import observable.RecomendadorObservable;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;

public class US5 {

	private Core core;
	private FiltradorPorCriterio criterio;
	private ObservadorDeRecomendaciones observadorMock;
	private String rutaJsonMercados = "src/test/resources/mercados.json";
	private String rutaJarCriterio = "src/test/resources/Distancia.jar";
	private List<String> productoExistente = Arrays.asList("P1");
	private List<String> productoInexistente = Arrays.asList("NoExiste");  

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        observadorMock = Mockito.mock(ObservadorDeRecomendaciones.class);
        CoreInit.RUTA_JSON_MERCADOS = rutaJsonMercados;
		CoreInit.RUTA_JAR_CRITERIO = rutaJarCriterio;
        CoreInit coreInit = new CoreInit();
        core = coreInit.inicializar();
        core.recomendador.recomendadorObservable.addObserver(observadorMock); 
        criterio = core.criterios.stream().findFirst().get();
    }

    @Test
    void CA1_ObservarNuevaRecomendacion(){
        core.obtenerRecomendacion(criterio, productoExistente);
        Mockito.verify(observadorMock, Mockito.times(1)).update(Mockito.isA(RecomendadorObservable.class), Mockito.isA(RecomendadorObservable.class));
    }
    
    @Test
    void CA2_NoObservarRecomendacion(){
        core.obtenerRecomendacion(criterio, productoInexistente);
        Mockito.verify(observadorMock, Mockito.never()).update(Mockito.any(), Mockito.any());
    }
}
