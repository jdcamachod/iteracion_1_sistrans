package uniandes.superAndes.negocio;

import java.util.Date;

public abstract class Promocion implements VOPromocion {

	private long id;
	private Date fechaInicial;
	private Date fechaFinal;
	private double precio;
	private Long pague1Lleve2Porcentaje;
	
	private Long pagueNLleveM;
	private Long pagueXLleveY;
	private Long descuentoPorcentaje;
	
	public Promocion()
	{
		id = 0;
		fechaInicial = new Date();
		fechaFinal = new Date();
		precio = 0;
		descuentoPorcentaje =null;
		pague1Lleve2Porcentaje=null;
		pagueNLleveM=null;
		pagueXLleveY=null;
	}
	
	public Promocion(long id, Date fechaInicial, Date fechaFinal, double precio, Long idDescuentoPorcentaje, Long idPague1Lleve2Porcentaje, Long idPagueNLleveM, Long idPagueXLleveY) {
		
		this.id = id;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.precio = precio;
		this.pague1Lleve2Porcentaje=idPague1Lleve2Porcentaje;
		this.pagueNLleveM=idPagueNLleveM;
		this.pagueXLleveY=idPagueXLleveY;
		this.descuentoPorcentaje=idDescuentoPorcentaje;
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
}
