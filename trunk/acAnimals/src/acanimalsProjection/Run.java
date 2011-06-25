package acanimalsProjection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;



import geomerative.RG;
import geomerative.RShape;
import processing.core.PApplet;
import processing.core.PVector;


public class Run extends PApplet {
	Boolean debug = true;
	
	ArrayList<PVector> points = new ArrayList<PVector>();
	float letterScale = 20f;
	float animalSize = 10f;
	float whiteSpaceWidth = 30f;
	float whiteSpaceHeight = 20f;
	float leftBorder = 20f;
	float topBorder = 20f;
	ArrayList<AcAnimal> animals = new ArrayList<AcAnimal>();
	ArrayList<AcAnimal> movingAnimals = new ArrayList<AcAnimal>();
	ArrayList<String> displayMsg = new ArrayList<String>();
	ArrayList<MLetter> currentWord = new ArrayList<MLetter>();
	int animalCnt = 200;
	Timer nextTimer = new Timer();
	Boolean gotoText = false;
	int msgPos = 0;
	
	HashMap<String, Boolean[][]> mAlphabet = new HashMap<String, Boolean[][]>();
	

	ArrayList<String> messageList = new ArrayList<String>();

	private boolean drawFinish = false;
	private boolean waiting = false;
	
	public void setup() {
		size(1280,720,OPENGL);
		
		Core.p5 = this;
		
		 RG.init(this);
		 
		 createLetters();
		 setupAnimals();
		 
		 messageList.add("som mer");
		 messageList.add("art+com");


		 
		 createMessage(messageList.get(0));
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
		displayMsg = msgToWords(s);
//		createPoints(s);
		createCurrentWord(displayMsg.get(0));
		createWordPoints();
		startDraw();
	}

	private void createCurrentWord(String word){
		currentWord.clear();
		ArrayList<String> chars = new ArrayList<String>();
		Collections.addAll(chars, word.split("")); 
		chars.remove(0);
		for (int i = 0; i < chars.size(); i++) {
			currentWord.add(new MLetter(mAlphabet.get(chars.get(i))));
		}
		for (int j = 0; j < currentWord.size(); j++) {
			if(j>0){
				currentWord.get(j).createDisplayPoints(leftBorder+whiteSpaceWidth+currentWord.get(j-1).rightDisplayPoint.x,topBorder);
			}else{
				currentWord.get(j).createDisplayPoints(leftBorder,topBorder);
			}
		}
	}
	
	
	private void createWordPoints(){
		points.clear();
		for (Iterator<MLetter> iterator = currentWord.iterator(); iterator.hasNext();) {
			MLetter letter = iterator.next();
			points.addAll(letter.getDisplayPoints());
		}
	}
	
	private void startDraw(){
		drawFinish = false;
		for (int i = 0; i < points.size(); i++) {
			if(i<animals.size()-1){
				animals.get(i).setTarget(points.get(i));
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
	
	private ArrayList<String> msgToWords(String msg){
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(msg.split(" ")));
		return words;
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
		
		println("nextMsg / gotoText: "+gotoText);
		waiting=false;
		if(displayMsg.size()>0){
			displayMsg.remove(0);	
		}
		if(displayMsg.size()>0){
			createCurrentWord(displayMsg.get(0));
			createWordPoints();
			startDraw();
		}else{
			if(gotoText){
				gotoText=false;
				println(msgPos);
				if(msgPos<messageList.size()-1){
					msgPos++;
				}else{
					msgPos=0;
				}
				createMessage(messageList.get(msgPos));
			}else{
				for (Iterator<AcAnimal> iterator = animals.iterator(); iterator.hasNext();) {
					AcAnimal a = iterator.next();
					a.setTarget(new PVector(random(width), height));
				}
				gotoText=true;
		}
		}
		drawFinish=false;
		
		
		/*
		 * 	alte version
		 * 	
		 */
//		if(gotoText){
//			if(msgPos<messageList.size()-2){
//					msgPos++;
//				}else{
//					msgPos=0;
//				}
//			createMessage(messageList.get(msgPos));
//		}else{
//			for (Iterator<AcAnimal> iterator = animals.iterator(); iterator.hasNext();) {
//				AcAnimal a = iterator.next();
//				a.setTarget(new PVector(random(width), random(height)));
//			}
//		}
//		drawFinish=false;
//		gotoText=!gotoText;
	}
	
	
	private void createLetters(){
		mAlphabet.put("a", new Boolean[][]{ { false,true,true,true,true}, {true, false, false, true, false}, {true, false, false, true, false}, { false,true,true,true,true} }); 
		mAlphabet.put("b", new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, true}, {true, false, true, false, true}, {false, true, false, true, false} }); 
		mAlphabet.put("c", new Boolean[][]{ { false, true, true, true, false}, {true, false, false, false, true}, {true, false, false, false,true}, {false, true, false, true, false} }); 
		mAlphabet.put("d", new Boolean[][]{ { true, true, true, true, true}, {true, false, false, false, true}, {true, false, false, false, true}, {false, true, true, true, false} }); 
		mAlphabet.put("e", new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, true}, {true, false, true, false,true} }); 
		mAlphabet.put("f", new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, false}, {true, false, true, false, false} }); 
		mAlphabet.put("g", new Boolean[][]{ { true, true, true, true, true}, {true, false, false, false, true}, {true, false, true, false, true}, {true, false, true, true, true} }); 
		mAlphabet.put("h", new Boolean[][]{ { true, true, true, true, true}, {false, false, true, false, false}, {false, false, true, false, false}, {true, true, true, true, true} }); 
		mAlphabet.put("i", new Boolean[][]{ { true, false, false, false, true}, {true, true, true, true, true}, {true, false, false, false, true} }); 
		mAlphabet.put("j", new Boolean[][]{ { false, false, false, true, false}, {true, false, false, false, true}, {true, true, true, true, false}, {true, false, false, false, false} }); 
		mAlphabet.put("k", new Boolean[][]{ { true, true, true, true, true}, {false, false, true, false, false}, {false, true, false, true, false}, {true, false, false, false, true} }); 
		mAlphabet.put("l", new Boolean[][]{ { true, true, true, true, true}, {false, false, false, false, true}, {false, false, false, false, true} }); 
		mAlphabet.put("m", new Boolean[][]{ { true, true, true, true, true}, {false, true, false, false, false}, {false, false, true, false, false}, {false, true, false, false, false}, {true, true, true, true, true} }); 
		mAlphabet.put("n", new Boolean[][]{ { true, true, true, true, true}, {false, true, false, false, false}, {false, false, true, false, false}, {false, false, false, true, false}, {true, true, true, true, true} }); 
		mAlphabet.put("o", new Boolean[][]{ { false, true, true, true, false}, {true, false, false, false, true}, {true, false, false, false, true}, {false, true, true, true, false} }); 
		mAlphabet.put("p", new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, false}, {true, false, true, false, false}, {false, true, false, false, false} }); 
		mAlphabet.put("q", new Boolean[][]{ { false, true, true, false, false}, {true, false, false, true, false}, {false, true, true, false, true} }); 
		mAlphabet.put("r", new Boolean[][]{ { true, true, true, true, true}, {true, false, true, false, false}, {true, false, true, true, false}, {false, true, false, false, true} }); 
		mAlphabet.put("s", new Boolean[][]{ { true, true, true, false, true}, {true, false, true, false, true}, {true, false, true, true, true} }); 
		mAlphabet.put("t", new Boolean[][]{ { true, false, false, false, false}, { true, false, false, false, false}, {true, true, true, true, true}, { true, false, false, false, false}, { true, false, false, false, false} }); 
		mAlphabet.put("u", new Boolean[][]{ { true, true, true, true, true}, {false, false, false, false, true}, {true, true, true, true, true} }); 
		mAlphabet.put("v", new Boolean[][]{ { true, true, true, true, false}, {false, false, false, false, true}, {true, true, true, true, false} }); 
		mAlphabet.put("w", new Boolean[][]{ { true, true, true, true, false}, {false, false, false, false, true}, {false, false, true, true, false}, {false, false, false, false, true}, {true, true, true, true, false} }); 
		mAlphabet.put("x", new Boolean[][]{ { true, true, false, true, true}, {false, false, true, false, false}, {false, false, true, false, false}, { true, true, false, true, true} }); 
		mAlphabet.put("y", new Boolean[][]{ { true, true, true, false, false}, {false, false, false, true, true}, { true, true, true, false, false} }); 
		mAlphabet.put("z", new Boolean[][]{ { true, false, false, true, true}, {true, false, true, false, true}, {true, true, false, false, true} }); 
		mAlphabet.put("1", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("2", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("3", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("4", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("5", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("6", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("7", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("8", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("9", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("0", new Boolean[][]{ { true,true,true,true,true}, {true, false, false, true,true}, {true, true, true, true,true}, {true, false, false, true,true}, {true, false, false, true,true} }); 
		mAlphabet.put("+", new Boolean[][]{ { false, false, true, false, false}, {false, true, true, true, false}, { false, false, true, false, false} }); 
		mAlphabet.put("-", new Boolean[][]{ { false, false, true, false, false}, { false, false, true, false, false}, { false, false, true, false, false} }); 
		mAlphabet.put("!", new Boolean[][]{ { true,true,true,false, true} }); 
		mAlphabet.put(" ", new Boolean[][]{ { false, false, false, false, false} });
		mAlphabet.put("?", new Boolean[][]{ { true, false, false, false, false}, { true, false, true, false, true}, { true, true, true, false, false} });
		mAlphabet.put(".", new Boolean[][]{ { false, false, false, false, true} }); 
		mAlphabet.put(",", new Boolean[][]{ { false, false, false, true, true} });
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
