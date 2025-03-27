package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scit_incapacidad")
@Getter
@Setter
public class SCITIncapacidad implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_incapacidad")
	private Long idIncapacidad;
	
	@NotNull
	@Valid
	@Size(max = 11)
	@Column(name="ref_folio")
	private String refFolio;
	
	@Size(max = 10)
	@Column(name="cve_matricula_med")
	private String matriculaMedico;
		
	@Size(max = 10)
	@Column(name="cve_matricula_autoriza")
	private String matriculaAutoriza;

	@Size(max = 10)
	@Column(name="imp_pagado")
	private Double importePago;
	
	@NotNull
	@Size(max = 4)
	@Column(name="num_dias_acumulados")
	private int diasAcumulados;
	
	@Size(max = 4)
	@Column(name="num_dias_subsidiados")
	private int diasSubsidiados;
	
	@Column(name="stp_fecha_expedicion")
	private Date fecExpedicion;
	
	@Size(max = 4)
	@Column(name="num_dias_autorizados")
	private int diasAutorizados;
	
	@Column(name="stp_fecha_inicio")
	private Date fecInicio;
	
	@Size(max = 2)
	@Column(name="num_nivel_incapacidad")
	private int nivelIncapacidad;
	
	@Size(max = 4)
	@Column(name="num_dias_prob_rec")
	private int diasProbableRecuperacion;
	
	@Column(name="stp_fecha_termino")
	private Date fecTermino;

	@Size(max = 100)
	@Column(name="ref_diagnostico")
	private String refDiagnostico;
	
	@Size(max = 1)
	@Column(name="tip_empleado_imss")
	private int tipoEmpleadoIMSS;
	
	@Column(name="imp_salario_diario")
	private Double importeSalarioDiario;
	
	@Column(name="imp_salario_topado")
	private Double importeSalarioTopado;

	@Column(name="num_dias_descubiertos")
	private int diasDescubiertos;

	@Size(max = 1)
	@Column(name="tip_certificacion")
	private int tipoCertificacion;
	
	@Size(max = 1)
	@Column(name="ind_subsecuente")
	private int indicadorSubsecuente;
	
	@Column(name="fec_proceso")
	private Date fechaProceso;
	
	@Size(max = 1)
	@Column(name="tip_riesgo")
	private int tipoRiesgo;
	
	@Size(max = 1)
	@Column(name="ind_ctrl_maternidad")
	private int indCtrlMaternidad;
	
	
	@Column(name="num_nivel_inca")
	private int nivelAtencionUnidadDeExpedida;
	
	//ya que se debe revisar que el tip_marca pago = 4 tip_empleado_imss` TINYINT(1) NULL
	@Size(max = 2)
	@Column(name="tip_marca_pago")
	private int tipoMarcaPago;
	
	@Size(max = 1)
	@Column(name="ind_reconocimiento_rt")
	private char indicadorReconocimientoRT;
	
	//COMMENT 'Número de convenio del consejo consultivo 0=Nominativo 1=Convenio'
	@Size(max = 1)
	@Column(name="ind_convenio")
	private int indicadorConvenio;
	
	//COMMENT 'Días autorizados adicionales a la incapacidad, a los días probables de recuperación'
	@Column(name="num_dias_adicionales")
	private int diasAdicionales;
	
	@Column(name="fec_primer_ingreso")
	private Date fechaPrimerIngreso;
	
	@Column(name="cve_periodo_oci")
	private int cvePeriodoOCI;

	@Size(max = 11)
	@Column(name="cve_matricula_alta")
	private String usuarioAlta;
	
	@Size(max = 11)
	@Column(name = "cve_reg_patronal")
    private String registroPatronal;

	@ManyToOne
	@JoinColumn(name = "cve_id_estado_incapacidad", nullable = false)
    private SCICEstadoIncapacidad edoIncapacidad;

	@ManyToOne
	@JoinColumn(name = "cve_id_ramo", nullable = false)
    private SCICRamo ramo;

	@ManyToOne
	@JoinColumn(name = "cve_id_tipo_incapacidad", nullable = false)
	private SCICTipoIncapacidad tipoIncapacidad;
	
	@ManyToOne
	@JoinColumn(name = "cve_id_unidad_expedicion")
    private SCICUnidad unidadExpedicion;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cve_id_unidad_tramitadora")
    private SCICUnidad unidadTramitadora;
    
	@Column(name = "cve_id_caso", nullable = false)
	private Long idCaso;
	
	
    @OneToMany(cascade=CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="incapacidad")
    private Set<SCITDiagnostico> diagnosticos = new HashSet<>(0);
    


}
