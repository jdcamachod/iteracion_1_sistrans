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
	 * @return  El identificador si la promocion es pague 1 lleve el segundo con descuento
	 */
	public long getIdPague1Lleve2Porcentaje();
	
	/**
	 * @return El identificador si la promocion es pague n lleve m n<m
	 */
	public long getIdPagueNLleveM();
	
	/**
	 * @return El identificador si la promocion es pague x cantidad lleve y cantidad x<y
	 */
	public long getIdPagueXLleveY();
	
	/**
	 * @return El identificador si el descuento se aplica sobre un porcentaje del producto
	 */
	public long getIdDescuentoPorcentaje();
	
	/**
	 * @return Una cadena de caracteres con la información de la promocion
	 */
	@Override
	public String toString(); 
}

