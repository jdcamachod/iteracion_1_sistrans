package uniandes.superAndes.negocio;

import java.util.ArrayList;

/**
 * Clase que representa una bodega de una sucursal
 *
 */
public class Bodega {

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
	private int peso;
	
	/**
	 * El identificador de la categoria de producto que almacena la bodega
	 */
	private long idCategoria;
	
	/**
	 * Capacidad en volumen que soporta la bodega
	 */
	private int volumen;
	
	/**
	 * El identificador de la sucursal a la que pertenece la bodega
	 */
	private long idSucursal;
	
	/**
	 * Crea una bodega, constructor vacío
	 */
	public Bodega()
	{
		id=0;
		direccion = "";
		peso = 0;
		idCategoria=0;
		volumen = 0;
		idSucursal = 0;
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
	public Bodega(long id, String direccion, int peso, long idCategoria, int volumen, long idSucursal) {
		
		this.id = id;
		this.direccion = direccion;
		this.peso = peso;
		this.idCategoria = idCategoria;
		this.volumen = volumen;
		this.idSucursal = idSucursal;
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
	public int getPeso() {
		return peso;
	}

	/** 
	 * Asigna un nuevo peso a la bodega
	 * @param peso peso a asignar
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Retorna el identificador de la categoria de los productos de la bodega
	 * @return id de la categoria de la bodega
	 */
	public long getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Asigna una nueva categoria de productos a la bodega
	 * @param idCategoria id a asignar
	 */
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Retorna el volumen capacidad de la bodega
	 * @return volumen de la bodega
	 */
	public int getVolumen() {
		return volumen;
	}

	/**
	 * Asigna un nuevo volumen a la bodega
	 * @param volumen volumen a asignar
	 */
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	/**
	 * Retorna el identificador de la sucursal de la bodega
	 * @return id de la sucursal
	 */
	public long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * Asigna un nuevo identificador a la sucursal
	 * @param idSucursal identificador de la sucursal 
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	
}
