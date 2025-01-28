package nueva_tarea_PSP;

public class Tarea {

	static boolean tareasAcabadas;
	
	private int id_tarea;
	private String nombre_tarea;
	private int dificultad_tarea;
	private boolean tareaAcabada;
	
	public Tarea(int id_tarea, String nombre_tarea, int dificultad_tarea, boolean tareaAcabada) {
		this.id_tarea = id_tarea;
		this.nombre_tarea = nombre_tarea;
		this.dificultad_tarea = dificultad_tarea;
		this.tareaAcabada = tareaAcabada;
	}

	public Tarea() {
	}

	public int getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}

	public String getNombre_tarea() {
		return nombre_tarea;
	}

	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}

	public int getDificultad_tarea() {
		return dificultad_tarea;
	}

	public void setDificultad_tarea(int dificultad_tarea) {
		this.dificultad_tarea = dificultad_tarea;
	}

	public boolean isTareaAcabada() {
		return tareaAcabada;
	}

	public void setTareaAcabada(boolean tareaAcabada) {
		this.tareaAcabada = tareaAcabada;
	}

	@Override
	public String toString() {
		String mensajeAcabada = "";
		if (tareaAcabada)
			mensajeAcabada="Completada";
		else
			mensajeAcabada="No completada";
		
		return "Tarea [id_tarea=" + id_tarea + ", nombre_tarea=" + nombre_tarea + ", dificultad_tarea="
				+ dificultad_tarea + ", Estado: " + mensajeAcabada + "]";
	}

}
