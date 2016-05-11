package rapigrama;

import java.util.Arrays;

public class VectorPalabra {
	
	private Integer dimensionVectorPalabra;
	private String[] vectorPalabra;
	
	
	/**
	 * Constructor de VectorPalabras que crea objeto tipo Vector y lo inicializa.
	 * @param dimension
	 */
	public VectorPalabra(Integer dimension){
		this.setDimensionVectorPalabra(dimension);
	    this.setVectorPalabra(new String[dimensionVectorPalabra]);	
	    
	    for(int i=0; i<vectorPalabra.length; i++)
	    	vectorPalabra[i]="";
	}
	
	/**
	 * Método de carga de VectorMath
	 * @param cCoordenada
	 * @param sPalabra
	 */
	public void cargarVectorArchivo(Integer cCoordenada, String sPalabra){
		vectorPalabra[cCoordenada]=sPalabra;
	}
	
	/**
	 * Método de impresión de VectorMath
	 */
	public void imprimirVector(){
		System.out.println("\nPalabras a buscar en el Tablero: ");
		for(int i=0; i<vectorPalabra.length; i++)
			System.out.println(vectorPalabra[i] + " ");
	}
	
	public char[] devolverToCharArray(int indice){
		char[]aux=vectorPalabra[indice].toCharArray();		
//		for(int i=0; i<this.getVectorPalabra().length;i++)
//			aux[indice]= vectorPalabra[i].toCharArray();
	    return aux;
	}
	
	/**
	 * Getters y Setters
	 * @return
	 */

	public Integer getDimensionVectorPalabra() {
		return dimensionVectorPalabra;
	}

	public void setDimensionVectorPalabra(Integer dimensionVectorPalabra) {
		this.dimensionVectorPalabra = dimensionVectorPalabra;
	}

	public String[] getVectorPalabra() {
		return vectorPalabra;
	}

	public void setVectorPalabra(String[] vectorPalabra) {
		this.vectorPalabra = vectorPalabra;
	}

		
	@Override
	public String toString() {
		return "Vector Palabra = " + Arrays.toString(vectorPalabra) + " ";
	}	
}
