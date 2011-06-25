package buffetKinect;

import processing.core.PApplet;
import processing.opengl.*;
import javax.media.opengl.*;
import controlP5.*;
import org.openkinect.*;
import org.openkinect.processing.*;

public class Run extends PApplet
{

	MonsterRunner[] mo = new MonsterRunner[100];
//	ControlP5 controlP5;
//	Range range;

	KinectFX kinect;

	public void setup()
	{
		Core.p5 = this;

		size(1440, 900, OPENGL);
		scale(0.1f);

		kinect = new KinectFX(this);

//		controlP5 = new ControlP5(this);
//		range = controlP5.addRange("rangeController", 0, 2048, 0, 2048, 20, height - 20, 200, 12);

		for (int i = 0; i < mo.length; i++)
		{
			mo[i] = new MonsterRunner(mo, 0, random(50, width - 50), random(50, height - 50), (int) random(3) * 90, 0, 71, random(-14, 14), (int) random(3) * 90);
			// mo[i] = new Monster(mo, int(random(3))*90, random(500, 7500),
			// random(500,5500), int(random(3))*90, int(random(3)), 71,
			// random(-14,14), int(random(3))*90);
		}

		smooth();
	}

	public void draw()
	{
		background(255);

		// scale(0.1);

		kinect.draw();

		for (int i = 0; i < mo.length; i += 1)
		{
			mo[i].update();
		}
	}
	
	public static void main(String _args[])
	{
		PApplet.main(new String[] { "--present", Run.class.getName() });
	}
	
}
