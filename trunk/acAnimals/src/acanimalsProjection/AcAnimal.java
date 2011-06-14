package acanimalsProjection;

import processing.core.PApplet;
import processing.core.PVector;

public class AcAnimal {
	Run p5;
	
	private Boolean moving = false;
	
	private PVector location = new PVector();
	private PVector target = new PVector();
	private PVector dir = new PVector();
	float size = Core.p5.animalSize;
	private float speed = 2;
	private float arriveDist = 1;
	
	public AcAnimal(PVector loc){
		p5 = Core.p5;
		location = loc;
	}
	
	public void update(){
		// check if the animal has arrived
		if(location.x >= target.x-arriveDist && location.x <= target.x+arriveDist){
			if(location.y >=target.y-arriveDist && location.y <= target.y+arriveDist){
				moving=false;
				Core.p5.removeMovingAnimal(this);
			}
		}
		// moves the animal
		if(moving){
			dir=PVector.sub(target,location);
			dir.normalize();
			dir.mult(speed);
			location.add(dir);
		}
	}
	
	public void setTarget(PVector t){
		target = t;
		moving=true;
	}
	public void draw(){
		p5.fill(255,0,0);
		p5.noStroke();
		p5.rect(location.x, location.y, size, size);
	}
}
