package uniandes.superAndes.negocio;

public interface VOBodega {
	
	/**
	 * Retorna el id de la bodega
	 * @return id de la bodega
	 */
	public long getId();
	
	/**
	 * Retorna la dirección de la bodega
	 * @return direccion de la bodega
	 */
	public String getDireccion();
	
	/**
	 * Retorna el peso de los productos que soporta la bodega
	 * @return peso capacidad de la bodega
	 */
	public double getPeso();
	
	/**
	 * Retorna la categoria de los productos en la bodega
	 * @return categoria de los productos en la bodega
	 */
	public long getCategoria();
	
	/**
	 * Retorna el volumen capacidad de la bodega
	 * @return volumen capacidad de la bodega
	 */
	public double getVolumen();
	
	/**
	 * Retorna el id de la succursal a la que corresponde la bodega
	 * @return id de la sucursal de la bodega
	 */
	public long getSucursal();
	
	/**
	 * @return Una cadena de caracteres con la información de la bodega
	 */
	@Override
	public String toString(); 
	
	
}
