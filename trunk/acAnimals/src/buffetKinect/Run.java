package buffetKinect;

import java.util.ArrayList;
import java.util.Iterator;

import oscP5.OscMessage;
import acanimalsProjection.AcAnimal;
import animalosc.AnimalOsc;
import processing.core.PApplet;
import processing.core.PVector;

public class Run extends PApplet
{

//	MonsterRunner[] mo = new MonsterRunner[100];
	ArrayList mo = new ArrayList();
	AnimalOsc osc;
	// ControlP5 controlP5;
	// Range range;

	KinectFX kinect;

	public void setup()
	{
		Core.p5 = this;
		osc = new AnimalOsc(this, 12000);

		size(1440, 900, OPENGL);
		scale(0.1f);

		kinect = new KinectFX(this);

		// controlP5 = new ControlP5(this);
		// range = controlP5.addRange("rangeController", 0, 2048, 0, 2048, 20,
		// height - 20, 200, 12);

		for (int i = 0; i < 100; i++)
		{
			mo.add(new MonsterRunner(mo, 0, random(50, width - 50), random(50, height - 50), (int) random(3) * 90, 0, (71/71), (random(-14, 14)/71), (int) random(3) * 90, (int) random(8), new PVector(10, 10, 0), (int) random(8), new PVector(40, 10, 0)));
		}
	}

	public void draw()
	{
		background(255);

		kinect.update();
		
		Iterator<MonsterRunner> iterator = mo.iterator();
		while (iterator.hasNext())
		{
			MonsterRunner monster = iterator.next();
			monster.update(kinect);
		}
		
	}

	public void oscEvent(OscMessage theOscMessage)
	{

		if (theOscMessage.checkAddrPattern("/animal") == true)
		{
			int t1 = Integer.parseInt(theOscMessage.get(0).stringValue());
			float x1 = Float.valueOf(theOscMessage.get(1).stringValue()).floatValue();
			float y1 = Float.valueOf(theOscMessage.get(2).stringValue()).floatValue();
			int r1 = Integer.parseInt(theOscMessage.get(3).stringValue());
			
			int t2 = Integer.parseInt(theOscMessage.get(4).stringValue());
			float x2 = Float.valueOf(theOscMessage.get(5).stringValue()).floatValue();
			float y2 = Float.valueOf(theOscMessage.get(6).stringValue()).floatValue();
			int r2 = Integer.parseInt(theOscMessage.get(7).stringValue());
			
			int a1t = Integer.parseInt(theOscMessage.get(8).stringValue());
			float a1x = Float.valueOf(theOscMessage.get(9).stringValue()).floatValue();
			float a1y = Float.valueOf(theOscMessage.get(10).stringValue()).floatValue();
			
			int a2t = Integer.parseInt(theOscMessage.get(11).stringValue());
			float a2x = Float.valueOf(theOscMessage.get(12).stringValue()).floatValue();
			float a2y = Float.valueOf(theOscMessage.get(13).stringValue()).floatValue();
			
			mo.add(new MonsterRunner(mo, t1, x1, y1, r1, t2, x2, y2, r2, a1t, new PVector(a1x, a1y), a2t, new PVector(a2x, a2y)));
			return;
		}
		println("### received an osc message. with address pattern " + theOscMessage.addrPattern());
	}

	public static void main(String _args[])
	{
		PApplet.main(new String[] { "--present", Run.class.getName() });
	}

}
