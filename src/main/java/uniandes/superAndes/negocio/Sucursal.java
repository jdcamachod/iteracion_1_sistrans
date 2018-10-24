package uniandes.superAndes.negocio;

import java.util.ArrayList;



public class Sucursal implements VOSucursal {
	
	private long id;
	private String nombre;
	private double tamano;
	private String direccion;
	private String ciudad;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Sucursal() 
	{
		this.id = 0;
		this.nombre = "Default";
		this.direccion = "Default";
		this.ciudad = "Default";
		this.tamano = 0.0;

	}
	
	public Sucursal(long id, String nombre, double tamano, String direccion, String ciudad) {
		
		this.id = id;
		this.nombre = nombre;
		this.tamano = tamano;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getTamano() {
		return tamano;
	}
	public void setTamano(double tamanio) {
		this.tamano = tamanio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", tamanio=" + tamano + " , direccion=" + direccion + ", ciudad=" + ciudad + "]";
	}

	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Sucursal tb = (Sucursal) tipo;
		return id == tb.id && nombre.equalsIgnoreCase (tb.nombre);
	}
	
	

}
