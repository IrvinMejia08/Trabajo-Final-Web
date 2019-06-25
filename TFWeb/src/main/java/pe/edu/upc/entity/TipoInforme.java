package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoinformes")
public class TipoInforme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoInforme;

	@Column(name = "", nullable = false, length = 70, unique = true)
	private String nombreTipoInforme;

	@Column(name = "descripcionTipoInforme", nullable = false, length = 100)
	private String descripcionTipoInforme;

	public TipoInforme(int idTipoInforme, String nombreTipoInforme, String descripcionTipoInforme) {
		super();
		this.idTipoInforme = idTipoInforme;
		this.nombreTipoInforme = nombreTipoInforme;
		this.descripcionTipoInforme = descripcionTipoInforme;
	}

	public TipoInforme() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdTipoInforme() {
		return idTipoInforme;
	}

	public void setIdTipoInforme(int idTipoInforme) {
		this.idTipoInforme = idTipoInforme;
	}

	public String getNombreTipoInforme() {
		return nombreTipoInforme;
	}

	public void setNombreTipoInforme(String nombreTipoInforme) {
		this.nombreTipoInforme = nombreTipoInforme;
	}

	public String getDescripcionTipoInforme() {
		return descripcionTipoInforme;
	}

	public void setDescripcionTipoInforme(String descripcionTipoInforme) {
		this.descripcionTipoInforme = descripcionTipoInforme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoInforme;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoInforme other = (TipoInforme) obj;
		if (idTipoInforme != other.idTipoInforme)
			return false;
		return true;
	}
}
