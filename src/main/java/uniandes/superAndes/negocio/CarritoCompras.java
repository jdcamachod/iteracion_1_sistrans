package uniandes.superAndes.negocio;

import java.util.Date;

public class CarritoCompras implements VOCarritoCompras{

	/**
	 * El identificador del carrito de compras
	 */
	private long id;
	
	/**
	 * Identificador del cliente que solicita el carrito
	 */
	private Long idCliente;
	
	/**
	 * Fecha en la que se solicitó el carrito por el cliente actual
	 */
	private Date fecha;
	
	/**
	 * Constructor que crea un carrito de compras vacío
	 */
	public CarritoCompras()
	{
		id = 0;
		idCliente= null;
		fecha = new Date();
	}

	/**
	 * Crea un carrito de compras con la informacion recibida por parametro
	 * @param id El identificador del carrito
	 * @param idCliente El identificador del cliente que solicita el carro
	 * @param fecha la fecha de solicitud del carro
	 */
	public CarritoCompras(long id, Long idCliente, Date fecha) {
		
		this.id = id;
		this.idCliente = idCliente;
		this.fecha = fecha;
	}

	/**
	 * @return El id del carrito
	 */
	public long getId() {
		return id;
	}

	/**
	 * Asigna un nuevo id al carrito
	 * @param id El id a asignar
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El identificador del cliente
	 */
	public Long getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente Asigna un nuevo cliente
	 */
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return La fecha de solicitud del carrito
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Asigna una nueva fecha al carrito
	 * @param fecha Fecha a asignar
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
