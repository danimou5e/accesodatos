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
@Table(name = "scit_usuario")
@Getter
@Setter
public class SCITUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Size(max = 11)
	@Column(name="cve_id_usuario")
	private Long idUsuario;
	
	@NotNull
	@Size(max = 10)
	@Column(name="cve_matricula_usr")
	private String matriculaUsuario;
	
	@NotNull
	@Size(max = 30)
	@Column(name="nom_nombre")
	private String nombre;
	
	@NotNull
	@Size(max = 30)
	@Column(name="nom_paterno")
	private String apPaterno;
	
	@Size(max = 30)
	@Column(name="nom_materno")
	private String apMaterno;
	
	@Size(max = 50)
	@Column(name="ref_email")
	private String correoElectronico;
	
	@Column(name="cve_id_unidad")
	private Long idUnidadMedica;
	
	@Size(max = 4)
	@Column(name="cve_consultorio")
	private String numeroConsultorio;
	
	@NotNull
	@Size(max = 11)
	@Column(name="cve_id_perfil")
	private Long idPerfil;


}
