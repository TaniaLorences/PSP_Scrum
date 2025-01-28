package nueva_tarea_PSP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class Main {
	
	static ArrayList<Tarea> listaTodasTareas;
	static ArrayList<Tarea> listaTareasPendientes;
	static ArrayList<Tarea> listaTareasParaScrum;
	static int contadorScrums;

	public static void main(String[] args) throws IOException {
		
		listaTodasTareas = new ArrayList<>();
		listaTareasPendientes = new ArrayList<>();
		listaTareasParaScrum = new ArrayList<>();
		
		
		BufferedReader br = new BufferedReader(new FileReader (new File("C:\\Users\\tania\\Desktop\\PSP\\Ejercicios\\Tema 2\\TareasSCRUM.txt")));
		
		String linea = br.readLine();
		int id_tarea=1;
		
		while(linea!=null) {
			String[] lineaDividida = linea.split(",");
			String nombre_tarea = lineaDividida[0];
			int dificultad_tarea = Integer.valueOf(lineaDividida[1]);		
						
			Tarea t = new Tarea(id_tarea, nombre_tarea, dificultad_tarea, false);
			listaTodasTareas.add(t);
			
			id_tarea++;
			linea = br.readLine();
		}
		
		br.close();
		
		for (Tarea t: listaTodasTareas) {
			if (!t.isTareaAcabada())
				listaTareasPendientes.add(t);
		}

			
		menu(listaTodasTareas, listaTareasPendientes, listaTareasParaScrum);

	}
	
	private static void menu(ArrayList<Tarea> todasTareas, ArrayList<Tarea> tareasPendientes, ArrayList<Tarea> listaTareasParaScrum) {
		
		int opcion=-1;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("1. Mostrar estado tareas. ");
			System.out.println("2. Mostrar tareas pendientes. ");
			System.out.println("3. Iniciar sprint. ");
			System.out.println("4. Rendirse. ");
			System.out.println("Introduce tu opción: ");
			opcion=sc.nextInt();
			sc.nextLine();
			
			switch(opcion) {
			case 1: 
				for (Tarea t: todasTareas) {
					System.out.println(t.toString());
				}
				break;
			case 2:
				for (Tarea t: tareasPendientes) {
					System.out.println(t.toString());
				}
				break;
			case 3:
				System.out.println("Elige 5 tareas pendientes para este sprint: ");
								
				for (int i=1; i<=5; i++) {
					System.out.println("Id de tarea para SCRUM "+i+": ");
					
					int idParaScrum = sc.nextInt();
					sc.nextLine();
					
					// Busco si la tarea está en pendientes y la añado al ArrayList de listaTareasParaScrum
					while(buscarTareaEnPendientesPorId(idParaScrum)==null || idParaScrum<1 || idParaScrum>30){
						System.out.println("Introduce un id de tarea válido. ");
						System.out.println("Id de tarea para SCRUM "+i+": ");
						idParaScrum = sc.nextInt();
						sc.nextLine();
					}
		
					listaTareasParaScrum.add(buscarTareaEnPendientesPorId(idParaScrum));	
				}
				
				try {
					envioTareasScrum(listaTareasParaScrum);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				// Actualizamos la lista de tareas pendientes // OJOOOOO: para eliminar se elimina con iterator no con for
				Iterator<Tarea> iterator = listaTareasPendientes.iterator();
				while (iterator.hasNext()) {
				    Tarea t = iterator.next();
				    if (t.isTareaAcabada()) {
				        iterator.remove();  
				    }
				}
				
				// Comprobamos si se acabaron todas
				if (listaTareasPendientes.size()==0) {
					System.out.println("¡¡ 🎉🥳🎉🕺🏾 Se han terminado todas las tareas 🎉🥳🎉🕺🏾 !!");
					System.out.println("Han hecho falta "+contadorScrums+" ciclos de Scrum. ");
					break;
				}
				break;
			case 4:
				System.out.println("¡Hasta la próxima!");
				break;
			default:
				System.out.println("Introduce un valor válido.");
				break;
			}
			
		}while(opcion!=4);
	}
	
	
	private static void envioTareasScrum(ArrayList<Tarea> listaTareasParaScrum) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("C:\\Users\\tania\\Desktop\\PSP\\C\\Gestion_Tareas_Scrum\\bin\\Debug\\Gestion_Tareas_Scrum.exe");
		for (Tarea t: listaTareasParaScrum) {
			Process proceso = pb.start();
			
			PrintStream ps = new PrintStream(proceso.getOutputStream());
			ps.println(t.getDificultad_tarea());
			ps.flush();
			
			BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

			String linea= br2.readLine();
			int completion=-3;
			
			while(linea!=null) {
				completion = Integer.valueOf(linea);
				linea= br2.readLine();
			}
			
			if (completion==1) t.setTareaAcabada(true);
		}
		contadorScrums++;
	}
	
	private static Tarea buscarTareaEnPendientesPorId(int id) {
		Tarea tareaEncontrada = null;	
		for (Tarea t : listaTareasPendientes) {
			if (t.getId_tarea()==id) 
				tareaEncontrada = t;
		}
		return tareaEncontrada;
	}
}
