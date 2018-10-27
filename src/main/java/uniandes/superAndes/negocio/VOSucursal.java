package uniandes.superAndes.negocio;

public interface VOSucursal {

	/**
	 * @return El identificador de la sucursal 
	 */
	public long getId();
	
	/**
	 * @return El nombre de la sucursal
	 */
	public String getNombre();
	
	/**
	 * @return El tamaño de la sucursal
	 */
	public double getTamano();
	
	/**
	 * @return La direccion de la sucursal
	 */
	public String getDireccion();
	
	/**
	 * @return la ciudad de la sucursal
	 */
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
