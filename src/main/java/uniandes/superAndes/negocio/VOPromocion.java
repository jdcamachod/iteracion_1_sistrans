package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOPromocion {

	/**
	 * @return El identificador de la promocion
	 */
	public long getId();
	
	/**
	 * @return La fecha en la que comienza la promocion
	 */
	public Date getFechaInicial();
	
	/**
	 * @return La fecha en la que finaliza la promocion
	 */
	public Date getFechaFinal();
	
	/**
	 * @return El precio de la promocion
	 */
	public int getPrecio();
	
	
	/**
	 * @return Una cadena de caracteres con la información de la promocion
	 */
	@Override
	public String toString(); 
}

