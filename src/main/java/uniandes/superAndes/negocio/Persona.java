package uniandes.superAndes.negocio;

public class Persona extends Cliente implements VOPersona {

	/**
	 * Identificador unico del Persona
	 */
	private long id;
	
	private String tipoDocumento;
	
	private int numeroDocumento;
	
	public Persona () {
		super ("DEFAULT", 0, "DEFAULT", 0, null,null);
		id= 0;
		tipoDocumento = "DEFAULT";
		numeroDocumento = 0;
	}
	
	public Persona(String correoElectronico, long idCliente, String nombre, double puntos,Long idPersona, String tipoDocumento, int numeroDocumento) {
		super(correoElectronico, idCliente, nombre, puntos, idPersona, null);
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.id = idPersona;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
