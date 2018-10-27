package uniandes.superAndes.negocio;

public interface VOProductosBodegas {

	/**
	 * @return El identificador del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return El identificador de la bodega
	 */
	public long getIdBodega();
	
	/**
	 * @return Una cadena de caracteres con la información del producto bodega
	 */
	@Override
	public String toString(); 
}
