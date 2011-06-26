package animalosc;

import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;

public class AnimalOsc {
	OscP5 oscP5;
	
	public AnimalOsc(PApplet p, int port){
		oscP5 = new OscP5(p,port);
	}
	
}
