package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scit_diagnostico")
@Getter
@Setter
public class SCITDiagnostico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_diagnostico")
	private Long idDiagnostico;
	
	@Size(max = 10)
	@Column(name="cve_matricula_medico")
	private String matriculaMedico;
	
	@NotNull
	@Size(max = 350)
	@Column(name="des_medica")
	private String diagnosticoMedico;
	
	@NotNull
	@Size(max = 10)
	@Column(name="cve_matricula_alta")
	private String usuarioAlta;
	
	@ManyToOne
	@JoinColumn(name = "cve_id_incapacidad", nullable = false)
    private SCITIncapacidad incapacidad;

	

}
