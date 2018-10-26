package uniandes.superAndes.negocio;

import java.util.Date;

public class PagueXLleveY extends Promocion implements VOPagueXLleveY{

	private int x;
	private int y;
	
	public PagueXLleveY(long id, Date fechaInicial, Date fechaFinal, int precio, long idPagueXLleveY, int x, int y) {
		super(id, fechaInicial, fechaFinal, precio, 0, 0, 0,
				idPagueXLleveY);
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
