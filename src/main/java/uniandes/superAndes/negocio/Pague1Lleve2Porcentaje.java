package uniandes.superAndes.negocio;

import java.util.Date;

public class Pague1Lleve2Porcentaje extends Promocion implements VOPague1Lleve2Porcentaje {

	private double porcentaje2;
	private long idPague1Lleve2Porcentaje;
	
	



	public Pague1Lleve2Porcentaje(long id, Date fechaInicial, Date fechaFinal, int precio, 
			long idPague1Lleve2Porcentaje, double porcentaje2
			) {
		super(id, fechaInicial, fechaFinal, precio, null, idPague1Lleve2Porcentaje, null,
				null);
		this.porcentaje2 = porcentaje2;
		this.idPague1Lleve2Porcentaje = idPague1Lleve2Porcentaje;
		
	}

	public double getPorcentaje2() {
		return porcentaje2;
	}

	public void setPorcentaje2(int porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}

	public long getIdPague1Lleve2Porcentaje() {
		return idPague1Lleve2Porcentaje;
	}

	public void setIdPague1Lleve2Porcentaje(long idPague1Lleve2Porcentaje) {
		this.idPague1Lleve2Porcentaje = idPague1Lleve2Porcentaje;
	}


	
}
