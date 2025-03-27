package mx.gob.imss.cit.sci.mssci.accesodatos.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIComentarioDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.SCIResponseCode;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCICClasificacion;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITComentario;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITUsuario;
import mx.gob.imss.cit.sci.mssci.accesodatos.service.ComentarioCRUDService;
import mx.gob.imss.cit.sci.mssci.accesodatos.utils.CustomResponse;
import mx.gob.imss.cit.sci.mssci.accesodatos.utils.ValidationUtils;
import mx.gob.imss.cit.sci.mssci.accesodatos.validation.CustomValidation;

@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/mssci-accesodatos/v1")
public class ComentarioCRUDController {
	private static final Logger logger = LoggerFactory.getLogger(ComentarioCRUDController.class);
	
	@Autowired
	private ComentarioCRUDService comentarioCRUDService;
	
	@Autowired
	private CustomValidation customValidation;

	private SCITComentario getComentario(SCIComentarioDTO comentarioDTO, SCITUsuario usuario, SCITComentario comentario) throws ParseException {
		comentario.setUsuarioEmiteComentario(usuario);
		comentario.setMatriculaAlta(usuario.getMatriculaUsuario());
		
		SCICClasificacion clasificacion = comentarioCRUDService.buscaClasificacion(comentarioDTO.getIdClasificacion());
		comentario.setClasificacion(clasificacion);
		
		Date fecRevaloracion = transformaFecha(comentarioDTO.getFecRevalorarCaso());
		comentario.setFecRevalorarCaso(fecRevaloracion);
		
		comentario.setComentarioHechoAlCaso(comentarioDTO.getComentarioHechoAlCaso());

		return comentario;

	}
		
	public static final String NOT_FOUND = "Not Found";

	@Operation(summary = "HU22 Obtener lista de comentarios correspondientes al Caso")
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
	@GetMapping("/caso/{idCaso}/comentarios")
	public ResponseEntity<CustomResponse<List<SCITComentario>>> listaComentariosDelCaso(@PathVariable Long idCaso) {
		ResponseEntity<CustomResponse<List<SCITComentario>>> response = null;
		logger.debug("GET todos los comentarios del caso");
		CustomResponse<List<SCITComentario>> customResponse = null;
		
		List<SCITComentario> listaComentarios = comentarioCRUDService.buscaTodosLosComentariosDelCaso(idCaso);
		if(!listaComentarios.isEmpty()) {
			customResponse = new CustomResponse<>(SCIResponseCode.OK, null, listaComentarios);
			
		} else {
			customResponse = new CustomResponse<>(SCIResponseCode.NO_EXIST, "no existen comentarios para este caso.", listaComentarios);

		}
		
		if(SCIResponseCode.OK.equals(customResponse.getStatus()) || SCIResponseCode.NO_EXIST.equals(customResponse.getStatus())) {
			response = ResponseEntity.ok().body(customResponse);

		} else {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		}
		logger.debug("GET todos los comentarios del caso response {} ", response);
		return response;

	}
	
	@Operation(summary = "Obtener comentario para editar")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", 
	                                     description = "Obtiene comentario correspondiente", 
	                                         content = {@Content(mediaType = "application/json", 
	                                                                schema = @Schema(implementation = CustomResponse.class)) }),
						   @ApiResponse(responseCode = "404", 
						                 description = "No se encuentra comentario en base de datos",
						                     content = {@Content(mediaType = "application/json", 
                                                                    schema = @Schema(implementation = CustomResponse.class)) }),	
						   @ApiResponse(responseCode = "500", 
						                 description = "Error interno en servidor", 
						                     content = @Content) })
	@GetMapping("/caso/{idCaso}/editar_comentario/")
	public ResponseEntity<CustomResponse<SCITComentario>> comentarioParaEdicion(@PathVariable Long idCaso) {
		logger.debug("GET comentario por idCaso {} ", idCaso);
		ResponseEntity<CustomResponse<SCITComentario>> response = null;
		CustomResponse<SCITComentario> customResponse = null;
		
		List<SCITComentario> listaComentarios = comentarioCRUDService.buscaTodosLosComentariosDelCaso(idCaso);
		if(!listaComentarios.isEmpty()) {
			customResponse = new CustomResponse<>(SCIResponseCode.OK, "", listaComentarios.get(0));
			
		} else {
			customResponse = new CustomResponse<>(SCIResponseCode.NO_EXIST, NOT_FOUND, null);

		}
		
		if(customResponse.getBodyResponse() != null) {
			response = ResponseEntity.ok().body(customResponse);

		} else {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);

		}
		logger.debug("GET comentario por ID response {} ", response);
		return response;

	}

	@Operation(summary = "HU23 Crear nuevo comentario")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", 
	                                     description = "El comentario fue guardado con éxito", 
	                                         content = {@Content(mediaType = "application/json",
	                                                                schema = @Schema(implementation = CustomResponse.class)) }),                       
	                        @ApiResponse(responseCode = "400",
	                                      description = "Informacion insuficiente para crear nuevo comentario",
	                                          content = {@Content(mediaType = "application/json",
	                                                                 schema = @Schema(implementation = CustomResponse.class)) }),	
	                        @ApiResponse(responseCode = "401",
                                          description = "Usuario no autorizado",
                                              content = {@Content(mediaType = "application/json",
                                                                     schema = @Schema(implementation = CustomResponse.class)) }),
							@ApiResponse(responseCode = "405", 
						                  description = "Operacion no autorizada",
						                      content = {@Content(mediaType = "application/json", 
                                                                    schema = @Schema(implementation = CustomResponse.class)) }),	
						   @ApiResponse(responseCode = "500", 
						                 description = "Error interno en servidor", 
						                     content = @Content) })
	@PostMapping(value= "/caso/{idCaso}/{idComentarioAnterior}/nuevo_comentario", produces = "application/json")
	public ResponseEntity<?> creaComentario(@RequestHeader(name = "authorization") String token,
			 								@PathVariable Long idCaso,
			 								@PathVariable Long idComentarioAnterior,
			                                @RequestBody SCIComentarioDTO comentarioDTO) {
		logger.debug("POST crea comentario {} ", comentarioDTO);
		ResponseEntity<?> response = null;
		CustomResponse<SCITComentario> customResponse = null; 
		try {
			customValidation.createComentarioValidation(comentarioDTO);
			SCITUsuario usuario = comentarioCRUDService.buscaUsuarioPorId(comentarioDTO.getIdUsuario());
			String matricula = usuario.getMatriculaUsuario();

			if(matricula != null) {
				SCITComentario comentario = new SCITComentario();
				
				if (idComentarioAnterior != 0) {
					SCITComentario comentarioAnterior = comentarioCRUDService.buscaComentarioPorId(idComentarioAnterior);
					comentarioAnterior.setIndUltimoComentario(0);
					comentarioCRUDService.guardaComentario(comentarioAnterior);
				}
				comentario = getComentario(comentarioDTO, usuario, comentario);
				comentario.setIdCaso(comentarioDTO.getIdCaso());
				comentario.setIndUltimoComentario(1);
				comentario.setFecGeneracionComentario(new Date());

				SCITComentario comentarioGuardado = comentarioCRUDService.guardaComentario(comentario);
				if(comentarioGuardado != null) {
					customResponse = new CustomResponse<>(SCIResponseCode.OK, null, comentarioGuardado);
					
					response = ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
				}
			} else {
				response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

			}
			logger.debug("POST crea comentario {} ", comentarioDTO);
	
		} catch(ConstraintViolationException ex){
			logger.error(ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationUtils.convertValidationError(ex));

		} catch(ParseException e){
			logger.error(e.getMessage(), e);
			response = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(e);

		}
		return response;
		
	}

	@Operation(summary = "HU24 Editar comentario")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", 
	                                     description = "El comentario fue actualizado con éxito", 
	                                         content = {@Content(mediaType = "application/json",
	                                                                schema = @Schema(implementation = CustomResponse.class)) }),                       
	                        @ApiResponse(responseCode = "400",
	                                      description = "Informacion insuficiente para actualizar comentario",
	                                          content = {@Content(mediaType = "application/json",
	                                                                 schema = @Schema(implementation = CustomResponse.class)) }),	
	                        @ApiResponse(responseCode = "404",
                                          description = "El comentario no se encuentra en BD",
                                              content = {@Content(mediaType = "application/json",
                                                                     schema = @Schema(implementation = CustomResponse.class)) }),
							@ApiResponse(responseCode = "405", 
						                  description = "Operacion no autorizada",
						                      content = {@Content(mediaType = "application/json", 
                                                                    schema = @Schema(implementation = CustomResponse.class)) }),	
						   @ApiResponse(responseCode = "500", 
						                 description = "Error interno en servidor", 
						                     content = @Content) })
	@PutMapping(value = "/caso/{idCaso}/editar_comentario/{idComentario}", produces = "application/json")
	public ResponseEntity<?> actualizaComentario(@RequestHeader(name = "authorization") String token,
											  @PathVariable Long idCaso,
											  @PathVariable Long idComentario,
						                      @RequestBody SCIComentarioDTO comentarioDTO) throws ParseException {
		logger.debug("PUT actualiza comentario {} ", comentarioDTO);
		ResponseEntity<CustomResponse<SCITComentario>> response = null;
		CustomResponse<SCITComentario> customResponse = null;
		SCITComentario comentarioActualizado = null;

		SCITUsuario usuario = comentarioCRUDService.buscaUsuarioPorId(comentarioDTO.getIdUsuario());
		String matricula = usuario.getMatriculaUsuario();
		
		if(matricula == null) {
			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				
		} else {
			SCITComentario comentario = comentarioCRUDService.buscaComentarioPorId(idComentario);
			if(comentario != null) {
				comentarioActualizado = getComentario(comentarioDTO, usuario, comentario);
				//Se queda con la misma informacion
				comentarioActualizado.setMatriculaAlta(comentario.getMatriculaAlta());
				comentarioActualizado.setFecGeneracionComentario(comentario.getFecGeneracionComentario());

				//para auditoría...
				comentarioActualizado.setFecActualizaRegistro(new Date());
				comentarioActualizado.setMatriculaActualizaRegistro(matricula);
				
				comentarioActualizado = comentarioCRUDService.guardaComentario(comentarioActualizado);

			}
			if(comentarioActualizado != null) {
				customResponse = new CustomResponse<>(SCIResponseCode.OK, "", comentarioActualizado);
				response = ResponseEntity.ok().body(customResponse);

			} else {
				customResponse = new CustomResponse<>(SCIResponseCode.ERROR, NOT_FOUND, null);
				response = ResponseEntity.notFound().build();

			}
		}
		logger.debug("PUT actualiza comentario response {} ", response);
		return response;

	}
	
	private Date transformaFecha(String tmpFecha) throws ParseException{
		String formatoFuente = "";
		DateFormat destDF = null;
		formatoFuente = "yyyy-MM-dd hh:mm:ss";
		destDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timezoneMX = "America/Mexico_City";
		DateFormat fuenteDF = new SimpleDateFormat(formatoFuente);
		fuenteDF.setTimeZone(TimeZone.getTimeZone(timezoneMX));
        Date tmpFecNueva = fuenteDF.parse(tmpFecha);
        String destino = destDF.format(tmpFecNueva);

		return destDF.parse(destino);
		
	}

}
