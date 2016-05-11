package rapigrama;

/**
 * Universidad Nacional del Oeste
 * Programación con Objetos II, comisión (7034) Turno Noche
 * 1er cuatrimestre 2016
 *                            "1° Parcial - Juego con letras"
 *                            
 * @author Gastón M. López y Eugenio Puente Perazzo
 *         GRUPO: 6.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Archivo{
	/**
	 */
	private static String Resource0 = "src//resources//in//00_test_inicial.in";
	private static String Resource1 = "src//resources//in//01_unaPalabraNoEsEncontradaEnNingunaOrientación.in";
	private static String Resource2 = "resources//in//02_unaPalabraRepetidaConDosOrientaciones.in";
	private static String Resource3 = "resources//in//03_unaPalabraRepetidaConMismaOrientacion.in";
	private static String Resource4 = "resources//in//04_palabraEnElExtremoDelTablero.in";
	private static String Resource5 = "resources//in//05_enTodasLasOrientaciones.in";
	private static String Resource6 = "resources//in//06_ningunaPalabraEsEncontrada.in";
	private static String Resource7 = "resources//in//07_mismaPalabraEnTodasLasOrientaciones.in";
	private static String Resource8 = "resources//in//08_fatiga.in";
	private static String ResourceSolucion = "resources//out//rapigrama.out";


	private Tablero tableroArchivo; //Busqueda
	private VectorPalabra vectorPalabrasABuscar; //Lee
	private VectorPalabra vectorPalabrasEncontradas; //Graba

	/**
	 * Métodos de lectura de archivo de texto para cargar valores de matriz y de vector.
	 * Más abajo, método para escribir los datos solicitados en archivo de salida: 
	 */

	public void leer(){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File(Resource0);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = "";
			Boolean PrimeraLinea = true;
			long l1 = -1;
			long l2 = 0;
			long l3 = 0;
			Long lNumeroLineas = new Long(l1); //Contador de lineas leidas del archivo
			Long lNumeroLineaFinMatriz = new Long(l2); //Tope de matriz a cargar
			Long lNumeroLineaFinVector = new Long(l3); //Tope de vector a cargar
			Integer posVector = 0; //Posicion del Vector

			System.out.println("Leyendo tablero del archivo...");
			int iFila = 0;
			String[] sTableroPalabras = null; //El tablero leido del archivo lo copio a este Array de Strings
			String sTableroPalabrasAux = "";
			Integer indice = 0;

			while ((linea = br.readLine()) != null){ //Primera lectura obtiene la dimension de la matriz y vector
				lNumeroLineas++; //Contador de Lineas liedas del tablero
				if(PrimeraLinea == true){	
					String[] lineaSpliteada = linea.split(" "); //Primera Linea contiene cantidad de palabras del tablero y cantidad de palabras a buscar en el tablero
					lNumeroLineaFinMatriz = (long) (Integer.parseInt(lineaSpliteada[0]));//Numero de linea de fin elementos de la matriz
					lNumeroLineaFinVector = lNumeroLineaFinMatriz + Integer.parseInt(lineaSpliteada[1]);
					this.setTableroArchivo(new Tablero(Integer.parseInt(lineaSpliteada[0]))); //Dimensiono la matriz
					this.setVectorPalabrasABuscar(new VectorPalabra(Integer.parseInt(lineaSpliteada[1]))); //Dimensiono el vector		
					PrimeraLinea = false;
				}else{
					if(lNumeroLineas <= lNumeroLineaFinMatriz){ //Elementos de la matriz nxn	
						sTableroPalabras = linea.split(" ");
						//Cargo el tablero leido en la matriz String[] sTableroPalabras
						for(iFila=0; iFila<sTableroPalabras.length;iFila++){
							this.getTableroArchivo().cargarTableroArchivo(indice, sTableroPalabras[iFila]);
						}
						indice++;							   
					}else{ //Elementos del vector
						if(lNumeroLineas <= lNumeroLineaFinVector){ //Elementos del vector
							sTableroPalabrasAux = linea;
							//Cargo el vector leido
							this.getVectorPalabrasABuscar().cargarVectorArchivo(posVector, sTableroPalabrasAux);
						}
						posVector++;
					}
				}
			}

			this.getTableroArchivo().imprimirTablero();
			this.getVectorPalabrasABuscar().imprimirVector();		
			System.out.println("\nFin de lectura...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}//fin leer

	public void escribir() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(ResourceSolucion);
			pw = new PrintWriter(fichero);

			System.out.println("\nEl archivo solucion fue grabado con exito.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}//fin escribir

	public void inicializarVectorCharacter(Character[] ch){
		for(int i=0; i<ch.length;i++)//Inicializa el vector de Character
			ch[i]=' ';
	}
	
	public void mostrarVectorCharacter(Character[] ch){
		for(int i=0; i<ch.length;i++)//Inicializa el vector de Character
			  System.out.print("[" + ch[i] + "]");
	}
	
	
	/**
	 * Lllamada al metodo para buscar las palabras del Vector "vectorArchivo" dentro la matriz "tableroArchivo"
	 * Luego las palabras encontradas las graba en "el Vector palabrasEncontradas"
	 * @return
	 */
	//
	public void buscarPalabraEnTablero(){
			
		tableroArchivo.buscaPalabras(tableroArchivo, vectorPalabrasABuscar);
	}

	/**
	 * Getters y Setters
	 * @return
	 */

	public Tablero getTableroArchivo() {
		return tableroArchivo;
	}

	public void setTableroArchivo(Tablero tablero) {
		this.tableroArchivo = tablero;
	}

	public VectorPalabra getVectorPalabrasABuscar() {
		return vectorPalabrasABuscar;
	}

	public void setVectorPalabrasABuscar(VectorPalabra vectorPalabrasABuscar) {
		this.vectorPalabrasABuscar = vectorPalabrasABuscar;
	}

	public VectorPalabra getVectorPalabrasEncontradas() {
		return vectorPalabrasEncontradas;
	}

	public void setVectorPalabrasEncontradas(VectorPalabra vectorPalabrasEncontradas) {
		this.vectorPalabrasEncontradas = vectorPalabrasEncontradas;
	}



}
