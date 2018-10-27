package uniandes.superAndes.negocio;

public interface VOCliente {
	
	/**
	 * Retorna el correo electronico del cliente 
	 * @return el correo electronico del cliente
	 */
	public String getCorreoElectronico();
	
	/**
	 * Retorna el id del cliente
	 * @return id del cliente
	 */
	public long getId();
	
	/**
	 * Retorna el nombre del cliente
	 * @return nombre del cliente
	 */
	public String getNombre();
	
	/**
	 * Retorna los puntos acumulados por el cliente
	 * @return
	 */
	public double getPuntos();
	
	/**
	 * @return Una cadena de caracteres con la información del cliente
	 */
	@Override
	public String toString(); 
	
}
