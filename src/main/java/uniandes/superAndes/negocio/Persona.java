package uniandes.superAndes.negocio;

public class Persona extends Cliente implements VOPersona {

	/**
	 * Identificador unico del Persona
	 */
	private long id;
	
	private String tipoDocumento;
	
	private int documentoIdentificacion;
	
	public Persona () {
		id= 0;
		tipoDocumento = "DEFAULT";
		documentoIdentificacion = 0;
	}
	
	public Persona(String correoElectronico, long idCliente, String nombre, double puntos,Long idPersona, String tipoDocumento, int numeroDocumento) {
		super(correoElectronico, idCliente, nombre, puntos, idPersona, null);
		this.tipoDocumento = tipoDocumento;
		this.documentoIdentificacion = numeroDocumento;
		this.id = idPersona;
	}
	public Persona (long id , String tipoDocumento, int documetoIdentificacion) {
		this.id = id;
		this.documentoIdentificacion = documetoIdentificacion;
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public int getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}
	public void setDocumentoIdentificacion(int numeroDocumento) {
		this.documentoIdentificacion = numeroDocumento;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return "Persona natural [Persona natural id:" + id +", Tipo de documento= "+ tipoDocumento +", Numero Documento = "+ documentoIdentificacion +" ]";
	}
	
	
	
}
