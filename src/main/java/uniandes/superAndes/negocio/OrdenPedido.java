package uniandes.superAndes.negocio;

import java.util.Date;

public class OrdenPedido implements VOOrdenPedido {

	private long id;
	private int calificacionPedido;
	private String estado;
	private Date fechaEsperadaDeEntrega;
	private Date fechaEntrega;
	private long proveedor;
	
	public OrdenPedido () {
		this.id = 0;
		this.calificacionPedido=0;
		this.estado="DEFAULT";
		fechaEntrega = new Date();
		fechaEsperadaDeEntrega = new Date();
		proveedor = 0;		
		
	}
	
	public OrdenPedido(long id, int calificacionPedido, String estado, Date fechaEsperadaEntrega, Date fechaEntrega,
			long idProveedor) {
		
		this.id = id;
		this.calificacionPedido = calificacionPedido;
		this.estado = estado;
		this.fechaEsperadaDeEntrega = fechaEsperadaEntrega;
		this.fechaEntrega = fechaEntrega;
		this.proveedor = idProveedor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getCalificacionPedido() {
		return calificacionPedido;
	}
	public void setCalificacionPedido(int calificacionPedido) {
		this.calificacionPedido = calificacionPedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEsperadaDeEntrega() {
		return fechaEsperadaDeEntrega;
	}

	public void setFechaEsperadaDeEntrega(Date fechaEsperadaDeEntrega) {
		this.fechaEsperadaDeEntrega = fechaEsperadaDeEntrega;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public long getProveedor() {
		return proveedor;
	}
	public void setProveedor(long idProveedor) {
		this.proveedor = idProveedor;
	}
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de bebida
	 */
	@Override
	public String toString() 
	{
		return "Orden [id=" + id + ", idProveedor= " + proveedor + ", estado=" + estado + " , fechaEsperada=" + fechaEsperadaDeEntrega +
				", fechadeEntrega= " + fechaEntrega + ", calificacion="  + calificacionPedido + "]";
	}

	
}
