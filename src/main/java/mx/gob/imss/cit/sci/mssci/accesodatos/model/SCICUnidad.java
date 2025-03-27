package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scic_unidad")
@Getter
@Setter
public class SCICUnidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_unidad")
	private Long idUnidad;
	
	@Column(name="cve_presupuestal_unidad")
	private String cvePresupuestalUnidad;
	
	@Column(name="des_nombre_unidad")
	private String nombreUnidadMedica;
	
	@Column(name="cve_id_unidad_origen")
	private String nombreUnidadOrigen;
	
	@Column(name="num_nivel")
	private String nivel;
	
	@Column(name="ind_mf")
	private String indicadorMF;
	
	@Column(name="num_unidad")
	private String numeroUnidad;
	
	@Column(name="cve_preii")
	private String cvePREII;
	
	@Column(name="cve_id_delegacion")
	private int idDelegacion;
	
	@OneToMany(mappedBy="unidadExpedicion")
	private Set<SCITIncapacidad> incapacidadesByUnidadExpedicion = new HashSet<>(0);
	
	@OneToMany(mappedBy="unidadTramitadora")
	private Set<SCITIncapacidad> incapacidadesByUnidadTramitadora = new HashSet<>(0);
	
	

}
