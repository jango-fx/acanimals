package acanimalsProjection;

import java.util.TimerTask;


public class NextMsg extends TimerTask {

	@Override
	public void run() {
		Core.p5.nextMsg();
	}

}
