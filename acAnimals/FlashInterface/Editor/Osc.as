﻿package {	import flash.net.XMLSocket;	import org.fwiidom.osc.OSCConnection; 	import org.fwiidom.osc.OSCPacket; 	import flash.events.MouseEvent;	public class Osc	{		private var n_osc:OSCConnection; 		private var n_port:Number = 3000;		private var r_port:Number = 12000;		private var n_host:String = 'localhost';						public function Osc()		{			n_osc = new OSCConnection(n_host, n_port);			n_osc.connect();			/*osc = new OSCConnection(null, 3334);			var msg:OSCMessage = new OSCMessage("/test");			msg.addArg(10);			msg.addArg("nummer");			osc.sendOSCPacket(*/		}				public function sendMsg(t1:Number, t1x:Number, t1y:Number, t1r:Number, t2:Number, t2x:Number, t2y:Number, t2r:Number, masg:String):void{			var msgArray:Array = new Array(masg);			trace(t1+" "+t1x+" "+t1y+" "+t1r+" "+t2+" "+t2x+" "+t2y+" "+t2r);			var animal:OSCPacket = new OSCPacket("/animal",[t1, t1x, t1y, t1r, t2, t2x, t2y, t2r], n_host, r_port);			n_osc.sendOSCPacket(animal);			var msg:OSCPacket = new OSCPacket("/gruss",msgArray,n_host, r_port);			n_osc.sendOSCPacket(msg);			}	}}