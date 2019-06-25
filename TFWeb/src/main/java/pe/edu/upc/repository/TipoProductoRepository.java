package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.TipoProducto;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {

	@Query("from TipoProducto t where t.nombreTipoProducto like %:nombreTipoProducto%")
	List<TipoProducto> findBynombreTipoProducto(String nombreTipoProducto);

	@Query("select count(t.nombreTipoProducto) from TipoProducto t where t.nombreTipoProducto =:nombreTipoProducto")
	public int buscarNombreTipoProducto(@Param("nombreTipoProducto") String nombreTipoProducto);
}
