package uniandes.superAndes.negocio;

import java.util.Date;

/**
 * Representa una promocion de un descuento de un porcentaje sobre un producto
 *
 */
public class DescuentoPorcentaje extends Promocion {

	/**
	 * El porcentaje a descontar
	 */
	private int porcentaje;
	
	private long idDescuentoPorcentaje;

	/**
	 * Constructor de una promocion por descuento de porcentaje
	 */
	public DescuentoPorcentaje()
	{
		super();
		porcentaje = 0;
		
	}
	
	/**
	 * Crea una nueva promocion de descuento de porcentaje con la informacion recibida
	 * @param id - id de la promocion
	 * @param fechaInicial - fecha donde comienza la promocion
	 * @param fechaFinal - fecha donde acaba la promocion
	 * @param precio - precio de la promocion
	 * @param porcentaje - porcentaje a descontar con la promocion
	 */
	public DescuentoPorcentaje(long id, Date fechaInicial, Date fechaFinal, int precio, int porcentaje, long idDescuentoPorcentaje) {
		super(id, fechaInicial, fechaFinal, precio,idDescuentoPorcentaje, 0,0,0);
		this.porcentaje = porcentaje;
	}

	/**
	 * Retorna el porcentaje a descontar
	 * @return porcentaje a descontar
	 */
	public int getPorcentaje() {
		return porcentaje;
	}

	/**
	 * Asigna un porcentaje en la promocion
	 * @param porcentaje porcentaje a asignar
	 */
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public long getIdDescuentoPorcentaje() {
		return idDescuentoPorcentaje;
	}

	public void setIdDescuentoPorcentaje(long idDescuentoPorcentaje) {
		this.idDescuentoPorcentaje = idDescuentoPorcentaje;
	}
	
	
	
	
}
