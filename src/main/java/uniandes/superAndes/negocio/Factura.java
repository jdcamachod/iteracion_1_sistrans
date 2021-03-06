package uniandes.superAndes.negocio;

import java.util.Date;

/**
 * Clase que representa una factura
 * @author Admin
 *
 */
public class Factura implements VOFactura {
	
	/**
	 * Identificador unico de la factura 
	 */
	private long id;
	
	/**
	 * fecha de  creaci�n de la factura
	 */
	private Date fecha;
	
	/**
	 * costo total de los productos de la factura
	 */
	private double costoTotal;
	
	/**
	 * Identificador del cliente que tiene la factura
	 */
	private Long cliente;
	
	/**
	 * Indentificador de la sucursal
	 */
	private Long sucursal;
	
	
	public Factura()
	{
		this.id = 0;
		this.fecha = new Date();
		this.costoTotal=0;
		this.cliente = null;
		this.sucursal = null;
	}
	/**
	 * Constructor con valores para crear la factura
	 * @param id Identificador a asignar
	 * @param fecha fecha a asignar
	 * @param costoTotal costo total a asignar
	 * @param idCliente identificador del cliente a asignar
	 */
	public Factura(long id, Date fecha, double costoTotal, Long cliente, Long sucursal) {
		
		this.id = id;
		this.fecha = fecha;
		this.costoTotal = costoTotal;
		this.cliente = cliente;
		this.sucursal = sucursal;
	}
	
	/**
	 * Retorna el id de la factura
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Asigna el id recibido por par�metro a la factura
	 * @param id identificador a asignar
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Retorna la fecha de la factura
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Asigna la fecha recinida por par�metro a la factura
	 * @param fecha fecha a asignar
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Retorna el costo total de la factura
	 */
	public double getCostoTotal() {
		return costoTotal;
	}
	
	/**
	 * Asigna un costo a la factura
	 * @param costoTotal costo a asignar
	 */
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	/**
	 * Retorna el id del cliente
	 */
	public Long getCliente() {
		return cliente;
	}
	
	/**
	 * Asigna un cliente con su id a la factura
	 * @param idCliente id a asignar
	 */
	public void setCliente(Long idCliente) {
		this.cliente = idCliente;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}
	
	
	
	
	
	

	
}
