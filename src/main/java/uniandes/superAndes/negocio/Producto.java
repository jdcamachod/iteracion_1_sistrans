package uniandes.superAndes.negocio;

import java.util.ArrayList;
import java.util.Date;

public class Producto {
	
	private long id;
	
	private String codigoBarras;
	private double peso;
	private double volumen;
	private String marca;
	private int nivelReorden;
	private String nombre;
	private double precioUnitario;
	private String presentacion;
	private double precioUnidadMedida;
	private long idCategoria;
	private Date fechaVencimiento;
	private int cantidad;
	private double cantidadPresentacion;
	
	public Producto()
	{
		this.id = 0;
		this.cantidad = 0;
		this.codigoBarras = "";
		this.marca = "";
		this.nivelReorden = 0;
		this.nombre = "";
		this.precioUnitario = 0;
		this.presentacion = "";
		this.idCategoria = 0;
		this.peso = 0;
		this.volumen = 0;
		this.fechaVencimiento = new Date();
		this.precioUnidadMedida =0;
		this.cantidadPresentacion=0;
	}
	public Producto(long id, int cantidad, String codigoBarras, double peso, double volumen, String marca,
			int nivelReorden, String nombre, double precioUnitario, String presentacion, 
			long idCategoria, Date fechaVencimiento, double precioUnidadMedida, double cantidadPresentacion) {
	
		this.id = id;
		this.cantidad = cantidad;
		this.codigoBarras = codigoBarras;
		this.marca = marca;
		this.nivelReorden = nivelReorden;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.presentacion = presentacion;
	
		this.idCategoria = idCategoria;
		this.peso = peso;
		this.volumen = volumen;
		this.fechaVencimiento =fechaVencimiento;
		this.precioUnidadMedida = precioUnidadMedida;
		this.cantidadPresentacion = cantidadPresentacion;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getVolumen() {
		return volumen;
	}
	public void setVolumen(double volumen) {
		this.volumen = volumen;
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
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}
	public void setPrecioUnidadMedida(double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getCantidadPresentacion() {
		return cantidadPresentacion;
	}
	public void setCantidadPresentacion(double cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}
	
	
	
	
	

	
}
