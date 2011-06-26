package
{
		import flash.display.MovieClip;
			
			
	public class Main extends MovieClip
	{
     	
		public function Main()
		{
			//This function is responsible for animating the shake
			function shakeRectangles (e:Event):void 
			{
 
				//Loop through the array
				for (var i = 0; i < 20; i++) 
				{
 
				//Rotate the rectangle a random amount  (from -4 to 4) 
				rectangles[i].rotation += Math.random() * 8 - 4;

				}
			}
		}
	}
}
