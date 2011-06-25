package monster;

import processing.core.*;

public class Monster
{
	  //  float width, height;
	  protected PVector pos;

	PVector subPos;
 
	  MonsterBody main, sub;
	  PApplet p;
	  protected float f=0.3f;

	  protected Monster(PApplet parent, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2)
	  {
		p = parent;
	    pos = new PVector(x1, y1);
	    subPos = new PVector(x2, y2);

	    main = new MonsterBody(t1, r1);
	    sub = new MonsterBody(t2, r2);
	  }

	  void update()
	  {
	    draw();
	  }

	  protected void draw()
	  { 
		  p.pushMatrix();

		  p.translate(pos.x, pos.y);
		  p.scale(f);
	    main.draw();

	    p.translate(subPos.x, subPos.y);
	    sub.draw();

	    p.popMatrix();
	  }
	}
