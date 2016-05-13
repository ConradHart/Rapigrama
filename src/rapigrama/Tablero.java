package rapigrama;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
		
		//Array de Palabras halladas en los tableros en todos sus sentidos para luego grabarlas
		//Uso ArrayList ya que con vectorPalabrasEncontradas.add("palabra"); voy agregandolas sin necesidad de declarar un tamaño
//		rapigrama.out Una línea por cada palabra encontrada indicando el número de palabra (coincidente con el orden de aparición
//		en la entrada), y la dirección en la que se lee.
//		1 E
//		3 S
//		4 N
		ArrayList<String> arrayListPalabrasEncontradas = new ArrayList<String>();
		ArrayList<String> arrayListPalabrasABuscar = new ArrayList<String>();
		Archivo archivoGrabarPalabras = new Archivo();
		
		Integer iContadorAux;
		iContadorAux = vectorPalabrasABuscar.getVectorPalabra().length;
		Boolean primerPalabra = false;
		
		for(int i=0; i<iContadorAux;i++){
			arrayListPalabrasABuscar.add(vectorPalabrasABuscar.getVectorPalabra()[i].toLowerCase());
		}
		
//		System.out.println("TEST");
//		// Declaramos el Iterador e imprimimos los Elementos del ArrayList
//		Iterator<String> palabrasIteratorAux = arrayListPalabrasABuscar.iterator();
//		while(palabrasIteratorAux.hasNext()){
//			String elemento = palabrasIteratorAux.next();
//			System.out.println(elemento+" ");
//		}
		
		
		// **********************************************
		// **************** SENTIDO ESTE ****************
		// **********************************************
		
//		imprimirTableroPalabrasE(tTablero);
//		imprimirPalabrasABuscar(vectorPalabrasABuscar);
		
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
		
		iContadorDeLineasEnTablero = tTablero.getDimension();//Tablero ESTE
		iContadorDePalabrasABuscar = vectorPalabrasABuscar.getVectorPalabra().length; //Cantida de palabras a buscar en el tablero, ej. 4
		
		//Si ya encontre todas las palabras que debia buscar no sigo buscando
		if(!(iContadorDePalabrasABuscar == iContadorDePalabrasEncontradas)){
		
			bFinCiclo = false;
			
			sSentido = Sentido.ESTE;
			
			try{

				//Ahora puedo buscar	
				while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
					
					System.out.println("\nBuscando en el tablero sentido Este(izq-->der)... ");
					
					for(int i=0; i<iContadorDePalabrasABuscar;i++){
						sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[i].toLowerCase();
		//				System.out.println(i+1 + " sPalabraABuscar: " + sPalabraABuscar);
		//				System.out.println("--------------");
		//				System.out.println("");
						
						for(int j=0; j<iContadorDeLineasEnTablero;j++){
							sLineaAExaminar = tTablero.getTablero()[j].toLowerCase();
		//					System.out.println(j+1 + " sLineaAExaminar: " + sLineaAExaminar);
							iContadorDeCiclos++;
							//Busco si la palabra esta en la linea a examinar
							if(sLineaAExaminar.contains(sPalabraABuscar)){
		//						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
		//						System.out.println("");
								//Busco el numero correspondiente a la palabra (previamente grabado) encontrada para grabarlo en el array de palabras encontradas
								if(arrayListPalabrasABuscar.contains(sPalabraABuscar)){
									int num = (arrayListPalabrasABuscar.indexOf(sPalabraABuscar))+1 ;
									//Si el arrayListPalabrasEncontradas no esta vacio busco si fue grabada sino gabro directamente
									if((!arrayListPalabrasEncontradas.isEmpty())&&(primerPalabra)){
										//Busco si la palabra a grabar ya habia sido grabada previamente para no guardarla repetida, aunque el sentido sea otro
										Iterator<String> palabrasExistentes = arrayListPalabrasEncontradas.iterator();
										Boolean exitWhile = false;
										while(palabrasExistentes.hasNext()&&!exitWhile){
											String palabra = palabrasExistentes.next();
											if((palabra.substring(0,1).contains(String.valueOf(num)))){ //Si da igual es que existe y no la agrego		
												exitWhile = true;
											}else{
												arrayListPalabrasEncontradas.add(num + " " + sSentido);
												exitWhile = true;
											}	
										}
									}else{
										arrayListPalabrasEncontradas.add(num + " " + sSentido);
									}
								}
								iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
								if (iContadorDePalabrasEncontradas == 1){primerPalabra = true;}
								iContadorDePalabrasBuscadas++;
								break;
							}else{
		//						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
		//						System.out.println("");
								iContadorDePalabrasBuscadas++;
							}
							
							if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
								bFinCiclo = true;
							}else if(iContadorDeCiclos == ((iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)-4)){ //Recorrio todo el tablero para todas las palabras y no encontro nada, el -4 es porque arrancan de 0 algunos y necesita restarlos para que de la condicion
								bFinCiclo = true;}
						}
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en la busqueda con sentido " + sSentido);
			}
		
			System.out.println("------------------------------------------------------------------");
			
			// **********************************************
			// **************** SENTIDO OESTE ***************
			// **********************************************
			
	//		imprimirTableroPalabrasO(tTablero);
	//		imprimirPalabrasABuscar(vectorPalabrasABuscar);
			
			//Limpio variables
			sLineaAExaminar = "";
			sPalabraABuscar = "";
			iContadorDePalabrasEncontradas = 0;
			iContadorDePalabrasBuscadas = 0;
			iContadorDeCiclos = 0;
			
			String sLineaInvertidaOeste = "";
			
			iContadorDeLineasEnTablero = tTablero.getDimension();//Tablero OESTE
			
			bFinCiclo = false;
			
			sSentido = Sentido.OESTE;
			
			try{

				//Ahora puedo buscar	
				while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
					
					System.out.println("\nBuscando en el tablero sentido Oeste(der-->izq)... ");
					
					for(int i=0; i<iContadorDePalabrasABuscar;i++){
						sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[i].toLowerCase();
		//				System.out.println(i+1 + " sPalabraABuscar: " + sPalabraABuscar);
		//				System.out.println("--------------");
		//				System.out.println("");
						
						for(int j=0; j<iContadorDeLineasEnTablero;j++){ //Lineas en el tablero leido
							sLineaInvertidaOeste = reverse(tTablero.getTablero()[j]);
							sLineaAExaminar = sLineaInvertidaOeste;
		//					System.out.println(j+1 + " sLineaAExaminar: " + sLineaAExaminar);
							iContadorDeCiclos++;
							
							if(sLineaAExaminar.contains(sPalabraABuscar)){
		//						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
		//						System.out.println("");
								//Busco el numero correspondiente a la palabra (previamente grabado) encontrada para grabarlo en el array de palabras encontradas
								if(arrayListPalabrasABuscar.contains(sPalabraABuscar)){
									int num = (arrayListPalabrasABuscar.indexOf(sPalabraABuscar))+1 ;
									//Si el arrayListPalabrasEncontradas no esta vacio busco si fue grabada sino gabro directamente
									if((!arrayListPalabrasEncontradas.isEmpty())&&(primerPalabra)){
										//Busco si la palabra a grabar ya habia sido grabada previamente para no guardarla repetida, aunque el sentido sea otro
										Iterator<String> palabrasExistentes = arrayListPalabrasEncontradas.iterator();
										Boolean exitWhile = false;
										while(palabrasExistentes.hasNext()&&!exitWhile){
											String palabra = palabrasExistentes.next();
											if((palabra.substring(0,1).contains(String.valueOf(num)))){ //Si da igual es que existe y no la agrego		
												exitWhile = true;
											}else{
												arrayListPalabrasEncontradas.add(num + " " + sSentido);
												exitWhile = true;
											}	
										}
									}else{
										arrayListPalabrasEncontradas.add(num + " " + sSentido);
									}
								}
								iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
								iContadorDePalabrasBuscadas++;
								break;
							}else{
		//						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
		//						System.out.println("");	
								iContadorDePalabrasBuscadas++;
							}
		
							if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
								bFinCiclo = true;
							}else if(iContadorDeCiclos == ((iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)-4)){ //Recorrio todo el tablero para todas las palabras y no encontro nada
								bFinCiclo = true;}
						}
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en la busqueda con sentido " + sSentido);
			}
			System.out.println("------------------------------------------------------------------");	
			
			// **********************************************
			// **************** SENTIDO SUR *****************
			// **********************************************
			
	//		imprimirTableroPalabrasS(tTablero);
	//		imprimirPalabrasABuscar(vectorPalabrasABuscar);
			
			//Limpio variables
			sLineaAExaminar = "";
			sPalabraABuscar = "";
			iContadorDePalabrasEncontradas = 0;
			iContadorDePalabrasBuscadas = 0;
			iContadorDeCiclos = 0;
			
			//Variables AUXILIARES
			final Character[][] cTableroSur; //Matriz de caracteres para invertir AUXILIAR	
			final Tablero tTableroAUXSur; //Tablero Auxiliar Invertido SUR
			Integer iFilaS = 0;
			Integer iColumnaS = 0;
			iFilaS = tTablero.getTablero()[0].length(); //8
			iColumnaS = this.getDimension(); //3
			cTableroSur = new Character[iFilaS][iColumnaS];
			
			//Inicializo la matriz de char auxiliar
			for (int i = 1; i < iFilaS; i++)
			    for (int j = 1; j < iColumnaS; j++)
			    	cTableroSur[i][j] = ' ';
			
			//Invierto en sentido Sur tTablero
			Integer i=0;
			Integer j=0;
			Integer topeS=((iFilaS)*(iColumnaS))+iFilaS; //32
			
			//Instancio el nuevo Tablero tTableroAUX
			tTableroAUXSur = new Tablero(iFilaS); //Dimension de filas
			try{
				//Armo la matriz de character cTablero con sentido SUR
				for(int z = 0; z<=topeS ;z++){
					if(i < iFilaS){ 
						if(j < iColumnaS){
							cTableroSur[i][j] = tTablero.getTablero()[j].toLowerCase().charAt(i);
							j++;
						}else{
							j=0;
							i++;
						}
					}else{
						i=0;
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en el armado de la matriz con sentido " + sSentido);
			}
			
			//Ahora tranformo esa matriz de char, ya invertida en sentido SUR, a un array de String para la busqueda
			i=0;
			j=0;
			String sLineaAuxS="";
			Character cLineaAuxS=' ';
			try{
				for(int z = 0; z<=topeS ;z++){
					if(i < iFilaS){ 
						if(j < iColumnaS){
							cLineaAuxS = tTablero.getTablero()[j].toLowerCase().charAt(i);
							sLineaAuxS = sLineaAuxS + cLineaAuxS;
							j++;
						}else{
							tTableroAUXSur.cargarTableroArchivo(i, sLineaAuxS);
							sLineaAuxS = "";
							cLineaAuxS = ' ';
							j=0;
							i++;
						}
					}else{
						i=0;
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en el armado de la matriz con sentido " + sSentido);
			}
			
			//Ahora puedo buscar	
			iContadorDeLineasEnTablero = tTableroAUXSur.getDimension();//Tablero SUR cambian las lineas
			bFinCiclo = false;
			
			sSentido = Sentido.SUR;
			
			try{

				while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
					
					System.out.println("\nBuscando en el tablero sentido Sur(arriba-->abajo)... ");
					
					for(int x=0; x<iContadorDePalabrasABuscar;x++){
						sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[x].toLowerCase();
		//				System.out.println(x+1 + " sPalabraABuscar: " + sPalabraABuscar);
		//				System.out.println("--------------");
		//				System.out.println("");
						
						for(int y=0; y<iContadorDeLineasEnTablero;y++){
							sLineaAExaminar = tTableroAUXSur.getTablero()[y].toLowerCase();
		//					System.out.println(y+1 + " sLineaAExaminar: " + sLineaAExaminar);
							iContadorDeCiclos++;
							
							if(sLineaAExaminar.contains(sPalabraABuscar)){
		//						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
		//						System.out.println("");
								//Busco el numero correspondiente a la palabra (previamente grabado) encontrada para grabarlo en el array de palabras encontradas
								if(arrayListPalabrasABuscar.contains(sPalabraABuscar)){
									int num = (arrayListPalabrasABuscar.indexOf(sPalabraABuscar))+1 ;
									//Si el arrayListPalabrasEncontradas no esta vacio busco si fue grabada sino gabro directamente
									if((!arrayListPalabrasEncontradas.isEmpty())&&(primerPalabra)){
										//Busco si la palabra a grabar ya habia sido grabada previamente para no guardarla repetida, aunque el sentido sea otro
										Iterator<String> palabrasExistentes = arrayListPalabrasEncontradas.iterator();
										Boolean exitWhile = false;
										while(palabrasExistentes.hasNext()&&!exitWhile){
											String palabra = palabrasExistentes.next();
											if((palabra.substring(0,1).contains(String.valueOf(num)))){ //Si da igual es que existe y no la agrego		
												exitWhile = true;
											}else{
												arrayListPalabrasEncontradas.add(num + " " + sSentido);
												exitWhile = true;
											}	
										}
									}else{
										arrayListPalabrasEncontradas.add(num + " " + sSentido);
									}
								}
								iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
								iContadorDePalabrasBuscadas++;
								break;
							}else{
		//						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
		//						System.out.println("");
								iContadorDePalabrasBuscadas++;
							}
							
							if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
								bFinCiclo = true;
							}else if(iContadorDeCiclos == ((iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)-4)){ //Recorrio todo el tablero para todas las palabras y no encontro nada
								bFinCiclo = true;}
						}
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en la busqueda con sentido " + sSentido);
			}
			
			System.out.println("------------------------------------------------------------------");	
			
			// **********************************************
			// **************** SENTIDO NORTE ***************
			// **********************************************
			
	//		imprimirTableroPalabrasN(tTablero);
	//		imprimirPalabrasABuscar(vectorPalabrasABuscar);
			
			//Limpio variables
			sLineaAExaminar = "";
			sPalabraABuscar = "";
			iContadorDePalabrasEncontradas = 0;
			iContadorDePalabrasBuscadas = 0;
			iContadorDeCiclos = 0;
			 
			//Variables AUXILIARES
			final Character[][] cTableroNorte; //Matriz de caracteres para invertir AUXILIAR	
			final Tablero tTableroAUXNorte; //Tablero Auxiliar Invertido NORTE
			Integer iFilaN = 0;
			Integer iColumnaN = 0;
			iFilaN = tTablero.getTablero()[0].length(); //8
			iColumnaN = this.getDimension(); //3
			cTableroNorte = new Character[iFilaN][iColumnaN];
			
			//Inicializo la matriz de char auxiliar
			for (int f = 1; f < iFilaN; f++)
			    for (int g = 1; g < iColumnaN; g++)
			    	cTableroNorte[f][g] = ' ';
			
			//Invierto en sentido Norte tTablero
			Integer f=0;
			Integer g=0;
			Integer topeN=((iFilaN)*(iColumnaN))+iFilaN; //32
			
			//Instancio el nuevo Tablero tTableroAUX
			tTableroAUXNorte = new Tablero(iFilaN); //Dimension de filas
			
			try{
				//Armo la matriz de character cTablero con sentido NORTE, aunque en este momento no esta en sentido SUR, luego lo invierto con reverse
				//Se puede mejorar
				for(int z = 0; z<=topeN ;z++){
					if(f < iFilaN){ 
						if(g < iColumnaN){
							cTableroNorte[f][g] = tTablero.getTablero()[g].toLowerCase().charAt(f);
							g++;
						}else{
							g=0;
							f++;
						}
					}else{
						f=0;
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en el armado de la matriz con sentido " + sSentido);
			}
	
			//Ahora tranformo esa matriz de char, invertida en sentido SUR(Debo invertirla en sentido NORTE), a un array de String para la busqueda
			i=0;
			j=0;
			String sLineaAuxN="";
			Character cLineaAuxN=' ';
			try{
				for(int z = 0; z<=topeN ;z++){
					if(i < iFilaN){ 
						if(j < iColumnaN){
							cLineaAuxN = tTablero.getTablero()[j].toLowerCase().charAt(i);
							sLineaAuxN = sLineaAuxN + cLineaAuxN;
							j++;
						}else{
							tTableroAUXNorte.cargarTableroArchivo(i, reverse(sLineaAuxN)); //Aca se invierte para el sentido Norte, ya que en el for de la linea 337 no se hizo correctamente
							sLineaAuxN = "";
							cLineaAuxN = ' ';
							j=0;
							i++;
						}
					}else{
						i=0;
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en el armado de la matriz con sentido " + sSentido);
			}
			
			//Ahora puedo buscar	
			iContadorDeLineasEnTablero = tTableroAUXNorte.getDimension();//Tablero SUR cambian las lineas
			bFinCiclo = false;
			
			sSentido = Sentido.NORTE;
			
			try{

				while((iContadorDePalabrasEncontradas < iContadorDePalabrasABuscar)&&(!bFinCiclo)){
					
					System.out.println("\nBuscando en el tablero sentido Norte(abajo-->arriba)... ");
					
					for(int x=0; x<iContadorDePalabrasABuscar;x++){
						sPalabraABuscar = vectorPalabrasABuscar.getVectorPalabra()[x].toLowerCase();
		//				System.out.println(x+1 + " sPalabraABuscar: " + sPalabraABuscar);
		//				System.out.println("--------------");
		//				System.out.println("");
						
						for(int y=0; y<iContadorDeLineasEnTablero;y++){
							sLineaAExaminar = tTableroAUXNorte.getTablero()[y].toLowerCase();
		//					System.out.println(y+1 + " sLineaAExaminar: " + sLineaAExaminar);
							iContadorDeCiclos++;
							
							if(sLineaAExaminar.contains(sPalabraABuscar)){
		//						System.out.println("Encontro la palabra '" + sPalabraABuscar + "' sentido " + sSentido);
		//						System.out.println("");
								//Busco el numero correspondiente a la palabra (previamente grabado) encontrada para grabarlo en el array de palabras encontradas
								if(arrayListPalabrasABuscar.contains(sPalabraABuscar)){
									int num = (arrayListPalabrasABuscar.indexOf(sPalabraABuscar))+1 ;
									//Si el arrayListPalabrasEncontradas no esta vacio busco si fue grabada sino gabro directamente
									if((!arrayListPalabrasEncontradas.isEmpty())&&(primerPalabra)){
										//Busco si la palabra a grabar ya habia sido grabada previamente para no guardarla repetida, aunque el sentido sea otro
										Iterator<String> palabrasExistentes = arrayListPalabrasEncontradas.iterator();
										Boolean exitWhile = false;
										while(palabrasExistentes.hasNext()&&!exitWhile){
											String palabra = palabrasExistentes.next();
											if((palabra.substring(0,1).contains(String.valueOf(num)))){ //Si da igual es que existe y no la agrego		
												exitWhile = true;
											}else{
												arrayListPalabrasEncontradas.add(num + " " + sSentido);
												exitWhile = true;
											}	
										}
									}else{
										arrayListPalabrasEncontradas.add(num + " " + sSentido);
									}
								}
								iContadorDePalabrasEncontradas++; //Contador de palabras buscadas
								iContadorDePalabrasBuscadas++;
								break;
							}else{
		//						System.out.println("No encontro la palabra '" + sPalabraABuscar +"'");
		//						System.out.println("");
								iContadorDePalabrasBuscadas++;
							}
							
							if(iContadorDePalabrasBuscadas == iContadorDePalabrasEncontradas){ //Encontro todas las palabras a buscar
								bFinCiclo = true;
							}else if(iContadorDeCiclos == ((iContadorDePalabrasABuscar * iContadorDeLineasEnTablero)-4)){ //Recorrio todo el tablero para todas las palabras y no encontro nada
								bFinCiclo = true;}
						}
					}
				}
			}catch(Exception e){
				 System.err.println("Exception: " + e.getMessage());
				 System.err.println("Se produjo un error en la busqueda con sentido " + sSentido);
			}
			
			System.out.println("------------------------------------------------------------------");	
			
	//		System.out.println("TEST");
	//		// Declaramos el Iterador e imprimimos los Elementos del ArrayList
	//		Iterator<String> palabrasIterator = arrayListPalabrasEncontradas.iterator();
	//		while(palabrasIterator.hasNext()){
	//			String elemento = palabrasIterator.next();
	//			System.out.println(elemento+" ");
	//		}
		}
		
		//Grabo el archivo con las palabras encontradas
		if (!arrayListPalabrasEncontradas.isEmpty())
			archivoGrabarPalabras.grabar(arrayListPalabrasEncontradas);
		
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
		iFila = tTablero.getTablero()[0].length(); //8
		iColumna = this.getDimension(); //3
		cTablero = new Character[iFila][iColumna];
		
		System.out.print("\nTablero Sur(arriba-->abajo): ");
		
		//Inicializo la matriz de char auxiliar
		for (int i = 1; i < iFila; i++)
		    for (int j = 1; j < iColumna; j++)
		    	cTablero[i][j] = ' ';
	
		//Invierto en sentido Sur tTablero
		Integer i=0;
		Integer j=0;
		Integer tope=((iFila)*(iColumna))+iFila; //32
		System.out.println();

		for(int z = 0; z<=tope ;z++){
			if(i < iFila){ 
				if(j < iColumna){
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

	}
	
	/**
	 * Imprime el tablero, matriz, en sentido Norte
	 * @param Tablero
	 */
	public void imprimirTableroPalabrasN(Tablero tTablero){
		
		final Character[][] cTablero; //Matriz de caracteres para invertir
		Integer iFila = 0;
		Integer iColumna = 0;
		iFila = tTablero.getTablero()[0].length(); //8
		iColumna = this.getDimension(); //3
		cTablero = new Character[iFila][iColumna];
		
		System.out.print("\nTablero Norte(abajo-->arriba): ");
		
		//Inicializo la matriz de char auxiliar
		for (int i = 1; i < iFila; i++)
		    for (int j = 1; j < iColumna; j++)
		    	cTablero[i][j] = ' ';
	
		//Invierto en sentido Norte tTablero
		Integer i=0;
		Integer j=0;
		Integer tope=((iFila)*(iColumna))+iFila; //32
		String sLineaReverse = null;
		Character cLineaAux = null;
		
		System.out.println();
		sLineaReverse = "";
		for(int z = 0; z<=tope ;z++){
			if(i < iFila){ 			
				if(j < iColumna){
					cLineaAux = tTablero.getTablero()[j].toLowerCase().charAt(i);
					sLineaReverse = sLineaReverse + cLineaAux + " ";
					j++;
				}else{
					System.out.print(reverse(sLineaReverse));
					sLineaReverse = "";
					cLineaAux = ' ';
					j=0;
					i++;
					System.out.println();
				}
			}else{
				i=0;
			}
		}
		
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
