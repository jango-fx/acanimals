package buffet;

import processing.core.*;

public class MonsterRunner extends Monster
{
	public PVector vel, acc;
	public MonsterRunner[] neighbours;
	
	  MonsterRunner(int t1, float x1, float y1, int r1, int t2, float x2,float y2, int r2)
	  {
			super(t1, x1, y1, r1, t2, x2, y2, r2);
			
		    vel = new PVector(random(-10.0f, 10.0f), random(-10.0f, 10.0f));
		    neighbours = n;
			// TODO Auto-generated constructor stub
	}
	  
	  MonsterRunner(MonsterRunner[] n, int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2)
	  {
	    super(t1, x1, y1, r1, t2, x2, y2, r2);
	    
	    vel = new PVector(random(-10.0f, 10.0f), random(-10.0f, 10.0f));
	    neighbours = n;
	  }

	  void update()
	  {
	    move();
	    super.draw();
	  }

	  void move()
	  {
	    float a = 0.6f;
	    acc = new PVector(random(-a, a), random(-a, a));

	    PVector fric = new PVector(vel.x*-0.01, vel.y*-0.01);
//	    acc.add(fric);
	    vel.add(acc);
	    constrain(vel.x, -0.5, 0.5);
	    constrain(vel.y, -0.5, 0.5);    
	    checkBoundaries();
	    checkNeighbours();
	    pos.add(vel);
	  }

	  void checkBoundaries()
	  {
	    if (pos.x-(0) < 0 || pos.x+(0) > width) 
	    {
	      vel.x *= -0.9;
	      if (pos.x < 0)
	        pos.x = 1;
	      else pos.x = width-1;
	    }
	    if (pos.y-(0) < 0 || pos.y+(0) > height)
	    { 
	      vel.y *= -0.9;
	      if (pos.y < 0)
	        pos.y = 1;
	      else pos.y = height-1;
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
	        if (dist(pos.x, pos.y, neighbours[i].pos.x, neighbours[i].pos.y) < 98*2*f)
	        {
	          PVector tmpVel = new PVector(vel.x, vel.y);
	          neighbours[i].vel.set(vel);
	          neighbours[i].vel.mult(.0);
	          //          neighbours[i].pos.add(vel);
	          vel.set(tmpVel);
	          vel.mult(.9);
	          //                    pos.add(vel);
	        }
	      }
	    }
	  }
	}