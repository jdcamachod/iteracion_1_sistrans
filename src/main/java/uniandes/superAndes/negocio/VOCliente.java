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
	
	public Long getEmpresa();

	public Long getPersonaNatural();
	
	/**
	 * @return Una cadena de caracteres con la información del cliente
	 */
	@Override
	public String toString(); 
	
		/**
	 * Define la igualdad dos Tipos de bebida
	 * @param tb - El tipo de bebida a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object tb); 
	
}
