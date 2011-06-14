package acanimalsProjection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.Vector;

import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;
import processing.core.PVector;


public class Run extends PApplet {
	ArrayList<RPoint> points = new ArrayList<RPoint>();
	int xSpace = 10;
	int ySpace = 10;
	int resolution = 20;		// lower -> higher resolution
	float letterScale = 1.3f;
	float animalSize = 10;
	float whiteSpaceWidth = 20;
	float whiteSpaceHeight = 20;
	ArrayList<AcAnimal> animals = new ArrayList<AcAnimal>();
	ArrayList<AcAnimal> movingAnimals = new ArrayList<AcAnimal>();
	ArrayList<String> displayMsg = new ArrayList<String>();
	int animalCnt = 300;
	Timer nextTimer = new Timer();
	
	
	String message = "ab ccdabcda a a a a dd abcd";
	HashMap<String, Letter> alphabet = new HashMap<String, Letter>();
	
	public void setup() {
		size(1024,758,OPENGL);
		
		Core.p5 =this;
		
		 RG.init(this);
		 RG.setPolygonizer(RG.UNIFORMLENGTH);
		 RG.setPolygonizerLength(resolution);
		 
		 displayMsg = msgToLines(message);
		 for (Iterator<String> iterator = displayMsg.iterator(); iterator.hasNext();) {
			println(iterator.next());
		}
		 
		 alphabet.put("a", new Letter("a"));
		 alphabet.put("b", new Letter("b"));
		 alphabet.put("c", new Letter("c"));
		 alphabet.put("d", new Letter("d"));
		 alphabet.put(" ", new Letter("space"));
		 createPoints(displayMsg);
		 setupAnimals();
		 startDraw();
	}

	public void draw() {
		background(255);

		fill(0);
	    stroke(0);
	    for(int i=0; i<points.size(); i++){
	        ellipse(points.get(i).x, points.get(i).y,5,5);  
	      }
	    for (Iterator<AcAnimal> i = animals.iterator(); i.hasNext();) {
			AcAnimal ac =  i.next();
			ac.update();
			ac.draw();
		}
	    if(movingAnimals.isEmpty()){
//	    	println("ready!");
	    	nextTimer.schedule(new NextMsg(), 1000);
	    }
	}
	
	private void createPoints(ArrayList<String> msg){
		int cnt = 0;
		for (Iterator<String> iterator = msg.iterator(); iterator.hasNext();) {
			cnt++;
			String line = iterator.next();
			String[] c = line.split("");
			ArrayList<String> chars = new ArrayList<String>();
			Collections.addAll(chars, c); 
			chars.remove(0);
			ArrayList<Letter> messageLetters = new ArrayList<Letter>();
			for (Iterator<String> i = chars.iterator(); i.hasNext();) {
				String s = i.next();
				messageLetters.add(alphabet.get(s));
//				println(messageLetters.get(messageLetters.size()-1));
			}
			
			for (int i = 0; i < messageLetters.size(); i++) {
				if(messageLetters.get(i)!=null){
					if(i!=0){
						// move Letter X Coordinate to the right according to the Letter before (first one isn't moved)
						if(messageLetters.get(i-1)!=null){
							messageLetters.get(i).wX = (int)messageLetters.get(i-1).getBottomRightWorld().x+xSpace;
						}else{
							messageLetters.get(i).wX = (int)messageLetters.get(i-2).getBottomRightWorld().x+xSpace;
						}
					}
					points.addAll(Arrays.asList(messageLetters.get(i).getWorldPoints()));
				}
			}
//			if(messageLetters.get(cnt)!=null){
//				if(cnt!=0){
//					messageLetters.get(cnt).wY = (int)messageLetters.get(cnt-1).getBottomLeftWorld().y+ySpace;
//				}
//			}
		}
				
		
		
//		points.addAll(Arrays.asList(alphabet.get(chars.get(0)).getPoints()));
//		for (int i = 1; i < chars.size(); i++) {
//			alphabet.get(chars.get(i)).wX = (int)alphabet.get(chars.get(i-1)).getBottomRightWorld().x+space;
//			RPoint[] tPoints = alphabet.get(chars.get(i)).getWorldPoints();
//			points.addAll(Arrays.asList(tPoints));
//		}
	}
	
	private void startDraw(){
		for (int i = 0; i < points.size(); i++) {
			if(i<animals.size()-1){
				animals.get(i).setTarget(Core.RPointToPVector(points.get(i)));
				movingAnimals.add(animals.get(i));
			}else{
				break;
			}
		}
	}
	private void setupAnimals(){
		for (int i = 0; i < animalCnt; i++) {
			animals.add(new AcAnimal(new PVector((int)random(width), (int)random(height))));
		}
	}
	
	private ArrayList<String> msgToLines(String msg){
		ArrayList<String> lines = new ArrayList<String>();
		String[] words = msg.split(" ");
		lines.add(words[0]);
		if(words.length > 1){
			for (int i = 1, pos=0; i < words.length; i++) {
				//println(lines.get(pos).length()+words[i].length());
				if(lines.get(pos).length()+words[i].length()<10){
					lines.set(pos, lines.get(pos)+" "+words[i]);
				}else{
					pos++;
					lines.add(words[i]);
				}
			}
		}
		return lines;
	}
	
	
	public void nextMsg(){
	//	println("next Msg");
	}
	
	
	public void removeMovingAnimal(AcAnimal a){
		movingAnimals.remove(a);
	}
	public static void main(String _args[]) {
		  PApplet.main(new String[] { "--present", Run.class.getName() });
	}
	
}
