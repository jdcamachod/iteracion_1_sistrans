package uniandes.superAndes.negocio;

import java.util.Date;

public class PagueNLleveM extends Promocion implements VOPagueNLleveM {

	private int m;
	private int n;
	private long idPagueNLleveM;
	
	public PagueNLleveM(long id, Date fechaInicial, Date fechaFinal, double precio, long idPagueNLleveM,  int m, int n, Long idProducto) {
		super(id, fechaInicial, fechaFinal, precio, null, null, idPagueNLleveM,
				null, idProducto);
		this.m = m;
		this.n = n;
		this.setIdPagueNLleveM(idPagueNLleveM);
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
	public long getIdPagueNLleveM() {
		return idPagueNLleveM;
	}
	public void setIdPagueNLleveM(long idPagueNLleveM) {
		this.idPagueNLleveM = idPagueNLleveM;
	}
	
	
}
