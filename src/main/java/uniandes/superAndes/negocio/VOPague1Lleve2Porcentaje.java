package uniandes.superAndes.negocio;

public interface VOPague1Lleve2Porcentaje {

	/**
	 * @return El porcentaje del segundo producto que se lleva con descuento
	 */
	public double getPorcentaje2();
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de promocion
	 */
	@Override
	public String toString(); 
}
