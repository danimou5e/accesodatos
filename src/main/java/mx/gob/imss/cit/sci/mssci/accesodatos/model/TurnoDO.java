package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "scic_turno")
public class TurnoDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_id_turno")
	private Long idTurno;
	
	@Column(name = "ref_turno")
	private String ref_turno;
	
	@Column(name = "des_turno")
	private String descripcion;
	
	@Column(name = "stp_alta_registro")
	private String altaRegistro;
	
	@Column(name = "cve_matricula_alta")
	private String matriculaAlta;
	
	@Column(name = "stp_actualiza_registro")
	private String fechaModificacion;
	
	@Column(name = "cve_matricula_actualiza")
	private String matriculaActualiza;
	
	@Column(name = "stp_baja_logica")
	private String bajaLogica;
	
	@Column(name = "cve_matricula_baja")
	private String matriculaBajaLogica;

}
