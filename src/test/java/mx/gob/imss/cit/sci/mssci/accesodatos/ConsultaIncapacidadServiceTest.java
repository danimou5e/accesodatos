package mx.gob.imss.cit.sci.mssci.accesodatos;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.PaginadoConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.EnumHttpStatus;
import mx.gob.imss.cit.sci.mssci.accesodatos.exceptions.BusinessException;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.IncapacidadRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;
import mx.gob.imss.cit.sci.mssci.accesodatos.service.IncapacidadService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsultaIncapacidadServiceTest {

	@Autowired
	private IncapacidadService service;

	@MockBean
	private IncapacidadRepository repository;

	@Test
	@DisplayName("Test consultaIncapacidad OK")
	void testConsultaIncapaciadOK() throws BusinessException {

		ConsultaIncapacidadRequest params = new ConsultaIncapacidadRequest();
		params.setIdDelegacion(2L);
		params.setIsAdscripcion(true);
		params.setEstadoIncapacidad(2);

		List<ConsultaIncapacidadesDTO> incapacidadList = new ArrayList<>();

		ConsultaIncapacidadesDTO incapacidad = new ConsultaIncapacidadesDTO();
		incapacidad.setNombrePaciente("ARTURO MARTINEZ DELGADO");
		incapacidad.setIdCaso(1L);
		incapacidad.setDiasAcumuladosIncapacidad(70);
		incapacidad.setIdUnidad(300L);
		incapacidad.setNombreUnidad("Hospital General De Atizapan");
		incapacidad.setNss("MYNSS");
		incapacidadList.add(incapacidad);
		doReturn(incapacidadList).when(repository).consultaIncapacidadPorAdscripcion(params, null, null, 10, 9);

		PaginadoConsultaIncapacidadDTO incTest = service.consultaIncapacidades(params, 10, 10);

		Assertions.assertNotNull(incTest);
		Assertions.assertTrue(!incTest.getConsultaIncapacidadList().isEmpty(), "Ninguna incapacidad encontrada");
		Assertions.assertEquals("ARTURO MARTINEZ DELGADO", incTest.getConsultaIncapacidadList().get(0).getNombrePaciente());
		Assertions.assertEquals("MYNSS", incTest.getConsultaIncapacidadList().get(0).getNss());

	}

	@Test()
	@DisplayName("Test consultaIncapacidad sin campos obligatorios")
	void testConsultaIncapaciadSinCamposObligatorios() throws BusinessException {

		ConsultaIncapacidadRequest params = new ConsultaIncapacidadRequest();
		doReturn(null).when(repository).consultaIncapacidadPorAdscripcion(params, 4, null, null, null);
		try {
			service.consultaIncapacidades(params, null, null);
		} catch (BusinessException e) {
			Assertions.assertEquals(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getCode(), Integer.valueOf(e.getRespuestaError().getCode()), "Codigo de error diferente al esperado");
			Assertions.assertEquals("La propiedad idDelegacion es requerida", e.getRespuestaError().getBusinessMessage(), "Mensaje de error diferente al esperado");
		}
	}
	
	@Test
	@DisplayName("Test consulta detalle de incapacidad - OK")
	void testIncapacidadDetalleOk() throws BusinessException {
		ConsultaIncapacidadDTO detalle = new ConsultaIncapacidadDTO();
		detalle.setNombrePaciente("ARTURO MARTINEZ DELGADO");
		detalle.setMontoAcumuladoSubsidio(3500.00);
		detalle.setNombreUnidad("Hospital General De Atizapan");
		detalle.setNss("MYNSS");
		doReturn(detalle).when(repository).consultaDetalleCaso(1L);
		
		ConsultaIncapacidadDTO testDetalle = service.consultaIncapacidadPorIdCaso(1L);
		
		Assertions.assertNotNull(testDetalle);
		Assertions.assertEquals("ARTURO MARTINEZ DELGADO", testDetalle.getNombrePaciente());
		Assertions.assertEquals("MYNSS", testDetalle.getNss());
	}
	
	@Test
	@DisplayName("Test consulta detalle de incapacidad - NotFound")
	void testIncapaciadadNoEncontrada() throws BusinessException {

		doReturn(null).when(repository).consultaDetalleCaso(10L);
		
		try {			
			service.consultaIncapacidadPorIdCaso(1L);
		} catch (BusinessException e) {
			Assertions.assertEquals(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getCode(), Integer.valueOf(e.getRespuestaError().getCode()), "Codigo de error diferente al esperado");
			Assertions.assertEquals("Registro no encontrado", e.getRespuestaError().getBusinessMessage(), "Mensaje de error diferente al esperado");
		}
	}

	@Test
	@DisplayName("Test consulta detalle de incapacidad - Sin idCaso")
	void testIncapacidadSinParametroRequerido() throws BusinessException {

		try {
			service.consultaIncapacidadPorIdCaso(null);
		} catch (BusinessException e) {
			Assertions.assertEquals(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getCode(), Integer.valueOf(e.getRespuestaError().getCode()), "Codigo de error diferente al esperado");
			Assertions.assertEquals("La propiedad idCaso es requerida", e.getRespuestaError().getBusinessMessage(), "Mensaje de error diferente al esperado");
		}
	}

}
