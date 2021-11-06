package Caro;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel().getClass().getName());
		} catch (UnsupportedLookAndFeelException | InstantiationException |ClassNotFoundException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new CaroIntro();
			}
		});
		
	}

}
