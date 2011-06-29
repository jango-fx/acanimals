package monster;

import javax.media.opengl.GL;

import processing.core.*;
import processing.opengl.PGraphicsOpenGL;

public class MonsterBody
{
	PApplet p;
	int type = 0;
	int rot = 0;

	MonsterBody(PApplet p, int t, int r)
	{
		this.p = p;
		type = t;
		rot = r;
	}

	void draw()
	{
		p.pushMatrix();
		p.translate(71 / 2, 98 / 2);
		p.rotate(PApplet.radians(rot));
		p.translate(-71 / 2, -98 / 2);

		switch (type)
		{
		case 0:
			drawA();
			break;
		case 1:
			drawPlus();
			break;
		case 2:
			drawPlus2();
			break;
		default:
			drawC();
			break;
		}
		p.popMatrix();
	}

	void drawA()
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();
		gl.glColor3f(0, 0, 0);

		gl.glBegin(GL.GL_TRIANGLES);
		// ============================
		gl.glVertex2f(0.0f, 0.0f);
		gl.glVertex2f(45.0f, 0.0f);
		gl.glVertex2f(0.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(0.0f, 98.0f);
		gl.glVertex2f(45.0f, 0.0f);
		gl.glVertex2f(45.0f, 98.0f);
		// ============================
		gl.glVertex2f(45.0f, 0.0f);
		gl.glVertex2f(71.0f, 0.0f);
		gl.glVertex2f(45.0f, 26.0f);
		// //----------------------------
		gl.glVertex2f(45.0f, 26.0f);
		gl.glVertex2f(71.0f, 26.0f);
		gl.glVertex2f(71.0f, 0.0f);
		// ============================
		gl.glVertex2f(45.0f, 45.0f);
		gl.glVertex2f(45.0f, 71.0f);
		gl.glVertex2f(53.0f, 45.0f);
		// //----------------------------
		gl.glVertex2f(53.0f, 45.0f);
		gl.glVertex2f(53.0f, 71.0f);
		gl.glVertex2f(45.0f, 71.0f);
		// ============================
		gl.glVertex2f(53.0f, 26.0f);
		gl.glVertex2f(71.0f, 26.0f);
		gl.glVertex2f(53.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(71.0f, 26.0f);
		gl.glVertex2f(53.0f, 98.0f);
		gl.glVertex2f(71.0f, 98.0f);
		// ============================
		gl.glEnd();

		pgl.endGL();
	}

	void drawC()
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();
		gl.glColor3f(0, 0, 0);

		// ============================
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glVertex2f(0.0f, 0.0f);
		gl.glVertex2f(18.0f, 0.0f);
		gl.glVertex2f(0.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(18.0f, 0.0f);
		gl.glVertex2f(18.0f, 98.0f);
		gl.glVertex2f(0.0f, 98.0f);
		// ============================
		gl.glVertex2f(18.0f, 0.0f);
		gl.glVertex2f(71.0f, 0.0f);
		gl.glVertex2f(18.0f, 26.0f);
		// //----------------------------
		gl.glVertex2f(18.0f, 26.0f);
		gl.glVertex2f(71.0f, 0.0f);
		gl.glVertex2f(71.0f, 26.0f);
		// ============================
		gl.glVertex2f(26.0f, 26.0f);
		gl.glVertex2f(71.0f, 26.0f);
		gl.glVertex2f(26.0f, 45.0f);
		// //----------------------------
		gl.glVertex2f(26.0f, 45.0f);
		gl.glVertex2f(71.0f, 45.0f);
		gl.glVertex2f(71.0f, 26.0f);
		// ============================
		gl.glVertex2f(26.0f, 53.0f);
		gl.glVertex2f(26.0f, 71.0f);
		gl.glVertex2f(71.0f, 53.0f);
		// //----------------------------
		gl.glVertex2f(26.0f, 71.0f);
		gl.glVertex2f(71.0f, 53.0f);
		gl.glVertex2f(71.0f, 71.0f);
		// ============================
		gl.glVertex2f(18.0f, 71.0f);
		gl.glVertex2f(18.0f, 98.0f);
		gl.glVertex2f(71.0f, 71.0f);
		// //----------------------------
		gl.glVertex2f(18.0f, 98.0f);
		gl.glVertex2f(71.0f, 71.0f);
		gl.glVertex2f(71.0f, 98.0f);
		// ============================
		gl.glEnd();
		pgl.endGL();
	}

	void drawPlus()
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();		
		gl.glColor3f(0, 0, 0);

		gl.glBegin(GL.GL_TRIANGLES);
		// ============================
		gl.glVertex2f(0.0f, 0.0f);
		gl.glVertex2f(26.0f, 0.0f);
		gl.glVertex2f(0.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(26.0f, 0.0f);
		gl.glVertex2f(26.0f, 98.0f);
		gl.glVertex2f(0.0f, 98.0f);
		// ============================
		gl.glVertex2f(26.0f, 0.0f);
		gl.glVertex2f(71.0f, 0.0f);
		gl.glVertex2f(26.0f, 45.0f);
		// //----------------------------
		gl.glVertex2f(26.0f, 45.0f);
		gl.glVertex2f(71.0f, 0.0f);
		gl.glVertex2f(71.0f, 45.0f);
		// ============================
		gl.glVertex2f(26.0f, 53.0f);
		gl.glVertex2f(71.0f, 53.0f);
		gl.glVertex2f(26.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(26.0f, 98.0f);
		gl.glVertex2f(71.0f, 53.0f);
		gl.glVertex2f(71.0f, 98.0f);
		// ============================
		gl.glEnd();
		pgl.endGL();
	}

	void drawPlus2()
	{
		PGraphicsOpenGL pgl = (PGraphicsOpenGL) p.g;
		GL gl = pgl.beginGL();
		gl.glColor3f(0, 0, 0);

		gl.glBegin(GL.GL_TRIANGLES);
		// ============================
		gl.glVertex2f(71.0f, 0.0f);
		gl.glVertex2f(45.0f, 0.0f);
		gl.glVertex2f(71.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(45.0f, 0.0f);
		gl.glVertex2f(45.0f, 98.0f);
		gl.glVertex2f(71.0f, 98.0f);
		// ============================
		gl.glVertex2f(45.0f, 0.0f);
		gl.glVertex2f(0.0f, 0.0f);
		gl.glVertex2f(45.0f, 45.0f);
		// //----------------------------
		gl.glVertex2f(45.0f, 45.0f);
		gl.glVertex2f(0.0f, 0.0f);
		gl.glVertex2f(0.0f, 45.0f);
		// ============================
		gl.glVertex2f(45.0f, 53.0f);
		gl.glVertex2f(0.0f, 53.0f);
		gl.glVertex2f(45.0f, 98.0f);
		// //----------------------------
		gl.glVertex2f(45.0f, 98.0f);
		gl.glVertex2f(0.0f, 53.0f);
		gl.glVertex2f(0.0f, 98.0f);
		// ============================
		gl.glEnd();
		pgl.endGL();
	}
}