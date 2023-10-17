import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.HistoricoRecomendacionesService;
import model.DB_Recomendacion;
import repository.DB_RecomendacionRepository;

public class HistoricoRecomendacionesServiceTest {
    private HistoricoRecomendacionesService service;
    private DB_RecomendacionRepository mockRepository;

    @BeforeEach
    void setUp() {
        mockRepository = mock(DB_RecomendacionRepository.class);
        service = new HistoricoRecomendacionesService();
        service.setRepository(mockRepository); // Inyecta el mockRepository en el servicio
    }

    @Test
    void testInsertarRecomendacion() {
        DB_Recomendacion recomendacion = new DB_Recomendacion("M1", Arrays.asList("P1"), "C1");

        doNothing().when(mockRepository).insert(recomendacion);

        service.insertarRecomendacion(recomendacion);

        verify(mockRepository, times(1)).insert(recomendacion);
    }
}
