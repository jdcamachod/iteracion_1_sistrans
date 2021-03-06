package uniandes.superAndes.negocio;

import java.util.Date;

public class PromocionPaquete {

	private long idProductoOrigen;
	private long idProductoPaquete;
	private Date fechaInicial;
	private Date fechaFinal;
	
	public PromocionPaquete(long idProductoOrigen, long idProductoPaquete, Date fechaInicial, Date fechaFinal) {
		
		this.idProductoOrigen = idProductoOrigen;
		this.idProductoPaquete = idProductoPaquete;
		this.fechaFinal = fechaFinal;
		this.fechaInicial = fechaInicial;
	}

	public long getIdProductoOrigen() {
		return idProductoOrigen;
	}

	public void setIdProductoOrigen(long idProductoOrigen) {
		this.idProductoOrigen = idProductoOrigen;
	}

	public long getIdProductoPaquete() {
		return idProductoPaquete;
	}

	public void setIdProductoPaquete(long idProductoPaquete) {
		this.idProductoPaquete = idProductoPaquete;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
	
	
	
}
