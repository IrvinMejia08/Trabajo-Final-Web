package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.TipoInforme;

public interface ITipoInformeService {

	public Integer insertar(TipoInforme tipoinforme);

	public void modificar(TipoInforme tipoinforme);

	public void eliminar(int idTipoInforme);

	Optional<TipoInforme> listarId(int idTipoInforme);

	List<TipoInforme> listar();

	List<TipoInforme> buscarnombreTipoInforme(String nombreTipoInforme);

}
