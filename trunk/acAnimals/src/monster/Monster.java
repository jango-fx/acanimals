package monster;

import javax.media.opengl.GL;

import buffet.Core;
import processing.core.*;
import processing.opengl.PGraphicsOpenGL;

public class Monster
{
	PApplet p;

	MonsterBody main, sub;
	protected PVector pos;
	PVector subPos;

	MonsterEye leftEye, rightEye;

	public static float f = 0.3f;

	protected Monster(PApplet parent, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, PVector a1, int aT2, PVector a2)
	{
		p = parent;
		pos = new PVector(x1, y1);
		subPos = new PVector(x2 * -71, y2 * -71);

		main = new MonsterBody(p, t1, r1);
		sub = new MonsterBody(p, t2, r2);

		leftEye = new MonsterEye(p, aT1, a1);
		rightEye = new MonsterEye(p, aT2, a2);
	}

	public void update()
	{
		draw();
//		getCenter();
	}
	
	public PVector getCenter(){
		PVector c = new PVector();
		c.set(subPos);
		c.mult(0.5f);
		c.add(pos);
		
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();
		gl.glPushMatrix();
		gl.glTranslatef(c.x, c.y, 0);
		gl.glBegin(GL.GL_POINTS);
		gl.glColor3f(1,0,0);
		gl.glVertex2f(0, 0);
		gl.glPopMatrix();
		gl.glEnd();

		return c;
	}

	protected void draw()
	{

		p.pushMatrix();

		p.translate(pos.x, pos.y);
		p.scale(f);
		main.draw();
	//	p.pushMatrix();
		p.translate(subPos.x, subPos.y);		
//		p.popMatrix();
		sub.draw();
		p.pushMatrix();
		p.translate(leftEye.x, leftEye.y);
		leftEye.draw();
		p.popMatrix();
		p.pushMatrix();
		p.translate(rightEye.x, rightEye.y);
		rightEye.draw();
		p.popMatrix();

		p.popMatrix();

	}
}
