package
{
	public class shake extends MovieClip
	{
		import flash.display.MovieClip;
		var dir:int = 1;
     
    	var myTimer:Timer = new Timer(2000, 1);
    	myTimer.addEventListener(TimerEvent.TIMER, stopShake);
    	myTimer.start();
     
    	deinAuto.addEventListener(Event.ENTER_FRAME, shake);
     
    	function shake(evt:Event):void 
		{
        	dir = -dir;
        	evt.currentTarget.x += dir;
        	evt.currentTarget.y += dir;
    	}
     
    	function stopShake(evt:TimerEvent):void 
		{
        	deinAuto.removeEventListener(Event.ENTER_FRAME, shake);
    	} 
	}
}