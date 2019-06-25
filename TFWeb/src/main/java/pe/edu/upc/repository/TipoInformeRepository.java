package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.TipoInforme;

public interface TipoInformeRepository extends JpaRepository<TipoInforme, Integer> {

	@Query("from TipoInforme t where t.nombreTipoInforme like %:nombreTipoInforme%")
	List<TipoInforme> findBynombreTipoInforme(String nombreTipoInforme);

	@Query("select count(t.nombreTipoInforme) from TipoInforme t where t.nombreTipoInforme =:nombreTipoInforme")
	public int buscarNombreTipoInforme(@Param("nombreTipoInforme") String nombreTipoInforme);
}
