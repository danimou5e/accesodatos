package mx.gob.imss.cit.sci.mssci.accesodatos.service.impl;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.sci.mssci.accesodatos.dto.AgrupaCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaTodosCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ExportIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.PaginadoConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIPaginadoConsultaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIParametrosHistoricoIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIRespuestaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.UpdatePacienteDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.EnumHttpStatus;
import mx.gob.imss.cit.sci.mssci.accesodatos.exceptions.BusinessException;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.CasoRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.CasoActivosRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.CasoAgrupadoDetRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.CasoAgrupadoRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.CieRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.ComentarioRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.IncapacidadRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.IncapacidadRepositoryJPA;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.OcupacionRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.SCITCasoRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.TurnoRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.Caso;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.CasoActivos;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.CasoAgrupado;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.CasoAgrupadoDet;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.CometarioJPA;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.IncapacidadGraficaModel;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.Paciente;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITIncapacidad;
import mx.gob.imss.cit.sci.mssci.accesodatos.service.IncapacidadService;

@Service
public class IncapacidadServiceImpl implements IncapacidadService {

	private static final Logger LOG = LoggerFactory.getLogger(IncapacidadServiceImpl.class);
	
	
	@Autowired
	IncapacidadRepository incapacidadRepository;
	
	@Autowired
	IncapacidadRepositoryJPA incapacidadRepositoryJpa;
	
	@Autowired
	OcupacionRepository ocupacionRepository;
	
	@Autowired
	TurnoRepository turnoRepository;

	@Autowired
	CieRepository cieRepository;
	
	@Autowired
	SCITCasoRepository casoRepository;
	
	@Autowired
	CasoRepository casoRepositoryJPA;
	
	@Autowired
	CasoActivosRepository casoActivosRepository;
	
	@Autowired
	CasoAgrupadoRepository casoAgrupadoRepository;
	
	@Autowired
	CasoAgrupadoDetRepository casoAgrupadoDetRepository;
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Override
	public PaginadoConsultaIncapacidadDTO consultaIncapacidades(ConsultaIncapacidadRequest params, Integer limit, Integer offset) throws BusinessException {

		List<ConsultaIncapacidadesDTO> paginadoIncapacidad = null;
		PaginadoConsultaIncapacidadDTO paginadoResponse = null;
		Integer incapacidadesTotales = null;
		Integer isEmpIMSS = null;
		Integer isntEmpIMSS = null;

		validateConsultaIncParams(params, limit, offset);

		offset = offset > 0 ? offset-1 : 0;
		
		if (params.getIsEmpleadoIMSS() != null && params.getIsEmpleadoIMSS() == true) {
			isEmpIMSS = 4;
		} else if (params.getIsEmpleadoIMSS() != null && params.getIsEmpleadoIMSS() == false) {
			isntEmpIMSS = 4;
		}
		
		if (params.getIsAdscripcion()) {
			if(params.getIdClasificacion() != null) {
				paginadoIncapacidad = incapacidadRepository.consultaIncPorAdscConClasificacion(params, isEmpIMSS, isntEmpIMSS, limit, offset);

			} else {
				paginadoIncapacidad = incapacidadRepository.consultaIncapacidadPorAdscripcion(params, isEmpIMSS, isntEmpIMSS, limit, offset);
			}
			
			if (paginadoIncapacidad!= null && !paginadoIncapacidad.isEmpty()) {
				if (offset <=1) {
					if(params.getIdClasificacion() != null) {
						incapacidadesTotales = incapacidadRepository.consultaIncPorAdscConClasificacionCount(params, isEmpIMSS, isntEmpIMSS);

					} else {
						incapacidadesTotales = incapacidadRepository.consultaIncapacidadPorAdscripcionCount(params, isEmpIMSS, isntEmpIMSS);

					}
				}
				paginadoResponse = new PaginadoConsultaIncapacidadDTO();
				paginadoResponse.setConsultaIncapacidadList(paginadoIncapacidad);
				paginadoResponse.setNumIncapacidades(incapacidadesTotales);
				String ultimaCarga = incapacidadRepository.consultaUltimaCargaNSSA();
				paginadoResponse.setUltimaCargaNSSA(ultimaCarga);
				
			}
		} else {
			if(params.getIdClasificacion() != null) {
				paginadoIncapacidad = incapacidadRepository.consultaIncPorExpedicionConClasificacion(params, isEmpIMSS, isntEmpIMSS, limit, offset);
			} else {
				paginadoIncapacidad = incapacidadRepository.consultaIncapacidadPorExpedicion(params, isEmpIMSS, isntEmpIMSS,limit, offset);
			}
			if (paginadoIncapacidad!= null && !paginadoIncapacidad.isEmpty()) {
				if (offset <=1) {
					if(params.getIdClasificacion() != null) {
						incapacidadesTotales = incapacidadRepository.consultaIncPorExpedicionConClasificacionCount(params, isEmpIMSS, isntEmpIMSS);
					} else {
						incapacidadesTotales = incapacidadRepository.consultaIncapacidadPorExpedicionCount(params, isEmpIMSS, isntEmpIMSS);
					}
				}
				paginadoResponse = new PaginadoConsultaIncapacidadDTO();
				paginadoResponse.setConsultaIncapacidadList(paginadoIncapacidad);
				paginadoResponse.setNumIncapacidades(incapacidadesTotales);
				String ultimaCarga = incapacidadRepository.consultaUltimaCargaNSSA();
				paginadoResponse.setUltimaCargaNSSA(ultimaCarga);
			}
		}
		
		if (paginadoIncapacidad == null || paginadoIncapacidad.isEmpty()) {
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "No se encontraron registros que cumplan con los criterios de busqueda", EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}
		return paginadoResponse;

	}

	@Override
	public ConsultaIncapacidadDTO consultaIncapacidadPorIdCaso(Long idCaso) throws BusinessException {
		ConsultaIncapacidadDTO detalleCaso = null;

		if (idCaso==null) {
			LOG.debug("Error al validar campos requeridos");
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST, "La propiedad idCaso es requerida", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}
		
		detalleCaso = incapacidadRepository.consultaDetalleCaso(idCaso);
		
		if ( detalleCaso == null ) {
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "Registro no encontrado", EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}

		String ultimaCarga = incapacidadRepository.consultaUltimaCargaNSSA();
		detalleCaso.setUltimaCargaNSSA(ultimaCarga);
		incapacidadRepository.agregaComentarioDiagnostico(detalleCaso, idCaso);

		List<IncapacidadGraficaModel> incapacidades = incapacidadRepository.consultaIncapacidadesPorCaso(idCaso);
		if (incapacidades.size() > 0) {
			detalleCaso.setLimiteDias(incapacidades.get(0).getDiasLimite());
			detalleCaso.setIncapacidadGraficaModel(incapacidades);			
		}

		return detalleCaso;
	}
	
	// COnsulta todos los casos
	@Override
	public List<ConsultaTodosCasosDTO> consultaTodosLosCasos(String nss) throws BusinessException {
		List<ConsultaTodosCasosDTO> detalleCaso = null;
		
		if (nss==null || nss.equals("")) {
			LOG.debug("Error al recibir el idPaciente");
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST, "La propiedad nss es requerida", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}
		
		detalleCaso = incapacidadRepository.consultaTodosLosCasos(nss);
		
		if ( detalleCaso == null ) {
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "No se encontraron casos con el NSS de ese paciente", EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}
		
		return detalleCaso;
		
		
	}
	
	public Caso insertaNuevoCasoAgrupado(AgrupaCasosDTO agrupaCasos) throws BusinessException {
		Caso caso = new Caso();
		
		//Armar caso a agrupar
		Paciente pac = new Paciente();
		pac.setIdPaciente(agrupaCasos.getCasosSeleccionados().get(0).getIdPaciente());
		caso.setIdPaciente(pac.getIdPaciente());
		caso.setIdEstadoIncapacidad(agrupaCasos.getNuevoEstadoIncapacidad());
		
		int lastIndex = (agrupaCasos.getCasosSeleccionados().size())-1;
		Date fechaInicio = agrupaCasos.getCasosSeleccionados().get(lastIndex).getInicioCaso();
		Date fechaFin = agrupaCasos.getCasosSeleccionados().get(0).getFinCaso();
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(fechaInicio); 
		c.add(Calendar.DATE, 1);
		fechaInicio = c.getTime();
		
		c.setTime(fechaFin); 
		c.add(Calendar.DATE, 1);
		fechaFin = c.getTime();
		
		caso.setFecInicioCaso(fechaInicio);
		caso.setFecUltimaIncapacidadCaso(fechaFin);
		
		
		caso.setDpr(agrupaCasos.getCasosSeleccionados().get(0).getDpr());
		caso.setNumDiasAcumulados(agrupaCasos.getCasosSeleccionados().get(0).getDiasAcumulados());
		Double montoTotal = (double)0;
		for (ConsultaTodosCasosDTO cadaCaso : agrupaCasos.getCasosSeleccionados()) {
			if ((double)cadaCaso.getMontoAcumulado()>=0.0) {//|| cadaCaso.getMontoAcumulado()!=null
				montoTotal += (double)cadaCaso.getMontoAcumulado();
			}
		}
		caso.setImpMontoAcumulado(montoTotal);
		caso.setCveMatAlta(agrupaCasos.getMatriculaUsuario());
		
		
		//Insertar caso
		Caso casoAgrupado = casoRepositoryJPA.save(caso);
		
		
		if(agrupaCasos.getNuevoEstadoIncapacidad()==4) {
			CasoActivos casoActivo = new CasoActivos();
			casoActivo.setIdCaso(casoAgrupado.getIdCaso());
			casoActivo.setIdPaciente(casoAgrupado.getIdPaciente());
			casoActivo.setIdEstadoIncapacidad(casoAgrupado.getIdEstadoIncapacidad());
			casoActivo.setFechaInicioCaso(fechaInicio);
			casoActivo.setDpr(casoAgrupado.getDpr());
			casoActivo.setNumDiasAcumulados(casoAgrupado.getNumDiasAcumulados());
			casoActivo.setImpMontoAcumulado(casoAgrupado.getImpMontoAcumulado());
			casoActivo.setFechaUltimaIncapacidadCaso(fechaFin);
			casoActivo.setMatriculaAlta(casoAgrupado.getCveMatAlta());
			
			casoActivosRepository.save(casoActivo);
		} 
		
		return casoAgrupado;
	}
	
	public void actualizaCasosIncapacidadesYComentarios(Caso casoAgrupado, AgrupaCasosDTO agrupaCasos) throws BusinessException {
		
		for (ConsultaTodosCasosDTO cadaCaso : agrupaCasos.getCasosSeleccionados()) {
			Caso caso = casoRepositoryJPA.findById(cadaCaso.getIdCaso()).orElse(null);
			
			if (caso!=null) {
				caso.setIdEstadoIncapacidad((long) 3);
				Date bajaLogica = new Date();
				caso.setBajaLogica(bajaLogica);
				caso.setMatriculaBaja(agrupaCasos.getMatriculaUsuario());
				
				//actualizar estado incapacidad y baja logica en casos anteriores
				casoRepositoryJPA.save(caso);
				
				//actualizar id de caso agrupado en la tabla incapacidad
				incapacidadRepository.actualizaIncapacidadesConNuevoIDCaso(casoAgrupado.getIdCaso(), agrupaCasos.getMatriculaUsuario(), caso.getIdCaso());
				
				//actualizar comentarios con nuevo id caso
				List<CometarioJPA> listaComentariosPorCaso = comentarioRepository.findByIdCaso(caso.getIdCaso());
				if (listaComentariosPorCaso!= null) {
					for (CometarioJPA lista : listaComentariosPorCaso) {
						lista.setIdCaso(casoAgrupado.getIdCaso());
						lista.setMatriculaActualiza(agrupaCasos.getMatriculaUsuario());
						lista.setActualizaRegistro(new Date());
						
						comentarioRepository.save(lista);
					}
				}
				List<CometarioJPA> comentariosActualizados = comentarioRepository.findByIdCaso(casoAgrupado.getIdCaso());
				
				for (CometarioJPA cadaComentario : comentariosActualizados) {
					if (cadaComentario.getIdComentario().equals(comentariosActualizados.get(comentariosActualizados.size()-1).getIdComentario()) ) {
						incapacidadRepository.actualizaIndUltComentario(cadaComentario.getIdComentario(), 1);
							
					} else {
						incapacidadRepository.actualizaIndUltComentario(cadaComentario.getIdComentario(), 0);
					}
				}
				
			}
		}//for
	
	}
	
	public void registrarEnBitacora(Caso casoAgrupado, AgrupaCasosDTO agrupaCasos) throws BusinessException {
		
		for (ConsultaTodosCasosDTO cadaCaso : agrupaCasos.getCasosSeleccionados()) {
			//scit_caso_agrupado
			CasoAgrupado bitacoraAgrupado = new CasoAgrupado();
			bitacoraAgrupado.setIdCasoAnterior(cadaCaso.getIdCaso());
			bitacoraAgrupado.setIdCasoNuevo(casoAgrupado.getIdCaso());
			bitacoraAgrupado.setMatriculaAlta(agrupaCasos.getMatriculaUsuario());
			
			CasoAgrupado validaBitacoraAgrupado = casoAgrupadoRepository.save(bitacoraAgrupado);
			
			if (validaBitacoraAgrupado != null) {
					List<Long> listaIdIncapacidades = incapacidadRepository.obtenerIdIncapacidadesPorCasoAgrupado(cadaCaso.getIdCaso());
					for (Long cadaId : listaIdIncapacidades) {
						//scit_caso_agrupado_det
						CasoAgrupadoDet bitacoraDetalleAgrupado = new CasoAgrupadoDet();
						bitacoraDetalleAgrupado.setCasoAgrupado(validaBitacoraAgrupado);
						bitacoraDetalleAgrupado.setIdIncapacidad(cadaId);
						bitacoraDetalleAgrupado.setMatriculaAlta(agrupaCasos.getMatriculaUsuario());
						
						casoAgrupadoDetRepository.save(bitacoraDetalleAgrupado);
					}
			}
			
		}//for
		
	}
	
	public void eliminarCasosActivosAnteriores(AgrupaCasosDTO agrupaCasos) throws BusinessException {
		
		for (ConsultaTodosCasosDTO cadaCaso : agrupaCasos.getCasosSeleccionados()) {
			if (cadaCaso.getEstadoIncapacidad()==2 || cadaCaso.getEstadoIncapacidad()==4) {
				try {
					CasoActivos existeCasoActivo = casoActivosRepository.findById(cadaCaso.getIdCaso()).orElse(null);
					if (existeCasoActivo != null) {
						casoActivosRepository.deleteById(cadaCaso.getIdCaso());
					}
				} catch (IllegalArgumentException iae) {
					
				}
			}		
		}
	}

	//Mejora: Agrupacion de casos a demanda
	public Long validarAgrupacionNivelCaso(Long idPaciente, Long idCasoPrevio) throws BusinessException {
		List<Caso> listaCasos = casoRepositoryJPA.findByIdPacienteAndIdEstadoIncapacidadNotOrderByFecInicioCasoDescFecUltimaIncapacidadCasoDescIdCasoDesc(idPaciente, 3L);
		List<Caso> casosPorAgrupar = new ArrayList<Caso>();
		List<Long> idCasos = new ArrayList<Long>();
		Long idCasoGenerado = idCasoPrevio;
		
		if (!listaCasos.isEmpty() && listaCasos.size()>1) {//recorrer lista de casos
			
			for (int i=0; i<listaCasos.size()-1; i++) {
				
				Date fechaInicio = listaCasos.get(i).getFecInicioCaso();
				Date fechaFin = listaCasos.get(i+1).getFecUltimaIncapacidadCaso();
				Long diferenciaMilisegundos = fechaInicio.getTime() - fechaFin.getTime();
				int diferenciaDias = (int) (((diferenciaMilisegundos/1000)/3600)/24);
				diferenciaDias = Math.abs(diferenciaDias);
				
				if (diferenciaDias<14) {
					if (idCasos.isEmpty()) {
						idCasos.add(listaCasos.get(i).getIdCaso());
						casosPorAgrupar.add(listaCasos.get(i));
					}
					idCasos.add(listaCasos.get(i+1).getIdCaso());
					casosPorAgrupar.add(listaCasos.get(i+1));
					
				} else { //se rompe la validacion de diferencia de dias
					if (idCasos.size()>1) {
						//hacer corte y agrupar
						idCasoGenerado = this.validarAgrupacionNivelIncapacidades(casosPorAgrupar, idCasos, idCasoPrevio, idCasoGenerado);
					}
					casosPorAgrupar.clear();
					idCasos.clear();
				}
				
			}//for listaCasos
			
			//Agrupar casos al salir del for
			if (idCasos.size()>1) {
				idCasoGenerado = this.validarAgrupacionNivelIncapacidades(casosPorAgrupar, idCasos, idCasoPrevio, idCasoGenerado);
			}
		
		}//if para recorrer lista de casos
		
		return idCasoGenerado;
	}
	
	private Long validarAgrupacionNivelIncapacidades(List<Caso> casosPorAgrupar, List<Long> idCasos, Long idCasoPrevio, Long idCasoGenerado) {
		List<SCITIncapacidad> listaIncapacidades =incapacidadRepositoryJpa.findByIdCasoInOrderByFecInicioAsc(idCasos); 
		List<SCITIncapacidad> incapacidadesPorAgrupar = new ArrayList<SCITIncapacidad>();
		List<Long> idIncapacidades = new ArrayList<Long>();
		
		for (int i=0; i<listaIncapacidades.size(); i++) {
			if (i==0) {//siempre poner la primera incapacidad en la lista
				incapacidadesPorAgrupar.add(listaIncapacidades.get(0));
				idIncapacidades.add(listaIncapacidades.get(0).getIdIncapacidad());
			}
			
			if (i!=0) {//resto de la lista de incapacidades
				
				if ((listaIncapacidades.get(i).getTipoIncapacidad().getIdTipoIncapacidad()==3) ||
						(listaIncapacidades.get(i).getDiasAcumulados() == listaIncapacidades.get(i).getDiasAutorizados()) ||
						(listaIncapacidades.get(i).getTipoIncapacidad().getIdTipoIncapacidad()==5) ||
						(listaIncapacidades.get(i).getTipoIncapacidad().getIdTipoIncapacidad()==8) ) {//si es inicial
					
					if (!incapacidadesPorAgrupar.isEmpty()) {//si no esta vacia, quitar el ultimo valor (inicial)
						
						incapacidadesPorAgrupar.remove(incapacidadesPorAgrupar.size()-1);
						idIncapacidades.remove(idIncapacidades.size()-1);
						
						if (incapacidadesPorAgrupar.size()>1) {//si tiene incapacidades por agrupar
							
							List<Long> countCasos = new ArrayList<Long>();
							incapacidadesPorAgrupar.forEach((inc) -> {//verificar si las incapacidades que pasaron pertenecen al mismo caso
								if(!countCasos.contains(inc.getIdCaso())) {
									countCasos.add(inc.getIdCaso());
								}
							});
							
							if ( (countCasos.size()>1) && (countCasos.size() != idCasos.size()) ) {//si hay diferencia de casos
								//actualizar agrupaCasos y idCasos
								idCasos = countCasos;
								/*Descomentar si se va a utilizar casosPorAgrupar*/
//								List<Caso> nuevosCasosPorAgrupar = new ArrayList<Caso>();
//								for (int k=0; k<casosPorAgrupar.size(); k++) {
//									if (idCasos.contains(casosPorAgrupar.get(k).getIdCaso())) {
//										nuevosCasosPorAgrupar.add(casosPorAgrupar.get(k));
//									} 
//								}
//								casosPorAgrupar = nuevosCasosPorAgrupar;
							}
							
							//funcion agrupacion
							if(countCasos.size()>1) {
								idCasoGenerado = this.agruparADemanda(casosPorAgrupar, incapacidadesPorAgrupar, idCasos, idCasoPrevio, idIncapacidades, idCasoGenerado);
							}
							
						}
						
						incapacidadesPorAgrupar.clear();
						idIncapacidades.clear();
						incapacidadesPorAgrupar.add(listaIncapacidades.get(i));
						idIncapacidades.add(listaIncapacidades.get(i).getIdIncapacidad());
						
					}//if si es inicial
					
				} else {//si es subsecuente
					if (i<listaIncapacidades.size()) {
						
						Date fechaInicio = listaIncapacidades.get(i).getFecInicio();
						Date fechaFin = listaIncapacidades.get(i-1).getFecTermino();
						Long diferenciaMilisegundos = fechaInicio.getTime() - fechaFin.getTime();
						int diferenciaDias = (int) (((diferenciaMilisegundos/1000)/3600)/24);
						diferenciaDias = Math.abs(diferenciaDias);
						
						if(diferenciaDias<14) {
							if(!incapacidadesPorAgrupar.contains(listaIncapacidades.get(i))) {
								incapacidadesPorAgrupar.add(listaIncapacidades.get(i));
								idIncapacidades.add(listaIncapacidades.get(i).getIdIncapacidad());
							}
							if (i<listaIncapacidades.size()-1) {
								incapacidadesPorAgrupar.add(listaIncapacidades.get(i+1));
								idIncapacidades.add(listaIncapacidades.get(i+1).getIdIncapacidad());
							}
						}
						else {//se hace el corte por romper la regla de diferencia de dias
							incapacidadesPorAgrupar.remove(incapacidadesPorAgrupar.size()-1);
							idIncapacidades.remove(idIncapacidades.size()-1);
									
							if ((!incapacidadesPorAgrupar.isEmpty()) && incapacidadesPorAgrupar.size()>1) {
								
								List<Long> countCasos = new ArrayList<Long>();
								incapacidadesPorAgrupar.forEach((inc) -> {//verificar si las incapacidades que pasaron pertenecen al mismo caso
									if(!countCasos.contains(inc.getIdCaso())) {
										countCasos.add(inc.getIdCaso());
									}
								});
								
								if ( (countCasos.size()>1) && (countCasos.size() != idCasos.size()) ) {//si hay diferencia de casos
									//actualizar agrupaCasos y idCasos
									idCasos = countCasos;
									/*Descomentar si se va a utilizar casosPorAgrupar*/
//									List<Caso> nuevosCasosPorAgrupar = new ArrayList<Caso>();
//									for (int k=0; k<casosPorAgrupar.size(); k++) {
//										if (idCasos.contains(casosPorAgrupar.get(k).getIdCaso())) {
//											nuevosCasosPorAgrupar.add(casosPorAgrupar.get(k));
//										} 
//									}
//									casosPorAgrupar = nuevosCasosPorAgrupar;
								}
								
								//funcion agrupacion
								if(countCasos.size()>1) {
									idCasoGenerado = this.agruparADemanda(casosPorAgrupar, incapacidadesPorAgrupar, idCasos, idCasoPrevio, idIncapacidades, idCasoGenerado);	
								}
								incapacidadesPorAgrupar.clear();
								idIncapacidades.clear();
							}
							
							incapacidadesPorAgrupar.add(listaIncapacidades.get(i));
							idIncapacidades.add(listaIncapacidades.get(i).getIdIncapacidad());
						}
					}//i<listaIncapacidades.size()
				}//else si es subsecuente
				
			}//if resto de las incapacidades
			
		}//for listaIncapacidades
			
		if (incapacidadesPorAgrupar.size()>1) {
			
			List<Long> countCasos = new ArrayList<Long>();
			incapacidadesPorAgrupar.forEach((inc) -> {//verificar si las incapacidades que pasaron pertenecen al mismo caso
				if(!countCasos.contains(inc.getIdCaso())) {
					countCasos.add(inc.getIdCaso());
				}
			});
			
			if ( (countCasos.size()>1) && (countCasos.size() != idCasos.size()) ) {//si hay diferencia de casos
				//actualizar agrupaCasos y idCasos
				idCasos = countCasos;
				/*Descomentar si se va a utilizar casosPorAgrupar*/
//				List<Caso> nuevosCasosPorAgrupar = new ArrayList<Caso>();
//				for (int k=0; k<casosPorAgrupar.size(); k++) {
//					if (idCasos.contains(casosPorAgrupar.get(k).getIdCaso())) {
//						nuevosCasosPorAgrupar.add(casosPorAgrupar.get(k));
//					} 
//				}
//				casosPorAgrupar = nuevosCasosPorAgrupar;
			}
			
			//funcion agrupacion
			if(countCasos.size()>1) {
				idCasoGenerado = this.agruparADemanda(casosPorAgrupar, incapacidadesPorAgrupar, idCasos, idCasoPrevio, idIncapacidades, idCasoGenerado);
			}
		}
		
		return idCasoGenerado;
	}
	
	private Long agruparADemanda(List<Caso> casosPorAgrupar, List<SCITIncapacidad> incapacidadesPorAgrupar, List<Long> idCasos, Long idCasoPrevio, List<Long> idIncapacidades, Long idCasoGenerado) {		
		// A NIVEL CASO
		//eliminar, si aplica, casos antiguos en scit_caso_activos (DELETE)
		casoRepositoryJPA.eliminarCasoActivo(idCasos);
		
		//modificar de caso(s) en scit_caso, el estado incapacidad (3) y la auditoria para darlo de baja (SCIADEM ?) (UPDATE)
		if((!idCasos.isEmpty()) && idCasos.size()>1) {
			casoRepositoryJPA.actualizaCasos(idCasos);
		}
		
		//crear caso en scit_caso -> idCasoNuevo (JPA / INSERT -> SELECT)
		Caso nuevoCaso = new Caso();
		if (incapacidadesPorAgrupar.get(incapacidadesPorAgrupar.size()-1).getEdoIncapacidad().getIdEstadoIncapacidad()==2) {
			nuevoCaso.setIdEstadoIncapacidad(4L);
		} else {
			nuevoCaso.setIdEstadoIncapacidad(5L);
		}
//		if(casosPorAgrupar.get(0).getIdEstadoIncapacidad()==2 || casosPorAgrupar.get(0).getIdEstadoIncapacidad()==4) {
//			nuevoCaso.setIdEstadoIncapacidad(4L);
//		} else {
//			nuevoCaso.setIdEstadoIncapacidad(5L);
//		}
		
		nuevoCaso.setFecInicioCaso(incapacidadesPorAgrupar.get(0).getFecInicio());
//		nuevoCaso.setFecInicioCaso(casosPorAgrupar.get(casosPorAgrupar.size()-1).getFecInicioCaso());
		
		SCITIncapacidad incTmp = Collections.max(incapacidadesPorAgrupar, Comparator.comparing(s -> s.getDiasProbableRecuperacion()));
		nuevoCaso.setDpr(incTmp.getDiasProbableRecuperacion());
//		nuevoCaso.setDpr(casosPorAgrupar.get(0).getDpr());
		
		nuevoCaso.setNumDiasAcumulados(incapacidadesPorAgrupar.get(incapacidadesPorAgrupar.size()-1).getDiasAcumulados());
//		nuevoCaso.setNumDiasAcumulados(casosPorAgrupar.get(0).getNumDiasAcumulados());
		
		Double montoTotal = (double)0;
		for (SCITIncapacidad inc : incapacidadesPorAgrupar) {
			if (inc.getImportePago() >= 0.0) {
			montoTotal += inc.getImportePago();
			}
		};
//		for (Caso caso : casosPorAgrupar) {
//			if ((double)caso.getImpMontoAcumulado()>=0.0) {//|| cadaCaso.getMontoAcumulado()!=null
//			montoTotal += (double)caso.getImpMontoAcumulado();
//			}
//		}
		nuevoCaso.setImpMontoAcumulado(montoTotal);
		
		nuevoCaso.setFecUltimaIncapacidadCaso(incapacidadesPorAgrupar.get(incapacidadesPorAgrupar.size()-1).getFecTermino());
//		nuevoCaso.setFecUltimaIncapacidadCaso(casosPorAgrupar.get(0).getFecUltimaIncapacidadCaso());
		
		nuevoCaso.setCveMatAlta("SCIADEM");
		nuevoCaso.setIdPaciente(casosPorAgrupar.get(0).getIdPaciente());
		Caso casoAgrupado= casoRepositoryJPA.save(nuevoCaso);
		
		//if idCasoPrevio esta en la lista de idCasos -> idCasoGenerado = idCasoNuevo, else idCasoGenerado = idCasoPrevio
		if(idCasoGenerado.equals(idCasoPrevio)) {
			if(idCasos.contains(idCasoPrevio)) {
				idCasoGenerado = casoAgrupado.getIdCaso();
			} 
		}
		
		//(cont.) validar si se crea caso en scit_caso_activos (INSERT)
		if(casoAgrupado != null && casoAgrupado.getIdEstadoIncapacidad() == 4) {
			casoRepositoryJPA.insertaCasoActivo(casoAgrupado.getIdCaso(), casoAgrupado.getIdEstadoIncapacidad(), casoAgrupado.getFecInicioCaso(), casoAgrupado.getDpr(), casoAgrupado.getNumDiasAcumulados(), casoAgrupado.getImpMontoAcumulado(), casoAgrupado.getFecUltimaIncapacidadCaso(), casoAgrupado.getIdPaciente());
		}
		
		//validar y actualizar comentarios (UPDATE) -> (UPDATE ind_ult_comentario)
		if((!idCasos.isEmpty()) && idCasos.size()>1){
			casoRepositoryJPA.agruparComentarios(idCasos, casoAgrupado.getIdCaso());
			casoRepositoryJPA.actualizarComentario(casoAgrupado.getIdCaso());
		}
		
		//NIVEL INCAPACIDAD
		//modificar incapacidades en scit_incapacidad, el idCaso con el idCasoNuevo (UPDATE)
		if((!idIncapacidades.isEmpty()) && idIncapacidades.size()>1) {
			incapacidadRepositoryJpa.actualizarIncapacidades(casoAgrupado.getIdCaso(), idIncapacidades);
		}
		
		//Validar si se fragmentaron casos con base en las incapacidades actualizadas
		for (int i=0; i<idCasos.size(); i++) {
			int count = incapacidadRepositoryJpa.contarIncapacidadesPorCaso(idCasos.get(i));
			if (count>0) {
				//obtener lista de incapacidades para cada caso separado
				List<SCITIncapacidad> incapacidades = incapacidadRepositoryJpa.findByIdCasoOrderByFecInicioAsc(idCasos.get(i));
				
				//Actualizar el caso que se habia dado de baja
				Long idEstadoIncapacidad = incapacidades.get(incapacidades.size()-1).getEdoIncapacidad().getIdEstadoIncapacidad();
				Date fechaInicio = incapacidades.get(0).getFecInicio();
				incTmp = Collections.max(incapacidades, Comparator.comparing(s -> s.getDiasProbableRecuperacion()));
				int dpr = incTmp.getDiasProbableRecuperacion();
				int diasAcumulados = incapacidades.get(incapacidades.size()-1).getDiasAcumulados();
				montoTotal = (double)0;
				for (SCITIncapacidad inc : incapacidades) {
					if (inc.getImportePago() >= 0.0) {
					montoTotal += inc.getImportePago();
					}
				}
				Double montoAcumulado = montoTotal;
				Date fechaFin = incapacidades.get(incapacidades.size()-1).getFecTermino();
				
				casoRepositoryJPA.conformaCaso(idEstadoIncapacidad, fechaInicio, dpr, diasAcumulados, montoAcumulado, fechaFin, idCasos.get(i));
			
				//Si se queda como activa, hay que insertar en caso_activos
				if (idEstadoIncapacidad ==2) {
					casoRepositoryJPA.insertaCasoActivo(idCasos.get(i), idEstadoIncapacidad, fechaInicio, dpr, diasAcumulados, montoAcumulado, fechaFin, casoAgrupado.getIdPaciente());
				}
			}
		}

		
		//NIVEL BITACORA
		//bitacorear en scit_caso_agrupado (JPA / INSERT -> SELECT)
		for(Long idCaso: idCasos ) {
			CasoAgrupado bitacoraAgrupado = new CasoAgrupado();
			bitacoraAgrupado.setIdCasoAnterior(idCaso);
			bitacoraAgrupado.setIdCasoNuevo(casoAgrupado.getIdCaso());
			bitacoraAgrupado.setMatriculaAlta("SCIADEM");
			
			CasoAgrupado validaBitacoraAgrupado = casoAgrupadoRepository.save(bitacoraAgrupado);
			
			//bitacorear en scit_agrupado_det (INSERT)
			if(validaBitacoraAgrupado != null) {
				List<Long> idIncapacidadesPorCaso = new ArrayList<Long>();
				for (SCITIncapacidad inc : incapacidadesPorAgrupar) {
					if (inc.getIdCaso().equals(validaBitacoraAgrupado.getIdCasoAnterior())) {
						idIncapacidadesPorCaso.add(inc.getIdIncapacidad());
					}
				}
				
				for(Long idIncapacidad: idIncapacidadesPorCaso) {
					casoAgrupadoDetRepository.insertaCasoAgrupadoDet(validaBitacoraAgrupado.getIdCasoAgrupado(), idIncapacidad);
				}
			}
			
		}
		
		return idCasoGenerado;
	}


	public Long actualizaPaciente(UpdatePacienteDTO pacienteRequqest) throws BusinessException {

		validateParametersUpdatePaciente(pacienteRequqest);

		UpdatePacienteDTO pacienteToUpdate = incapacidadRepository.consultaPacientePorId(pacienteRequqest.getIdPaciente());
		
		if (pacienteToUpdate == null) {
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "Registro no encontrado ID: "+pacienteRequqest.getIdPaciente(), EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}

		if (pacienteRequqest.getConsultorio() != null) {
			pacienteToUpdate.setConsultorio(pacienteRequqest.getConsultorio());
		}
		if (pacienteRequqest.getIdTurno() != null) {
			pacienteToUpdate.setIdTurno(pacienteRequqest.getIdTurno());
		}
		if (pacienteRequqest.getIdOcupacion() != null) {
			pacienteToUpdate.setIdOcupacion(pacienteRequqest.getIdOcupacion());
		}

		if (pacienteRequqest.getMatriculaUsuario() != null && pacienteRequqest.getIdUsuarioModifica() != null) {
			pacienteToUpdate.setMatriculaUsuario(pacienteRequqest.getMatriculaUsuario());
			pacienteToUpdate.setIdUsuarioModifica(pacienteRequqest.getIdUsuarioModifica());
		}
		
		// Se actualiza diagnostico
		if (pacienteRequqest.getIdCie() != null) {
			pacienteToUpdate.setIdCie(pacienteRequqest.getIdCie());
			pacienteToUpdate.setCie(pacienteRequqest.getCie());
			pacienteToUpdate.setIdIncapacidad(pacienteRequqest.getIdIncapacidad());

			int updateIncapacidad = incapacidadRepository.updateCieIncapacidad(pacienteToUpdate);
			if (updateIncapacidad == 0) {
				throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al actualizar el CIE de la incapacidad", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
			}

			int createDiagnostico = incapacidadRepository.createDiagnostico(pacienteRequqest);
			if (createDiagnostico == 0) {
				throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al crear nuevo diagnostico", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
			}
		}
		
		
		int updatePaciente = incapacidadRepository.updatePaciente(pacienteToUpdate);
		if (updatePaciente == 0) {
			throw new BusinessException(EnumHttpStatus.SERVER_ERROR_INTERNAL, "Error al actualizar el paciente", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}

		return pacienteRequqest.getIdPaciente();
	}
	
	private void validateParametersUpdatePaciente(UpdatePacienteDTO paciente) throws BusinessException {
		
		if (paciente == null || paciente.getIdPaciente() == null || (paciente.getIdOcupacion() == null && paciente.getConsultorio() == null && paciente.getIdTurno() == null)) {
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST, "Parametros requeridos no validos", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}
		
		if (paciente.getIdOcupacion() != null) {
			Boolean ocupacionExist = this.ocupacionRepository.findById(paciente.getIdOcupacion()).isPresent();
			if (!ocupacionExist)
				throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "Ocupacion no encontrada, ID: "+paciente.getIdOcupacion(), EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}

		if (paciente.getIdTurno() != null) {
			Boolean turnoExist = this.turnoRepository.findById(paciente.getIdTurno()).isPresent();
			if (!turnoExist)
				throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "Turno no encontrada, ID: "+paciente.getIdOcupacion(), EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}
		
		if ( paciente.getIdCie() != null || paciente.getCie() != null || paciente.getIdIncapacidad() != null ) {
			if ( paciente.getIdCie() == null || paciente.getCie() == null || paciente.getIdIncapacidad() == null ) {
				throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "Los datos de CIE-10 y la incapacidad son requeridos", EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
			}
			
			Boolean cieExist = this.cieRepository.findById(paciente.getIdCie()).isPresent();
			if (!cieExist)
				throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "CIE-10 no encontrada, ID_CIE: "+paciente.getIdCie(), EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
		}
		
	}
	
	private void validateConsultaIncParams(ConsultaIncapacidadRequest params, Integer limit, Integer offset) throws BusinessException {

		if (params.getIdDelegacion()==null) {
			LOG.debug("Error al validar campos requeridos");
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST, "La propiedad idDelegacion es requerida", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}
		
		if (limit == null) {			
			LOG.debug("Error al validar campos requeridos");
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST, "La propiedad limit es requerida", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}

		if (offset == null) {
			LOG.debug("Error al validar campos requeridos");
			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST, "La propiedad offset es requerida", EnumHttpStatus.CLIENT_ERROR_BAD_REQUEST.getDescription());
		}
		
		if (params.getIsAdscripcion() == null) {
			params.setIsAdscripcion(false);
		}

		// Se valida que las propiedades no sean String vacios
		if (params.getAmaterno() != null && params.getAmaterno().trim().length() == 0)
		    params.setAmaterno(null);
		if (params.getApaterno() != null && params.getApaterno().trim().length() == 0)
		    params.setApaterno(null);
		if (params.getNombre() != null && params.getNombre().trim().length() == 0)
		    params.setNombre(null);
		if (params.getAmaterno() != null && params.getAmaterno().trim().length() == 0)
		    params.setAmaterno(null);
		if (params.getCurp() != null && params.getCurp().trim().length() == 0)
		    params.setCurp(null);
		if (params.getNss() != null && params.getNss().trim().length() == 0)
		    params.setNss(null);
		
	}
	
	@Override
	public List<ExportIncapacidadesDTO> consultaTodasIncapacidades(ConsultaIncapacidadRequest params) throws BusinessException {
		List<ExportIncapacidadesDTO> listadoIncapacidades = null;
//		Integer incapacidadesTotales = 0;
		Integer isEmpIMSS = null;
		Integer isntEmpIMSS = null;

		validateConsultaIncParams(params, 0, 0);
		
		if (params.getIsEmpleadoIMSS() != null && params.getIsEmpleadoIMSS() == true) {
			isEmpIMSS = 4;
		} else if (params.getIsEmpleadoIMSS() != null && params.getIsEmpleadoIMSS() == false) {
			isntEmpIMSS = 4;
		}
		
		if (params.getIsAdscripcion()) {
			if(params.getIdClasificacion() != null) {
				listadoIncapacidades= incapacidadRepository.consultaTodasIncapacidadesPorAdscripcionConClasificacion(params, isEmpIMSS, isntEmpIMSS);
				
			} else {
				listadoIncapacidades = incapacidadRepository.consultaTodasIncapacidadesPorAdscripcionSinClasificacion(params, isEmpIMSS, isntEmpIMSS);
			}
			
		} else {
			if (params.getIdClasificacion() != null) {
				listadoIncapacidades = incapacidadRepository.consultaTodasIncapacidadesPorExpedicionConClasificacion(params, isEmpIMSS, isntEmpIMSS);
			} else {
				listadoIncapacidades = incapacidadRepository.consultaTodasIncapacidadesPorExpedicionSinClasificacion(params, isEmpIMSS, isntEmpIMSS);
			}
		}
		
		
//		if (params.getIsEmpleadoIMSS() != null && params.getIsEmpleadoIMSS() == true) {
//			isEmpIMSS = 4;
//		} else if (params.getIsEmpleadoIMSS() != null && params.getIsEmpleadoIMSS() == false) {
//			isntEmpIMSS = 4;
//		}
//		
//		if (params.getIsAdscripcion()) {
//			listadoIncapacidades = incapacidadRepository.consultaTodasIncapacidadesPorAdscripcion(params, isEmpIMSS, isntEmpIMSS);
//		} else {
//			listadoIncapacidades  = incapacidadRepository.consultaTodasIncapacidadesPorExpedicion(params, isEmpIMSS, isntEmpIMSS);
//		}
//		
//		if (listadoIncapacidades == null || listadoIncapacidades.isEmpty()) {
//			throw new BusinessException(EnumHttpStatus.CLIENT_ERROR_NOT_FOUND, "No se encontraron registros que cumplan con los criterios de busqueda", EnumHttpStatus.CLIENT_ERROR_NOT_FOUND.getDescription());
//		}
		
		return listadoIncapacidades;
	}
	
	@Override
	public void exportWithApachePOI(List<ExportIncapacidadesDTO> listadoIncapacidades, HttpServletResponse response) throws BusinessException, IOException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		String dateStr= formatter.format(date);
		SXSSFWorkbook wb = null;
//		String home = System.getProperty("user.home"); 
//		System.out.println(home);
//		File f = new File("Casos_Incapacidad_NLISTA_SCI_"+dateStr+".xlsx");
//		int version =1;
//		
//		if (f.isFile()) {
//			f = null;
//			while (f==null) {
//				f = new File("Casos_Incapacidad_NLISTA_SCI_"+dateStr+"("+version+").xlsx");
//				if (f.isFile()) {
//					version++;
//					f = null;
//				}
//			}
//		}
		
		
//	        try (FileOutputStream os = new FileOutputStream(f)) {
		try {
	        	wb = new SXSSFWorkbook(-1); 
	            Sheet sheet = wb.createSheet("Hoja 1");
	            
	            //Encabezado 1
	            Row row = sheet.createRow(0);
	            row.setHeightInPoints(65);
	            sheet.setColumnWidth(0, 12000);
	            
	            Cell cell = row.createCell(0);  
	            InputStream inputStream = new FileInputStream("/tmp/src/SCI_Exce.png");
	            byte[] bytes = IOUtils.toByteArray(inputStream);
	            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
	            inputStream.close();
	            CreationHelper helper = wb.getCreationHelper();
	            Drawing<?> drawing = sheet.createDrawingPatriarch();
	            ClientAnchor anchor = helper.createClientAnchor();
	            anchor.setCol1(0);
	            anchor.setRow1(0);
	            anchor.setCol2(1);
	            anchor.setRow2(1);
	            Picture pict = drawing.createPicture(anchor, pictureIdx);
	            //pict.resize();

	            cell = row.createCell(2);  
	            cell.setCellValue("Instituto Mexicano del Seguro Social\n"
	            		+ "Dirección de Inovación y Desarrollo Tecnológico");
	            sheet.addMergedRegion(new CellRangeAddress(0,0,2,3));
	            CellStyle csHeader1 = wb.createCellStyle();
	            csHeader1.setAlignment(HorizontalAlignment.RIGHT);
	            csHeader1.setVerticalAlignment(VerticalAlignment.TOP);
	            csHeader1.setWrapText(true);
	            Font fontHeader1 = wb.createFont();  
	            fontHeader1.setFontHeightInPoints((short)14);  
	            fontHeader1.setFontName("Montserrat Regular");  
	            csHeader1.setFont(fontHeader1);  
	            cell.setCellStyle(csHeader1);
	            
	            
	            //Encabezado 2
	            row = sheet.createRow(2);  
	            row.setHeightInPoints(50);
	            sheet.setColumnWidth(1, 6000);
	            
	            cell = row.createCell(0);  
	            InputStream inputStream2 = new FileInputStream("/tmp/src/SCI_Excel.png");
	            byte[] bytes2 = IOUtils.toByteArray(inputStream2);
	            int pictureIdx2 = wb.addPicture(bytes2, Workbook.PICTURE_TYPE_PNG);
	            inputStream.close();
	            CreationHelper helper2 = wb.getCreationHelper();
	            Drawing<?> drawing2 = sheet.createDrawingPatriarch();
	            ClientAnchor anchor2 = helper2.createClientAnchor();
	            anchor2.setCol1(0);
	            anchor2.setRow1(2);
	            anchor2.setCol2(1);
	            anchor2.setRow2(3);
	            Picture pict2 = drawing2.createPicture(anchor2, pictureIdx2);
	            //pict2.resize();

	            cell = row.createCell(2);  
	            cell.setCellValue("Casos de incapacidades");
	            sheet.addMergedRegion(new CellRangeAddress(2,2,2,3));
	            CellStyle csHeader2 = wb.createCellStyle();
	            csHeader2.setAlignment(HorizontalAlignment.CENTER);
	            csHeader2.setVerticalAlignment(VerticalAlignment.CENTER);
	            Font fontHeader2 = wb.createFont();  
	            fontHeader2.setFontHeightInPoints((short)16);  
	            fontHeader2.setFontName("Montserrat Medium");  
	            csHeader2.setFont(fontHeader2);  
	            cell.setCellStyle(csHeader2);
	            
	            inputStream.close();
	            inputStream2.close();
	            
	            //Fecha de generacion de lista
	            row = sheet.createRow(4);  
	            
	            cell = row.createCell(0);
	            cell.setCellValue("Fecha de generación de la lista");
	            CellStyle csFechaGeneracion =  wb.createCellStyle();
	            Font fontFechaGeneracion = wb.createFont();  
	            fontFechaGeneracion.setFontHeightInPoints((short)11);  
	            fontFechaGeneracion.setFontName("Montserrat SemiBold");  
	            csFechaGeneracion.setFont(fontFechaGeneracion);
	            csFechaGeneracion.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csFechaGeneracion.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
	            cell.setCellStyle(csFechaGeneracion);
	            
	            formatter = new SimpleDateFormat("dd-MM-yyyy");
	    		dateStr= formatter.format(date);
	            cell = row.createCell(1);
	            cell.setCellValue(dateStr);
	            CellStyle csFecha =  wb.createCellStyle();
	            Font fontFecha = wb.createFont();  
	            fontFecha.setFontHeightInPoints((short)12);  
	            fontFecha.setFontName("Montserrat Medium");
	            fontFecha.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
	            csFecha.setFont(fontFecha);  
	            csFecha.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csFecha.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            cell.setCellStyle(csFecha);
	            
//	            for (int i=2; i<=7; i++) {
//	            	cell = row.createCell(i);
//	            	cell.setCellStyle(csFecha);
//	            }
	            
	            //Nombre de columnas (headers)
	            row = sheet.createRow(6);
	            CellStyle csHeaders = wb.createCellStyle();
	            Font fontHeaders = wb.createFont();  
	            fontHeaders.setFontHeightInPoints((short)12);  
	            fontHeaders.setFontName("Verdana Bold");
	            fontHeaders.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
	            csHeaders.setAlignment(HorizontalAlignment.LEFT);
	            csHeaders.setVerticalAlignment(VerticalAlignment.TOP);
	            csHeaders.setWrapText(true);
	            csHeaders.setFont(fontHeaders);  
	            csHeaders.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csHeaders.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            
	            cell = row.createCell(0);
	            cell.setCellValue("OOAD");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(1);
	            sheet.setColumnWidth(1, 12000);
	            cell.setCellValue("Unidad adscripción");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(2);
	            sheet.setColumnWidth(2, 12000);
	            cell.setCellValue("Unidad expedición");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(3);
	            sheet.setColumnWidth(3, 12000);
	            cell.setCellValue("Nombre");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(4);
	            sheet.setColumnWidth(4, 6000);
	            cell.setCellValue("NSS");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(5);
	            sheet.setColumnWidth(5, 9000);
	            cell.setCellValue("CURP");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(6);
	            sheet.setColumnWidth(6, 4500);
	            cell.setCellValue("Consultorio");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(7);
	            sheet.setColumnWidth(7, 6000);
	            cell.setCellValue("Turno");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(8);
	            sheet.setColumnWidth(8, 12000);
	            cell.setCellValue("Ocupación");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(9);
	            sheet.setColumnWidth(9, 6000);
	            cell.setCellValue("Registro patronal");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(10);
	            sheet.setColumnWidth(10, 6000);
	            cell.setCellValue("Días \nacumulados \nde incapacidad");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(11);
	            sheet.setColumnWidth(11, 5000);
	            cell.setCellValue("Fecha \nde inicio \ndel caso");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(12);
	            sheet.setColumnWidth(12, 5000);
	            cell.setCellValue("Fecha \nde fin \ndel caso");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(13);
	            sheet.setColumnWidth(13, 4500);
	            cell.setCellValue("Clave \ndiagnóstico");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(14);
	            sheet.setColumnWidth(14, 24000);
	            cell.setCellValue("Diagnóstico");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(15);
	            sheet.setColumnWidth(15, 6000);
	            cell.setCellValue("Ramo");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(16);
	            sheet.setColumnWidth(16, 3000);
	            cell.setCellValue("D.P.R");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(17);
	            sheet.setColumnWidth(17, 6000);
	            cell.setCellValue("Monto acumulado \nde subsidio");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(18);
	            sheet.setColumnWidth(18, 5000);
	            cell.setCellValue("Matrícula \nde médico");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(19);
	            sheet.setColumnWidth(19, 12000);
	            cell.setCellValue("Clasificación \ndel caso");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(20);
	            sheet.setColumnWidth(20, 6000);
	            cell.setCellValue("Fecha de \npróxima \nvaloración");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(21);
	            sheet.setColumnWidth(21, 12000);
	            cell.setCellValue("Comentario");
	            cell.setCellStyle(csHeaders);
	            
	            //Insertar datos
	            CellStyle csContent = wb.createCellStyle();
	            Font fontContent = wb.createFont();  
	            fontContent.setFontHeightInPoints((short)12);  
	            fontContent.setFontName("Calibri");
	            fontContent.setColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
	            csContent.setAlignment(HorizontalAlignment.LEFT);
	            csContent.setFont(fontContent);
	            
	            for (int i=0; i<listadoIncapacidades.size(); i++) {
	            	row = sheet.createRow(i+7);
	            	
	            	cell = row.createCell(0);
		            cell.setCellValue(listadoIncapacidades.get(i).getNombreDelegacion());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(1);
		            cell.setCellValue(listadoIncapacidades.get(i).getNombreUnidad());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(2);
		            cell.setCellValue(listadoIncapacidades.get(i).getNombreUnidadExp());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(3);
		            cell.setCellValue(listadoIncapacidades.get(i).getNombrePaciente()
		            		+" "+ listadoIncapacidades.get(i).getPrimerApellido()
		            		+" "+ listadoIncapacidades.get(i).getSegundoApellido());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(4);
		            cell.setCellValue(listadoIncapacidades.get(i).getNss());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(5);
		            cell.setCellValue(listadoIncapacidades.get(i).getCurp());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(6);
		            cell.setCellValue(listadoIncapacidades.get(i).getUmf());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(7);
		            cell.setCellValue(listadoIncapacidades.get(i).getNombreTurno());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(8);
		            cell.setCellValue(listadoIncapacidades.get(i).getDescOcupacion());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(9);
		            cell.setCellValue(listadoIncapacidades.get(i).getCveRegistroPatronal());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(10);
		            cell.setCellValue(listadoIncapacidades.get(i).getDiasAcumuladosIncapacidad());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(11);
		            String inicioCasoStr= listadoIncapacidades.get(i).getInicioCaso()!=null ? 
		            		formatter.format(listadoIncapacidades.get(i).getInicioCaso()) : "";
		            cell.setCellValue(inicioCasoStr);
		            cell.setCellStyle(csContent);
		            cell = row.createCell(12);
		            String finCasoStr= listadoIncapacidades.get(i).getFinCaso()!=null ? 
		            		formatter.format(listadoIncapacidades.get(i).getFinCaso()) : "";
		            cell.setCellValue(finCasoStr);
		            cell.setCellStyle(csContent);
		            cell = row.createCell(13);
		            cell.setCellValue(listadoIncapacidades.get(i).getCveCie());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(14);
		            cell.setCellValue(listadoIncapacidades.get(i).getUltimoDiagnostico());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(15);
		            cell.setCellValue(listadoIncapacidades.get(i).getDescRamo());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(16);
		            cell.setCellValue(listadoIncapacidades.get(i).getDpr());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(17);
		            cell.setCellValue(listadoIncapacidades.get(i).getMontoAcumuladoSubsidio());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(18);
		            cell.setCellValue(listadoIncapacidades.get(i).getMatriculaMedico());
		            cell.setCellStyle(csContent);
		            cell = row.createCell(19);
		            cell.setCellValue((listadoIncapacidades.get(i).getClaveClasificacion()!=null ?
		            		listadoIncapacidades.get(i).getClaveClasificacion() : "")
		            		+ " - "
		            		+ (listadoIncapacidades.get(i).getDescClasificacion()!= null ?
		            				listadoIncapacidades.get(i).getDescClasificacion() : ""));
		            cell.setCellStyle(csContent);
		            cell = row.createCell(20);
		            String fechaRevaloracionStr= listadoIncapacidades.get(i).getFechaRevaloracion()!=null ? 
		            		formatter.format(listadoIncapacidades.get(i).getFechaRevaloracion()) : "";
		            cell.setCellValue(fechaRevaloracionStr);
		            cell.setCellStyle(csContent);
		            cell = row.createCell(21);
		            cell.setCellValue(listadoIncapacidades.get(i).getComentario());
		            cell.setCellStyle(csContent);
		            
		            
		            
		            // manually control how rows are flushed to disk 
		            if(i % 100 == 0) {
		            	//((SXSSFSheet)sheet).flushRows(100); // retain 100 last rows and flush all others
		                ((SXSSFSheet)sheet).flushRows();// is a shortcut for ((SXSSFSheet)sheet).flushRows(0),
		                // this method flushes all rows
		            }
	            }
	            
	            //Ancho dinamico para cuando son menos de 1000
//	            if (listadoIncapacidades.size()<=100) {
//		            for (int i=1; i<=21; i++) {
//		            	sheet.autoSizeColumn(i);
//		            }
//	            }
	          
	            //Escribir y cerrar
	            ServletOutputStream outputStream = response.getOutputStream();
		        wb.write(outputStream);
		        outputStream.close();
	        }catch(Exception e) {  
	            System.out.println(e.getMessage());  
	        }  finally {
	        	wb.close();
	        }
	}
	
	//MASV 01/19/2021
	@Override
	public SCIPaginadoConsultaHistoricoDTO consultaHistorico(SCIParametrosHistoricoIncapacidadesDTO parametros, Integer limit, Integer offset) throws BusinessException {
		SCIPaginadoConsultaHistoricoDTO paginaHistorico = null; 
		Integer totalHistorico = 0;
		offset = offset > 0 ? offset-1 : 0;
		List<SCIRespuestaHistoricoDTO> paginadoHistorico = incapacidadRepository.consultaHistoricoIncapacidades(parametros, limit, offset);
		if (!paginadoHistorico.isEmpty()) {
			if (offset <=1) {
				totalHistorico = incapacidadRepository.totalConsultaHistoricoIncapacidades(parametros);
			}
			paginaHistorico = new SCIPaginadoConsultaHistoricoDTO();
			paginaHistorico.setHistoricoIncapacidades(paginadoHistorico);
			paginaHistorico.setTotalIncapacidades(totalHistorico);
				
		}		
		return paginaHistorico;
	}
	
	@Override
	public void exportarHistorial(List<SCIRespuestaHistoricoDTO> listadoHistorial, HttpServletResponse response) throws BusinessException, IOException {
		String nombre = listadoHistorial.get(0).getNombre() + " " + listadoHistorial.get(0).getApPaterno() + " " + listadoHistorial.get(0).getApMaterno();
		String nss = listadoHistorial.get(0).getNss();
		String unidadAdscripcion = listadoHistorial.get(0).getDescripcionUnidadAdscripcion();
		XSSFWorkbook wb = null;

		try {
	        	wb = new XSSFWorkbook(); 
	            Sheet sheet = wb.createSheet("Hoja 1");
	            
	            //Encabezado
	            Row row = sheet.createRow(0);
	            row.setHeightInPoints(65);
	            sheet.setColumnWidth(0, 12000);
	            
	            Cell cell = row.createCell(0);  
	            InputStream inputStream = new FileInputStream("/tmp/src/SCI_Exce.png");
	            byte[] bytes = IOUtils.toByteArray(inputStream);
	            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
	            inputStream.close();
	            CreationHelper helper = wb.getCreationHelper();
	            Drawing<?> drawing = sheet.createDrawingPatriarch();
	            ClientAnchor anchor = helper.createClientAnchor();
	            anchor.setCol1(0);
	            anchor.setRow1(0);
	            anchor.setCol2(1);
	            anchor.setRow2(1);
	            Picture pict = drawing.createPicture(anchor, pictureIdx);
	            
	            cell = row.createCell(2);  
	            cell.setCellValue("Instituto Mexicano del Seguro Social\n"
	            		+ "Dirección de Inovación y Desarrollo Tecnológico");
	            sheet.addMergedRegion(new CellRangeAddress(0,0,2,6));
	            CellStyle csHeader1 = wb.createCellStyle();
	            csHeader1.setAlignment(HorizontalAlignment.RIGHT);
	            csHeader1.setVerticalAlignment(VerticalAlignment.TOP);
	            csHeader1.setWrapText(true);
	            Font fontHeader1 = wb.createFont();  
	            fontHeader1.setFontHeightInPoints((short)14);  
	            fontHeader1.setFontName("Montserrat Regular");  
	            csHeader1.setFont(fontHeader1);  
	            cell.setCellStyle(csHeader1);
	            
	          //Encabezado 2
	            row = sheet.createRow(2);  
	            row.setHeightInPoints(50);
	            sheet.setColumnWidth(1, 6000);
	            
	            cell = row.createCell(0);  
	            InputStream inputStream2 = new FileInputStream("/tmp/src/SCI_Excel.png");
	            byte[] bytes2 = IOUtils.toByteArray(inputStream2);
	            int pictureIdx2 = wb.addPicture(bytes2, Workbook.PICTURE_TYPE_PNG);
	            inputStream.close();
	            CreationHelper helper2 = wb.getCreationHelper();
	            Drawing<?> drawing2 = sheet.createDrawingPatriarch();
	            ClientAnchor anchor2 = helper2.createClientAnchor();
	            anchor2.setCol1(0);
	            anchor2.setRow1(2);
	            anchor2.setCol2(1);
	            anchor2.setRow2(3);
	            Picture pict2 = drawing2.createPicture(anchor2, pictureIdx2);
	            //pict2.resize();

	            cell = row.createCell(2);  
	            cell.setCellValue("Historial de incapacidades del asegurado");
	            sheet.addMergedRegion(new CellRangeAddress(2,2,2,6));
	            CellStyle csHeader2 = wb.createCellStyle();
	            csHeader2.setAlignment(HorizontalAlignment.CENTER);
	            csHeader2.setVerticalAlignment(VerticalAlignment.CENTER);
	            Font fontHeader2 = wb.createFont();  
	            fontHeader2.setFontHeightInPoints((short)16);  
	            fontHeader2.setFontName("Montserrat Medium");  
	            csHeader2.setFont(fontHeader2);  
	            cell.setCellStyle(csHeader2);
	            
	            inputStream.close();
	            inputStream2.close();
	            
	            //Datos del paciente
	            row = sheet.createRow(4);  
	            cell = row.createCell(0);
	            cell.setCellValue("Nombre");
	            CellStyle csFechaGeneracion =  wb.createCellStyle();
	            Font fontFechaGeneracion = wb.createFont();  
	            fontFechaGeneracion.setFontHeightInPoints((short)11);  
	            fontFechaGeneracion.setFontName("Montserrat SemiBold");  
	            csFechaGeneracion.setFont(fontFechaGeneracion);
	            csFechaGeneracion.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csFechaGeneracion.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
	            cell.setCellStyle(csFechaGeneracion);
	           
	            cell = row.createCell(1);
	            cell.setCellValue(nombre);
	            sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));
	            CellStyle csFecha =  wb.createCellStyle();
	            Font fontFecha = wb.createFont();  
	            fontFecha.setFontHeightInPoints((short)12);  
	            fontFecha.setFontName("Montserrat Medium");
	            fontFecha.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
	            csFecha.setFont(fontFecha);  
	            csFecha.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csFecha.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            cell.setCellStyle(csFecha);
	            
	            // NSS
	            row = sheet.createRow(5);  
	            cell = row.createCell(0);
	            cell.setCellValue("NSS");
	            CellStyle csNss =  wb.createCellStyle();
	            Font fontNss = wb.createFont();  
	            fontNss.setFontHeightInPoints((short)11);  
	            fontNss.setFontName("Montserrat SemiBold");  
	            csNss.setFont(fontNss);
	            csNss.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csNss.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
	            cell.setCellStyle(csNss);
	           
	            cell = row.createCell(1);
	            cell.setCellValue(nss);
	            sheet.addMergedRegion(new CellRangeAddress(5,5,1,3));
	            CellStyle csNss1 =  wb.createCellStyle();
	            Font fontNss1 = wb.createFont();  
	            fontNss1.setFontHeightInPoints((short)12);  
	            fontNss1.setFontName("Montserrat Medium");
	            fontNss1.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
	            csNss1.setFont(fontNss1);  
	            csNss1.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csNss1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            cell.setCellStyle(csNss1);
	            
	         // UNIDAD DE ADSCRIPCION
	            row = sheet.createRow(6);  
	            cell = row.createCell(0);
	            cell.setCellValue("Unidad de adscripción");
	            CellStyle csUni =  wb.createCellStyle();
	            Font fontUni = wb.createFont();  
	            fontUni.setFontHeightInPoints((short)11);  
	            fontUni.setFontName("Montserrat SemiBold");  
	            csUni.setFont(fontUni);
	            csUni.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csUni.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
	            cell.setCellStyle(csUni);
	           
	            cell = row.createCell(1);
	            cell.setCellValue(unidadAdscripcion);
	            sheet.addMergedRegion(new CellRangeAddress(6,6,1,3));
	            CellStyle csUnidad =  wb.createCellStyle();
	            Font fontUnidad = wb.createFont();  
	            fontUnidad.setFontHeightInPoints((short)12);  
	            fontUnidad.setFontName("Montserrat Medium");
	            fontUnidad.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
	            csUnidad.setFont(fontUnidad);  
	            csUnidad.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csUnidad.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            cell.setCellStyle(csUnidad);
	            
	          //Nombre de columnas (headers)
	            row = sheet.createRow(9);
	            CellStyle csHeaders = wb.createCellStyle();
	            Font fontHeaders = wb.createFont();  
	            fontHeaders.setFontHeightInPoints((short)12);  
	            fontHeaders.setFontName("Verdana Bold");
	            fontHeaders.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
	            csHeaders.setAlignment(HorizontalAlignment.LEFT);
	            csHeaders.setVerticalAlignment(VerticalAlignment.TOP);
	            csHeaders.setWrapText(true);
	            csHeaders.setFont(fontHeaders);  
	            csHeaders.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());  
	            csHeaders.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            
	            cell = row.createCell(0);
	            cell.setCellValue("Serie y folio");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(1);
	            sheet.setColumnWidth(1, 12000);
	            cell.setCellValue("Fecha inicio");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(2);
//	            sheet.setColumnWidth(2, 12000);
	            cell.setCellValue("Fecha fin");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(3);
//	            sheet.setColumnWidth(3, 12000);
	            cell.setCellValue("Días subsidiados");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(4);
//	            sheet.setColumnWidth(4, 8000);
	            cell.setCellValue("Días autorizados");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(5);
//	            sheet.setColumnWidth(5, 8000);
	            cell.setCellValue("Días acumulados");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(6);
//	            sheet.setColumnWidth(6, 10000);
	            cell.setCellValue("Mátricula del médico");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(7);
//	            sheet.setColumnWidth(7, 9000);
	            cell.setCellValue("Ramo de seguro");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(8);
//	            sheet.setColumnWidth(8, 10000);
	            cell.setCellValue("Tipo de incapacidad");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(9);
//	            sheet.setColumnWidth(9, 14000);
	            cell.setCellValue("Unidad expedidora");
	            cell.setCellStyle(csHeaders);
	            cell = row.createCell(10);
//	            sheet.setColumnWidth(10, 14000);
	            cell.setCellValue("Diagnóstico");
	            cell.setCellStyle(csHeaders);
	           
	            
	            //Insertar datos
	            CellStyle csContent = wb.createCellStyle();
	            Font fontContent = wb.createFont();  
	            fontContent.setFontHeightInPoints((short)12);  
	            fontContent.setFontName("Calibri");
	            fontContent.setColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
	            csContent.setAlignment(HorizontalAlignment.LEFT);
	            csContent.setFont(fontContent);
	            
	            for (int i=0; i<listadoHistorial.size(); i++) {
	            	row = sheet.createRow(i+10);
	            	
	            	cell = row.createCell(0);
		            cell.setCellValue(listadoHistorial.get(i).getRefFolio());
		            cell.setCellStyle(csContent);
		            //fecha inicio
		            cell = row.createCell(1);
		            String fechaInicio = listadoHistorial.get(i).getFecInicio();
		            String formatoFecha = fechaInicio.substring(8,11) + "/" + fechaInicio.substring(5, 7) + "/" + fechaInicio.substring(0,4);
		            cell.setCellValue(formatoFecha);
		            cell.setCellStyle(csContent);
		            // fecha termino
		            cell = row.createCell(2);
		            String fechaTermino = listadoHistorial.get(i).getFecTermino();
		            String formatoFecha1 = fechaTermino.substring(8,11) + "/"+ fechaTermino.substring(5,7) + "/" + fechaTermino.substring(0,4);
		            cell.setCellValue(formatoFecha1);
		            cell.setCellStyle(csContent);
		            // dias subsidiados
		            cell = row.createCell(3);
		            cell.setCellValue(listadoHistorial.get(i).getDiasSubsidiados());
		            cell.setCellStyle(csContent);
		            // dias autorizados
		            cell = row.createCell(4);
		            cell.setCellValue(listadoHistorial.get(i).getDiasAutorizados());
		            cell.setCellStyle(csContent);
		            // dias acumulados
		            cell = row.createCell(5);
		            cell.setCellValue(listadoHistorial.get(i).getDiasAcumulados());
		            cell.setCellStyle(csContent);
		            // matricula medico
		            cell = row.createCell(6);
		            cell.setCellValue(listadoHistorial.get(i).getMatriculaMedico());
		            cell.setCellStyle(csContent);
		            // ramo
		            cell = row.createCell(7);
		            cell.setCellValue(listadoHistorial.get(i).getDescRamo());
		            cell.setCellStyle(csContent);
		            // tipo incapacidad
		            cell = row.createCell(8);
		            cell.setCellValue(listadoHistorial.get(i).getDescTipoIncapacidad());
		            cell.setCellStyle(csContent);
		            // unidad expedicion
		            cell = row.createCell(9);
		            cell.setCellValue(listadoHistorial.get(i).getDescUnidadExpedicion());
		            cell.setCellStyle(csContent);
		            // diagnostico
		            cell = row.createCell(10);
		            cell.setCellValue(listadoHistorial.get(i).getRefDiagnostico());
		            cell.setCellStyle(csContent);
		            
	            }
	            
	           // Ancho dinamico para cuando son menos de 1000
		        for (int i=1; i<=10; i++) {
		           	sheet.autoSizeColumn(i);
		        }
	            
	            
	          //Escribir y cerrar
		      ServletOutputStream outputStream = response.getOutputStream();
		      wb.write(outputStream);
		      outputStream.close();
		            
	            
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally { 
			wb.close();
		}
	}

}
