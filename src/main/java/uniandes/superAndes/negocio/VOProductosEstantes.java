package uniandes.superAndes.negocio;

public interface VOProductosEstantes {

	/**
	 * @return El identificador del estante
	 */
	public long getIdEstante();
	
	/**
	 * @return El identificador del producto
	 */
	public long getIdProducto();
	
	/**
	 * @return Una cadena de caracteres con la información del producto estante
	 */
	
	public int getCantidad();
	@Override
	public String toString(); 
}
