package mx.gob.imss.cit.sci.mssci.accesodatos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.PaginadoConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;
import mx.gob.imss.cit.sci.mssci.accesodatos.service.IncapacidadService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ConsultaIncapacidadControllerTests {

	@MockBean
	private IncapacidadService incapacidadService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /incapacidad - OK - Con campos obligatorios")
	void testGetIncapacidadOK() throws Exception {
		PaginadoConsultaIncapacidadDTO paginado = new PaginadoConsultaIncapacidadDTO();
		List<ConsultaIncapacidadesDTO> incapacidadList = new ArrayList<>();
		ConsultaIncapacidadRequest params = new ConsultaIncapacidadRequest();
		ConsultaIncapacidadesDTO incapacidad = new ConsultaIncapacidadesDTO();
		incapacidad.setNombrePaciente("ARTURO MARTINEZ DELGADO");
		incapacidad.setNss("MYNSS");
		incapacidadList.add(incapacidad);
		paginado.setConsultaIncapacidadList(incapacidadList);
		doReturn(paginado).when(incapacidadService).consultaIncapacidades(params, 10, 1);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(params);

		mockMvc.perform(
				post("/mssci-accesodatos/v1/consultaIncapacidad")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
				.param("limit", "10")
				.param("offset", "1")
				.sessionAttr("token", "MyToken"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.consultaIncapacidadList[0].nombrePaciente", is("ARTURO MARTINEZ DELGADO")))
		.andExpect(jsonPath("$.consultaIncapacidadList[0].nss").value("MYNSS"))
		.andDo(print());
	}

	@Test
	@DisplayName("GET /incapacidad - Sin Delegacion")
	void testGetIncapacidadSinDelegacion() throws Exception {
		
		mockMvc.perform(
				get("/mssci-accesodatos/v1/consultaIncapacidad").
				param("isAdscripcion", "1").
				sessionAttr("token", "MyToken"))
		.andExpect(status().is4xxClientError())
		.andDo(print());
	}

	@Test
	@DisplayName("GET /incapacidad - Sin limit")
	void testGetIncapacidadSinLimite() throws Exception {

		mockMvc.perform(
				post("/mssci-accesodatos/v1/consultaIncapacidad").
				sessionAttr("token", "MyToken"))
		.andExpect(status().isBadRequest())
		.andExpect(status().reason(containsString("Required Integer parameter 'limit' is not present")))
		.andDo(print());
	}
	
	@Test
	@DisplayName("GET /incapacidad - Sin Offset")
	void testGetIncapacidadSinOffset() throws Exception {

		mockMvc.perform(
				post("/mssci-accesodatos/v1/consultaIncapacidad").
				param("limit", "10").
				sessionAttr("token", "MyToken"))
		.andExpect(status().isBadRequest())
		.andExpect(status().reason(containsString("Required Integer parameter 'offset' is not present")))
		.andDo(print());
	}

}
