package uniandes.superAndes.negocio;

import java.sql.Date;

public class FechasIngresos {
	
	public Date fecha;
	
	long sucursal;
	
	public java.math.BigDecimal SUMAPRECIO;
	
	public FechasIngresos() {
		super();
		this.fecha = new Date(9999999);
		this.sucursal = 0;
		this.SUMAPRECIO = null;
	}

	public FechasIngresos(Date fecha, long sucursal, java.math.BigDecimal sumaPrecio) {
		super();
		this.fecha = fecha;
		this.sucursal = sucursal;
		this.SUMAPRECIO = sumaPrecio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getSucursal() {
		return sucursal;
	}

	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}

	
	
	public java.math.BigDecimal getSumaPrecio() {
		return SUMAPRECIO;
	}

	public void setSumaPrecio(java.math.BigDecimal SUMAPRECIO) {
		this.SUMAPRECIO = SUMAPRECIO;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sucursal " + sucursal + "  con fecha " + fecha + " ingresos " + SUMAPRECIO + "\n";
	}
	
	

}
