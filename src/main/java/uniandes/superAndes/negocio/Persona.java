package uniandes.superAndes.negocio;

public class Persona extends Cliente implements VOPersona {

	private String tipoDocumento;
	private int numeroDocumento;
	public Persona(String tipoDocumento, int numeroDocumento) {
	
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public int getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	
}
