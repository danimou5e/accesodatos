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
@Table(name = "scic_tipo_incapacidad")
@Getter
@Setter
public class SCICTipoIncapacidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_tipo_incapacidad")
	private Long idTipoIncapacidad;
	
	@Size(max = 4)
	@Column(name="cve_tipo_incapacidad")
	private int claveTipoIncapacidad;
	
	@Size(max = 100)
	@Column(name="des_tipo_incapacidad")
	private String descripcionTipoIncapacidad;
	
	@Size(max = 10)
	@Column(name="cve_matricula_alta")
	private String matriculaUsuarioAlta;
	
	@OneToMany(mappedBy="tipoIncapacidad")
	private Set<SCITIncapacidad> incapacidades = new HashSet<>(0);

}
