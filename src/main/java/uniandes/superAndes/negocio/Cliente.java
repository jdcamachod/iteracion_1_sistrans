package uniandes.superAndes.negocio;

/**
 * Representa un cliente
 *
 */
public abstract class Cliente implements VOCliente
{

	/**
	 * El correo electronico del cliente
	 */
	private String correoElectronico;
	
	/**
	 * Identificador unico del cliente
	 */
	private long id;
	
	/**
	 * nombre del cliente
	 */
	private String nombre;
	
	/**
	 * Los puntos de fidelidad del cliente
	 */
	private double puntos;
	
	/**
	 * Crea un nuevo cliente, constructor vacio
	 */
	public Cliente()
	{
		correoElectronico = "";
		id =0;
		nombre = "";
		puntos = 0;
	}
	
	/**
	 * Crea un nuevo cliente con la informacion recibida por parametro
	 * @param correoElectronico - correo electronico del cliente
	 * @param id - identificador del cliente
	 * @param nombre - nombre del cliente
	 * @param puntos - puntos del cliente
	 */
	public Cliente(String correoElectronico, long id, String nombre, double puntos) {
	
		this.correoElectronico = correoElectronico;
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
	}
	/**
	 * Retorna el correo electronico del cliente
	 * @return correo electronico del cliente
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	/**
	 * Asigna un nuevo correo electronico al cliente
	 * @param correoElectronico correo electronico a asignar
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * Retorna el identificador del cliente
	 * @return el identificador del cliente
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Asigna un nuevo id al cliente
	 * @param id - id a asignar
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Retorna el nombre del cliente
	 * @return nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Asigna un nuevo nombre al cliente
	 * @param nombre nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Retorna los puntos de fidelidad del cliente
	 * @return puntos de fidelidad del cliente
	 */
	public double getPuntos() {
		return puntos;
	}
	
	/**
	 * Asigna un nuevo valor de puntos al cliente
	 * @param puntos puntos a asignar
	 */
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}
	
	
	
	
}
