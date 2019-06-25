package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.TipoProducto;

public interface ITipoProductoService {

	public Integer insertar(TipoProducto tipoproducto);

	public void modificar(TipoProducto tipoproducto);

	public void eliminar(int idTipoProducto);

	Optional<TipoProducto> listarId(int idTipoProducto);

	List<TipoProducto> listar();

	List<TipoProducto> buscarnombreTipoProducto(String nombreTipoProducto);

}
