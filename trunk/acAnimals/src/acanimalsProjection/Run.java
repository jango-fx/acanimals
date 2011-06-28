package acanimalsProjection;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;

import oscP5.OscMessage;

import animalosc.AnimalOsc;

import monster.Monster;


import processing.core.PApplet;
import processing.core.PVector;
import saveTxt.Save;


public class Run extends PApplet {
	
	AnimalOsc osc;
	Boolean debug = false;
	
	ArrayList<PVector> points = new ArrayList<PVector>();
	float letterScale = 35f;
	float animalSize = 0.37f;
	float whiteSpaceWidth = 60f;
	float whiteSpaceHeight = 20f;
	float leftBorder = 20f;
	float topBorder = 50f;
	ArrayList<AcAnimal> animals = new ArrayList<AcAnimal>();
	ArrayList<AcAnimal> newanimals = new ArrayList<AcAnimal>();
	ArrayList<AcAnimal> movingAnimals = new ArrayList<AcAnimal>();
	ArrayList<String> displayMsg = new ArrayList<String>();
	ArrayList<MLetter> currentWord = new ArrayList<MLetter>();
	int animalCnt = 300;
	Timer nextTimer = new Timer();
	Boolean gotoText = false;
	int msgPos = 0;
	
	HashMap<String, Boolean[][]> mAlphabet = new HashMap<String, Boolean[][]>();

	 Save saver = new Save(this, "proj");
	
	ArrayList<String> messageList = new ArrayList<String>();
	ArrayList<String> newMessageList = new ArrayList<String>();

	private boolean drawFinish = false;
	private boolean waiting = false;
	
	public void setup() {
		size(1920,1200,OPENGL);
		
		Core.p5 = this;
		
		osc = new AnimalOsc(this, 12000);
		
		saver.initSaver();
		
	//	 RG.init(this);
		 
		 createLetters();
		 setupAnimals();
		 
		 
		 //messageList.add("sommerfest test");
		 //messageList.add("und das ist auch ein fest");
		 messageList.addAll(saver.getMessages());
		 
		 createMessage(messageList.get(0));
		 
		 Monster.f=animalSize;
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
	    animals.addAll(newanimals);
	    newanimals.clear();
	    messageList.addAll(newMessageList);
	    newMessageList.clear();
	    for (Iterator<AcAnimal> i = animals.iterator(); i.hasNext();) {
			AcAnimal ac =  i.next();
			ac.update();
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
		for (Iterator<AcAnimal> iterator = animals.iterator(); iterator.hasNext();) {
			AcAnimal a = iterator.next();
			if(!a.isMoving()){
				a.setTarget(new PVector(random(width),400));
			}
			
		}
	}
	private void setupAnimals(){
//		for (int i = 0; i < animalCnt; i++) {
//			//animals.add(new AcAnimal(Core.p5, 0, random(50, width-50), random(50,height-50), (int)random(3)*90, 0, 71, random(-14,14), (int)random(3)*90));
//		}
		println("animalSetup");
		String[] animalData = saver.getAnimalData();
		println(animalData);
		for (int i = 0; i < animalData.length; i++) {
			String[] singleAnimal = animalData[i].split("/t");
			println(singleAnimal.length);
			 int t1 = Integer.parseInt(singleAnimal[0]);
	    	 float x1 = Float.valueOf(singleAnimal[1]).floatValue();
	    	 float y1 = Float.valueOf(singleAnimal[2]).floatValue();
	    	 int r1 = Integer.parseInt(singleAnimal[3]);
	    	 int t2 = Integer.parseInt(singleAnimal[4]);
	    	 float x2 = Float.valueOf(singleAnimal[5]).floatValue();
	    	 float y2 = Float.valueOf(singleAnimal[6]).floatValue();
	    	 int r2 = Integer.parseInt(singleAnimal[7]);
	    	 int aT1 = Integer.parseInt(singleAnimal[8]);
	    	float a1x = Float.valueOf(singleAnimal[9]).floatValue();
	    	float a1y = Float.valueOf(singleAnimal[10]).floatValue();
	    	int aT2 = Integer.parseInt(singleAnimal[11]);
	    	float a2x =  Float.valueOf(singleAnimal[12]).floatValue();
	    	float a2y = Float.valueOf(singleAnimal[13]).floatValue();
	    	animals.add(new AcAnimal(this, t1, x1, y1, r1, t2, x2, y2, r2, aT1, a1x, a1y, aT2, a2x, a2y));
			//animals.add(new AcAnimal(this, singleAnimal[0], singleAnimal[1], singleAnimal[2], singleAnimal[3], singleAnimal[4], singleAnimal[5], singleAnimal[6], singleAnimal[6], singleAnimal[7], singleAnimal[8], singleAnimal[9], singleAnimal[10], singleAnimal[11], singleAnimal[12], ));
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
		mAlphabet.put("Š", new Boolean[][]{ { false, true, true, true, true}, {true, true, false, true, false}, {true, true, false, true, false},{false, true, true, true, true} });
		mAlphabet.put("Ÿ", new Boolean[][]{ { true, false, true, true, true},{false, false, false, false, true}, { true, false, true, true, true}});
		mAlphabet.put("š", new Boolean[][]{{ true, false, true, true, true},{false, false, true, false, true}, { true, false, true, true, true}});
		mAlphabet.put("/", new Boolean[][]{{ false, false, false, false, true}, {false, false, false, true, false}, {false, false, true, false, false},	{false, true, false, false, false}, {true, false, false, false, false}});
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
	
	public void oscEvent(OscMessage theOscMessage) {
		  
		  if(theOscMessage.checkAddrPattern("/gruss")==true) {
		    /* check if the typetag is the right one. */
			  newMessageList.add(theOscMessage.get(0).stringValue());
			  println(theOscMessage.get(0).stringValue());
//			  try{
//				  newMessageList.add(URLDecoder.decode(theOscMessage.get(0).stringValue(),"UTF-8"));
//			  }catch(UnsupportedEncodingException er){
//				  System.out.println(er);
//			  }

			  saver.addMessage(theOscMessage.get(0).stringValue());

			  //msgTxt.close();
		      return;
		    }else if(theOscMessage.checkAddrPattern("/animal")==true){
		    	println(theOscMessage.arguments());
		    	 int t1 = Integer.parseInt(theOscMessage.get(0).stringValue());
		    	 float x1 = Float.valueOf(theOscMessage.get(1).stringValue()).floatValue();
		    	 float y1 = Float.valueOf(theOscMessage.get(2).stringValue()).floatValue();
		    	 int r1 = Integer.parseInt(theOscMessage.get(3).stringValue());
		    	 int t2 = Integer.parseInt(theOscMessage.get(4).stringValue());
		    	 float x2 = Float.valueOf(theOscMessage.get(5).stringValue()).floatValue();
		    	 float y2 = Float.valueOf(theOscMessage.get(6).stringValue()).floatValue();
		    	 int r2 = Integer.parseInt(theOscMessage.get(7).stringValue());
		    	 int aT1 = Integer.parseInt(theOscMessage.get(8).stringValue());
		    	float a1x = Float.valueOf(theOscMessage.get(9).stringValue()).floatValue();
		    	float a1y = Float.valueOf(theOscMessage.get(10).stringValue()).floatValue();
		    	int aT2 = Integer.parseInt(theOscMessage.get(11).stringValue());
		    	float a2x =  Float.valueOf(theOscMessage.get(12).stringValue()).floatValue();
		    	float a2y = Float.valueOf(theOscMessage.get(13).stringValue()).floatValue();
		    	 newanimals.add(new AcAnimal(this, t1, x1, y1, r1, t2, x2, y2, r2, aT1, a1x, a1y, aT2, a2x, a2y));
		    	 saver.addAnimal(t1, x1, y1, r1, t2, x2, y2, r2, aT1, a1x, a1y, aT2, a2x, a2y);
		      return;
		    } 
		  println("### received an osc message. with address pattern "+theOscMessage.addrPattern());
		}
	
}
