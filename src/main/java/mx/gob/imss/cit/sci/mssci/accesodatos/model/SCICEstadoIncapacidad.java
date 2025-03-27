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
@Table(name = "scic_estado_incapacidad")
@Getter
@Setter
public class SCICEstadoIncapacidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_estado_incapacidad")
	private Long idEstadoIncapacidad;
	
	@Column(name="ind_estado_incapacidad")
	private int indicadorEstadoIncapacidad;
	
	@Column(name="des_estado_incapacidad")
	private String desEstadoIncapacidad;
	
	@OneToMany(mappedBy="edoIncapacidad")
	private Set<SCITIncapacidad> incapacidadesByEdoIncapacidad = new HashSet<>(0);
	
	
	
}
