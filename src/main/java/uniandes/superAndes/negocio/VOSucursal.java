package uniandes.superAndes.negocio;

public interface VOSucursal {

	
	public long getId();
	
	public String getNombre();
	
	public double getTamano();
	
	public String getDireccion();
	
	public String getCiudad();

	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
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
