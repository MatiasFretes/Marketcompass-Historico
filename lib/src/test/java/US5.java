

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import extensible.FiltradorPorCriterio;
import model.DB_Recomendacion;
import modelo.Core;
import modelo.CoreInit;
import observador.ObservadorDeRecomendaciones;
import repository.DB_RecomendacionRepository;
import service.HistoricoRecomendacionesService;
import observable.RecomendadorObservable;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

public class US5 {

	private Core core;
	private FiltradorPorCriterio criterio;
	private ObservadorDeRecomendaciones observadorMock;
    private HistoricoRecomendacionesService service;
    private DB_RecomendacionRepository repositorioMock;
	private String rutaJsonMercados = "src/test/resources/mercados.json";
	private String rutaJarCriterio = "src/test/resources/Distancia.jar";
	private List<String> productoExistente = Arrays.asList("P1");
	private List<String> productoInexistente = Arrays.asList("NoExiste");  
	private DB_Recomendacion recomendacionValida = new DB_Recomendacion("M1", Arrays.asList("P1"), "C1");
	private DB_Recomendacion recomendacionInvalida = new DB_Recomendacion("", Arrays.asList(""), "");
	

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
        repositorioMock = mock(DB_RecomendacionRepository.class);
        service = new HistoricoRecomendacionesService();
        service.setRepository(repositorioMock);
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
    
    @Test
    void CA3_InsertarRecomendacionValida() {
        doNothing().when(repositorioMock).insert(recomendacionValida);
        service.insertarRecomendacion(recomendacionValida);
        verify(repositorioMock, times(1)).insert(recomendacionValida);
    }
    
    @Test
    void CA4_NoInsertarRecomendacionInvalida() {
        assertFalse(recomendacionInvalida.esValida());
        service.insertarRecomendacion(recomendacionInvalida);
        verify(repositorioMock, never()).insert(recomendacionInvalida);
    }
}
