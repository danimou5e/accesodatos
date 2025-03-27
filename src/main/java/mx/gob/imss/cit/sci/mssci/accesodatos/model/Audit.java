package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.sql.Date;

public class Audit {

	private Date altaRegistro;
	private String matriculaAlta;
	private Date actualizaRegistro;
	private String matriculaActualiza;
	private Date bajaLogica;
	private String matriculaBaja;
	
	public Audit() {
		//constructor
	}

	public Date getAltaRegistro() {
		return altaRegistro;
	}

	public void setAltaRegistro(Date altaRegistro) {
		this.altaRegistro = altaRegistro;
	}

	public String getMatriculaAlta() {
		return matriculaAlta;
	}

	public void setMatriculaAlta(String matriculaAlta) {
		this.matriculaAlta = matriculaAlta;
	}

	public Date getActualizaRegistro() {
		return actualizaRegistro;
	}

	public void setActualizaRegistro(Date actualizaRegistro) {
		this.actualizaRegistro = actualizaRegistro;
	}

	public String getMatriculaActualiza() {
		return matriculaActualiza;
	}

	public void setMatriculaActualiza(String matriculaActualiza) {
		this.matriculaActualiza = matriculaActualiza;
	}

	public Date getBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(Date bajaLogica) {
		this.bajaLogica = bajaLogica;
	}

	public String getMatriculaBaja() {
		return matriculaBaja;
	}

	public void setMatriculaBaja(String matriculaBaja) {
		this.matriculaBaja = matriculaBaja;
	}

}
