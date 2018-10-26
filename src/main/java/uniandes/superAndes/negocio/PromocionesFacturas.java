package uniandes.superAndes.negocio;

public class PromocionesFacturas implements VOPromocionesFacturas {

	private long idPromocion;
	private long idFactura;
	public PromocionesFacturas(long idPromocion, long idFactura) {
		super();
		this.idPromocion = idPromocion;
		this.idFactura = idFactura;
	}
	public long getIdPromocion() {
		return idPromocion;
	}
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}
	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	
	
}
