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
@Table(name = "scic_ramo")
@Getter
@Setter
public class SCICRamo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_ramo")
	private Long idRamo;
	
	@Size(max = 3)
	@Column(name="cve_ramo")
	private int cveRamo;
	
	@Size(max = 30)
	@Column(name="des_ramo")
	private String descripcionRamo;
	
	@Size(max = 6)
	@Column(name="num_limite")
	private int limiteRamo;
	
	@OneToMany(mappedBy="ramo")
	private Set<SCITIncapacidad> incapacidades = new HashSet<>(0);
	

}
