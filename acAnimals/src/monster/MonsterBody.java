package monster;

import javax.media.opengl.GL;

import buffet.Core;

import processing.core.*;
import processing.opengl.PGraphicsOpenGL;

public class MonsterBody {
	  int type=0;
	  int rot=0;
	  
	  MonsterBody(int t, int r)
	  {
	    type = t;
	    rot = r;
	  }
	  
	  void draw()
	  {
		  Core.p5.pushMatrix();
		  Core.p5.translate(71/2, 98/2);
		  Core.p5.rotate(PApplet.radians(rot));
		  Core.p5.translate(-71/2, -98/2);
	    
	    switch(type)
	    {
	    case 0: drawA();
	      break;
	    case 1: drawC();
	      break;
	    case 2: drawPlus();
	      break;
	    }
	    Core.p5.popMatrix();
	  }

	  void drawA()
	  {
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) Core.p5.g;
	    GL gl = pgl.beginGL();
	    gl.glColor3f(0,0,0);
	    
	    gl.glBegin(GL.GL_TRIANGLES);
	    //============================
	    gl.glVertex2f(  0.0f,  0.0f);
	    gl.glVertex2f( 45.0f,  0.0f);
	    gl.glVertex2f(  0.0f, 98.0f);
	    ////----------------------------
	    gl.glVertex2f(  0.0f, 98.0f);
	    gl.glVertex2f( 45.0f,  0.0f);
	    gl.glVertex2f( 45.0f, 98.0f);
	    //============================
	    gl.glVertex2f( 45.0f,  0.0f);
	    gl.glVertex2f( 71.0f,  0.0f);
	    gl.glVertex2f( 45.0f, 26.0f);
	    ////----------------------------
	    gl.glVertex2f( 45.0f, 26.0f);
	    gl.glVertex2f( 71.0f, 26.0f);
	    gl.glVertex2f( 71.0f,  0.0f);
	    //============================
	    gl.glVertex2f( 45.0f, 45.0f);
	    gl.glVertex2f( 45.0f, 71.0f);
	    gl.glVertex2f( 53.0f, 45.0f);
	    ////----------------------------
	    gl.glVertex2f( 53.0f, 45.0f);
	    gl.glVertex2f( 53.0f, 71.0f);
	    gl.glVertex2f( 45.0f, 71.0f);
	    //============================
	    gl.glVertex2f( 53.0f, 26.0f);
	    gl.glVertex2f( 71.0f, 26.0f);
	    gl.glVertex2f( 53.0f, 98.0f);
	    ////----------------------------
	    gl.glVertex2f( 71.0f, 26.0f);
	    gl.glVertex2f( 53.0f, 98.0f);
	    gl.glVertex2f( 71.0f, 98.0f);
	    //============================
	    gl.glEnd();
	    
	    pgl.endGL();
	  }

	  void drawC()
	  {
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) Core.p5.g;
	    GL gl = pgl.beginGL();
	    //============================
	    gl.glBegin(GL.GL_TRIANGLES);
	    gl.glVertex2f(  0.0f,  0.0f);
	    gl.glVertex2f( 18.0f,  0.0f);
	    gl.glVertex2f(  0.0f, 98.0f);
	    ////----------------------------
	    gl.glVertex2f( 18.0f,  0.0f);  
	    gl.glVertex2f( 18.0f, 98.0f);
	    gl.glVertex2f(  0.0f, 98.0f);
	    //============================
	    gl.glVertex2f( 18.0f,  0.0f);
	    gl.glVertex2f( 71.0f,  0.0f);
	    gl.glVertex2f( 18.0f, 26.0f);
	    ////----------------------------
	    gl.glVertex2f( 18.0f, 26.0f);
	    gl.glVertex2f( 71.0f,  0.0f);
	    gl.glVertex2f( 71.0f, 26.0f);
	    //============================
	    gl.glVertex2f( 26.0f, 26.0f);
	    gl.glVertex2f( 71.0f, 26.0f);
	    gl.glVertex2f( 26.0f, 45.0f);
	    ////----------------------------
	    gl.glVertex2f( 26.0f, 45.0f);
	    gl.glVertex2f( 71.0f, 45.0f);
	    gl.glVertex2f( 71.0f, 26.0f);
	    //============================
	    gl.glVertex2f( 26.0f, 53.0f);
	    gl.glVertex2f( 26.0f, 71.0f);
	    gl.glVertex2f( 71.0f, 53.0f);
	    ////----------------------------
	    gl.glVertex2f( 26.0f, 71.0f);
	    gl.glVertex2f( 71.0f, 53.0f);
	    gl.glVertex2f( 71.0f, 71.0f);
	    //============================
	    gl.glVertex2f( 18.0f, 71.0f);
	    gl.glVertex2f( 18.0f, 98.0f);
	    gl.glVertex2f( 71.0f, 71.0f);
	    ////----------------------------
	    gl.glVertex2f( 18.0f, 98.0f);
	    gl.glVertex2f( 71.0f, 71.0f);
	    gl.glVertex2f( 71.0f, 98.0f);
	    //============================
	    gl.glEnd( );
	    pgl.endGL();
	  }

	  void drawPlus()
	  {
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) Core.p5.g;
	    GL gl = pgl.beginGL();
	    gl.glBegin(GL.GL_TRIANGLES);
	    //============================
	    gl.glVertex2f(  0.0f,  0.0f);
	    gl.glVertex2f( 26.0f,  0.0f);
	    gl.glVertex2f(  0.0f, 98.0f);
	    ////----------------------------
	    gl.glVertex2f( 26.0f,  0.0f);  
	    gl.glVertex2f( 26.0f, 98.0f);
	    gl.glVertex2f(  0.0f, 98.0f);
	    //============================
	    gl.glVertex2f( 26.0f,  0.0f);
	    gl.glVertex2f( 71.0f,  0.0f);
	    gl.glVertex2f( 26.0f, 45.0f);
	    ////----------------------------
	    gl.glVertex2f( 26.0f, 45.0f);
	    gl.glVertex2f( 71.0f,  0.0f);
	    gl.glVertex2f( 71.0f, 45.0f);
	    //============================
	    gl.glVertex2f( 26.0f, 53.0f);
	    gl.glVertex2f( 71.0f, 53.0f);
	    gl.glVertex2f( 26.0f, 98.0f);
	    ////----------------------------
	    gl.glVertex2f( 26.0f, 98.0f);
	    gl.glVertex2f( 71.0f, 53.0f);
	    gl.glVertex2f( 71.0f, 98.0f);
	    //============================
	    gl.glEnd( );
	    pgl.endGL();
	  }
	}