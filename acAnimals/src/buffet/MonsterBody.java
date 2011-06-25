package buffet;

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
	    pushMatrix();
	    translate(71/2, 98/2);
	    rotate(radians(rot));
	    translate(-71/2, -98/2);  
	    
	    switch(type)
	    {
	    case 0: drawA();
	      break;
	    case 1: drawC();
	      break;
	    case 2: drawPlus();
	      break;
	    }
	    popMatrix();
	  }

	  void drawA()
	  {
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) g;
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
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) g;
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
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) g;
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