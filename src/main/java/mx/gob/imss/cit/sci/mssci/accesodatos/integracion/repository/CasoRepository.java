package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.Caso;

@Repository
public interface CasoRepository extends JpaRepository<Caso, Long> {
	
	List<Caso> findByIdPacienteAndIdEstadoIncapacidadNotOrderByFecInicioCasoDescFecUltimaIncapacidadCasoDescIdCasoDesc(Long idPaciente, Long estadoAgrupado);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE scit_caso SET cve_id_estado_incapacidad =3, cve_matricula_baja ='SCIADEM', stp_baja_logica = NOW() WHERE cve_id_caso IN ( :idCasos)")
	void actualizaCasos(@Param("idCasos") List<Long> idCasos );
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE scit_caso SET cve_id_estado_incapacidad = :idEstadoIncapacidad, "
			+ "fec_ini_caso = :fechaInicio, num_dias_prob_rec= :dpr, num_dias_acumulados= :diasAcumulados, "
			+ "imp_monto_acumulado = :montoAcumulado, fec_ult_incap_caso = :fechaFin,"
			+ "stp_actualiza_registro = NOW(), cve_matricula_actuailza = 'SCIADEM', cve_matricula_baja = NULL, stp_baja_logica = NULL "
			+ "WHERE cve_id_caso = :idCaso")
	void conformaCaso(@Param("idEstadoIncapacidad") Long idEstadoIncapacidad, @Param("fechaInicio") Date fechaInicio,
			@Param("dpr") int dpr, @Param("diasAcumulados") int diasAcumulados, @Param("montoAcumulado") Double montoAcumulado,
			@Param("fechaFin") Date fechaFin, @Param("idCaso") Long idCaso);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "INSERT INTO scit_caso_activos (cve_id_caso, cve_id_estado_incapacidad, fec_ini_caso, num_dias_prob_rec, num_dias_acumulados, imp_monto_acumulado, fec_ult_incap_caso, cve_matricula_alta, cve_id_paciente) "
									 + "VALUES ( :idCaso, :idEstadoIncapacidad, :fechaInicioCaso, :dpr, :diasAcumulados, :montoAcumulado, :fechaFinCaso, 'SCIADEM', :idPaciente ) ")
	void insertaCasoActivo(@Param("idCaso") Long idCaso, @Param("idEstadoIncapacidad") Long idEstadoIncapacidad, @Param("fechaInicioCaso") Date fechaInicioCaso, @Param("dpr") int dpr, 
							@Param("diasAcumulados") int diasAcumulados, @Param("montoAcumulado") Double montoAcumulado, @Param("fechaFinCaso") Date fechaFinCaso, @Param("idPaciente") Long idPaciente);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE scit_comentario SET cve_id_caso =:idCaso, ind_ult_comentario=0  WHERE cve_id_caso IN (:idCasos)")
	void agruparComentarios(@Param("idCasos") List<Long> idCasos, @Param("idCaso") Long idCaso);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE scit_comentario SET ind_ult_comentario = 1 WHERE cve_id_caso =:idCaso ORDER BY stp_fecha_comentario DESC LIMIT 1")
	void actualizarComentario(@Param("idCaso") Long idCaso);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "DELETE FROM scit_caso_activos WHERE cve_id_caso IN ( :idCasos)")
	void eliminarCasoActivo(@Param("idCasos") List<Long> idCasos);
	
}
