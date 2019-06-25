package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoproductos")
public class TipoProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoProducto;

	@Column(name = "", nullable = false, length = 70, unique = true)
	private String nombreTipoProducto;

	@Column(name = "descripcionTipoProducto", nullable = false, length = 100)
	private String descripcionTipoProducto;

	public TipoProducto(int idTipoProducto, String nombreTipoProducto, String descripcionTipoProducto) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.descripcionTipoProducto = descripcionTipoProducto;
	}

	public TipoProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getNombreTipoProducto() {
		return nombreTipoProducto;
	}

	public void setNombreTipoProducto(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}

	public String getDescripcionTipoProducto() {
		return descripcionTipoProducto;
	}

	public void setDescripcionTipoProducto(String descripcionTipoProducto) {
		this.descripcionTipoProducto = descripcionTipoProducto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoProducto;
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
		TipoProducto other = (TipoProducto) obj;
		if (idTipoProducto != other.idTipoProducto)
			return false;
		return true;
	}
}
