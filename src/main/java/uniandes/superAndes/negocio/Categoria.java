package uniandes.superAndes.negocio;

/**
 *Clase que representa la categoria de un producto
 */
public class Categoria implements VOCategoria
{

	/**
	 * Identificador unico de la categoria
	 */
	private long id;
	
	/**
	 * nombre de la categoria
	 */
	private String nombre;
	
	/**
	 * Crea una nueva categoria, constructor vacio
	 */
	public Categoria(){
		id = 0;
		nombre ="";
	}
	
	/**
	 * Crea una categoria con la informacion recibida por parametro
	 * @param id id de la categoria
	 * @param nombre nombre de la categoria
	 */
	public Categoria(long id, String nombre) {
		
		this.id = id;
		this.nombre = nombre;
	}
	
	/**
	 * Retorna el id de la categoria
	 * @return id de la categoria
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Asigna un nuevo id a la categoria
	 * @param id id a asignar
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Retorna el nombre de la categoria
	 * @return nombre de la categoria
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Asigna un nuevo nombre a la categoria
	 * @param nombre el nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
