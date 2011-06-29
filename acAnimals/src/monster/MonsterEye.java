package monster;

import processing.core.*;
import processing.opengl.PGraphicsOpenGL;
import javax.media.opengl.GL;

import java.lang.Math;

public class MonsterEye
{
	PApplet p;
	float x, y;
	float a;

	int type = 0;
	int r = 10;
	float DEG2RAD = (float) (3.14159 / 180);

	MonsterEye(PApplet p, int t, PVector v)
	{
		this.p = p;
		x = v.x*71;
		y = v.y*71;
		a = v.z;
		type = t;
	}

	void draw()
	{
		p.pushMatrix();
		p.translate(0, 0);
		p.rotate(PApplet.radians(a));
		p.translate(0, 0);

		switch (type)
		{
		case 1:
		case 2:
			drawEye_Angry(1);
			break;
		case 3:
		case 4:
			drawEye_Rolling(1,0);
			break;
		case 5:
		case 6:
			drawEye_Rolling(0,-1);
			break;
		case 7:
		case 8:
			drawEye_Normal();
			break;	
		case 9:
		case 10:
			drawEye_Angry(-1);
			break;
		case 11:
		case 12:
			drawEye_Rolling(-1,0);
			break;
		case 13:
		case 14:
			drawEye_Rolling(0,1);
			break;
		case 15:
		case 16:
			drawEye_Squeezed();
			break;	
		default:
			break;
		}
		p.popMatrix();
	}

	void drawEye_Normal()
	{
		drawCircle(0, 0, r, 0, 360, 1, 1, 1);
		drawCircle(0, 0, (float) (r * 0.4), 0, 360, 0, 0, 0);
	}

	void drawEye_Rolling(int x, int y)
	{
		drawCircle(0, 0, r, 0, 360, 1, 1, 1);
		drawCircle((float) (x * r * 0.4), (float) (y * r * 0.4), (float) (r * 0.4), 0, 360, 0, 0, 0);
	}

	void drawEye_Squeezed()
	{
		drawCircle(0, 0, r, -40, 40, 1, 1, 1);
		drawCircle(0, 0, r, 140, 220, 1, 1, 1);
		drawRectInCircle(0, 0, r, -40, 40, 1, 1, 1);
		drawCircle(0, 0, (float) (r * 0.4), 0, 360, 0, 0, 0);
	}

	void drawEye_Lash()
	{
		drawCircle(0, 0, r, 0, 360, 1, 1, 1);
		drawCircle(0, 0, (float) (r * 0.4), 0, 360, 0, 0, 0);
		drawWimper(0, 0, r);
	}

	void drawEye_Angry(int c)
	{
	  drawCircle(0, 0, r, -35-(c*35), 205-(c*35), 1, 1, 1);
	  drawCircle(0, 0, (float) (r * 0.4), 0, 360, 0, 0, 0);
	}

	void drawCircle(float x, float y, float radius, int start, int end, float c1, float c2, float c3)
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();
		float DEG2RAD = (float) (3.14159 / 180);
		gl.glTranslatef(x, y, 0);
		gl.glBegin(GL.GL_POLYGON);
		gl.glColor3f(c1, c2, c3);
		for (int i = start; i < end; i++)
		{
			float degInRad = i * DEG2RAD;
			gl.glVertex2f((float) (Math.cos(degInRad) * radius), (float) (Math.sin(degInRad) * radius));
		}

		gl.glEnd();

		pgl.endGL();
	}

	void drawRectInCircle(float x, float y, float radius, int start, int end, float c1, float c2, float c3)
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();

		gl.glTranslatef(x, y, 0);
		gl.glBegin(GL.GL_POLYGON);
		gl.glColor3f(c1, c2, c3);

		gl.glVertex2f((float) (Math.cos(start * DEG2RAD) * radius), (float) (Math.sin(start * DEG2RAD) * radius));
		gl.glVertex2f((float) (Math.cos(end * DEG2RAD) * radius), (float) (Math.sin(end * DEG2RAD) * radius));

		gl.glVertex2f((float) (Math.cos((180 - start) * DEG2RAD) * radius), (float) (Math.sin((180 - start) * DEG2RAD) * radius));
		gl.glVertex2f((float) (Math.cos((180 - end) * DEG2RAD) * radius), (float) (Math.sin((180 - end) * DEG2RAD) * radius));

		gl.glEnd();

		pgl.endGL();
	}

	void drawWimper(float x, float y, float radius)
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();
		gl.glTranslatef(50, 50, 0);
		gl.glColor3f(0, 0, 0);
		gl.glEnable(GL.GL_LINE_SMOOTH);
		gl.glLineWidth(2);
		gl.glBegin(GL.GL_LINES);

		for (int i = -120; i < -60; i += 10)
		{
			float degInRad = i * DEG2RAD;
			gl.glVertex2f((float) (Math.cos(degInRad) * radius), (float) (Math.sin(degInRad) * radius));
			gl.glVertex2f((float) (Math.cos(degInRad) * radius * 1.5), (float) (Math.sin(degInRad) * radius * 1.5));
		}

		gl.glEnd();

		pgl.endGL();
	}
}
