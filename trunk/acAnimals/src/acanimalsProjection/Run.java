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
	Boolean debug = true;
	
	ArrayList<RPoint> points = new ArrayList<RPoint>();
	int xSpace = 10;
	int ySpace = 10;
	int resolution = 20;		// lower -> higher resolution
	float letterScale = 1.3f;
	float animalSize = 10f;
	float whiteSpaceWidth = 30f;
	float whiteSpaceHeight = 20f;
	int lineHeight = 140;
	ArrayList<AcAnimal> animals = new ArrayList<AcAnimal>();
	ArrayList<AcAnimal> movingAnimals = new ArrayList<AcAnimal>();
	ArrayList<String> displayMsg = new ArrayList<String>();
	int animalCnt = 200;
	Timer nextTimer = new Timer();
	Boolean gotoText = true;
	int msgPos = -1;
	

	ArrayList<String> messageList = new ArrayList<String>();
	//String message;
	
	HashMap<String, RShape> alphabet = new HashMap<String, RShape>();

	private boolean drawFinish = false;
	private boolean waiting = false;
	
	public void setup() {
		size(1024,758,OPENGL);
		
		Core.p5 = this;
		
		 RG.init(this);
		 RG.setPolygonizer(RG.UNIFORMLENGTH);
		 RG.setPolygonizerLength(resolution);
		 
		 messageList.add("sommer test");
		 Boolean[][] a = new Boolean[][]{ {	true,true,true,true}, {true, false, false, true}, {true, true, true, true}, {true, false, false, true}, {true, false, false, true} };
		 new MLetter("a", a);
		 
		 for (Iterator<String> iterator = displayMsg.iterator(); iterator.hasNext();) {
			println(iterator.next());
		 }
		 messageList.add("abcdefghij");
//		 messageList.add("a");
		 messageList.add("klmnopqrst");
		 messageList.add("uvwxyz");
		 
//		  buchstaben laden
		 
		 createLetters();
		 for(char ch='a'; ch<='z'; ch++){
			 alphabet.put(String.valueOf(ch), new RShape(RG.loadShape("../data/alphabet/"+String.valueOf(ch)+".svg")));	 
		 }
		 for(int num=0; num<=9; num++){
			 alphabet.put(String.valueOf(num), new RShape(RG.loadShape("../data/alphabet/"+String.valueOf(num)+".svg")));	 
		 }
		 
		 alphabet.put(" ", new RShape(RG.loadShape("../data/alphabet/space.svg")));
		 alphabet.put("!", new RShape(RG.loadShape("../data/alphabet/ausrufezeichen.svg")));
		 alphabet.put("?", new RShape(RG.loadShape("../data/alphabet/fragezeichen.svg")));
		 alphabet.put(",", new RShape(RG.loadShape("../data/alphabet/komma.svg")));
		 alphabet.put(".", new RShape(RG.loadShape("../data/alphabet/punkt.svg")));
		 alphabet.put("+", new RShape(RG.loadShape("../data/alphabet/plus.svg")));
		 alphabet.put("-", new RShape(RG.loadShape("../data/alphabet/minus.svg")));
//		

		 setupAnimals();
		 
//		 createMessage(messageList.get(msgPos));
	}

	public void draw() {
		background(0x1BBBE9);

		fill(0);
	    stroke(0);
	    if(debug){
	    for(int i=0; i<points.size(); i++){
	        ellipse(points.get(i).x, points.get(i).y,5,5);  
	      }
	    }
	    for (Iterator<AcAnimal> i = animals.iterator(); i.hasNext();) {
			AcAnimal ac =  i.next();
			ac.update();
			ac.draw();
		}
	    if(movingAnimals.isEmpty() && drawFinish==false && !waiting){
	    	drawFinish = true;
	    }
	    if(drawFinish && !waiting){
	    	waiting=true;
	    	nextTimer.schedule(new NextMsg(), 1000);
	    }
	}
	
	private void createMessage(String s){
		println("message create");
		displayMsg = msgToLines(s);
		createPoints(displayMsg);
		startDraw();
	}
	
	private void createPoints(ArrayList<String> msg){
		points.clear();
		int cnt = 0;
		for (Iterator<String> iterator = msg.iterator(); iterator.hasNext();) {
			String line = iterator.next();
			String[] c = line.split("");
			ArrayList<String> chars = new ArrayList<String>();
			Collections.addAll(chars, c); 
			chars.remove(0);
			ArrayList<Letter> messageLetters = new ArrayList<Letter>();
			println("line: "+cnt);
			for (Iterator<String> i = chars.iterator(); i.hasNext();) {
				String s = i.next();
				// letters are created
				messageLetters.add(new Letter(s));
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
					// line move y
					messageLetters.get(i).wY = (cnt)*lineHeight;
					// add all Points for positions
					points.addAll(Arrays.asList(messageLetters.get(i).getWorldPoints()));
				}
			}
			cnt++;
		}
				
		
		
//		points.addAll(Arrays.asList(alphabet.get(chars.get(0)).getPoints()));
//		for (int i = 1; i < chars.size(); i++) {
//			alphabet.get(chars.get(i)).wX = (int)alphabet.get(chars.get(i-1)).getBottomRightWorld().x+space;
//			RPoint[] tPoints = alphabet.get(chars.get(i)).getWorldPoints();
//			points.addAll(Arrays.asList(tPoints));
//		}
	}
	
	private void startDraw(){
		drawFinish = false;
		for (int i = 0; i < points.size(); i++) {
			if(i<animals.size()-1){
				animals.get(i).setTarget(Core.RPointToPVector(points.get(i)));
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
		println("nextMsg");
		waiting=false;
		if(gotoText){
			if(msgPos<messageList.size()-2){
					msgPos++;
				}else{
					msgPos=0;
				}
			createMessage(messageList.get(msgPos));
		}else{
			for (Iterator<AcAnimal> iterator = animals.iterator(); iterator.hasNext();) {
				AcAnimal a = iterator.next();
				a.setTarget(new PVector(random(width), random(height)));
			}
		}
		drawFinish=false;
		gotoText=!gotoText;
	}
	
	
	private void createLetters(){
		 Boolean[][] a = new Boolean[][]{ { false,true,true,true,true}, {true, false, false, true, false}, {true, false, false, true, false}, { false,true,true,true,true} }; 
		 Boolean[][] b = new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, true}, {true, false, true, false, true}, {false, true, false, true, false} }; 
		 Boolean[][] c = new Boolean[][]{ { false, true, true, true, false}, {true, false, false, false, true}, {true, false, false, false,true}, {false, true, false, true, false} }; 
		 Boolean[][] d = new Boolean[][]{ { true, true, true, true, true}, {true, false, false, false, true}, {true, false, false, false, true}, {false, true, true, true, false} }; 
		 Boolean[][] e = new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, true}, {true, false, true, false,true} }; 
		 Boolean[][] f = new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, false}, {true, false, true, false, false} }; 
		 Boolean[][] g = new Boolean[][]{ { true, true, true, true, true}, {true, false, false, false, true}, {true, false, true, false, true}, {true, false, true, true, true} }; 
		 Boolean[][] h = new Boolean[][]{ { true, true, true, true, true}, {false, false, true, false, false}, {false, false, true, false, false}, {true, true, true, true, true} }; 
		 Boolean[][] i = new Boolean[][]{ { true, false, false, false, true}, {true, true, true, true, true}, {true, false, false, false, true} }; 
		 Boolean[][] j = new Boolean[][]{ { false, false, false, true, false}, {true, false, false, false, true}, {true, true, true, true, false}, {true, false, false, false, false} }; 
		 Boolean[][] k = new Boolean[][]{ { true, true, true, true, true}, {false, false, true, false, false}, {false, true, false, true, false}, {true, false, false, false, true} }; 
		 Boolean[][] l = new Boolean[][]{ { true, true, true, true, true}, {false, false, false, false, true}, {false, false, false, false, true} }; 
		 Boolean[][] m = new Boolean[][]{ { true, true, true, true, true}, {false, true, false, false, false}, {false, false, true, false, false}, {false, true, false, false, false}, {true, true, true, true, true} }; 
		 Boolean[][] n = new Boolean[][]{ { true, true, true, true, true}, {false, true, false, false, false}, {false, false, true, false, false}, {false, false, false, true, false}, {true, true, true, true, true} }; 
		 Boolean[][] o = new Boolean[][]{ { false, true, true, true, false}, {true, false, false, false, true}, {true, false, false, false, true}, {false, true, true, true, false} }; 
		 Boolean[][] p = new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, false}, {true, false, true, false, false}, {false, true, false, false, false} }; 
		 Boolean[][] q = new Boolean[][]{ { false, true, true, false, false}, {true, false, false, true, false}, {false, true, true, false, true} }; 
		 Boolean[][] r = new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, false}, {true, false, true, true, false}, {false, true, false, false, true} }; 
		 Boolean[][] s = new Boolean[][]{ { true, true, true, false, true}, {true, false, true, false, true}, {true, false, true, true, true} }; 
		 Boolean[][] t = new Boolean[][]{ { true, false, false, false, false}, { true, false, false, false, false}, {true, true, true, true, true}, { true, false, false, false, false}, { true, false, false, false, false} }; 
		 Boolean[][] u = new Boolean[][]{ { true, true, true, true, true}, {false, false, false, false, true}, {true, true, true, true, true} }; 
		 Boolean[][] v = new Boolean[][]{ { true, true, true, true, false}, {false, false, false, false, true}, {true, true, true, true, false} }; 
		 Boolean[][] w = new Boolean[][]{ { true, true, true, true, false}, {false, false, false, false, true}, {false, false, true, true, false}, {false, false, false, false, true}, {true, true, true, true, false} }; 
		 Boolean[][] x = new Boolean[][]{ { true, true, false, true, true}, {false, false, true, false, false}, {false, false, true, false, false}, { true, true, false, true, true} }; 
		 Boolean[][] y = new Boolean[][]{ { true, true, true, false, false}, {false, false, false, true, true}, { true, true, true, false, false} }; 
		 Boolean[][] z = new Boolean[][]{ { true, false, false, true, true}, {true, false, true, false, true}, {true, true, false, false, true} }; 
		 Boolean[][] eins = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] zwei = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] drei = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] vier = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] fuenf = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] sechs = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] sieben = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] acht = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] neun = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] zehn = new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }; 
		 Boolean[][] plus = new Boolean[][]{ { false, false, true, false, false}, {false, true, true, true, false}, { false, false, true, false, false} }; 
		 Boolean[][] minus = new Boolean[][]{ { false, false, true, false, false}, { false, false, true, false, false}, { false, false, true, false, false} }; 
		 Boolean[][] rufzeichen = new Boolean[][]{ { true,true,true,false, true} }; 
		 Boolean[][] leerzeichen = new Boolean[][]{ { false, false, false, false, false} };
		 Boolean[][] fragezeichen = new Boolean[][]{ { true, false, false, false, false}, { true, false, true, false, true}, { true, true, true, false, false} };
		 Boolean[][] punkt = new Boolean[][]{ { false, false, false, false, true} }; 
		 Boolean[][] komma = new Boolean[][]{ { false, false, false, true, true} }; 

	}
	
	
	public void addMovingAnimal(AcAnimal a){
		movingAnimals.add(a);
	}
	public void removeMovingAnimal(AcAnimal a){
		movingAnimals.remove(a);
	}
	public static void main(String _args[]) {
		  PApplet.main(new String[] { "--present", Run.class.getName() });
	}
	
}
