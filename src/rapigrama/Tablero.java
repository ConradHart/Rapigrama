package rapigrama;

import java.util.Arrays;

public class Tablero implements Cloneable {
	private Integer dimension; //Dimension del tablero
	private String[] tablero; //Tablero leido (matriz de string) cargado como Array de String reglon a reglon
	private Integer fila;
	
    /**
	 * Constructor de Tablero que crea objeto tipo array de String y lo inicializa.
	 * @param dimension
	 */
	public Tablero(Integer dimension) {
		this.setDimension(dimension);
		this.setFila(dimension);
		
		this.setTablero(new String[dimension]);
		for(int i=0; i<this.getDimension(); i++){
				tablero[i]="";
			}//Fin inicializacion del vector Tablero
	}

	/**
	 * Método para cargar Tablero desde archivo
	 * @param iFila
	 * @param sTableroPalabras
	 */
	public void cargarTableroArchivo(Integer iFila, String sTableroPalabras){
		tablero[iFila]=sTableroPalabras;
	}//fin cargar
    
	/**
	 * Método para imprimir el vector Tablero
	 */
	public void imprimirTablero(){
		System.out.println("\nTablero de palabras cargadas: ");
		for(int i=0; i<this.getFila();i++){
			System.out.println(tablero[i] + " ");
		}
	}//fin imprimir


	/**
	 * Método para buscas palabras del tablero(vector)
	 */
	public void buscaPalabras(Tablero tTablero, VectorPalabra vectorPalabrasABuscar){

		imprimirTableroPalabrasE(tTablero);
		imprimirPalabrasABuscar(vectorPalabrasABuscar);
		
		//Limpio variables
		String sLineaAExaminar;  //Linea leida del Tablero cargada en la matriz
		String sPalabraABuscar; //Palabra a buscar cargada en el vector
		Sentido sSentido = null;
		
		Integer iContadorDeLineasEnTablero;
		Integer iContadorDePalabrasABuscar;
		Integer iContadorDePalabrasEncontradas; //Contador de palabras encontradas, si encuentra todas las palabras a buscar no sigue buscando
		Integer iContadorDePalabrasBuscadas;
		Integer iContadorDeCiclos;
		Boolean bFinCiclo;
		
		sLineaAExaminar = "";
		sPalabraABuscar = "";
		iContadorDeLineasEnTablero = 0;
		iContadorDePalabrasABuscar = 0;
		iContadorDePalabrasEncontradas = 0;
		iContadorDePalabrasBuscadas = 0;
		iContadorDeCiclos = 0;
		
		
		iContadorDeLineasEnTablero = tTablero.getDimension();
		iContadorDePalabrasABuscar = vectorPalabrasABuscar.getVectorPalabra().length; //Cantida de palabras a buscar en el tablero, ej. 4
		
		
		bFinCiclo = false;
		
		sSentido = Sentido.ESTE;
		
		while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
			
			System.out.println("\nBuscando en el tablero sentido Este(izq-->der)... ");
			
			for(int i=0; i<iContadorDePalabrasABuscar;i++){
				sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[i].toLowerCase();
				System.out.println(i+1 + " sPalabraABuscar: " + sPalabraABuscar);
				System.out.println("--------------");
				System.out.println("");
				
				for(int j=0; j<iContadorDeLineasEnTablero;j++){
					sLineaAExaminar = tTablero.getTablero()[j].toLowerCase();
					System.out.println(j+1 + " sLineaAExaminar: " + sLineaAExaminar);
					iContadorDeCiclos++;
					
					if(sLineaAExaminar.contains(sPalabraABuscar)){
						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
						System.out.println("");
						iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
						iContadorDePalabrasBuscadas++;
						break;
					}else{
						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
						System.out.println("");
						iContadorDePalabrasBuscadas++;
					}
					
					if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
						bFinCiclo = true;
					}else if(iContadorDeCiclos == (iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)){ //Recorrio todo el tablero para todas las palabras y no encontro nada
						bFinCiclo = true;}
				}
			}
		}
	
		System.out.println("------------------------------------------------------------------");
		
		//Limpio variables
		sLineaAExaminar = "";
		sPalabraABuscar = "";
		iContadorDePalabrasEncontradas = 0;
		iContadorDePalabrasBuscadas = 0;
		iContadorDeCiclos = 0;
		
		String sLineaInvertidaOeste = "";
			
		imprimirTableroPalabrasO(tTablero);
		imprimirPalabrasABuscar(vectorPalabrasABuscar);
		
		bFinCiclo = false;
		
		sSentido = Sentido.OESTE;
		
		while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
			
			System.out.println("\nBuscando en el tablero sentido Oeste(der-->izq)... ");
			
			for(int i=0; i<iContadorDePalabrasABuscar;i++){
				sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[i].toLowerCase();
				System.out.println(i+1 + " sPalabraABuscar: " + sPalabraABuscar);
				System.out.println("--------------");
				System.out.println("");
				
				for(int j=0; j<iContadorDeLineasEnTablero;j++){ //Lineas en el tablero leido
					sLineaInvertidaOeste = reverse(tTablero.getTablero()[j]);
					sLineaAExaminar = sLineaInvertidaOeste;
					System.out.println(j+1 + " sLineaAExaminar: " + sLineaAExaminar);
					iContadorDeCiclos++;
					
					if(sLineaAExaminar.contains(sPalabraABuscar)){
						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
						System.out.println("");
						iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
						iContadorDePalabrasBuscadas++;
						break;
					}else{
						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
						System.out.println("");	
						iContadorDePalabrasBuscadas++;
					}

					if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
						bFinCiclo = true;
					}else if(iContadorDeCiclos == (iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)){ //Recorrio todo el tablero para todas las palabras y no encontro nada
						bFinCiclo = true;}
				}
			}
		}
	
		System.out.println("------------------------------------------------------------------");	
		
		//Limpio variables
		sLineaAExaminar = "";
		sPalabraABuscar = "";
		iContadorDePalabrasEncontradas = 0;
		iContadorDePalabrasBuscadas = 0;
		iContadorDeCiclos = 0;
		 
		//sLineaInvertidaOeste = "";
			
		imprimirTableroPalabrasS(tTablero);
		imprimirPalabrasABuscar(vectorPalabrasABuscar);
		
		bFinCiclo = false;
		
		sSentido = Sentido.SUR;
		
//		while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
//			
//			System.out.println("\nBuscando en el tablero sentido Oeste(der-->izq)... ");
//			
//			for(int i=0; i<iContadorDePalabrasABuscar;i++){
//				sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[i].toLowerCase();
//				System.out.println(i+1 + " sPalabraABuscar: " + sPalabraABuscar);
//				System.out.println("--------------");
//				System.out.println("");
//				
//				for(int j=0; j<iContadorDeLineasEnTablero;j++){ //Lineas en el tablero leido
//					sLineaInvertidaOeste = reverse(tTablero.getTablero()[j]);
//					sLineaAExaminar = sLineaInvertidaOeste;
//					System.out.println(j+1 + " sLineaAExaminar: " + sLineaAExaminar);
//					iContadorDeCiclos++;
//					
//					if(sLineaAExaminar.contains(sPalabraABuscar)){
//						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
//						System.out.println("");
//						iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
//						iContadorDePalabrasBuscadas++;
//						break;
//					}else{
//						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
//						System.out.println("");	
//						iContadorDePalabrasBuscadas++;
//					}
//
//					if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
//						bFinCiclo = true;
//					}else if(iContadorDeCiclos == (iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)){ //Recorrio todo el tablero para todas las palabras y no encontro nada
//						bFinCiclo = true;}
//				}
//			}
//		}	
		
		//Inicializacion del vector Tablero para luego copiar el tablero invertido sentido Sur
//		for(int i=0; i<this.getDimension(); i++){
//			tablero[i]="";
//		}

//		original
//      0             7
//	0	x o i a c e r o
//	1	cerovaia
//	2	thceraio
		
//		Sur
//		xct
//		oex
//		irc
//		aoe
//		cvr
//		eaa
//		rii
//		oao
		
		//Lo muestro en pantalla
//	for(int i=0; i<iFila; i++){
//	for(int j=0; j<iColumna; j++){
//	System.out.print(cTablero[i][j] + " ");
//}
//System.out.println();
	

//		for(int i=0; i<tTablero.getTablero().length;i++){
//			System.out.println(tTablero.getTablero()[i] + " ");
//		}
		
		
//		//Reescribo el array de string con el tablero orientado al sur
//		this.setDimension(this.getDimension());
//		this.setFila(this.getDimension());
//		
//		//Inicializo el array de string
//		this.setTablero(new String[this.getDimension()]);
//		for(int i=0; i<this.getDimension(); i++){
//				tablero[i]="";
//			}//Fin inicializacion del vector Tablero
//		
//		
////		public void cargarTableroArchivo(Integer iFila, String sTableroPalabras){
////			tablero[iFila]=sTableroPalabras;
////		}//fin cargar

//		for(int i=0; i<this.getTableroArchivo().getTablero().length;i++){
//			for(int j=0; j<this.getTableroArchivo().getTablero().length; j++){
//				System.out.print(this.getTableroArchivo().getTablero()[j][i] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println("------------------------------------------------------------------");	

		
		System.out.println("------------------------------------------------------------------");	
		
	}
	
	/**
	 * Función recursiva para dar vuelta una cadena
	 * @param str
	 * @return str reverse
	 */
	public String reverse(String str) {
		if ((null == str) || (str.length() <= 1)) {
			return str;
		}
	return reverse(str.substring(1)) + str.charAt(0);
	}
	
	/**
	 * Imprime el vector de las palabras a buscar en el tablero
	 * 
	 */
	public void imprimirPalabrasABuscar(VectorPalabra vectorPalabrasABuscar){
		
		System.out.println("\nPalabras a buscar: ");
		for(int i=0; i<vectorPalabrasABuscar.getVectorPalabra().length; i++)
			System.out.println(vectorPalabrasABuscar.getVectorPalabra()[i] + " ");

		System.out.println();

		System.out.println("------------------------------------------------------------------");
		
	}
	
	/**
	 * Imprime el tablero, matriz, en sentido Este
	 * @param Tablero
	 */
	public void imprimirTableroPalabrasE(Tablero tTablero){
		
		System.out.println("\nTablero Este(izq-->der): ");
		for(int i=0; i<tTablero.getTablero().length;i++){
			System.out.println(tTablero.getTablero()[i] + " ");
		}
	}
	
	/**
	 * Imprime el tablero, matriz, en sentido Oeste
	 * @param Tablero
	 */
	public void imprimirTableroPalabrasO(Tablero tTablero){
		
		String sLineaInvertidaOeste = "";
		System.out.println("\nTablero Oeste(der-->izq): ");
		for(int i=0; i<tTablero.getTablero().length;i++){
			sLineaInvertidaOeste = reverse(tTablero.getTablero()[i]);
			System.out.println(sLineaInvertidaOeste + " ");
		}
	}
	
	/**
	 * Imprime el tablero, matriz, en sentido Sur
	 * @param Tablero
	 */
	public void imprimirTableroPalabrasS(Tablero tTablero){
		
		final Character[][] cTablero; //Matriz de caracteres para invertir
		Integer iFila = 0;
		Integer iColumna = 0;
		iFila = tTablero.getTablero()[0].length();
		iColumna = this.getDimension();
		cTablero = new Character[iFila][iColumna];
		
		System.out.print("\nTablero Sur(arriba-->abajo): ");
		
		//Inicializo la matriz de char
		for (int i = 1; i < iFila; i++)
		    for (int j = 1; j < iColumna; j++)
		    	cTablero[i][j] = ' ';
	
		//Invierto en sentido Sur tTablero
		Integer i=0;
		Integer j=0;
		Integer tope=(iFila+1)*(iColumna+1) + 6; //24;
		System.out.println();
		
		for(int z = 0; z<=tope ;z++)
		if(i < iFila){ 
			if(j < iColumna){
//				cTablero[i][j] = tTablero.getTablero()[j].toLowerCase().charAt(i);
				System.out.print(tTablero.getTablero()[j].toLowerCase().charAt(i) + " ");
				j++;
			}else{
				j=0;
				i++;
				System.out.println();
			}
		}else{
			i=0;
		}
	
	}
	
	/**
	 * Imprime el tablero, matriz, en sentido Norte
	 * @param Tablero
	 */
	public void imprimirTableroPalabrasN(Tablero tTablero){
		
//		System.out.println("\nMatrizN(abajo-->arriba): ");
//		for(int i=0; i<this.getTableroArchivo().getTablero().length; i++){
//			for(int j=this.getTableroArchivo().getTablero().length-1; j>=0; j--){
//				System.out.print(this.getTableroArchivo().getTablero()[j][i] + " ");
//			}
//			System.out.println();
//		}
		
	}
	

	/**
	 * Getters y Setters
	 * @return
	 */
	
	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}

	public String[]getTablero() {
		return tablero;
	}

	public void setTablero(String[] strings) {
		this.tablero = strings;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	
	@Override
	public String toString() {
		return  " " + Arrays.toString(this.getTablero()) + " ";
	}
}
