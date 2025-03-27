package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITIncapacidad;

@Repository
public interface IncapacidadRepositoryJPA extends JpaRepository<SCITIncapacidad, Long>{

	
	List<SCITIncapacidad> findByIdCasoInOrderByFecInicioAsc(List<Long> idCasos);
	
	List<SCITIncapacidad> findByIdCasoOrderByFecInicioAsc(Long idCaso);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "UPDATE scit_incapacidad SET cve_id_caso =:idCaso, stp_actualiza_registro = NOW(), cve_matricula_actualiza = 'SCIADEM' WHERE cve_id_incapacidad IN ( :idIncapacidades)")
	void actualizarIncapacidades(@Param("idCaso") Long idCaso, @Param("idIncapacidades") List<Long> idIncapacidades);
	
	@Query(nativeQuery=true, value = "SELECT COUNT(*) FROM scit_incapacidad WHERE cve_id_caso = :idCaso")
	int contarIncapacidadesPorCaso(@Param("idCaso") Long idCaso);
}
