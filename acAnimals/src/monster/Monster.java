package monster;

import processing.core.*;

public class Monster
{
	  //  float width, height;
	  protected PVector pos;

	PVector subPos;
 
	  MonsterBody main, sub;
	  protected float f=0.3f;

	  protected Monster(int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2)
	  {
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
		  Core.p5.pushMatrix();

		  Core.p5.translate(pos.x, pos.y);
		  Core.p5.scale(f);
	    main.draw();

	    Core.p5.translate(subPos.x, subPos.y);
	    sub.draw();

	    Core.p5.popMatrix();
	  }
	}
