package uniandes.superAndes.negocio;

public interface VOPromocionesFacturas {

	/**
	 * @return El identificador de la promocion
	 */
	public long getIdPromocion();
	
	/**
	 * @return El identificador de la factura
	 */
	public long getIdFactura();
	
	/**
	 * @return Una cadena de caracteres con la información de la relacion promociones facturas
	 */
	@Override
	public String toString(); 
}
