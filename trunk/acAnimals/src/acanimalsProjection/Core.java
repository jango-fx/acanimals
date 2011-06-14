package acanimalsProjection;

import processing.core.PVector;
import geomerative.RPoint;
import acanimalsProjection.Run;

public class Core {
	public static Run p5;
	
	public static PVector RPointToPVector(RPoint r){
		return new PVector(r.x, r.y);
	}
}