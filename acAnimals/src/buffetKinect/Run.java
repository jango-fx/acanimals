package buffetKinect;

import java.util.ArrayList;
import java.util.Iterator;

import monster.Monster;
import saveTxt.Save;
import oscP5.OscMessage;
import acanimalsProjection.AcAnimal;
import animalosc.AnimalOsc;

import processing.core.PApplet;
import processing.core.PVector;

public class Run extends PApplet
{
	ArrayList<MonsterRunner> mo = new ArrayList();
	ArrayList<MonsterRunner> mo_tmp = new ArrayList();
	AnimalOsc osc;

	KinectFX kinect;
	Save saver = new Save(this, "buff");

	public void setup()
	{
		Core.p5 = this;
		osc = new AnimalOsc(this, 12000);
		saver.initSaver();

		size(1440, 900, OPENGL);
		scale(0.1f);

		kinect = new KinectFX(this);

		loadAnimals();

		for (int i = 0; i < 50; i++)
		{
			mo.add(new MonsterRunner(mo, 0, random(50, width - 50), random(50, height - 50), (int) random(3) * 90, 0, (71 / 71), (random(-14, 14) / 71), (int) random(3) * 90, (int) random(8), new PVector(1, 1, 0), (int) random(8), new PVector(1, 0.5f, 0)));
		}
		Monster.f = 0.3f;
	}

	public void draw()
	{
		background(255);

		kinect.update();

		mo.addAll(mo_tmp);
		mo_tmp.clear();

		Iterator<MonsterRunner> iterator = mo.iterator();
		while (iterator.hasNext())
		{
			MonsterRunner monster = iterator.next();
			// monster.update(null);
			monster.update(kinect);
		}

	}

	private void loadAnimals()
	{
		String[] animalData = saver.getAnimalData();
		println(animalData);
		for (int i = 0; i < animalData.length; i++)
		{
			String[] singleAnimal = animalData[i].split("\t");

			int t1 = Integer.parseInt(singleAnimal[0]);
			float x1 = random(50, width - 50); //Float.valueOf(singleAnimal[1]);
			float y1 = random(50, height - 50); //Float.valueOf(singleAnimal[2]);
			int r1 = Integer.parseInt(singleAnimal[3]);

			int t2 = Integer.parseInt(singleAnimal[4]);
			float x2 = Float.valueOf(singleAnimal[5]);
			float y2 = Float.valueOf(singleAnimal[6]);
			int r2 = Integer.parseInt(singleAnimal[7]);

			int a1t = Integer.parseInt(singleAnimal[8]);
			float a1x = Float.valueOf(singleAnimal[9]);
			float a1y = Float.valueOf(singleAnimal[10]);

			int a2t = Integer.parseInt(singleAnimal[11]);
			float a2x = Float.valueOf(singleAnimal[12]);
			float a2y = Float.valueOf(singleAnimal[13]);
			println(t1 + "\t" + x1 + "\t" + y1 + "\t" + r1 + "\t" + t2 + "\t" + x2 + "\t" + y2 + "\t" + r2 + "\t" + a1t + "\t" + a1x + "\t" + a1y + "\t" + a2t + "\t" + a2x + "\t" + a2y);

			MonsterRunner monster = new MonsterRunner(mo, t1, x1, y1, r1, t2, x2, y2, r2, a1t, new PVector(a1x, a1y), a2t, new PVector(a2x, a2y));
			mo.add(monster);
		}
	}

	public void oscEvent(OscMessage theOscMessage)
	{
		println("### OSC MESSAGE - Pattern: '" + theOscMessage.addrPattern() + "'");
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

			println("Add Monster...");
			MonsterRunner newMo = new MonsterRunner(mo, t1, x1, y1, r1, t2, x2, y2, r2, a1t, new PVector(a1x, a1y), a2t, new PVector(a2x, a2y));
			newMo.vel.add(new PVector(1, 1, 0));
			mo_tmp.add(newMo);

			saver.addAnimal(t1, x1, y1, r1, t2, x2, y2, r2, a1t, a1x, a1y, a2t, a2x, a2y);
			println("done.");

			return;
		}
	}

	public static void main(String _args[])
	{
		PApplet.main(new String[] { "--present", Run.class.getName() });
	}

}
