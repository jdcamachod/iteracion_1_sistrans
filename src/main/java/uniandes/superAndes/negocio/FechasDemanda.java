package uniandes.superAndes.negocio;

import java.sql.Date;

public class FechasDemanda {
	
	public Date fecha;
	
	private long sucursal;
	
	private int sumacantidades;
	
	public FechasDemanda() {
		super();
		this.fecha = new Date(9999999);
		this.sucursal = 0;
		this.sumacantidades = 0;
	}

	public FechasDemanda(Date fecha, long sucursal, int sumacantidades) {
		super();
		this.fecha = fecha;
		this.sucursal = sucursal;
		this.sumacantidades = sumacantidades;
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

	public int getSumacantidades() {
		return sumacantidades;
	}

	public void setSumacantidades(int sumacantidades) {
		this.sumacantidades = sumacantidades;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sucursal " + sucursal + "  con fecha " + fecha + " cantidad " + sumacantidades + "\n";
	}
	
	

}
