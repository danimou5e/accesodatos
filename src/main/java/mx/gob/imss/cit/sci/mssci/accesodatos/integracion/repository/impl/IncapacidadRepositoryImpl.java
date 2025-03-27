package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.imss.cit.sci.mssci.accesodatos.constants.SQLAgrupacionCasos;
import mx.gob.imss.cit.sci.mssci.accesodatos.constants.SQLIncapacidadClasificacionCasosConstants;
import mx.gob.imss.cit.sci.mssci.accesodatos.constants.SQLIncapacidadConstants;
import mx.gob.imss.cit.sci.mssci.accesodatos.constants.SQLTodasIncapacidades;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaTodosCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ExportIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIParametrosHistoricoIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIRespuestaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.UpdatePacienteDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.EnumHttpStatus;
import mx.gob.imss.cit.sci.mssci.accesodatos.exceptions.BusinessException;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.IncapacidadRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.IncapacidadGraficaModel;

@Repository("incapacidadRepository")
public class IncapacidadRepositoryImpl implements IncapacidadRepository {

	private static final Logger logger = LoggerFactory.getLogger(IncapacidadRepositoryImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ConsultaIncapacidadesDTO> consultaIncapacidadPorAdscripcion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS, Integer limit, Integer offset) throws BusinessException {

		List<ConsultaIncapacidadesDTO> consultaIncapacidad = null;
		String querySearch = (params.getEstadoIncapacidad() == 2) ?
				SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_ADSCRIPCION_ACTIVOS :
				SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_ADSCRIPCION;
		
		try {
			consultaIncapacidad = jdbcTemplate.query(querySearch,
									new Object[] {
											params.getEstadoIncapacidad(),
											(params.getEstadoIncapacidad() == 2)?4:5,
											params.getIdDelegacion(),
											params.getIdDelegacion(),
											params.getIdUnidad(),
											params.getIdUnidad(),
											params.getIdRamo(),
											params.getIdRamo(),
											isEmpIMSS,
											isEmpIMSS,
											isntEmpIMSS,
											isntEmpIMSS,
											params.getNss(),
											params.getNss(),
											params.getCurp(),
											params.getCurp(),
							
											params.getIdTurno(),
											params.getIdTurno(),
											params.getConsultorio(),
											params.getConsultorio(),
											
											params.getNombre(),
											params.getNombre(),
											params.getApaterno(),
											params.getApaterno(),
											params.getAmaterno(),
											params.getAmaterno(),
											offset,
											limit
											},
									(rs, rowNum) -> {
										ConsultaIncapacidadesDTO incapacidad = new ConsultaIncapacidadesDTO();
										incapacidad.setIdUnidad(rs.getLong("idUnidad"));
										incapacidad.setNombrePaciente(rs.getString("paciente").trim());
										incapacidad.setPrimerApellido(rs.getString("pApellido"));
										incapacidad.setSegundoApellido(rs.getString("sApellido"));
										incapacidad.setIdPaciente(rs.getLong("idPaciente"));
										incapacidad.setIdCaso(rs.getLong("idCaso"));
										incapacidad.setNss(rs.getString("nss"));
										incapacidad.setNombreUnidad(rs.getString("unidad"));
										incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumulados"));
										incapacidad.setInicioCaso(rs.getString("inicioCaso"));
										incapacidad.setFinCaso(rs.getString("finCaso"));
										return incapacidad;
									});

		} catch (Exception ex) {
			logger.debug("Error al consultar incapacidad por adscripcion");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por adscripcion", ex.getMessage());
		}

		return consultaIncapacidad;
	}

	@Override
	public Integer consultaIncapacidadPorAdscripcionCount(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {

		Integer totalRegistro = null;
		String querySearch = (params.getEstadoIncapacidad() == 2) ?
				SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_ADSCRIPCION_COUNT_ACTIVOS :
				SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_ADSCRIPCION_COUNT;

		try {

			totalRegistro = jdbcTemplate.queryForObject(
								querySearch,
								new Object[] {
										params.getEstadoIncapacidad(),
										(params.getEstadoIncapacidad() == 2)?4:5,
										params.getIdDelegacion(),
										params.getIdDelegacion(),
										params.getIdUnidad(),
										params.getIdUnidad(),
										params.getIdRamo(),
										params.getIdRamo(),
										isEmpIMSS,
										isEmpIMSS,
										isntEmpIMSS,
										isntEmpIMSS,
										params.getNss(),
										params.getNss(),
										params.getCurp(),
										params.getCurp(),
										
										params.getIdTurno(),
										params.getIdTurno(),
										params.getConsultorio(),
										params.getConsultorio(),
										
										params.getNombre(),
										params.getNombre(),
										params.getApaterno(),
										params.getApaterno(),
										params.getAmaterno(),
										params.getAmaterno(),
										},
								(rs, rowNum) -> {
									return Integer.valueOf(rs.getInt("total"));
								});

		} catch (Exception ex) {
			logger.debug("Error al contar incapacidades por adscripcion");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al contar incapacidades por adscripcion", ex.getMessage());
		}
		
		return totalRegistro;
	}

	//MASV 11/03/2021 para filtro de Clasificacion del Caso - U ADSCRIPCION 
	@Override
	public List<ConsultaIncapacidadesDTO> consultaIncPorAdscConClasificacion(ConsultaIncapacidadRequest params,
			                                                                 Integer isEmpIMSS, Integer isntEmpIMSS, 
			                                                                 Integer limit, Integer offset) throws BusinessException {
		List<ConsultaIncapacidadesDTO> consultaIncapacidad = null;
		
		try {
			String sqlIncPorAdsc = "";
			if(params.getEstadoIncapacidad() == 2) {
				sqlIncPorAdsc = SQLIncapacidadClasificacionCasosConstants.QS_INC_ACT_ADSC_CON_CLASIFICACION_DEL_CASO;

			} else {
				sqlIncPorAdsc = SQLIncapacidadClasificacionCasosConstants.QS_INC_RST_ADSC_CON_CLASIFICACION_DEL_CASO;

			}
			consultaIncapacidad = jdbcTemplate.query(sqlIncPorAdsc,
													 new Object[] {
													 		 params.getEstadoIncapacidad(),
													 		 (params.getEstadoIncapacidad() == 2)?4:5,
													 		 params.getIdDelegacion(),
													 		 params.getIdDelegacion(),
													 		 params.getIdClasificacion(),
													 		 params.getIdClasificacion(),
													 		 params.getIdUnidad(),
													 		 params.getIdUnidad(),
													 		 params.getIdRamo(),
													 		 params.getIdRamo(),
													 		 isEmpIMSS,
													 		 isEmpIMSS,
													 		 isntEmpIMSS,
													 		 isntEmpIMSS,
													 		 params.getNss(),
													 		 params.getNss(),
													 		 params.getCurp(),
													 		 params.getCurp(),
													 		 params.getIdTurno(),
													 		 params.getIdTurno(),
													 		 params.getConsultorio(),
													 		 params.getConsultorio(),
															 params.getNombre(),
															 params.getNombre(),
															 params.getApaterno(),
															 params.getApaterno(),
															 params.getAmaterno(),
															 params.getAmaterno(),
															 offset,
															 limit
													 },
													 (rs, rowNum) -> {
														 ConsultaIncapacidadesDTO incapacidad = new ConsultaIncapacidadesDTO();
														 incapacidad.setIdUnidad(rs.getLong("idUnidad"));
														 incapacidad.setNombrePaciente(rs.getString("paciente").trim());
														 incapacidad.setPrimerApellido(rs.getString("pApellido"));
														 incapacidad.setSegundoApellido(rs.getString("sApellido"));
														 incapacidad.setIdPaciente(rs.getLong("idPaciente"));
														 incapacidad.setIdCaso(rs.getLong("idCaso"));
														 incapacidad.setNss(rs.getString("nss"));
														 incapacidad.setNombreUnidad(rs.getString("unidad"));
														 incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumulados"));
														 incapacidad.setInicioCaso(rs.getString("inicioCaso"));
														 incapacidad.setFinCaso(rs.getString("finCaso"));
														 return incapacidad;
													 });

		} catch (Exception ex) {
			logger.debug("Error al consultar incapacidad por adscripcion con clasificacion de casos");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por adscripcion con clasificacion de casos ", ex.getMessage());
		}

		return consultaIncapacidad;
	}

	//MASV 11/03/2021 para filtro de Clasificacion del Caso - U ADSCRIPCION
	@Override
	public Integer consultaIncPorAdscConClasificacionCount(ConsultaIncapacidadRequest params, 
			                                               Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {
		Integer totalRegistro = null;

		try {
			String sqlIncPorAdsc = "";
			if(params.getEstadoIncapacidad() == 2) {
				sqlIncPorAdsc = SQLIncapacidadClasificacionCasosConstants.QS_INC_ACT_ADSC_CON_CLASIFICACION_DEL_CASO_COUNT;

			} else {
				sqlIncPorAdsc = SQLIncapacidadClasificacionCasosConstants.QS_INC_RST_ADSC_CON_CLASIFICACION_DEL_CASO_COUNT;

			}

			totalRegistro = jdbcTemplate.queryForObject(sqlIncPorAdsc,
								new Object[] {
										params.getEstadoIncapacidad(),
										(params.getEstadoIncapacidad()== 2)?4:5,
										params.getIdDelegacion(),
										params.getIdDelegacion(),
										params.getIdClasificacion(),
										params.getIdClasificacion(),
										params.getIdUnidad(),
										params.getIdUnidad(),
										params.getIdRamo(),
										params.getIdRamo(),
										isEmpIMSS,
										isEmpIMSS,
										isntEmpIMSS,
										isntEmpIMSS,
										params.getNss(),
										params.getNss(),
										params.getCurp(),
										params.getCurp(),
										params.getIdTurno(),
										params.getIdTurno(),
										params.getConsultorio(),
										params.getConsultorio(),
										params.getNombre(),
										params.getNombre(),
										params.getApaterno(),
										params.getApaterno(),
										params.getAmaterno(),
										params.getAmaterno(),
										},
								(rs, rowNum) -> {
									return Integer.valueOf(rs.getInt("total"));
								});

		} catch (Exception ex) {
			logger.debug("Error al contar incapacidades por adscripcion con clasificacion de casos");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al contar incapacidades por adscripcion con clasificacion de casos", ex.getMessage());
		}
		
		return totalRegistro;
	}

	@Override
	public List<ConsultaIncapacidadesDTO> consultaIncapacidadPorExpedicion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS, Integer limit, Integer offset) throws BusinessException {

			List<ConsultaIncapacidadesDTO> consultaIncapacidad = null;
			String querySearch = (params.getEstadoIncapacidad() == 2) ?
					SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_EXPEDICION_ACTIVOS :
					SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_EXPEDICION;
			
			try {
				consultaIncapacidad = jdbcTemplate.query(querySearch,
										new Object[] {
												params.getEstadoIncapacidad(),
												(params.getEstadoIncapacidad()== 2)?4:5,
												params.getIdDelegacion(),
												params.getIdDelegacion(),
												params.getIdUnidad(),
												params.getIdUnidad(),
												params.getIdRamo(),
												params.getIdRamo(),
												isEmpIMSS,
												isEmpIMSS,
												isntEmpIMSS,
												isntEmpIMSS,
												params.getNss(),
												params.getNss(),
												params.getCurp(),
												params.getCurp(),
												
												params.getIdTurno(),
												params.getIdTurno(),
												params.getConsultorio(),
												params.getConsultorio(),
												
												params.getNombre(),
												params.getNombre(),
												params.getApaterno(),
												params.getApaterno(),
												params.getAmaterno(),
												params.getAmaterno(),
												offset,
												limit
												},
										(rs, rowNum) -> {
											ConsultaIncapacidadesDTO incapacidad = new ConsultaIncapacidadesDTO();
											incapacidad.setIdUnidad(rs.getLong("idUnidad"));
											incapacidad.setNombrePaciente(rs.getString("paciente").trim());
											incapacidad.setPrimerApellido(rs.getString("pApellido"));
											incapacidad.setSegundoApellido(rs.getString("sApellido"));
											incapacidad.setIdPaciente(rs.getLong("idPaciente"));
											incapacidad.setIdCaso(rs.getLong("idCaso"));
											incapacidad.setNss(rs.getString("nss"));
											incapacidad.setNombreUnidad(rs.getString("unidad"));
											incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumulados"));
											incapacidad.setInicioCaso(rs.getString("inicioCaso"));
											incapacidad.setFinCaso(rs.getString("finCaso"));
											return incapacidad;
										});
			} catch (Exception ex) {
				logger.debug("Error al consultar incapacidad por Expedicion");
				throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por Expedicion", ex.getMessage());
			}

			return consultaIncapacidad;
	}

	@Override
	public Integer consultaIncapacidadPorExpedicionCount(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {

		Integer totalRegistro = null;
		String querySearch = (params.getEstadoIncapacidad() == 2) ?
				SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_EXPEDICION_COUNT_ACTIVOS :
				SQLIncapacidadConstants.QS_CONSULTA_INCAPACIDADES_EXPEDICION_COUNT;

		try {
			totalRegistro = jdbcTemplate.queryForObject(
								querySearch,
								new Object[] {
										params.getEstadoIncapacidad(),
										(params.getEstadoIncapacidad()== 2)?4:5,
										params.getIdDelegacion(),
										params.getIdDelegacion(),
										params.getIdUnidad(),
										params.getIdUnidad(),
										params.getIdRamo(),
										params.getIdRamo(),
										isEmpIMSS,
										isEmpIMSS,
										isntEmpIMSS,
										isntEmpIMSS,
										params.getNss(),
										params.getNss(),
										params.getCurp(),
										params.getCurp(),
										
										params.getIdTurno(),
										params.getIdTurno(),
										params.getConsultorio(),
										params.getConsultorio(),
										
										params.getNombre(),
										params.getNombre(),
										params.getApaterno(),
										params.getApaterno(),
										params.getAmaterno(),
										params.getAmaterno(),
										},
								(rs, rowNum) -> {
									return Integer.valueOf(rs.getInt("total"));
								});
			
		} catch (Exception ex) {
			logger.debug("Error al contar incapacidades por Expedicion");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al contar incapacidades por Expedicion", ex.getMessage());
		}

		return totalRegistro;
	}
	
	//MASV 11/03/2021 para filtro de Clasificacion del Caso - U EXPEDICION
	@Override
	public List<ConsultaIncapacidadesDTO> consultaIncPorExpedicionConClasificacion(ConsultaIncapacidadRequest params,
																				   Integer isEmpIMSS, Integer isntEmpIMSS, 
																				   Integer limit, Integer offset) throws BusinessException {
		List<ConsultaIncapacidadesDTO> consultaIncapacidad = null;
		
		String sqlIncPorExp = "";
		if(params.getEstadoIncapacidad() == 2) {
			sqlIncPorExp = SQLIncapacidadClasificacionCasosConstants.QS_INC_ACT_EXP_CON_CLASIFICACION_DEL_CASO;

		} else {
			sqlIncPorExp = SQLIncapacidadClasificacionCasosConstants.QS_INC_RST_EXP_CON_CLASIFICACION_DEL_CASO;

		}

		try {
			consultaIncapacidad = jdbcTemplate.query(sqlIncPorExp,
													 new Object[] {
															 params.getEstadoIncapacidad(),
															 (params.getEstadoIncapacidad()== 2)?4:5,
															 params.getIdDelegacion(),
															 params.getIdDelegacion(),
															
															 params.getIdClasificacion(),
															 params.getIdClasificacion(),
															 
															 params.getIdUnidad(),
															 params.getIdUnidad(),
															 params.getIdRamo(),
															 params.getIdRamo(),
															 isEmpIMSS,
															 isEmpIMSS,
															 isntEmpIMSS,
															 isntEmpIMSS,
															 params.getNss(),
															 params.getNss(),
															 params.getCurp(),
															 params.getCurp(),
															 
															 params.getIdTurno(),
															 params.getIdTurno(),
															 params.getConsultorio(),
															 params.getConsultorio(),
															 
															 params.getNombre(),
															 params.getNombre(),
															 params.getApaterno(),
															 params.getApaterno(),
															 params.getAmaterno(),
															 params.getAmaterno(),
															 offset,
															 limit
													 },
													 (rs, rowNum) -> {
														 ConsultaIncapacidadesDTO incapacidad = new ConsultaIncapacidadesDTO();
														 incapacidad.setIdUnidad(rs.getLong("idUnidad"));
														 incapacidad.setNombrePaciente(rs.getString("paciente").trim());
														 incapacidad.setPrimerApellido(rs.getString("pApellido"));
														 incapacidad.setSegundoApellido(rs.getString("sApellido"));
														 incapacidad.setIdPaciente(rs.getLong("idPaciente"));
														 incapacidad.setIdCaso(rs.getLong("idCaso"));
														 incapacidad.setNss(rs.getString("nss"));
														 incapacidad.setNombreUnidad(rs.getString("unidad"));
														 incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumulados"));
														 incapacidad.setInicioCaso(rs.getString("inicioCaso"));
														 incapacidad.setFinCaso(rs.getString("finCaso"));
														 return incapacidad;
													 });
		} catch (Exception ex) {
			logger.debug("Error al consultar incapacidad por Expedicion  con clasificacion de casos");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por Expedicion  con clasificacion de casos", ex.getMessage());
		}

		return consultaIncapacidad;
	}

	//MASV 11/03/2021 para filtro de Clasificacion del Caso - U EXPEDICION
	@Override
	public Integer consultaIncPorExpedicionConClasificacionCount(ConsultaIncapacidadRequest params, 
			                                                     Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {
		Integer totalRegistro = null;
		String sqlIncPorAdsc = "";
		if(params.getEstadoIncapacidad() == 2) {
			sqlIncPorAdsc = SQLIncapacidadClasificacionCasosConstants.QS_INC_ACT_EXP_CON_CLASIFICACION_DEL_CASO_COUNT;

		} else {
			sqlIncPorAdsc = SQLIncapacidadClasificacionCasosConstants.QS_INC_RST_EXP_CON_CLASIFICACION_DEL_CASO_COUNT;

		}

		try {
			totalRegistro = jdbcTemplate.queryForObject(sqlIncPorAdsc,
														new Object[] {
																params.getEstadoIncapacidad(),
																(params.getEstadoIncapacidad()== 2)?4:5,
																params.getIdDelegacion(),
																params.getIdDelegacion(),
																
																params.getIdClasificacion(),
																params.getIdClasificacion(),
																
																params.getIdUnidad(),
																params.getIdUnidad(),
																params.getIdRamo(),
																params.getIdRamo(),
																isEmpIMSS,
																isEmpIMSS,
																isntEmpIMSS,
																isntEmpIMSS,
																params.getNss(),
																params.getNss(),
																params.getCurp(),
																params.getCurp(),
																
																params.getIdTurno(),
																params.getIdTurno(),
																params.getConsultorio(),
																params.getConsultorio(),
																
																params.getNombre(),
																params.getNombre(),
																params.getApaterno(),
																params.getApaterno(),
																params.getAmaterno(),
																params.getAmaterno(),
														},
														(rs, rowNum) -> {
															return Integer.valueOf(rs.getInt("total"));
														});
			
		} catch (Exception ex) {
			logger.debug("Error al contar incapacidades por Expedicion con clasificacion de casos");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al contar incapacidades por Expedicion con clasificacion de casos", ex.getMessage());
		}

		return totalRegistro;
	}

	

	@Override
	public ConsultaIncapacidadDTO consultaDetalleCaso(Long idCaso) throws BusinessException {
		
		ConsultaIncapacidadDTO detalleCaso = null;
		
		try {

			detalleCaso = jdbcTemplate.queryForObject(
					SQLIncapacidadConstants.QS_CONSULTA_DETALLE_INCAPACIDAD,
					new Object[] {idCaso},
					(rs, rowNum) -> {
						ConsultaIncapacidadDTO detalle = new ConsultaIncapacidadDTO();
						String papellido = rs.getString("papellido")!=null?" "+rs.getString("papellido").trim():"";
						String sapellido = rs.getString("sapellido")!=null?" "+rs.getString("sapellido").trim():"";
						detalle.setNombrePaciente(rs.getString("paciente").trim() + papellido + sapellido);
						detalle.setIdPaciente(rs.getLong("idPaciente"));
						detalle.setIdIncapacidad(rs.getLong("idIncapacidad"));
						detalle.setNss(rs.getString("nss").trim());
						detalle.setCurp(rs.getString("curp").trim());
						detalle.setNombreUnidad(rs.getString("unidad"));
						detalle.setIdUnidad(rs.getLong("idUnidad"));
						detalle.setUmf(rs.getString("umf"));
						detalle.setNombreTurno(rs.getString("turno"));
						detalle.setDescRamo(rs.getString("ramo"));
						detalle.setDescOcupacion(rs.getString("ocupacion"));
						detalle.setMontoAcumuladoSubsidio(rs.getDouble("montoAcumulado"));
						detalle.setUltimoDiagnostico(rs.getString("diagnostico")!=null?rs.getString("diagnostico").trim():"");
						detalle.setCveCie(rs.getString("cie"));
						detalle.setDpr(rs.getInt("dpr"));
						detalle.setMatricula(rs.getString("matMedico"));
						detalle.setCveRegistroPatronal(rs.getString("regPatronal"));
						detalle.setInicioCaso(rs.getString("inicioCaso"));
						detalle.setFinCaso(rs.getString("finCaso"));
						detalle.setIdCie(rs.getLong("idCie"));
						detalle.setIdTurno(rs.getLong("idTurno"));
						detalle.setIdOcupacion(rs.getLong("idOcupacion"));
						detalle.setIdCaso(idCaso);
						return detalle;
					});

		} catch (Exception ex) {
			logger.debug("Error al consultar el detalle de incapacidad");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar el detalle de incapacidad", ex.getMessage());
		}

		return detalleCaso;
	}
	
	// Todos los casos
	@Override
	public List<ConsultaTodosCasosDTO> consultaTodosLosCasos(String nss) throws BusinessException {
		
		List <ConsultaTodosCasosDTO> detalleCaso = null;
		
		try {

			detalleCaso = jdbcTemplate.query(
					SQLAgrupacionCasos.QS_CONSULTA_CASOS_TOTALES,
					new Object[] {nss},
					(rs, rowNum) -> {
						ConsultaTodosCasosDTO detalle = new ConsultaTodosCasosDTO();
						String papellido = rs.getString("papellido")!=null?" "+rs.getString("papellido").trim():"";
						String sapellido = rs.getString("sapellido")!=null?" "+rs.getString("sapellido").trim():"";
						detalle.setIdCaso(rs.getLong("idCaso"));
						detalle.setNombrePaciente(rs.getString("paciente").trim() + papellido + sapellido);
						detalle.setIdPaciente(rs.getLong("idPaciente"));
						detalle.setDesUnidadExpedicion(rs.getString("nombreUnidadExp"));
						detalle.setNombreUnidad(rs.getString("nombreUniAdscripcion"));
						detalle.setNss(rs.getString("nss").trim());
						detalle.setCveRegistroPatronal(rs.getString("regPatronal"));
						detalle.setDiasAcumulados(rs.getInt("diasAcumuladosIncapacidad"));
						detalle.setInicioCaso(rs.getDate("inicioCaso"));
						detalle.setFinCaso(rs.getDate("finCaso"));
						detalle.setUltimoDiagnostico(rs.getString("ultimoDiagnostico")!=null?rs.getString("ultimoDiagnostico").trim():"");
						detalle.setCveCie(rs.getString("cie"));
						detalle.setDpr(rs.getInt("dpr"));
						detalle.setMontoAcumulado(rs.getDouble("montoacumulado"));
						detalle.setEstadoIncapacidad(rs.getInt("estadoinc"));
						return detalle;
					});

		} catch (Exception ex) {
			logger.debug("Error al consultar todos los casos por nss");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar todos los casos por nss", ex.getMessage());
		}

		return detalleCaso;
	}
	
	@Override
	public int actualizaIncapacidadesConNuevoIDCaso(Long idCasoNuevo, String matriculaActualiza, Long idCasoAnterior) {
		 int status = jdbcTemplate.update(SQLAgrupacionCasos.QS_ACTUALIZA_INCAPACIDADES_CON_NUEVO_ID_CASO,
				idCasoNuevo, matriculaActualiza, idCasoAnterior); 
		return status;
	}
	
	@Override
	public List<Long> obtenerIdIncapacidadesPorCasoAgrupado(Long idCaso) throws BusinessException {
		List<Long> listaIdIncapacidades = null;
		
		try {
			listaIdIncapacidades = jdbcTemplate.query(
					SQLAgrupacionCasos.QS_CONSULTA_ID_INCAPACIDADES_POR_NUEVO_ID_CASO,
					new Object[] {idCaso},
					(rs, rowNum) -> {
						Long idIncapacidad = 0L;
						idIncapacidad = rs.getLong("IdIncapacidad");
						return idIncapacidad;
					});
					
			
		} catch (Exception ex) {
			logger.debug("Error al obtener los ID de incapacidades");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al obtener IDs de incapacidades por el nuevo ID del caso agrupado.", ex.getMessage());
		}
		
		return listaIdIncapacidades;
	}
	
	@Override
	public int actualizaIndUltComentario(Long idComentario, int indice) throws BusinessException {
		int status = jdbcTemplate.update(SQLAgrupacionCasos.QS_ACTUALIZA_IND_ULT_COMENTARIO,
				indice, idComentario); 
		return status;
	}
	
	@Override
	public String consultaUltimaCargaNSSA() throws BusinessException {
		String ultimaCarga = null;
		try {
			ultimaCarga = jdbcTemplate.queryForObject(SQLIncapacidadConstants.QS_CONSULTA_PARAMETRO_ULTIMA_CARGA,
							(rs, rowNum) -> {
								return rs.getString("ultimaCarga");
							});
		} catch(Exception ex ) {
			logger.debug("Error al consultar ultima carga NSSA");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar ultima carga NSSA", ex.getMessage());
		}
		
		return ultimaCarga;
	}
	
	public void agregaComentarioDiagnostico(ConsultaIncapacidadDTO consultaDTO, Long idCaso) throws BusinessException {

		try {
			ConsultaIncapacidadDTO result = jdbcTemplate.queryForObject(SQLIncapacidadConstants.QS_CONSULTA_ULTIMA_COMENTARIO,
							new Object[] {idCaso},
							(rs, rowNum) -> {
								ConsultaIncapacidadDTO detalle = null;
								if (rs!=null) {									
									detalle = new ConsultaIncapacidadDTO();
									detalle.setComentario(rs.getString("comentario"));
									detalle.setSituacionActual(rs.getString("situacionActual"));
								}
								return detalle;
							});

			if (result!=null) {
				consultaDTO.setComentario(result.getComentario());
				consultaDTO.setSituacionActual(result.getSituacionActual());
			}

		} catch (EmptyResultDataAccessException er){
			logger.debug("No se encontro ningun comentario para el caso: " + idCaso);
		} catch (Exception e) {
			logger.debug("Error al consultar ultimo comentario");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar ultimo comentario", e.getMessage());
		}
	}
	
	public List<IncapacidadGraficaModel> consultaIncapacidadesPorCaso(Long idCaso) throws BusinessException {
		List<IncapacidadGraficaModel> incapacidadList = null;

		try {
			incapacidadList = jdbcTemplate.query(SQLIncapacidadConstants.QS_CONSULTA_LISTA_INCAPACIDAD,
							new Object[] {idCaso},
							(rs, rowNum) -> {
								IncapacidadGraficaModel incapacidades = new IncapacidadGraficaModel();
								incapacidades.setExpedicion(rs.getString("unidad"));
								incapacidades.setFolio(rs.getString("folio"));
								incapacidades.setDiasAutorizados(rs.getInt("dias"));
								incapacidades.setMatriculaMedico(rs.getString("medico"));
								incapacidades.setDiasLimite(rs.getInt("limite"));
								incapacidades.setNivelIncapacidad(rs.getInt("nivel"));
								incapacidades.setFechaInicio(rs.getString("fechaInicio"));
								incapacidades.setFechaFin(rs.getString("fechaFin"));
								incapacidades.setIdRamo(rs.getLong("idRamo"));
								incapacidades.setDescRamo(rs.getString("descRamo"));
								return incapacidades;
							});
		} catch (Exception e) {
			logger.debug("Error al consultar listado de incapacidades");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar listado de incapacidades", e.getMessage());
		}
		
		return incapacidadList;
	}
	
	public UpdatePacienteDTO consultaPacientePorId(Long idPaciente) throws BusinessException {
		UpdatePacienteDTO paciente = null;
		
		try {
			paciente = jdbcTemplate.queryForObject(SQLIncapacidadConstants.QS_CONSULTA_DETALLE_PACIENTE,
					new Object [] {idPaciente},
					(rs, rowNum) -> {
						UpdatePacienteDTO p = new UpdatePacienteDTO();
						p.setConsultorio(rs.getString("consultorio"));
						p.setIdOcupacion(rs.getLong("idOcupacion")!=0?rs.getLong("idOcupacion"):null);
						p.setIdPaciente(rs.getLong("idPaciente")!=0?rs.getLong("idPaciente"):null);
						p.setIdTurno(rs.getLong("idTurno")!=0?rs.getLong("idTurno"):null);
						return p;
			});
		} catch (Exception e ) {
			logger.debug("Error al consultar paciente especifico");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar paciente especifico", e.getMessage());
		}
		
		return paciente;
	}
	
	@Transactional
	public int updatePaciente(UpdatePacienteDTO paciente) throws BusinessException {
		try {
			return jdbcTemplate.update(SQLIncapacidadConstants.QU_ACTUALIZA_PACIENTE,
					new Object[] {
							paciente.getConsultorio(),
							paciente.getIdOcupacion(),
							paciente.getIdTurno(),
							paciente.getMatriculaUsuario(),
							paciente.getIdPaciente()
					});
		} catch (Exception e) {
			logger.debug("Error al actualizar paciente especifico");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al actualizar paciente especifico", e.getMessage());
		}
	}
	
	@Transactional
	public int updateCieIncapacidad(UpdatePacienteDTO paciente ) throws BusinessException {
		
		try {
			return jdbcTemplate.update(SQLIncapacidadConstants.QU_ACTUALIZA_CIE_INCAPACIDAD,
					new Object[] {
							paciente.getIdCie(),
							paciente.getCie(),
							paciente.getMatriculaUsuario(),
							paciente.getIdIncapacidad()
					});
		} catch (Exception e) {
			logger.debug("Error al actualizar paciente especifico");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al agregar nuevo diagnostico", e.getMessage());
		}
	}
	
	@Transactional
	public int createDiagnostico(UpdatePacienteDTO paciente) throws BusinessException {
		try {
			return jdbcTemplate.update(SQLIncapacidadConstants.QI_AGREGA_NUEVO_DIAGNOSTICO_POR_CIE,
					new Object[] {
							paciente.getIdIncapacidad(),
							paciente.getCie(),
							paciente.getMatriculaUsuario(),
							paciente.getMatriculaUsuario()
					});	
		} catch (Exception e) {
			logger.debug("Error al actualizar paciente especifico");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al agregar nuevo diagnostico", e.getMessage());
		}
	}
	
	@Override
	public List<SCIRespuestaHistoricoDTO> consultaHistoricoIncapacidades(
			SCIParametrosHistoricoIncapacidadesDTO parametros, Integer limit, Integer offset) throws BusinessException {
		List<SCIRespuestaHistoricoDTO> consultaHistorico = null;
		
		try {
			consultaHistorico = jdbcTemplate.query(SQLIncapacidadConstants.QS_CONSULTA_HISTORICO,
									new Object[] {
											parametros.getIdUnidadAdscripcion(),
											parametros.getNss(),
											parametros.getNombre(),
											parametros.getNombre(),
											parametros.getAPaterno(),
											parametros.getAPaterno(),
											parametros.getAMaterno(),
											parametros.getAMaterno(),
											offset,
											limit
											},
									(rs, rowNum) -> {
										SCIRespuestaHistoricoDTO historico = new SCIRespuestaHistoricoDTO();
										historico.setNss(rs.getString("nss"));
										historico.setApPaterno(rs.getString("ap_paterno").trim());
										historico.setApMaterno(rs.getString("ap_materno").trim());
										historico.setNombre(rs.getString("nom_paciente").trim());
										historico.setUnidadAdscripcion(rs.getString("unidad_adscripcion"));
										historico.setDescripcionUnidadAdscripcion(rs.getString("descripcion_unidad_adscripcion"));
										historico.setIdCaso(rs.getLong("id_caso"));
										historico.setEstadoCaso(rs.getString("estado_caso"));									
										historico.setIdIncapacidad(rs.getLong("id_incapacidad"));
										historico.setEstadoIncapacidad(rs.getString("estado_incapacidad"));
										historico.setRefFolio(rs.getString("folio"));
										historico.setFecInicio(rs.getString("fecha_inicio"));
										historico.setFecTermino(rs.getString("fecha_termino"));
										historico.setDiasSubsidiados(rs.getInt("dias_subsidiados"));
										historico.setDiasAutorizados(rs.getInt("dias_autorizados"));
										historico.setDiasAcumulados(rs.getInt("dias_acumulados"));
										historico.setEstadoIncapacidad(rs.getString("estado_incapacidad"));
										historico.setTipoIncapacidad(rs.getString("cve_tincapacidad"));
										historico.setDescTipoIncapacidad(rs.getString("des_tincapacidad"));
										historico.setRamo(rs.getString("cve_ramo"));
										historico.setDescRamo(rs.getString("des_ramo"));
										historico.setMatriculaMedico(rs.getString("cve_matricula"));
										historico.setUnidadExpedicion(rs.getString("cve_presupuestal_expedicion"));
										historico.setDescUnidadExpedicion(rs.getString("des_nombre_expedicion"));
										historico.setRefDiagnostico(rs.getString("des_medica"));
										return historico;
									});

		} catch (Exception ex) {
			logger.debug("Error al consultar el historico de incapacidades");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar el historico de incapacidades", ex.getMessage());
		}
		return consultaHistorico;
	}
	
	@Override
	public Integer totalConsultaHistoricoIncapacidades(SCIParametrosHistoricoIncapacidadesDTO parametros) throws BusinessException{
		Integer totalRegistro = null;

		try {

			totalRegistro = jdbcTemplate.queryForObject(
								SQLIncapacidadConstants.QS_CONSULTA_HISTORICO_COUNT, 
								new Object[] {
										parametros.getIdUnidadAdscripcion(),
										parametros.getNss(),
										parametros.getNombre(),
										parametros.getNombre(),
										parametros.getAPaterno(),
										parametros.getAPaterno(),
										parametros.getAMaterno(),
										parametros.getAMaterno()
										},
								(rs, rowNum) -> {
									return Integer.valueOf(rs.getInt("total_historico"));
								});
		} catch (Exception ex) {
			logger.debug("Error al contar incapacidades por Expedicion");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al contar incapacidades por Expedicion", ex.getMessage());
		}
		return totalRegistro;
	}
	
	//METODO ADSCRIPCION CON CLASIFICACION
	@Override
	public List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorAdscripcionConClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {

		List<ExportIncapacidadesDTO> consultaIncapacidad = null;
		
		try {
			String sqlIncPorAdsc = "";
			if(params.getEstadoIncapacidad() == 2) {
				sqlIncPorAdsc = SQLTodasIncapacidades.QS_INC_TODAS_ACT_ADSC_CON_CLASIFICACION_DEL_CASO;
			} 
			else {
				sqlIncPorAdsc = SQLTodasIncapacidades.QS_INC_TODAS_RST_ADSC_CON_CLASIFICACION_DEL_CASO;
			}
			consultaIncapacidad = jdbcTemplate.query(sqlIncPorAdsc,
						new Object[] {
								params.getEstadoIncapacidad(),
								(params.getEstadoIncapacidad()==2)?4:5,
								params.getIdDelegacion(), params.getIdDelegacion(),
								params.getIdClasificacion(), params.getIdClasificacion(),
								params.getIdUnidad(), params.getIdUnidad(),
								params.getIdRamo(), params.getIdRamo(),
								isEmpIMSS, isEmpIMSS,
								isntEmpIMSS, isntEmpIMSS,
								params.getNss(), params.getNss(),
								params.getCurp(), params.getCurp(),
								params.getIdTurno(), params.getIdTurno(),
								params.getConsultorio(), params.getConsultorio(),
								params.getNombre(), params.getNombre(),
								params.getApaterno(), params.getApaterno(),
								params.getAmaterno(), params.getAmaterno(),
								},
						(rs, rowNum) -> {
							ExportIncapacidadesDTO incapacidad = new ExportIncapacidadesDTO();
							incapacidad.setIdCaso(rs.getLong("idCaso"));
							incapacidad.setIdPaciente(rs.getLong("idPaciente"));
							incapacidad.setNombrePaciente(rs.getString("nombrePaciente").trim());
							incapacidad.setPrimerApellido(rs.getString("primerApellido"));
							incapacidad.setSegundoApellido(rs.getString("segundoApellido"));
							incapacidad.setNss(rs.getString("nss"));
							incapacidad.setIdUnidad(rs.getLong("idUnidad"));
							incapacidad.setNombreUnidad(rs.getString("nombreUnidad"));
							incapacidad.setNombreUnidadExp(rs.getString("nombreUnidadExp"));
							incapacidad.setIdDelegacion(rs.getLong("idDelegacion"));
							incapacidad.setNombreDelegacion(rs.getString("nombreDelegacion"));
							incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumuladosIncapacidad"));
							incapacidad.setInicioCaso(rs.getDate("inicioCaso"));
							incapacidad.setFinCaso(rs.getDate("finCaso"));
							incapacidad.setIdIncapacidad(rs.getLong("idIncapacidad"));
							incapacidad.setCurp(rs.getString("curp").trim());
							incapacidad.setUmf(rs.getString("umf"));
							incapacidad.setNombreTurno(rs.getString("nombreTurno"));
							incapacidad.setDescRamo(rs.getString("descRamo"));
							incapacidad.setDescOcupacion(rs.getString("descOcupacion"));
							incapacidad.setMontoAcumuladoSubsidio(rs.getDouble("montoAcumuladoSubsidio"));
							incapacidad.setUltimoDiagnostico(rs.getString("ultimoDiagnostico")!=null?rs.getString("ultimoDiagnostico").trim():"");
							incapacidad.setCveCie(rs.getString("cveCie"));
							incapacidad.setDpr(rs.getInt("dpr"));
							incapacidad.setMatriculaMedico(rs.getString("matriculaMedico"));
							incapacidad.setCveRegistroPatronal(rs.getString("cveRegistroPatronal"));
							incapacidad.setIdCie(rs.getLong("idCie"));
							incapacidad.setIdTurno(rs.getLong("idTurno"));
							incapacidad.setIdOcupacion(rs.getLong("idOcupacion"));
							incapacidad.setComentario(rs.getString("comentario"));
							incapacidad.setClaveClasificacion(rs.getString("cveClasificacion"));
							incapacidad.setDescClasificacion(rs.getString("descClasificacion"));
							incapacidad.setFechaEmision(rs.getDate("fecComentario"));
							incapacidad.setFechaRevaloracion(rs.getDate("proximaValoracion"));
							return incapacidad;
						});
			
		} catch (Exception ex) {
			logger.debug("Error al consultar todas incapacidades para exportado por adscripcion");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por adscripcion", ex.getMessage());
		}

		return consultaIncapacidad;
	}
	
	
	// METODO ADSCRIPCION SIN CLASIFICAION 
	@Override
	public List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorAdscripcionSinClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {

		List<ExportIncapacidadesDTO> consultaIncapacidad = null;
		
		try {
			String sqlIncPorAdsc = "";
			if(params.getEstadoIncapacidad() == 2) {
				sqlIncPorAdsc = SQLTodasIncapacidades.QS_INC_TODAS_ACT_ADSC_SIN_CLASIFICACION_DEL_CASO;
			}

			else {
				sqlIncPorAdsc = SQLTodasIncapacidades.QS_INC_TODAS_RST_ADSC_SIN_CLASIFICACION_DEL_CASO;
			}
			consultaIncapacidad = jdbcTemplate.query(sqlIncPorAdsc,
						new Object[] {
								params.getEstadoIncapacidad(),
								(params.getEstadoIncapacidad()==2)?4:5,
								params.getIdDelegacion(), params.getIdDelegacion(),
								params.getIdUnidad(), params.getIdUnidad(),
								params.getIdRamo(), params.getIdRamo(),
								isEmpIMSS, isEmpIMSS,
								isntEmpIMSS, isntEmpIMSS,
								params.getNss(), params.getNss(),
								params.getCurp(), params.getCurp(),
								params.getIdTurno(), params.getIdTurno(),
								params.getConsultorio(), params.getConsultorio(),
								params.getNombre(), params.getNombre(),
								params.getApaterno(), params.getApaterno(),
								params.getAmaterno(), params.getAmaterno(),
								//UNION
								params.getEstadoIncapacidad(),
								(params.getEstadoIncapacidad()==2)?4:5,
								params.getIdDelegacion(), params.getIdDelegacion(),
								params.getIdUnidad(), params.getIdUnidad(),
								params.getIdRamo(), params.getIdRamo(),
								isEmpIMSS, isEmpIMSS,
								isntEmpIMSS, isntEmpIMSS,
								params.getNss(), params.getNss(),
								params.getCurp(), params.getCurp(),
								params.getIdTurno(), params.getIdTurno(),
								params.getConsultorio(), params.getConsultorio(),
								params.getNombre(), params.getNombre(),
								params.getApaterno(), params.getApaterno(),
								params.getAmaterno(), params.getAmaterno(),
								},
						(rs, rowNum) -> {
							ExportIncapacidadesDTO incapacidad = new ExportIncapacidadesDTO();
							incapacidad.setIdCaso(rs.getLong("idCaso"));
							incapacidad.setIdPaciente(rs.getLong("idPaciente"));
							incapacidad.setNombrePaciente(rs.getString("nombrePaciente").trim());
							incapacidad.setPrimerApellido(rs.getString("primerApellido"));
							incapacidad.setSegundoApellido(rs.getString("segundoApellido"));
							incapacidad.setNss(rs.getString("nss"));
							incapacidad.setIdUnidad(rs.getLong("idUnidad"));
							incapacidad.setNombreUnidad(rs.getString("nombreUnidad"));
							incapacidad.setNombreUnidadExp(rs.getString("nombreUnidadExp"));
							incapacidad.setIdDelegacion(rs.getLong("idDelegacion"));
							incapacidad.setNombreDelegacion(rs.getString("nombreDelegacion"));
							incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumuladosIncapacidad"));
							incapacidad.setInicioCaso(rs.getDate("inicioCaso"));
							incapacidad.setFinCaso(rs.getDate("finCaso"));
							incapacidad.setIdIncapacidad(rs.getLong("idIncapacidad"));
							incapacidad.setCurp(rs.getString("curp").trim());
							incapacidad.setUmf(rs.getString("umf"));
							incapacidad.setNombreTurno(rs.getString("nombreTurno"));
							incapacidad.setDescRamo(rs.getString("descRamo"));
							incapacidad.setDescOcupacion(rs.getString("descOcupacion"));
							incapacidad.setMontoAcumuladoSubsidio(rs.getDouble("montoAcumuladoSubsidio"));
							incapacidad.setUltimoDiagnostico(rs.getString("ultimoDiagnostico")!=null?rs.getString("ultimoDiagnostico").trim():"");
							incapacidad.setCveCie(rs.getString("cveCie"));
							incapacidad.setDpr(rs.getInt("dpr"));
							incapacidad.setMatriculaMedico(rs.getString("matriculaMedico"));
							incapacidad.setCveRegistroPatronal(rs.getString("cveRegistroPatronal"));
							incapacidad.setIdCie(rs.getLong("idCie"));
							incapacidad.setIdTurno(rs.getLong("idTurno"));
							incapacidad.setIdOcupacion(rs.getLong("idOcupacion"));
							incapacidad.setComentario(rs.getString("comentario"));
							incapacidad.setClaveClasificacion(rs.getString("cveClasificacion"));
							incapacidad.setDescClasificacion(rs.getString("descClasificacion"));
							incapacidad.setFechaEmision(rs.getDate("fecComentario"));
							incapacidad.setFechaRevaloracion(rs.getDate("proximaValoracion"));
							return incapacidad;
						});
			
		} catch (Exception ex) {
			logger.debug("Error al consultar todas incapacidades para exportado por adscripcion");
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por adscripcion", ex.getMessage());
		}

		return consultaIncapacidad;
	}
	
	//METODO EXPEDICION CON CLASIFICACION
	@Override
	public List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorExpedicionConClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {
			
			List<ExportIncapacidadesDTO> consultaIncapacidad = null;
			try {
				String sqlIncPorExp = "";
				if(params.getEstadoIncapacidad() == 2) {
					sqlIncPorExp = SQLTodasIncapacidades.QS_INC_TODAS_ACT_EXP_CON_CLASIFICACION_DEL_CASO;
				} else {
					sqlIncPorExp = SQLTodasIncapacidades.QS_INC_TODAS_RST_EXP_CON_CLASIFICACION_DEL_CASO;
				}
				consultaIncapacidad = jdbcTemplate.query(sqlIncPorExp,
									new Object[] {
									params.getEstadoIncapacidad(),
									(params.getEstadoIncapacidad()==2)?4:5,
									params.getIdDelegacion(), params.getIdDelegacion(),
									params.getIdClasificacion(), params.getIdClasificacion(),
									params.getIdUnidad(), params.getIdUnidad(),
									params.getIdRamo(), params.getIdRamo(),
									isEmpIMSS, isEmpIMSS,
									isntEmpIMSS, isntEmpIMSS,
									params.getNss(), params.getNss(),
									params.getCurp(), params.getCurp(),
									params.getIdTurno(), params.getIdTurno(),
									params.getConsultorio(), params.getConsultorio(),
									params.getNombre(), params.getNombre(),
									params.getApaterno(), params.getApaterno(),
									params.getAmaterno(), params.getAmaterno()
									},
							(rs, rowNum) -> {
								ExportIncapacidadesDTO incapacidad = new ExportIncapacidadesDTO();
								incapacidad.setIdCaso(rs.getLong("idCaso"));
								incapacidad.setIdPaciente(rs.getLong("idPaciente"));
								incapacidad.setNombrePaciente(rs.getString("nombrePaciente").trim());
								incapacidad.setPrimerApellido(rs.getString("primerApellido"));
								incapacidad.setSegundoApellido(rs.getString("segundoApellido"));
								incapacidad.setNss(rs.getString("nss"));
								incapacidad.setIdUnidad(rs.getLong("idUnidad"));
								incapacidad.setNombreUnidad(rs.getString("nombreUnidad"));
								incapacidad.setNombreUnidadExp(rs.getString("nombreUnidadExp"));
								incapacidad.setIdDelegacion(rs.getLong("idDelegacion"));
								incapacidad.setNombreDelegacion(rs.getString("nombreDelegacion"));
								incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumuladosIncapacidad"));
								incapacidad.setInicioCaso(rs.getDate("inicioCaso"));
								incapacidad.setFinCaso(rs.getDate("finCaso"));
								incapacidad.setIdIncapacidad(rs.getLong("idIncapacidad"));
								incapacidad.setCurp(rs.getString("curp").trim());
								incapacidad.setUmf(rs.getString("umf"));
								incapacidad.setNombreTurno(rs.getString("nombreTurno"));
								incapacidad.setDescRamo(rs.getString("descRamo"));
								incapacidad.setDescOcupacion(rs.getString("descOcupacion"));
								incapacidad.setMontoAcumuladoSubsidio(rs.getDouble("montoAcumuladoSubsidio"));
								incapacidad.setUltimoDiagnostico(rs.getString("ultimoDiagnostico")!=null?rs.getString("ultimoDiagnostico").trim():"");
								incapacidad.setCveCie(rs.getString("cveCie"));
								incapacidad.setDpr(rs.getInt("dpr"));
								incapacidad.setMatriculaMedico(rs.getString("matriculaMedico"));
								incapacidad.setCveRegistroPatronal(rs.getString("cveRegistroPatronal"));
								incapacidad.setIdCie(rs.getLong("idCie"));
								incapacidad.setIdTurno(rs.getLong("idTurno"));
								incapacidad.setIdOcupacion(rs.getLong("idOcupacion"));
								incapacidad.setComentario(rs.getString("comentario"));
								incapacidad.setClaveClasificacion(rs.getString("cveClasificacion"));
								incapacidad.setDescClasificacion(rs.getString("descClasificacion"));
								incapacidad.setFechaEmision(rs.getDate("fecComentario"));
								incapacidad.setFechaRevaloracion(rs.getDate("proximaValoracion"));
								return incapacidad;
							});
				
			} catch (Exception ex) {
				logger.debug("Error al consultar incapacidad por Expedicion");
				throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por Expedicion", ex.getMessage());
			}

			return consultaIncapacidad;
	}
	
	//METODO EXPEDICION SIN CLASIFICACION
	@Override
	public List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorExpedicionSinClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException {
			List<ExportIncapacidadesDTO> consultaIncapacidad = null;
			try {
				String sqlIncPorExp = "";
				if(params.getEstadoIncapacidad() == 2) {
						sqlIncPorExp = SQLTodasIncapacidades.QS_INC_TODAS_ACT_EXP_SIN_CLASIFICACION_DEL_CASO;
				}
				else {
						sqlIncPorExp = SQLTodasIncapacidades.QS_INC_TODAS_RST_EXP_SIN_CLASIFICACION_DEL_CASO;
				}
				consultaIncapacidad = jdbcTemplate.query(sqlIncPorExp,
									new Object[] {
									params.getEstadoIncapacidad(),
									(params.getEstadoIncapacidad()==2)?4:5,
									params.getIdDelegacion(), params.getIdDelegacion(),
									params.getIdUnidad(), params.getIdUnidad(),
									params.getIdRamo(), params.getIdRamo(),
									isEmpIMSS, isEmpIMSS,
									isntEmpIMSS, isntEmpIMSS,
									params.getNss(), params.getNss(),
									params.getCurp(), params.getCurp(),
									params.getIdTurno(), params.getIdTurno(),
									params.getConsultorio(), params.getConsultorio(),
									params.getNombre(), params.getNombre(),
									params.getApaterno(), params.getApaterno(),
									params.getAmaterno(), params.getAmaterno(),
									//UNION
									params.getEstadoIncapacidad(),
									(params.getEstadoIncapacidad()==2)?4:5,
									params.getIdDelegacion(), params.getIdDelegacion(),
									params.getIdUnidad(), params.getIdUnidad(),
									params.getIdRamo(), params.getIdRamo(),
									isEmpIMSS, isEmpIMSS,
									isntEmpIMSS, isntEmpIMSS,
									params.getNss(), params.getNss(),
									params.getCurp(), params.getCurp(),
									params.getIdTurno(), params.getIdTurno(),
									params.getConsultorio(), params.getConsultorio(),
									params.getNombre(), params.getNombre(),
									params.getApaterno(), params.getApaterno(),
									params.getAmaterno(), params.getAmaterno(),
									},
							(rs, rowNum) -> {
								ExportIncapacidadesDTO incapacidad = new ExportIncapacidadesDTO();
								incapacidad.setIdCaso(rs.getLong("idCaso"));
								incapacidad.setIdPaciente(rs.getLong("idPaciente"));
								incapacidad.setNombrePaciente(rs.getString("nombrePaciente").trim());
								incapacidad.setPrimerApellido(rs.getString("primerApellido"));
								incapacidad.setSegundoApellido(rs.getString("segundoApellido"));
								incapacidad.setNss(rs.getString("nss"));
								incapacidad.setIdUnidad(rs.getLong("idUnidad"));
								incapacidad.setNombreUnidad(rs.getString("nombreUnidad"));
								incapacidad.setNombreUnidadExp(rs.getString("nombreUnidadExp"));
								incapacidad.setIdDelegacion(rs.getLong("idDelegacion"));
								incapacidad.setNombreDelegacion(rs.getString("nombreDelegacion"));
								incapacidad.setDiasAcumuladosIncapacidad(rs.getInt("diasAcumuladosIncapacidad"));
								incapacidad.setInicioCaso(rs.getDate("inicioCaso"));
								incapacidad.setFinCaso(rs.getDate("finCaso"));
								incapacidad.setIdIncapacidad(rs.getLong("idIncapacidad"));
								incapacidad.setCurp(rs.getString("curp").trim());
								incapacidad.setUmf(rs.getString("umf"));
								incapacidad.setNombreTurno(rs.getString("nombreTurno"));
								incapacidad.setDescRamo(rs.getString("descRamo"));
								incapacidad.setDescOcupacion(rs.getString("descOcupacion"));
								incapacidad.setMontoAcumuladoSubsidio(rs.getDouble("montoAcumuladoSubsidio"));
								incapacidad.setUltimoDiagnostico(rs.getString("ultimoDiagnostico")!=null?rs.getString("ultimoDiagnostico").trim():"");
								incapacidad.setCveCie(rs.getString("cveCie"));
								incapacidad.setDpr(rs.getInt("dpr"));
								incapacidad.setMatriculaMedico(rs.getString("matriculaMedico"));
								incapacidad.setCveRegistroPatronal(rs.getString("cveRegistroPatronal"));
								incapacidad.setIdCie(rs.getLong("idCie"));
								incapacidad.setIdTurno(rs.getLong("idTurno"));
								incapacidad.setIdOcupacion(rs.getLong("idOcupacion"));
								incapacidad.setComentario(rs.getString("comentario"));
								incapacidad.setClaveClasificacion(rs.getString("cveClasificacion"));
								incapacidad.setDescClasificacion(rs.getString("descClasificacion"));
								incapacidad.setFechaEmision(rs.getDate("fecComentario"));
								incapacidad.setFechaRevaloracion(rs.getDate("proximaValoracion"));
								return incapacidad;
							});
				
			} catch (Exception ex) {
				logger.debug("Error al consultar incapacidad por Expedicion");
				throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al consultar incapacidad por Expedicion", ex.getMessage());
			}

			return consultaIncapacidad;
	}
	

}
