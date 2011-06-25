package buffetKinect;

import processing.opengl.*;
import org.openkinect.*;
import org.openkinect.processing.*;
import processing.core.*;

import javax.media.opengl.*;

public class KinectFX
{
	PApplet app;
	Kinect kinect;
	float[] depthLookUp = new float[2048];

	float depthMin = 800;
	float depthMax = 950;

	float xOffset = 600;
	float yOffset = 500;
	float factor = 800;

	KinectFX(PApplet a)
	{
		app = a;
		kinect = new Kinect(app);
		kinect.start();
		kinect.enableDepth(true);
		kinect.processDepthImage(false); // Effiezienzsteigerung

		for (int i = 0; i < depthLookUp.length; i++)
		{
			depthLookUp[i] = rawDepthToMeters(i);
		}
	}

	void draw()
	{
		Core.p5.pushMatrix();
		int[] depth = kinect.getRawDepth();
		int skip = 4;
		for (int x = 0; x < 640; x += skip)
		{
			for (int y = 0; y < 480; y += skip)
			{
				int offset = x + y * 640;

				// Convert kinect data to world xyz coordinate
				int rawDepth = depth[offset];
				// Core.p5.println(depthMin);
				Core.p5.stroke(0, 0, 200);

				if (rawDepth > depthMin && rawDepth < depthMax)
				{
					Core.p5.stroke(0, 200, 200);
				}
				PVector v = depthToWorld(640 - x, y, rawDepth);

				Core.p5.pushMatrix();
				// Scale up by 200
				Core.p5.translate(xOffset + (v.x * factor), yOffset
						+ (v.y * factor), factor - v.z * factor);
				// Draw a point
				// Core.p5.point(0, 0);
				Core.p5.ellipse(0, 0, 5, 5);
				Core.p5.popMatrix();
			}
		}
		Core.p5.popMatrix();
	}

	// These functions come from:
	// http://graphics.stanford.edu/~mdfisher/Kinect.html
	float rawDepthToMeters(int depthValue)
	{
		if (depthValue < 2047)
		{
			return (float) (1.0 / ((double) (depthValue) * -0.0030711016 + 3.3309495161));
		}
		return 0.0f;
	}

	PVector depthToWorld(int x, int y, int depthValue)
	{

		final double fx_d = 1.0 / 5.9421434211923247e+02;
		final double fy_d = 1.0 / 5.9104053696870778e+02;
		final double cx_d = 3.3930780975300314e+02;
		final double cy_d = 2.4273913761751615e+02;

		PVector result = new PVector();
		double depth = depthLookUp[depthValue];// rawDepthToMeters(depthValue);
		result.x = (float) ((x - cx_d) * depth * fx_d);
		result.y = (float) ((y - cy_d) * depth * fy_d);
		result.z = (float) (depth);
		return result;
	}

	// void stop() {
	// kinect.quit();
	// app.super.stop();
	// }
}
