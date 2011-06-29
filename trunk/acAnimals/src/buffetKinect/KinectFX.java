package buffetKinect;

import java.util.ArrayList;
import java.util.Iterator;

import org.openkinect.processing.*;
import processing.core.*;

public class KinectFX
{
	PApplet app;
	Kinect kinect;
	float[] depthLookUp = new float[2048];
	int skip = 4;
	
	ArrayList<PVector> teller;
	

	float depthMin = 800.52f;
	float depthMax = 824.07f;

	float xOffset = 0.00f;
	float yOffset = 0.00f;
	float factor = 2.50f;

	KinectFX(PApplet a)
	{
		app = a;
		kinect = new Kinect(app);
		kinect.start();
		kinect.enableDepth(true);
		kinect.processDepthImage(false); // Performance-Steigerung

		for (int i = 0; i < depthLookUp.length; i++)
		{
			depthLookUp[i] = rawDepthToMeters(i);
		}
	}

	void draw()
	{

		Iterator<PVector> iterator = teller.iterator();

		while (iterator.hasNext())
		{
			PVector punkt = iterator.next();

			Core.p5.pushMatrix();
			Core.p5.translate(punkt.x, punkt.y, 0);

			Core.p5.strokeWeight(3);
			Core.p5.stroke(255, 0, 0);

			Core.p5.point(0, 0);
			Core.p5.popMatrix();
		}
	}

	void update()
	{
		teller = new ArrayList<PVector>();

		Core.p5.pushMatrix();
		int[] depth = kinect.getRawDepth();
		for (int x = 0; x < 640; x += skip)
		{
			for (int y = 0; y < 480; y += skip)
			{
				int offset = x + y * 640;

				// Convert kinect data to world xyz coordinate
				int rawDepth = depth[offset];

				// PVector v = depthToWorld(640 - x, y, rawDepth);

				Core.p5.pushMatrix();

				// Core.p5.translate(xOffset + (v.x * factor), yOffset + (v.y *
				// factor), factor - v.z * factor);
				Core.p5.translate(xOffset + (x * factor), yOffset + (y * factor), 0);

				Core.p5.stroke(0, 0, 255, 100);
				if (rawDepth > depthMin && rawDepth < depthMax)
				{
					teller.add(new PVector(xOffset + ((640-x) * factor), yOffset + ((480-y) * factor), 0));
				}

				Core.p5.strokeWeight(3);
				Core.p5.point(0, 0);
				Core.p5.popMatrix();
			}
		}
		Core.p5.popMatrix();
		draw();
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
