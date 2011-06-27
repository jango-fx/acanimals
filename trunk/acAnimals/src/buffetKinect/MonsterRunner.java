package buffetKinect;

import java.util.Iterator;

import monster.Monster;
import processing.core.*;

public class MonsterRunner extends Monster
{
	public PVector vel, acc;
	public MonsterRunner[] neighbours;

	MonsterRunner(int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, PVector a1, int aT2, PVector a2)
	{
		super(Core.p5, t1, x1, y1, r1, t2, x2, y2, r2, aT1, a1, aT2, a2);
	}

	MonsterRunner(MonsterRunner[] n, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, PVector a1, int aT2, PVector a2)
	{
		super(Core.p5, t1, x1, y1, r1, t2, x2, y2, r2, aT1, a1, aT2, a2);

		vel = new PVector(Core.p5.random(-10.0f, 10.0f), Core.p5.random(-10.0f, 10.0f));
		neighbours = n;
	}

	protected void update(KinectFX kinect)
	{
		move(kinect);
		super.draw();
	}

	void move(KinectFX kinect)
	{
		float a = 0.6f;
		acc = new PVector(Core.p5.random(-a, a), Core.p5.random(-a, a));

		// PVector fric = new PVector(vel.x*-0.01f, vel.y*-0.01f);
		// acc.add(fric);
		checkKinect(kinect);
		vel.add(acc);
		// constrain(vel.x, -0.5, 0.5);
		// constrain(vel.y, -0.5, 0.5);
		checkBoundaries();
		checkNeighbours();
		
		pos.add(vel);
	}

	private void checkKinect(KinectFX kinect)
	{
		// TODO Auto-generated method stub
		Iterator<PVector> iterator = kinect.teller.iterator();

		while (iterator.hasNext())
		{
			PVector punkt = iterator.next();
			
			if (PApplet.dist(pos.x, pos.y, punkt.x, punkt.y) < (98 * 2 * f)+(kinect.skip*kinect.factor))
			{
				PVector v = new PVector(punkt.x, punkt.y, 0);
				v.sub(pos);
				v.normalize();
				v.mult(-0.1f);
				acc.add(v);
			}
		}
		
	}

	void checkBoundaries()
	{
		if (pos.x - (0) < 0 || pos.x + (0) > Core.p5.width)
		{
			vel.x *= -0.9;
			if (pos.x < 0)
				pos.x = 1;
			else
				pos.x = Core.p5.width - 1;
		}
		if (pos.y - (0) < 0 || pos.y + (0) > Core.p5.height)
		{
			vel.y *= -0.9;
			if (pos.y < 0)
				pos.y = 1;
			else
				pos.y = Core.p5.height - 1;
		}
	}

	void checkNeighbours()
	{
		for (int i = 0; i < neighbours.length; i++)
		{
			if (neighbours[i] != this)
			{
				PVector tmpAcc = new PVector(neighbours[i].pos.x, neighbours[i].pos.y);
				tmpAcc.sub(pos);
				tmpAcc.normalize();
				tmpAcc.mult(12);
				acc.add(tmpAcc);
				if (PApplet.dist(pos.x, pos.y, neighbours[i].pos.x, neighbours[i].pos.y) < 98 * 2 * f)
				{
					PVector tmpVel = new PVector(vel.x, vel.y);
					neighbours[i].vel.set(vel);
					neighbours[i].vel.mult(.0f);
					// neighbours[i].pos.add(vel);
					vel.set(tmpVel);
					vel.mult(.9f);
					// pos.add(vel);
				}
			}
		}
	}
}