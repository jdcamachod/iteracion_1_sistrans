package uniandes.superAndes.negocio;

import java.util.Date;

public abstract class Promocion {

	private long id;
	private Date fechaInicial;
	private Date fechaFinal;
	private int precio;
	public Promocion()
	{
		id = 0;
		fechaInicial = new Date();
		fechaFinal = new Date();
		precio = 0;
	}
	public Promocion(long id, Date fechaInicial, Date fechaFinal, int precio) {
		super();
		this.id = id;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.precio = precio;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
}
