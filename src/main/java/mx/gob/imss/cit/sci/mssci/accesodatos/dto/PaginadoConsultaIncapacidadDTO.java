package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import java.util.List;

public class PaginadoConsultaIncapacidadDTO {

	private List<ConsultaIncapacidadesDTO> consultaIncapacidadList;
	private Integer numIncapacidades;
	private String ultimaCargaNSSA;

	public PaginadoConsultaIncapacidadDTO() {
		//constructor
	}

	public List<ConsultaIncapacidadesDTO> getConsultaIncapacidadList() {
		return consultaIncapacidadList;
	}
	public void setConsultaIncapacidadList(List<ConsultaIncapacidadesDTO> consultaIncapacidadList) {
		this.consultaIncapacidadList = consultaIncapacidadList;
	}
	public Integer getNumIncapacidades() {
		return numIncapacidades;
	}
	public void setNumIncapacidades(Integer numIncapacidades) {
		this.numIncapacidades = numIncapacidades;
	}

	public String getUltimaCargaNSSA() {
		return ultimaCargaNSSA;
	}

	public void setUltimaCargaNSSA(String ultimaCargaNSSA) {
		this.ultimaCargaNSSA = ultimaCargaNSSA;
	}

}
