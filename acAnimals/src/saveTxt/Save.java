package saveTxt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import processing.core.PApplet;

public class Save {
	PrintWriter msgTxt;
	PrintWriter animalTxt;
	String prefix;
	String animalFile;
	String messageFile;
	
	PApplet p5;
	
	public Save(PApplet parent, String pre){
		p5 = parent;
		prefix=pre;
		animalFile = (prefix+"_animals.txt");
		messageFile = (prefix+"_messages.txt");
	}
	public void initSaver(){
		String[] msgs = p5.loadStrings(messageFile);
		String[] anims = p5.loadStrings(animalFile);
		
		msgTxt = p5.createWriter(messageFile);
		for (int i = 0; i < msgs.length; i++) {
			msgTxt.println(msgs[i]);
		}
		msgTxt.flush();
		
		animalTxt = p5.createWriter(animalFile);
		for (int i = 0; i < anims.length; i++) {
			animalTxt.println(anims[i]);
		}
		animalTxt.flush();
	}
	
	public void addAnimal(int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2, int aT1, float a1x, float a1y, int aT2, float a2x, float a2y){
		animalTxt.println(t1 +"\t"+ x1 +"\t"+ y1+"\t"+r1+"\t"+t2+"\t"+x2+"\t"+y2+"\t"+r2+"\t"+aT1+"\t"+a1x+"\t"+a1y+"\t"+aT2+"\t"+a2x+"\t"+a2y);
		animalTxt.flush();
	}
	
	public void addMessage(String msg){
		  msgTxt.println(msg);
		  msgTxt.flush();		
	}
	
	public ArrayList<String> getMessages(){
		ArrayList<String> msgs = new ArrayList<String>();
		Collections.addAll(msgs, p5.loadStrings(messageFile));
		return msgs;
	}
	
	public String[] getAnimalData(){
		PApplet.println(p5.loadStrings(animalFile));
		return p5.loadStrings(animalFile);
	}
	
}