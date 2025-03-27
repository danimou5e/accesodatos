package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "scit_caso")
@Getter
@Setter
public class SCITCaso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotNull
//	@Size(max = 10)
	@Column(name="cve_id_caso")
	private Long idCaso;
	
	@Column(name = "cve_id_estado_incapacidad")
    private Long idEstadoIncapacidad;

//	@NotNull
	@Column(name="fec_ini_caso")
	private Date fecInicioCaso;
	
	@Column(name="num_dias_prob_rec")
	private int dpr;
	
//	@NotNull
//	@Size(max = 4)
	@Column(name="num_dias_acumulados")
	private int numDiasAcumulados;
	
//	@NotNull
//	@Size(max = 10)
	@Column(name="imp_monto_acumulado")
	private Double impMontoAcumulado;
	
	@Column(name="fec_ult_incap_caso")
	private Date fecUltimaIncapacidadCaso;
	
//	@NotNull
//	@Size(max = 10)
	@Column(name="cve_matricula_alta")
	private String cveMatAlta;

//	@Size(max = 10)
	@Column(name="cve_matricula_actuailza")
	private String cveMatActualizaRegistro;
	
	@Column(name="stp_actualiza_registro")
	private Date fecActualizaRegistro;  

	@Column(name="stp_baja_logica")
	private Date bajaLogica;
	
	@Column(name="cve_matricula_baja")
	private String matriculaBaja;
	
	@ManyToOne
	@JoinColumn(name = "cve_id_paciente", nullable = false)
    private Paciente paciente;

}
