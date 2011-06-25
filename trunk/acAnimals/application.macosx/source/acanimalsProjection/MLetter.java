package acanimalsProjection;

import java.util.ArrayList;
import java.util.Iterator;



import processing.core.PVector;


/**
 * 
 * @author christophs
 * 
 * new MLetter("a", new Boolean[][] = { {true,true,true,true}, {true, false, false, true}, {true, true, true, true}, {true, false, false, true}, {true, false, false, true} });
 * Boolean[][] a = ;
 *
 */

public class MLetter {
	private Boolean[][] matrix;
	int width = 0;
	private ArrayList<PVector> letterPoints = new ArrayList<PVector>();
	private ArrayList<PVector> displayPoints = new ArrayList<PVector>();
	PVector rightDisplayPoint = new PVector();
	
	public MLetter(Boolean[][] matrix){
		this.matrix = matrix;
		width = matrix.length;
		createPoints();
	}
	
	private void createPoints(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j]){
					letterPoints.add(new PVector(i*Core.p5.letterScale, j*Core.p5.letterScale));
				}
			}
		}
	}
	
	public ArrayList<PVector> getPoints(){
		return letterPoints;
	}
	
	public ArrayList<PVector> getDisplayPoints(){
		return displayPoints;
	}
	
	public void createDisplayPoints(float xShift, float yShift){
		for (Iterator<PVector> iterator = letterPoints.iterator(); iterator.hasNext();) {
			PVector lPoint = iterator.next();
			PVector dPoint = new PVector(lPoint.x, lPoint.y);
			dPoint.x+=xShift;
			dPoint.y+=yShift;
			displayPoints.add(dPoint);
		}
		rightDisplayPoint = displayPoints.get(displayPoints.size()-1);
	}
	
	
}
