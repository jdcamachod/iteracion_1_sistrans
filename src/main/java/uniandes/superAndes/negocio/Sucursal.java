package uniandes.superAndes.negocio;

import java.util.ArrayList;

public class Sucursal {
	
	private long id;
	private String nombre;
	private int tamanio;
	private String direccion;
	private String ciudad;
	public Sucursal(long id, String nombre, int tamanio, String direccion, String ciudad) {
		
		this.id = id;
		this.nombre = nombre;
		this.tamanio = tamanio;
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
	public int getTamanio() {
		return tamanio;
	}
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
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
	
	
	
	
	

}
