package uniandes.superAndes.negocio;

import java.util.Date;

public class PagueNLleveM extends Promocion {

	private int m;
	private int n;
	
	public PagueNLleveM(long id, Date fechaInicial, Date fechaFinal, int precio, long idPagueNLleveM,  int m, int n) {
		super(id, fechaInicial, fechaFinal, precio, 0, 0, idPagueNLleveM,
				0);
		this.m = m;
		this.n = n;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
	
}
