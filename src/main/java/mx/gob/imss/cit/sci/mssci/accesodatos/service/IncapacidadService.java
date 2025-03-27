package mx.gob.imss.cit.sci.mssci.accesodatos.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mx.gob.imss.cit.sci.mssci.accesodatos.dto.AgrupaCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ConsultaTodosCasosDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.ExportIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIPaginadoConsultaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.PaginadoConsultaIncapacidadDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIParametrosHistoricoIncapacidadesDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIRespuestaHistoricoDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.dto.UpdatePacienteDTO;
import mx.gob.imss.cit.sci.mssci.accesodatos.exceptions.BusinessException;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.Caso;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.ConsultaIncapacidadRequest;

public interface IncapacidadService {

	PaginadoConsultaIncapacidadDTO consultaIncapacidades(ConsultaIncapacidadRequest params, Integer limit, Integer offset) throws BusinessException;
	
	ConsultaIncapacidadDTO consultaIncapacidadPorIdCaso(Long idCaso) throws BusinessException;
	
	List<ConsultaTodosCasosDTO> consultaTodosLosCasos(String nss) throws BusinessException;
	
	Caso insertaNuevoCasoAgrupado(AgrupaCasosDTO agrupaCasos) throws BusinessException;
	
	void actualizaCasosIncapacidadesYComentarios(Caso casoAgrupado, AgrupaCasosDTO agrupaCasos) throws BusinessException;
	
	void registrarEnBitacora(Caso casoAgrupado, AgrupaCasosDTO agrupaCasos) throws BusinessException;
	
	void eliminarCasosActivosAnteriores(AgrupaCasosDTO agrupaCasos) throws BusinessException;
	
	Long actualizaPaciente(UpdatePacienteDTO pacienteReq) throws BusinessException;
	
	List<ExportIncapacidadesDTO> consultaTodasIncapacidades(ConsultaIncapacidadRequest params) throws BusinessException;
	
	void exportWithApachePOI(List<ExportIncapacidadesDTO> listadoIncapacidades, HttpServletResponse response) throws BusinessException, IOException;
	
	Long validarAgrupacionNivelCaso(Long idPaciente, Long idCasoPrevio) throws BusinessException;

	//MASV 01/19/2021
	SCIPaginadoConsultaHistoricoDTO consultaHistorico(SCIParametrosHistoricoIncapacidadesDTO parametros, Integer limit, Integer offset)  throws BusinessException;
	
	void exportarHistorial(List<SCIRespuestaHistoricoDTO> listadoHistorial, HttpServletResponse response) throws BusinessException, IOException;
}
