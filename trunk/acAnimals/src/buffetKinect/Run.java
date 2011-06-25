package buffetKinect;

import processing.core.PApplet;
import processing.opengl.*;
import javax.media.opengl.*;

public class Run extends PApplet {
	
	MonsterRunner[] mo = new MonsterRunner[100];

	public void setup()
	{
		Core.p5 = this;
		
	  size(1440, 900, OPENGL);
	  scale(0.1f);
	  for (int i= 0; i < mo.length; i++) {
	    mo[i] = new MonsterRunner(mo, 0, random(50, width-50), random(50,height-50), (int)random(3)*90, 0, 71, random(-14,14), (int)random(3)*90);    
	    //    mo[i] = new Monster(mo, int(random(3))*90, random(500, 7500), random(500,5500), int(random(3))*90, int(random(3)), 71, random(-14,14), int(random(3))*90);
	  }

	  smooth();
	}


	public void draw()
	{
	//  background(200);
	    PGraphicsOpenGL pgl = (PGraphicsOpenGL) g;
	    GL gl = pgl.beginGL();
	    gl.glColor4f(1,1,1,0.4f);    
	    gl.glBegin(GL.GL_QUADS);
	    gl.glVertex2f(  0.0f,  0.0f);
	    gl.glVertex2f(  0.0f,  height);
	    gl.glVertex2f( width,  height);
	    gl.glVertex2f( width,  0.0f);
	    

	    gl.glEnd( );
	    pgl.endGL();
	 

	//  scale(0.1);

	  for (int i= 0; i < mo.length; i+= 1) {
	    mo[i].update();
	  }
	}
	public static void main(String _args[]) {
		  PApplet.main(new String[] { "--present", Run.class.getName() });
	}
}
