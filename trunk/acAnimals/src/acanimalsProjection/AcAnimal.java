package acanimalsProjection;

import monster.Monster;
import geomerative.RG;
import geomerative.RShape;
import processing.core.PApplet;
import processing.core.PVector;

public class AcAnimal extends Monster{
	Run p5;
	
	private Boolean moving = false;
	private PVector target = new PVector();
	private PVector dir = new PVector();
	float size = Core.p5.animalSize;
	private float speed = 5;
	private float arriveDist = speed-1;
//	private RShape gfx;
	
	public AcAnimal(PApplet parent, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, float a1x, float a1y, int aT2, float a2x, float a2y){
		super(parent, t1,x1,y1,r1,t2,x2,y2,r2, aT1, new PVector(a1x, a1y), aT2, new PVector(a2x, a2y));
		p5 = Core.p5;
//		gfx = RG.loadShape("../data/testtierchen.svg");
//		gfx.scale(0.15f);
	}
	
	public void update(){
		
		// check if the animal has arrived
		if(pos.x >= target.x-arriveDist && pos.x <= target.x+arriveDist){
			if(pos.y >=target.y-arriveDist && pos.y <= target.y+arriveDist){
				moving=false;
				Core.p5.removeMovingAnimal(this);
			}
		}
		// moves the animal
		if(moving){
			dir=PVector.sub(target,pos);
			dir.normalize();
			dir.mult(speed);
			pos.add(dir);
		}
		super.update();
	}
	
	public void setTarget(PVector t){
		p5.addMovingAnimal(this);
		target = t;
		moving=true;
	}
	
	public Boolean isMoving(){
		return moving;
	}
//	public void draw(){
////		p5.fill(255,0,0);
////		p5.noStroke();
////		p5.rect(location.x, location.y, size, size);
//		p5.pushMatrix();
//		p5.translate(pos.x,pos.y);
//		gfx.draw(p5);
//		p5.popMatrix();
//	}
}
