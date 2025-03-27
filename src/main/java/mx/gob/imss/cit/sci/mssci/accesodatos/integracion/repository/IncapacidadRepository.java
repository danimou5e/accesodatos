package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import java.util.List;

import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaTodosCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ExportIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIParametrosHistoricoIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIRespuestaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.UpdatePacienteDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.exceptions.BusinessException;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.IncapacidadGraficaModel;

public interface IncapacidadRepository {
	
	List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorAdscripcionConClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;
	List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorAdscripcionSinClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;
	List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorExpedicionSinClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;
	List<ExportIncapacidadesDTO> consultaTodasIncapacidadesPorExpedicionConClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;
	
	// Todos los casos
	List<ConsultaTodosCasosDTO> consultaTodosLosCasos(String nss) throws BusinessException;
	
	int actualizaIncapacidadesConNuevoIDCaso(Long idCasoNuevo, String matriculaActualiza, Long idCasoAnterior) throws BusinessException;
	
	List<Long> obtenerIdIncapacidadesPorCasoAgrupado(Long idCaso) throws BusinessException;

	int actualizaIndUltComentario(Long idComentario, int indice) throws BusinessException;
	
	List<ConsultaIncapacidadesDTO> consultaIncapacidadPorAdscripcion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS, Integer limit, Integer offset) throws BusinessException;
	Integer consultaIncapacidadPorAdscripcionCount(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;
	
	//MASV 11/03/2021 para filtro de Clasificacion del Caso
	List<ConsultaIncapacidadesDTO> consultaIncPorAdscConClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS, Integer limit, Integer offset) throws BusinessException;
	Integer consultaIncPorAdscConClasificacionCount(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;

	List<ConsultaIncapacidadesDTO> consultaIncapacidadPorExpedicion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS, Integer limit, Integer offset) throws BusinessException;
	Integer consultaIncapacidadPorExpedicionCount(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;
	
	//MASV 11/03/2021 para filtro de Clasificacion del Caso
	List<ConsultaIncapacidadesDTO> consultaIncPorExpedicionConClasificacion(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS, Integer limit, Integer offset) throws BusinessException;
	Integer consultaIncPorExpedicionConClasificacionCount(ConsultaIncapacidadRequest params, Integer isEmpIMSS, Integer isntEmpIMSS) throws BusinessException;

	ConsultaIncapacidadDTO consultaDetalleCaso(Long idCaso) throws BusinessException;
	String consultaUltimaCargaNSSA() throws BusinessException;
	void agregaComentarioDiagnostico(ConsultaIncapacidadDTO consultaDTO, Long idCaso) throws BusinessException;
	List<IncapacidadGraficaModel> consultaIncapacidadesPorCaso(Long idCaso) throws BusinessException;
	
	UpdatePacienteDTO consultaPacientePorId(Long idPaciente) throws BusinessException;
	
	//Historico MASV 01/20/2021
	List<SCIRespuestaHistoricoDTO> consultaHistoricoIncapacidades(SCIParametrosHistoricoIncapacidadesDTO parametros, Integer limit, Integer offset) throws BusinessException;
	Integer totalConsultaHistoricoIncapacidades(SCIParametrosHistoricoIncapacidadesDTO parametros) throws BusinessException;

	int updatePaciente(UpdatePacienteDTO paciente) throws BusinessException;
	int createDiagnostico(UpdatePacienteDTO paciente) throws BusinessException;
	int updateCieIncapacidad(UpdatePacienteDTO paciente ) throws BusinessException;
}
