package uniandes.superAndes.negocio;

import java.util.Date;

public class OrdenPedido implements VOOrdenPedido {

	private long id;
	private String calificacionPedido;
	private int estado;
	private Date fechaEsperadaEntrega;
	private Date fechaEntrega;
	private long idProveedor;
	public OrdenPedido(long id, String calificacionPedido, int estado, Date fechaEsperadaEntrega, Date fechaEntrega,
			long idProveedor) {
		
		this.id = id;
		this.calificacionPedido = calificacionPedido;
		this.estado = estado;
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.fechaEntrega = fechaEntrega;
		this.idProveedor = idProveedor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCalificacionPedido() {
		return calificacionPedido;
	}
	public void setCalificacionPedido(String calificacionPedido) {
		this.calificacionPedido = calificacionPedido;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}
	public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
	
}
