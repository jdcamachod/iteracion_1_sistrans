package uniandes.superAndes.negocio;

import java.util.Date;

public class Factura {
	
	private long id;
	private Date fecha;
	private double costoTotal;
	private long idCliente;
	public Factura(long id, Date fecha, double costoTotal, long idCliente) {
		
		this.id = id;
		this.fecha = fecha;
		this.costoTotal = costoTotal;
		this.idCliente = idCliente;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	

	
}
