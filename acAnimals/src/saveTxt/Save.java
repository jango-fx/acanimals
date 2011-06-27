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
	
	PApplet p5;
	
	public Save(PApplet parent, String pre){
		p5 = parent;
		prefix=pre;
		
	}
	public void initSaver(){
		String[] msgs = p5.loadStrings(prefix+"_messages.txt");
		String[] anims = p5.loadStrings(prefix+"_animals.txt");
		
		for (int i = 0; i < msgs.length; i++) {
			 try{
					byte[] e=msgs[i].getBytes();
					String v=new String(e,"utf-8");
					byte[] f=v.getBytes("iso-8859-2");
					String w=new String(f);
					msgs[i]=w;
				    }
				    catch (Exception e){
				     //println("error");
				   }
		}
		msgTxt = p5.createWriter(prefix+"_messages.txt");
		for (int i = 0; i < msgs.length; i++) {
			msgTxt.println(msgs[i]);
		}
		msgTxt.flush();
		
		animalTxt = p5.createWriter(prefix+"_animals.txt");
		for (int i = 0; i < anims.length; i++) {
			msgTxt.println(anims[i]);
		}
		animalTxt.flush();
	}
	
	public void addAnimal(int t1, float x1, float y1, int r1, int t2, float x2, float y2, int r2){
		animalTxt.println(t1 +"\t"+ x1 +"\t"+ y1+"\t"+r1+"\t"+t2+"\t"+x2+"\t"+y2+"\t"+r2);
		animalTxt.flush();
	}
	
	public void addMessage(String msg){
		  msgTxt.println(msg);
		  msgTxt.flush();		
	}
	
	public ArrayList<String> getMessages(){
		ArrayList<String> msgs = new ArrayList<String>();
		Collections.addAll(msgs, p5.loadStrings(prefix+"_messages.txt"));
		return msgs;
	}
}
