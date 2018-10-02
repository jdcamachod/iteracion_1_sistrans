package uniandes.superAndes.negocio;

import java.util.ArrayList;

public class Producto {
	
	private long id;
	private String calidad;
	private String codigoBarras;
	private String especificacionEmpacado;
	private String marca;
	private int nivelReorden;
	private String nombre;
	private double precioUnitario;
	private int presentacion;
	private String unidadMedida;
	private long idCategoria;
	public Producto(long id, String calidad, String codigoBarras, String especificacionEmpacado, String marca,
			int nivelReorden, String nombre, double precioUnitario, int presentacion, String unidadMedida,
			long idCategoria) {
	
		this.id = id;
		this.calidad = calidad;
		this.codigoBarras = codigoBarras;
		this.especificacionEmpacado = especificacionEmpacado;
		this.marca = marca;
		this.nivelReorden = nivelReorden;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.presentacion = presentacion;
		this.unidadMedida = unidadMedida;
		this.idCategoria = idCategoria;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getEspecificacionEmpacado() {
		return especificacionEmpacado;
	}
	public void setEspecificacionEmpacado(String especificacionEmpacado) {
		this.especificacionEmpacado = especificacionEmpacado;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getNivelReorden() {
		return nivelReorden;
	}
	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(int presentacion) {
		this.presentacion = presentacion;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
	
	
	

	
}
