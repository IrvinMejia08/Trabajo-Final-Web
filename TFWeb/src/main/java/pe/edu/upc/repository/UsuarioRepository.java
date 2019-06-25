package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("from Usuario u where u.nombreUsuario like %:nombreUsuario%")
	List<Usuario> findByNombreUsuario(String nombreUsuario);

	@Query("from Usuario u where u.dniUsuario like %:dniUsuario%")
	List<Usuario> findByDniUsuario(String dniUsuario);

	@Query("select u from Usuario u where u.rol.nombreRol like %?1%")
	public List<Usuario> buscarRol(String nombreRol);

	@Query("select count(u.dniUsuario) from Usuario u where u.dniUsuario =:dniUsuario")
	public int buscarNumeroDni(@Param("dniUsuario") String dniUsuario);

}
