package hackathon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import de.yadrone.base.command.VideoCodec;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.video.ImageListener;

public class MainLauncher extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int varw = -720;
	private BufferedImage image = null;
	private BufferedImage image2 = null;
	ControlGUI control;

	public MainLauncher() {

		super("Oculus Feed");

		IARDrone drone = null;
		try {//
			drone = new ARDrone();
			drone.start();
		} catch (Exception exc) {
			System.out.println("Error");
			exc.printStackTrace();
		} finally {
			drone.addExceptionListener(new IExceptionListener() {
				public void exeptionOccurred(ARDroneException exc) {
					exc.printStackTrace();
				}
			});
			control = new ControlGUI(drone);
			drone.getCommandManager().setVideoCodec(VideoCodec.H264_360P);
			drone.getCommandManager().setVideoBitrate(250);
			drone.getCommandManager().setVideoChannel(VideoChannel.HORI);
		
			drone.getVideoManager().addImageListener(new ImageListener() {
				public void imageUpdated(BufferedImage newImage) {
					image = newImage;
					image2 = newImage;
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							repaint();
						}
					});
				}
			});
			
			setSize(3840 + varw, 3240);
			//setSize(2560 + varw, 720);
			//setSize(2560 + varw, 792);
			setLocationRelativeTo(null);
			//setLocation(4000, 2000);
			setResizable(false);
	        setVisible(true);
	        
		}
	}
	
	

	public void paint(Graphics g) {
		if (image != null)
		{
			g.drawImage(image, 0, 0, image.getWidth()*2, image.getHeight()*3, null);
			//g.drawImage(image, 0, 0, image.getWidth(), (int)(image.getHeight()*1.1), null);
			g.drawImage(image, 1280 + (varw / 2), 0, image.getWidth()*2, image.getHeight()*3, null);
			//g.drawImage(image2, 1280 + (varw / 2), 0, image.getWidth(), (int)(image.getHeight()*1.1), null);
			
		}
	}

	public static void main(String[] args) {
		new MainLauncher();
	}
}
