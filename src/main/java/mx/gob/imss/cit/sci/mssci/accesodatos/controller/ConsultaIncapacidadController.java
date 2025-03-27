package mx.gob.imss.cit.sci.mssci.accesodatos.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.AgrupaCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaTodosCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ExportIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.PaginadoConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIPaginadoConsultaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIParametrosHistoricoIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.UpdatePacienteDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.SCIResponseCode;
import mx.gob.imss.cit.sci.mssci.accesodatos.exceptions.BusinessException;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.Caso;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.RespuestaError;
import mx.gob.imss.cit.sci.mssci.accesodatos.service.IncapacidadService;
import mx.gob.imss.cit.sci.mssci.accesodatos.utils.CustomResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mssci-accesodatos/v1")
public class ConsultaIncapacidadController {

	public static final Logger log = LoggerFactory.getLogger(ConsultaIncapacidadController.class);
	
	@Autowired
	private IncapacidadService incapacidadService;

	@PostMapping(value = "/consultaIncapacidad", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerIncapacidades(
		@RequestParam(value = "limit", required=true)Integer limit,
		@RequestParam(value = "offset", required=true)Integer offset,
		@RequestBody ConsultaIncapacidadRequest params
		) {
		ResponseEntity<?> responseEntity = null;

		try {
			PaginadoConsultaIncapacidadDTO paginadoIncapacidad = incapacidadService.consultaIncapacidades(params, limit, offset);
        	responseEntity = new ResponseEntity<>(paginadoIncapacidad, HttpStatus.OK);

		} catch (BusinessException be) {

			log.info("Error al consultar incapacidades: {}", be);

            int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());

			RespuestaError respuestaError = be.getRespuestaError();
			responseEntity = new ResponseEntity<>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));

		}

		return responseEntity;
	}

	@PostMapping(value = "/consultaIncapacidad/{idCaso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerIncapacidadesDetalle(@PathVariable(value = "idCaso", required = true)Long idCaso,
			@RequestBody (required=false)Long idPaciente) {
		ResponseEntity<?> responseEntity = null;
		Long idCasoAgrupacionADemanda;
		ConsultaIncapacidadDTO paginadoIncapacidad = new ConsultaIncapacidadDTO();
		
		try {

			//Mejora: Agrupacion de casos a demanda
			int i=0;
			Long idCasoTmp = idCaso;
			do {
				idCasoAgrupacionADemanda = incapacidadService.validarAgrupacionNivelCaso(idPaciente, idCasoTmp);
				idCasoTmp = idCasoAgrupacionADemanda;
				i++;
			} while(i<2);//repetir 2 veces
			
			if (idCasoAgrupacionADemanda.equals(idCaso)) {
				paginadoIncapacidad = incapacidadService.consultaIncapacidadPorIdCaso(idCaso);
			} else {
				paginadoIncapacidad = incapacidadService.consultaIncapacidadPorIdCaso(idCasoAgrupacionADemanda);
			}
        	responseEntity = new ResponseEntity<>(paginadoIncapacidad, HttpStatus.OK);

		} catch (BusinessException be) {

			log.info("Error al consultar detalle de incapacidad con id: {}", idCaso);

            int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());

			RespuestaError respuestaError = be.getRespuestaError();
			responseEntity = new ResponseEntity<>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));

		}

		return responseEntity;
	}
	
	// Obtener todos los casos de un paciente
	@GetMapping(value= "/consultaTodosLosCasos/{nss}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consultaTodosLosCasos(@PathVariable(value = "nss", required = true)String nss) {
		
		ResponseEntity<?> responseEntity = null;
		
		try {

			List<ConsultaTodosCasosDTO> detalleCaso = incapacidadService.consultaTodosLosCasos(nss);
        	responseEntity = new ResponseEntity<>(detalleCaso, HttpStatus.OK);

		} catch (BusinessException be) {

			log.info("Error al consultar el paciente con NSS: {}", nss);

            int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());

			RespuestaError respuestaError = be.getRespuestaError();
			responseEntity = new ResponseEntity<>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));

		}

		return responseEntity;
		
	}
	
	// Obtener todos los casos de un paciente
		@PostMapping(value= "/agrupaCasos", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> agrupaCasos(@RequestBody AgrupaCasosDTO agrupaCasos) {
			
			ResponseEntity<?> responseEntity = null;
			
			try {
				//insertar caso en tabla caso y caso_activo (si aplica)
				Caso nuevoCasoAgrupado = incapacidadService.insertaNuevoCasoAgrupado(agrupaCasos);
				
				//registrar cambios en bitacora
				incapacidadService.registrarEnBitacora(nuevoCasoAgrupado, agrupaCasos);
				
				//actualizar registros de casos, sus incapacidades y sus comentarios
				incapacidadService.actualizaCasosIncapacidadesYComentarios(nuevoCasoAgrupado, agrupaCasos);
				
				//eliminar de la tabla caso_activos los casos anteriores
				incapacidadService.eliminarCasosActivosAnteriores(agrupaCasos);
				
				//Regresar consulta detalleCaso
				List<ConsultaTodosCasosDTO> detalleCaso = incapacidadService.consultaTodosLosCasos(agrupaCasos.getCasosSeleccionados().get(0).getNss());
	        	responseEntity = new ResponseEntity<>(detalleCaso, HttpStatus.OK);
	        	

			} catch (BusinessException be) {

				log.info("Error al agrupar los casos de forma manual");

	            int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());

				RespuestaError respuestaError = be.getRespuestaError();
				responseEntity = new ResponseEntity<>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));

			}

			return responseEntity;
			
		}
	
	
	@PostMapping(value = "/actualizaIncapacidad", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerIncapacidadDetalle(@RequestBody UpdatePacienteDTO paciente) {
		ResponseEntity<?> responseEntity = null;
		
		try {
			Long idPaciente = incapacidadService.actualizaPaciente(paciente);
			responseEntity = new ResponseEntity<>(idPaciente, HttpStatus.OK);

		} catch (BusinessException be) {
			log.info("Error al actualizar paciente con id: {}", paciente.getIdPaciente());
            int numberHTTPDesired = Integer.parseInt(be.getRespuestaError().getCode());
			RespuestaError respuestaError = be.getRespuestaError();
			responseEntity = new ResponseEntity<>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));
		}
		return responseEntity;
	}
	
	/*
	 * HU32 Consulta del Historico de Incapacidades
	 * @author Ing. Martha Aurora Sanchez Valdivieso
	 * @date 01/19/2021
	 */
	@Operation(summary = "HU32 Consulta del Historico de Incapacidades")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", 
    									 description = "Obtiene la lista de los comentarios correspondientes al Caso", 
                                             content = {@Content(mediaType = "application/json", 
                                              schema = @Schema(implementation = CustomResponse.class)) }),
                           @ApiResponse(responseCode = "404", 
                                         description = "No se encuentran comentarios en base de datos para este Caso",
                                             content = {@Content(mediaType = "application/json", 
                                              schema = @Schema(implementation = CustomResponse.class)) }),	
                           @ApiResponse(responseCode = "500",
                                         description = "",
                                             content = @Content) })                 
	@PostMapping(value = "/historicoIncapacidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerHistoricoIncapacidades( @RequestParam(value = "limit", required=true)Integer limit,
															@RequestParam(value = "offset", required=true)Integer offset,
															@RequestBody SCIParametrosHistoricoIncapacidadesDTO parametrosDTO)
															 {
		log.debug("POST obtener historico incapacidades {} ",  parametrosDTO);
		ResponseEntity<?> responseEntity = null;
		CustomResponse<SCIPaginadoConsultaHistoricoDTO> customResponse = null; 
		
		try {
			
			SCIPaginadoConsultaHistoricoDTO paginadoHistorico = incapacidadService.consultaHistorico(parametrosDTO, limit, offset);
			if(paginadoHistorico != null) {
				customResponse = new CustomResponse<>(SCIResponseCode.OK, null, paginadoHistorico);
		
			}else {
				customResponse = new CustomResponse<>(SCIResponseCode.NO_EXIST, "no existen incapacidades para los criterios dados.", paginadoHistorico);

			}
			
			if(SCIResponseCode.OK.equals(customResponse.getStatus()) || SCIResponseCode.NO_EXIST.equals(customResponse.getStatus())) {
				responseEntity = ResponseEntity.ok().body(customResponse);

			} else {
				responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

			}
			log.debug("POST obtener historico incapacidades response {} ", responseEntity);

		} catch (BusinessException e) {
			log.info("Error al consultar el historico de incapacidades: {}", e);
            int numberHTTPDesired = Integer.parseInt(e.getRespuestaError().getCode());
			RespuestaError respuestaError = e.getRespuestaError();
			responseEntity = new ResponseEntity<>(respuestaError, HttpStatus.valueOf(numberHTTPDesired));

		}
		return responseEntity;

	}
	
	//Export incapacidades
	@PostMapping(value = "/exportHistoricoIncapacidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportHistoricoIncapacidades(HttpServletResponse response,
															@RequestParam(value = "limit", required=true)Integer limit,
															@RequestParam(value = "offset", required=true)Integer offset,
															@RequestBody SCIParametrosHistoricoIncapacidadesDTO parametrosDTO)
															throws IOException {
		
		log.debug("POST exportar historico incapacidades {} ",  parametrosDTO);
		
		response.setContentType("application/vnd.ms-excel");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
        String currentDate = dateFormatter.format(new Date()); 
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Historial_Asegurado_NSS_SCI_"+ currentDate +".xlsx";
        response.setHeader(headerKey, headerValue);

		try {
			if (Boolean.TRUE.equals(parametrosDTO.getExportar())) {
				SCIPaginadoConsultaHistoricoDTO paginadoHistorico = incapacidadService.consultaHistorico(parametrosDTO, limit, offset);
				incapacidadService.exportarHistorial(paginadoHistorico.getHistoricoIncapacidades(), response);
			}

		} catch (BusinessException e) {
			log.info("Error al consultar el historico de incapacidades: {}", e);

		}

	}
	
	@PostMapping(value = "/consultaIncapacidadesTodas", produces = MediaType.APPLICATION_JSON_VALUE)
	public void obtenerTodasIncapacidades(HttpServletResponse response, @RequestBody ConsultaIncapacidadRequest params) throws IOException {
		response.setContentType("application/vnd.ms-excel");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
        String currentDate = dateFormatter.format(new Date()); 
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Casos_Incapacidad_NLISTA_SCI_"+ currentDate +".xlsx";
        response.setHeader(headerKey, headerValue);
        
	try {
		log.info("Generando listado de incapacidades. . .");
		List<ExportIncapacidadesDTO> listadoIncapacidades= incapacidadService.consultaTodasIncapacidades(params);
		log.info("Listado de incapacidades generado.");
		log.info("Exportando listado de incapacidades a Excel . . .");
		incapacidadService.exportWithApachePOI(listadoIncapacidades, response);
		log.info("Exportado ha finalizado.\n");

	 } catch (BusinessException be) {

	 log.info("Error al consultar incapacidades: {}", be);

	 }

	
	}
	
	
	
}
