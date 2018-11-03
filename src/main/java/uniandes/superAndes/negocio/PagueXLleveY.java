package uniandes.superAndes.negocio;

import java.util.Date;

public class PagueXLleveY extends Promocion implements VOPagueXLleveY{

	private int x;
	private int y;
	private long idPagueXLleveY;
	
	public PagueXLleveY(long id, Date fechaInicial, Date fechaFinal, double precio, long idPagueXLleveY, int x, int y, Long idProducto) {
		super(id, fechaInicial, fechaFinal, precio, null, null, null,
				idPagueXLleveY, idProducto);
		this.x = x;
		this.y = y;
		this.idPagueXLleveY=idPagueXLleveY;
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
	public long getIdPagueXLleveY() {
		return idPagueXLleveY;
	}
	public void setIdPagueXLleveY(long idPagueXLleveY) {
		this.idPagueXLleveY = idPagueXLleveY;
	}
	
	
}
