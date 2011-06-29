package acanimalsProjection;

import monster.Monster;
import processing.core.PApplet;
import processing.core.PVector;
import de.looksgood.ani.*;

public class AcAnimal extends Monster{
	Run p5;
	
	private Boolean moving = false;
	private PVector target = new PVector();
	private PVector dir = new PVector();
	float size = Core.p5.animalSize;
	private float speed = 5;
//	private float eSpeed = 3;
	private int steps = 100;
	private int tSteps;
	private int cStep;
	private float arriveDist = speed-1;
	private PVector bPos = new PVector();
	private float posx;
	private float posy;
	private Ani xAni;
	private Ani yAni;
	private Boolean xFin = false;
	private Boolean yFin = false;
//	private RShape gfx;
	
	public AcAnimal(PApplet parent, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, float a1x, float a1y, int aT2, float a2x, float a2y){
		super(parent, t1,x1,y1,r1,t2,x2,y2,r2, aT1, new PVector(a1x, a1y), aT2, new PVector(a2x, a2y));
		p5 = Core.p5;
		  Ani.init(p5);
	
//		gfx = RG.loadShape("../data/testtierchen.svg");
//		gfx.scale(0.15f);
	}
	
	public void update(){
		// check if the animal has arrived
//		if(pos.x >= target.x-arriveDist && pos.x <= target.x+arriveDist){
//			if(pos.y >=target.y-arriveDist && pos.y <= target.y+arriveDist){
//				moving=false;
//				Core.p5.removeMovingAnimal(this);
//			}
//		}
//		PApplet.println(cStep);
//		if(cStep==0){
//			Core.p5.removeMovingAnimal(this);
//			moving=false;
//		}
		
		if(xFin && yFin){
			Core.p5.removeMovingAnimal(this);
			moving=false;
			xFin = false;
			yFin = false;
		}
		// moves the animal
//		if(moving){
//			dir=PVector.sub(target,pos);
//			dir.normalize();
//			dir.mult(speed);
//			pos.add(dir);
//			//pos.x = easePos(pos, dir).x;
//			//pos.y = easePos(pos, dir).x;
//			cStep--;
//		}
		pos.x = posx;
		pos.y = posy;
		super.update();
	}
	
	public void setTarget(PVector t, float eSpeed){
//		bPos.x = pos.x;
//		bPos.y = pos.y;
		target = t;
//		posx = getCenter().x;//pos.x;
//		posy = getCenter().y;//pos.y;
		xAni = new Ani(this, eSpeed, "posx", t.x, Ani.QUAD_IN_OUT, "onEnd:xFinished");
		yAni = new Ani (this, eSpeed, "posy", t.y, Ani.QUAD_IN_OUT, "onEnd:yFinished");
//		steps = stepSize;
		moving=true;
		p5.addMovingAnimal(this);		

//		float rPlus = Core.p5.random(0,1);
//		float dist = PVector.dist(pos, target); 
//		speed = (dist/steps+rPlus);
//		tSteps = (int)(dist/speed);
//		PApplet.println("speed: "+speed+"tSteps: "+tSteps);
		//arriveDist = speed-1;
//		cStep=tSteps;
	}
	
	
	public void xFinished(){
		xFin = true;
	}
	public void yFinished(){
		yFin = true;
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
