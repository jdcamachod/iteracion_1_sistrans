package uniandes.superAndes.negocio;

public interface VODescuentoPorcentaje {

	/**
	 * Retorna el porcentaje del descuento que se va a aplicar
	 * @return porcentaje a aplicar
	 */
	public double getPorcentaje();
	
	/**
	 * Retorna el identificador de la proomocion del descuento por porcentaje
	 * @return identificador de este tipo de promocion
	 */
	public long getIdDescuentoPorcentaje();
	
	/**
	 * @return Una cadena de caracteres con la información del descuento porcentaje
	 */
	@Override
	public String toString(); 
}
