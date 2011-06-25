package buffet;

import processing.core.*;

public class Monster
{
	  //  float width, height;
	  PVector pos, subPos;

	  MonsterBody main, sub;
	  float f=0.3;

	  Monster(int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2)
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

	  void draw()
	  { 
	    pushMatrix();

	    translate(pos.x, pos.y);
	    scale(f);
	    main.draw();

	    translate(subPos.x, subPos.y);
	    sub.draw();

	    popMatrix();
	  }
	}
