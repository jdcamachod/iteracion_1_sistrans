package uniandes.superAndes.negocio;

import java.util.Date;

public interface VOPromocion {

	public long getId();
	public Date getFechaInicial();
	public Date getFechaFinal();
	public int getPrecio();
	public long getIdPague1Lleve2Porcentaje();
	public long getIdPagueNLleveM();
	public long getIdPagueXLleveY();
	public long getIdDescuentoPorcentaje();
}
