package uniandes.superAndes.negocio;

import java.util.Date;

public abstract class Promocion implements VOPromocion {

	private long id;
	private Date fechaInicial;
	private Date fechaFinal;
	private int precio;
	private long idPague1Lleve2Porcentaje;
	
	private long idPagueNLleveM;
	private long idPagueXLleveY;
	private long idDescuentoPorcentaje;
	
	public Promocion()
	{
		id = 0;
		fechaInicial = new Date();
		fechaFinal = new Date();
		precio = 0;
		idDescuentoPorcentaje =0;
		idPague1Lleve2Porcentaje=0;
		idPagueNLleveM=0;
		idPagueXLleveY=0;
	}
	
	public Promocion(long id, Date fechaInicial, Date fechaFinal, int precio, long idDescuentoPorcentaje, long idPague1Lleve2Porcentaje, long idPagueNLleveM, long idPagueXLleveY) {
		
		this.id = id;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.precio = precio;
		this.idPague1Lleve2Porcentaje=idPague1Lleve2Porcentaje;
		this.idPagueNLleveM=idPagueNLleveM;
		this.idPagueXLleveY=idPagueXLleveY;
		this.idDescuentoPorcentaje=idDescuentoPorcentaje;
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
	public long getIdPague1Lleve2Porcentaje() {
		return idPague1Lleve2Porcentaje;
	}

	public void setIdPague1Lleve2Porcentaje(long idPague1Lleve2Porcentaje) {
		this.idPague1Lleve2Porcentaje = idPague1Lleve2Porcentaje;
	}

	public long getIdPagueNLleveM() {
		return idPagueNLleveM;
	}

	public void setIdPagueNLleveM(long idPagueNLleveM) {
		this.idPagueNLleveM = idPagueNLleveM;
	}

	public long getIdPagueXLleveY() {
		return idPagueXLleveY;
	}

	public void setIdPagueXLleveY(long idPagueXLleveY) {
		this.idPagueXLleveY = idPagueXLleveY;
	}

	public long getIdDescuentoPorcentaje() {
		return idDescuentoPorcentaje;
	}

	public void setIdDescuentoPorcentaje(long idDescuentoPorcentaje) {
		this.idDescuentoPorcentaje = idDescuentoPorcentaje;
	}
	
	
}
