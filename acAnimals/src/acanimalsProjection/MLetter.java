package acanimalsProjection;


/**
 * 
 * @author christophs
 * 
 * new MLetter("a", new Boolean[][] = { {true,true,true,true}, {true, false, false, true}, {true, true, true, true}, {true, false, false, true}, {true, false, false, true} });
 * Boolean[][] a = ;
 *
 */

public class MLetter {
	String letter;
	Boolean[][] matrix;
	
	public MLetter(String letter, Boolean[][] matrix){
		this.letter = letter;
		this.matrix = matrix;
	}
}
