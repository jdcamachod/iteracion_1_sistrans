package uniandes.superAndes.negocio;

public interface VOSucursalesClientes {

	/**
	 * @return El identificador de la sucursal 
	 */
	public long getIdSucursal();
	
	/**
	 * @return El identificador del cliente
	 */
	public long getIdCliente();
	
	/**
	 * @return Una cadena de caracteres con la información de la relacion 
	 */
	@Override
	public String toString(); 
	
}
