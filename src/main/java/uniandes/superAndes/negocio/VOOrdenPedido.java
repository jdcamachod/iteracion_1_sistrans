package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOOrdenPedido {

	/**
	 * @return El identificador de la orden
	 */
	public long getId();
	
	/**
	 * @return la calificacion del pedido
	 */
	public int getCalificacionPedido();
	
	/**
	 * @return El estado del pedido
	 */
	public String getEstado();
	
	/**
	 * @return La fecha esperada de entrega
	 */
	public Date getFechaEsperadaDeEntrega();
	
	/**
	 * @return La fecha real de entrega
	 */
	public Date getFechaEntrega();
	
	/**
	 * @return El identificador del proveedor
	 */
	public long getProveedor();
	
	/**
	 * @return Una cadena de caracteres con la información de la orden pedido
	 */
	@Override
	public String toString(); 
	
	
}
