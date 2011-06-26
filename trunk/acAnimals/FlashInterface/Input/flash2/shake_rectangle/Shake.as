//This array will hold all the rectangles on the stage
var rectangles:Array = new Array();
 
//In this loop, we'll create 20 rectangles
for (var i = 0; i < 20; i++) {
 
	//Create one rectangle
	var rectangle:Rectangle = new Rectangle();
 
	//Assign a random scale for the rectangle (scaleX and scaleY are the same)
	rectangle.scaleX = Math.random();
	rectangle.scaleY = rectangle.scaleX;
 
	//Assign a random position
	rectangle.x = Math.random() * stage.stageWidth;
	rectangle.y = Math.random() * stage.stageHeight;
 
	//Add the rectangle on to the stage
	addChild (rectangle);
 
	//Add the rectangle to the array
	rectangles.push (rectangle);
}
 
//The timer will call the "shakeRectangles" function every 0.02 seconds
var timer:Timer = new Timer(20, 100000000);
timer.addEventListener (TimerEvent.TIMER, shakeRectangles);
timer.start ();
 
//This function is responsible for animating the shake
function shakeRectangles (e:Event):void {
 
	//Loop through the array
	for (var i = 0; i < 20; i++) {
 
		//Rotate the rectangle a random amount  (from -4 to 4) 
		rectangles[i].rotation += Math.random() * 8 - 4;
 
		//Assign a new random position
		rectangles[i].x += Math.random() * 8 - 4;
		rectangles[i].y += Math.random() * 8 - 4;
	}
}