package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.TipoProducto;
import pe.edu.upc.repository.TipoProductoRepository;
import pe.edu.upc.service.ITipoProductoService;

@Service
public class TipoProductoServiceImpl implements ITipoProductoService {

	@Autowired
	private TipoProductoRepository tpR;

	@Override
	@Transactional
	public Integer insertar(TipoProducto tipoproducto) {

		int rpta = tpR.buscarNombreTipoProducto(tipoproducto.getNombreTipoProducto());
		if (rpta == 0) {
			tpR.save(tipoproducto);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(TipoProducto tipoproducto) {
		tpR.save(tipoproducto);
	}

	@Override
	@Transactional
	public void eliminar(int idTipoProducto) {
		tpR.deleteById(idTipoProducto);
	}

	@Override
	@Transactional (readOnly = true)
	public Optional<TipoProducto> listarId(int idTipoProducto) {
		return tpR.findById(idTipoProducto);
	}

	@Override
	@Transactional (readOnly = true)
	public List<TipoProducto> listar() {
		return tpR.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public List<TipoProducto> buscarnombreTipoProducto(String nombreTipoProducto) {
		return tpR.findBynombreTipoProducto(nombreTipoProducto);
	}
}
