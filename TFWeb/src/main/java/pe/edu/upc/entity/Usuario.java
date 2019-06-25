package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@NotEmpty(message = "Ingresa el nombre")
	@Column(name = "nombreUsuario", nullable = false, length = 40)
	private String nombreUsuario;

	@Size(min = 2, max = 2)
	@NotEmpty(message = "Ingresa tu edad")
	@Column(name = "edadUsuario", nullable = false, length = 2)
	private String edadUsuario;

	@NotEmpty(message = "Ingresa Email")
	@Email
	@Column(name = "emailUsuario", nullable = false, length = 50)
	private String emailUsuario;

	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingrese DNI")
	@Column(name = "", nullable = false, length = 8, unique = true)
	private String dniUsuario;

	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;
	
	private String fotoUsuario;

	public Usuario(int idUsuario, String nombreUsuario, String edadUsuario, String emailUsuario, String dniUsuario,
			Rol rol, String fotoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.edadUsuario = edadUsuario;
		this.emailUsuario = emailUsuario;
		this.dniUsuario = dniUsuario;
		this.rol = rol;
		this.fotoUsuario = fotoUsuario;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEdadUsuario() {
		return edadUsuario;
	}

	public void setEdadUsuario(String edadUsuario) {
		this.edadUsuario = edadUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
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
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
}