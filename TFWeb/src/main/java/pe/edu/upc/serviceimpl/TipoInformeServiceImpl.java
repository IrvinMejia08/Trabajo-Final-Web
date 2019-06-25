package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.TipoInforme;
import pe.edu.upc.repository.TipoInformeRepository;
import pe.edu.upc.service.ITipoInformeService;

@Service
public class TipoInformeServiceImpl implements ITipoInformeService {

	@Autowired
	private TipoInformeRepository tiR;

	@Override
	@Transactional
	public Integer insertar(TipoInforme tipoinforme) {

		int rpta = tiR.buscarNombreTipoInforme(tipoinforme.getNombreTipoInforme());
		if (rpta == 0) {
			tiR.save(tipoinforme);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(TipoInforme tipoinforme) {
		tiR.save(tipoinforme);
	}

	@Override
	@Transactional
	public void eliminar(int idTipoInforme) {
		tiR.deleteById(idTipoInforme);
	}

	@Override
	@Transactional (readOnly = true)
	public Optional<TipoInforme> listarId(int idTipoInforme) {
		return tiR.findById(idTipoInforme);
	}

	@Override
	@Transactional (readOnly = true)
	public List<TipoInforme> listar() {
		return tiR.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public List<TipoInforme> buscarnombreTipoInforme(String nombreTipoInforme) {
		return tiR.findBynombreTipoInforme(nombreTipoInforme);
	}
}
