package uniandes.superAndes.negocio;

import java.util.Date;

public class Promocion implements VOPromocion {

	private long id;
	private Date fechaInicial;
	private Date fechaFinal;
	private double precio;
	private Long pague1Lleve2Porcentaje;
	
	private Long pagueNLleveM;
	private Long pagueXLleveY;
	private Long descuentoPorcentaje;
	private Long idProducto;
	
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
		idProducto = null;
	}
	
	public Promocion(long id, Date fechaInicial, Date fechaFinal, double precio, Long idDescuentoPorcentaje, Long idPague1Lleve2Porcentaje, Long idPagueNLleveM, Long idPagueXLleveY, Long idProducto) {
		
		this.id = id;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.precio = precio;
		this.pague1Lleve2Porcentaje=idPague1Lleve2Porcentaje;
		this.pagueNLleveM=idPagueNLleveM;
		this.pagueXLleveY=idPagueXLleveY;
		this.descuentoPorcentaje=idDescuentoPorcentaje;
		this.idProducto=idProducto;
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
	
	public Long getPague1Lleve2Porcentaje() {
		return pague1Lleve2Porcentaje;
	}

	public void setPague1Lleve2Porcentaje(Long pague1Lleve2Porcentaje) {
		this.pague1Lleve2Porcentaje = pague1Lleve2Porcentaje;
	}

	public Long getPagueNLleveM() {
		return pagueNLleveM;
	}

	public void setPagueNLleveM(Long pagueNLleveM) {
		this.pagueNLleveM = pagueNLleveM;
	}

	public Long getPagueXLleveY() {
		return pagueXLleveY;
	}

	public void setPagueXLleveY(Long pagueXLleveY) {
		this.pagueXLleveY = pagueXLleveY;
	}

	public Long getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(Long descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String toString()
	{
		String tipo = "";
		if(pague1Lleve2Porcentaje!=null)
		{
			tipo = ", Pague 1 lleve el segundo por porcentaje menor"+", idTipoPromocion=" + pague1Lleve2Porcentaje;
		}
		else if(pagueNLleveM!=null)
		{
			tipo = ", Pague n productos de producto lleve m productos"+", idTipoPromocion=" + pagueNLleveM;
		}
		else if(pagueXLleveY!=null)
		{
			tipo = ", Pague x cantidad lleve y cantidad"+", idTipoPromocion=" + pagueXLleveY;
		}
		else if(descuentoPorcentaje!=null)
		{
			tipo = ", Lleve el producto por un porcentaje menor"+", idTipoPromocion=" + descuentoPorcentaje;
		}
		return "Promocion [id= "+id+ ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal +
				", precio=" + precio + ", idProducto=" + idProducto+ tipo+"]";
	}
	
	
	
}
