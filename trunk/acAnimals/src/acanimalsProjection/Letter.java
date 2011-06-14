package acanimalsProjection;

import java.util.Iterator;

import processing.core.PApplet;

import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;

public class Letter extends RShape {
	int wX = 0;
	int wY = 0;
	String letter;
	
	public Letter (String l){
		super(Core.p5.alphabet.get(l));
//		PApplet.println(this.getPoints());
		this.scale(Core.p5.letterScale);
		letter = l;
		PApplet.println(l);
	}
	
	public RPoint[] getWorldPoints(){
		RPoint[] tPoints=this.getPoints();
		if(tPoints!=null){
			for (int i = 0; i < tPoints.length; i++) {
				RPoint rPoint = tPoints[i];
				rPoint.x+=wX;
				rPoint.y+=wY;
			}
		}else{
			tPoints = new RPoint[0];
		}
		return tPoints;
	}
	public RPoint getBottomRightWorld(){
		RPoint r;
		if(letter.equals(" ")){
			r = new RPoint(Core.p5.whiteSpaceWidth+wX, Core.p5.whiteSpaceHeight+wY);
		}else{
			r = new RPoint(getBottomRight().x+wX, getBottomRight().y+wY);
		}
		return r;
	}
	public RPoint getBottomLeftWorld(){
		RPoint r;
		if(letter.equals(" ")){
			r = new RPoint(0+wX, Core.p5.whiteSpaceHeight+wY);
		}else{
			r = new RPoint(getBottomLeft().x+wX, getBottomLeft().y+wY);
		}
		return r;  
	}
}
