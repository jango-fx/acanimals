﻿package {	import flash.display.MovieClip;	import flash.events.*;	import flash.globalization.Collator;	import flash.ui.MouseCursor;	import flash.utils.Timer;	import com.greensock.TweenMax;    import flash.system.fscommand;	public class Main extends MovieClip	{		public var auswahlteil:Drag;		public var auswahlzahl:Number = 0;		public var auswahlListe:Collection;		public var augenListe:Collection;		var overlayTimer:Timer;				var osc:Osc;				public function Main()		{			fscommand("fullscreen", "true");            fscommand("allowscale", "false");						osc = new Osc();						setupMessageInput();			overlayTimer = new Timer(4000, 1);			overlayTimer.addEventListener(TimerEvent.TIMER, showOverlay);						overlay.mouseEnabled = false;			overlay.mouseChildren = false;						enjoy.visible = false;			enjoy.mouseEnabled = false;			enjoy.mouseChildren = false;			enjoy.alpha=0;						sendbtn.alpha=0.3;			scribble.mouseEnabled = false;			scribble.mouseChildren = false;			//scribble.visible=false;			//overlay.mouseEnabled = false;			//overlay.MouseCursor = false;			//overlay.visible = false;			auswahlListe = new Collection();			augenListe = new Collection();			t1.addEventListener(AuswahlEvent.ON_AUSWAHL_HIT, neueAuswahl);			t1.addEventListener(AuswahlEvent.ON_AUSWAHL_REMOVE, removeAuswahl);			t2.addEventListener(AuswahlEvent.ON_AUSWAHL_HIT, neueAuswahl);			t2.addEventListener(AuswahlEvent.ON_AUSWAHL_REMOVE, removeAuswahl);			t3.addEventListener(AuswahlEvent.ON_AUSWAHL_HIT, neueAuswahl);			t3.addEventListener(AuswahlEvent.ON_AUSWAHL_REMOVE, removeAuswahl);			t4.addEventListener(AuswahlEvent.ON_AUSWAHL_HIT, neueAuswahl);			t4.addEventListener(AuswahlEvent.ON_AUSWAHL_REMOVE, removeAuswahl);			a1.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a1.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a2.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a2.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a3.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a3.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a4.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a4.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a5.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a5.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a6.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a6.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a7.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a7.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a8.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a8.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a9.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a9.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a10.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a10.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a11.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a11.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a12.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a12.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a13.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a13.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a14.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a14.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a15.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a15.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			a16.addEventListener(AugenEvent.ON_AUGEN_HIT, neueAugen);			a16.addEventListener(AugenEvent.ON_AUGEN_REMOVE, removeAugen);			stage.addEventListener(MouseEvent.MOUSE_MOVE, hideOverlay);			for (var i:Number=1; i<=16; i++)			{				this["a" + i].alpha = 0.2;			}		}		function neueAugen(e:Event):void		{			if (augenListe.numItems >= 2)			{				if (! augenListe.contains(e.target))				{					(e.target as Auge).returnToOriginalPosition();				}			}			else			{				if (! augenListe.contains(e.target))				{					augenListe.addItems(e.target);				}			}		}		function removeAugen(e:Event):void		{			augenListe.removeItems(e.target);		}		function neueAuswahl(e:Event)		{			if (auswahlListe.numItems >= 2)			{				if (! auswahlListe.contains(e.target))				{					(e.target as Drag).returnToOriginalPosition();				}				else				{					snap();				}			}			else			{				if (! auswahlListe.contains(e.target))				{					auswahlListe.addItems(e.target);				}				if (auswahlListe.numItems == 2)				{					snap();					activateAugen();				}			}		}		function activateAugen():void		{			for (var i:Number=1; i<=16; i++)			{				var auge = this["a" + i];				auge.alpha = 1;				auge.buttonMode = true;			}		}		function deactivateAugen():void		{			for (var i:Number=1; i<=16; i++)			{				var auge = this["a" + i];				auge.alpha = 0.2;				auge.buttonMode = false;			}		}		function removeAuswahl(e:Event)		{			this.setChildIndex(scribble,numChildren-1);			auswahlListe.removeItems(e.target);			//if(auswahlListe.indexOf(e.target)){;			//auswahlListe.push(e.target);			//  }		}		function snap():void		{			var st1:Drag = auswahlListe._collection[0];			var st2:Drag = auswahlListe._collection[1];			var xmove:Number = st1.x - st2.x;			var ymove:Number = st1.y - st2.y;			var w:Number = winkel(st1.x,st1.y,st2.x,st2.y);			if(xmove>st1.width || ymove>st1.height || xmove<(st1.width)*-1 || ymove<(st1.height)*-1){			if (w > -45 && w < 45)			{				st2.x +=  xmove + st1.width / 2 + st2.width / 2;				if (st2.y < st1.y - st1.height)				{					st2.y = st1.y - st1.height;				}				else if (st2.y>st1.y+st1.height)				{					st2.y = st1.y + st1.height;				}			}			else if (w>45 && w<135)			{				st2.y +=  ymove + st1.height / 2 + st2.height / 2;				if (st2.x < st1.x - st1.width)				{					st2.x = st1.x - st1.width;				}				else if (st2.x>st1.x+st1.width)				{					st2.x = st1.x + st1.width;				}			}			else if (w>135 || w<-135)			{				st2.x +=  xmove - st1.width / 2 - st2.width / 2;				if (st2.y < st1.y - st1.height)				{					st2.y = st1.y - st1.height;				}				else if (st2.y>st1.y+st1.height)				{					st2.y = st1.y + st1.height;				}			}			else			{				st2.y +=  ymove - st1.height / 2 - st2.height / 2;				if (st2.x < st1.x - st1.width)				{					st2.x = st1.x - st1.width;				}				else if (st2.x>st1.x+st1.width)				{					st2.x = st1.x + st1.width;				}			}			}		}		//a = atan ( (y2 - y1) / (x2 - x1) )		//winkel = Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI; -damit es von bogenmaß in grad umgerechnet wird		public function winkel(x1, y1, x2, y2):Number		{			//return Math.atan ( (y2 - y1) / (x2 - x1) )+Math.PI/2;			return Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI;		}		public function hideOverlay(e:Event):void		{			overlayTimer.reset();			TweenMax.to(overlay, 0.3, {autoAlpha:0});						 //.visible = false;			overlayTimer.start();		}		public function showOverlay(event:TimerEvent):void			{				setChildIndex(overlay, this.numChildren-1);				TweenMax.to(overlay, 0.3, {autoAlpha:1});			}					private function setupMessageInput():void{			messageField.maxChars = 40;			messageField.restrict = "a-z 0-9/:!?+.,"			messageField.addEventListener(TextEvent.TEXT_INPUT, activateSend);			}		public function activateSend(e:TextEvent):void{			overlayTimer.reset();			TweenMax.to(overlay, 0.3, {autoAlpha:0});			overlayTimer.start();			if(messageField.text!=""){					TweenMax.to(sendbtn,0.5,{alpha:1});					sendbtn.buttonMode = true;					sendbtn.addEventListener(MouseEvent.CLICK, transmit);				}		}		              public function deactivateSend():void{                                        TweenMax.to(sendbtn,0.5,{alpha:0.3});                                        sendbtn.buttonMode = false;                                        sendbtn.removeEventListener(MouseEvent.CLICK, transmit);                }		function transmit(e:Event):void{			var t1:MovieClip = (auswahlListe._collection[0] as MovieClip);			var t2:MovieClip = (auswahlListe._collection[1] as MovieClip);			var a1:MovieClip = (augenListe._collection[0] as MovieClip);			// hier kommt die nummer fuer das leere auge rein			var aT2:Number=17;			var a2x:Number=0;			var a2y:Number=0;			if(augenListe._collection.length>1){				var a2:MovieClip = (augenListe._collection[1] as MovieClip);				aT2 = Number(a2.name.substr(1,a2.name.length-1));				a2x = Math.round((a2.x-t1.x))/t1.width;				a2y = Math.round((a2.y-t1.y))/t1.height;			}			var t1name:Number = Number(t1.name.substr(1,1))-1;			var t1r:Number = t1.rotation;						var t2name:Number = Number(t2.name.substr(1,1))-1;			var t2x:Number = Math.round((t2.x-t1.x))/t1.width;			var t2y:Number = Math.round((t2.y-t1.y))/t1.height;			var t2r:Number = t2.rotation;						var aT1:Number = Number(a1.name.substr(1,a1.name.length-1));			trace(a1.name.substr(1,a1.name.length-1));			trace(a1.name);			var a1x:Number = Math.round((a1.x-t1.x))/t1.width;			var a1y:Number = Math.round((a1.y-t1.y))/t1.height;									//var msg:String = messageField.text.substring(0,messageField.text.length);			var msg:String = messageField.text;			//trace(msg);						//trace("t1name: "+t1name+" t1r: "+t1r+" t2name: "+t2name+" t2x: "+t2x+" t2y: "+t2y+" t2r: "+t2r+" aT1: "+aT1+" a1x: "+a1x+" a1y: "+a1y+" aT2: "+aT2+" a2x: "+a2x+" a2y:"+a2y);						osc.sendMsg( t1name, 0, 0, t1r, t2name, t2x, t2y, t2r, aT1, a1x, a1y, aT2, a2x, a2y, msg);			resetAll();			}						function resetAll():void{								var aLen:int = auswahlListe._collection.length;				for(var i:Number = 0; i<aLen; i++){					auswahlListe._collection[0].returnToOriginalPosition();				}								var agLen:int = augenListe._collection.length;				for(var j:Number = 0; j<agLen; j++){					augenListe._collection[0].returnToOriginalPosition();				}			auswahlListe = new Collection();			augenListe = new Collection();			messageField.text = "";			deactivateAugen();			deactivateSend();						TweenMax.to(enjoy, 0.5, {autoAlpha:1, delay:0.2, onComplete:hideEnjoy});			}						function hideEnjoy():void{				TweenMax.to(enjoy, 0.5, {autoAlpha:0, delay:2});			}	}}