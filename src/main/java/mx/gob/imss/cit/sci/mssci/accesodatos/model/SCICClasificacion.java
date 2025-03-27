package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scic_clasificacion")
@Getter
@Setter
public class SCICClasificacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Size(max = 10)
	@Column(name="cve_id_clasificacion")
	private Long idClasificacion;
	
	@NotNull
	@Size(max = 1)
	@Column(name="cve_clasificacion")
	private String cveClasificacion;
	
	@NotNull
	@Size(max = 200)
	@Column(name="des_clasificacion")
	private String desClasificacion;

	
	
}
