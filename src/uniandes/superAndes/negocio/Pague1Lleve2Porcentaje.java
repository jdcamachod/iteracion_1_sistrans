package uniandes.superAndes.negocio;

import java.util.Date;

public class Pague1Lleve2Porcentaje extends Promocion {

	private double porcentaje2;
	
	



	public Pague1Lleve2Porcentaje(long id, Date fechaInicial, Date fechaFinal, int precio, 
			long idPague1Lleve2Porcentaje, double porcentaje2
			) {
		super(id, fechaInicial, fechaFinal, precio, 0, idPague1Lleve2Porcentaje, 0,
				0);
		this.porcentaje2 = porcentaje2;
		
	}

	public double getPorcentaje2() {
		return porcentaje2;
	}

	public void setPorcentaje2(int porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}


	
}
