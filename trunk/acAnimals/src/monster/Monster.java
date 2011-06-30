package monster;

import buffet.Core;
import processing.core.*;

public class Monster
{
	PApplet p;
	
	private float eyeScale = 1.5f;
	
	MonsterBody main, sub;
	protected PVector pos;
	protected PVector subPos;
	protected PVector c = new PVector();

	MonsterEye leftEye, rightEye;

	public static float f = 0.3f;

	protected Monster(PApplet parent, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, PVector a1, int aT2, PVector a2)
	{
		p = parent;
		pos = new PVector(x1, y1);
		subPos = new PVector(x2 * 71, y2 * 71);

		main = new MonsterBody(p, t1, r1);
		sub = new MonsterBody(p, t2, r2);

		leftEye = new MonsterEye(p, aT1, a1);
		rightEye = new MonsterEye(p, aT2, a2);
		c.set(subPos);
		c.mult(f);
		c.mult(-.5f);
	}

	public void update()
	{
		draw();
	}
	
	public void getCenter(){
		
		c.set(subPos);
		c.mult(0.5f);
		//c.sub(pos);
		
		c.add(pos);
		//p.point(c.x,c.y);
		pos = c;
	//	return c;
	}

	protected void draw()
	{

		p.pushMatrix();

		p.translate(pos.x, pos.y);
		p.scale(f);
		main.draw();

		p.translate(subPos.x, subPos.y);
		sub.draw();
		p.translate(-subPos.x, -subPos.y);
		p.translate(leftEye.x+78*f, leftEye.y+98*f);
		p.pushMatrix();
		p.scale(eyeScale);
		leftEye.draw();
		p.popMatrix();
		p.translate(-(leftEye.x+78*f), -(leftEye.y+98*f));
		p.translate(rightEye.x+78*f, rightEye.y+98*f);
		p.pushMatrix();
		p.scale(eyeScale);
		rightEye.draw();
		p.popMatrix();
		p.popMatrix();

	}
}
