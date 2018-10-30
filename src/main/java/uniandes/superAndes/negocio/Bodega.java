package uniandes.superAndes.negocio;

import java.util.ArrayList;

/**
 * Clase que representa una bodega de una sucursal
 *
 */
public class Bodega implements VOBodega{

	/**
	 * Identificador único de la bodega
	 */
	private long id;
	
	/**
	 * Dirección de la bodega
	 */
	private String direccion;
	
	/**
	 * La capacidad en peso que tiene la bodega
	 */
	private double peso;
	
	/**
	 * El identificador de la categoria de producto que almacena la bodega
	 */
	private long categoria;
	
	/**
	 * Capacidad en volumen que soporta la bodega
	 */
	private double volumen;
	
	/**
	 * El identificador de la sucursal a la que pertenece la bodega
	 */
	private long sucursal;
	
	/**
	 * Crea una bodega, constructor vacío
	 */
	public Bodega()
	{
		id=0;
		direccion = "";
		peso = 0;
		categoria=0;
		volumen = 0;
		sucursal = 0;
	}
	
	/**
	 * Crea la bodega que se recibe por parámetro
	 * @param id - id de la bodega
	 * @param direccion - direccion de la bodega
	 * @param peso - peso de capacidad de la bodega
	 * @param idCategoria - identificador de la categoria de los productos de la bodega
	 * @param volumen - volumen capacidad de la bodega
	 * @param idSucursal - identificador de la sucursal de la bodega
	 */
	public Bodega(long id, String direccion, double peso, long idCategoria, double volumen, long idSucursal) {
		
		this.id = id;
		this.direccion = direccion;
		this.peso = peso;
		this.categoria = idCategoria;
		this.volumen = volumen;
		this.sucursal = idSucursal;
	}

	/**
	 * Retorna el id de la bodega
	 * @return id de la bodega
	 */
	public long getId() {
		return id;
	}

	/**
	 * Asigna un nuevo id a la bodega
	 * @param id id a asignar
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la direccion de la bodega
	 * @return direccion de la bodega
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Asigna una nueva direccion a la bodega
	 * @param direccion direccion a asignar
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna el peso capacidad de la bodega
	 * @return el peso de la bodega
	 */
	public double getPeso() {
		return peso;
	}

	/** 
	 * Asigna un nuevo peso a la bodega
	 * @param peso peso a asignar
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * Retorna el identificador de la categoria de los productos de la bodega
	 * @return id de la categoria de la bodega
	 */
	public long getCategoria() {
		return categoria;
	}

	/**
	 * Asigna una nueva categoria de productos a la bodega
	 * @param idCategoria id a asignar
	 */
	public void setCategoria(long idCategoria) {
		this.categoria = idCategoria;
	}

	/**
	 * Retorna el volumen capacidad de la bodega
	 * @return volumen de la bodega
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * Asigna un nuevo volumen a la bodega
	 * @param volumen volumen a asignar
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * Retorna el identificador de la sucursal de la bodega
	 * @return id de la sucursal
	 */
	public long getSucursal() {
		return sucursal;
	}

	/**
	 * Asigna un nuevo identificador a la sucursal
	 * @param idSucursal identificador de la sucursal 
	 */
	public void setSucursal(long idSucursal) {
		this.sucursal = idSucursal;
	}
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "Bodega [id=" + id + ", sucursal=" + sucursal + ", tipo=" + categoria + " , direccion=" + direccion + ", volumen=" + volumen + ", peso=" + peso + "]";
	}
	
}
