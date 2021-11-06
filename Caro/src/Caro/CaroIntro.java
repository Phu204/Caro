package Caro;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class CaroIntro extends JFrame implements ActionListener {
	private ImageIcon IMbackground = new ImageIcon("src/Caro/img/Background.jpg");
	private ImageIcon BackGround = new ImageIcon("src/Caro/img/preview.jpg");
	private SetImagePanel mainpane = new SetImagePanel(BackGround.getImage());
	private JButton btStart, btQuit, btOp;
	private Option option = new Option();
	private ImageIcon name = new ImageIcon("src/Caro/img/TicTacToeName.jpg");
	public static ImageIcon logo = new ImageIcon("src/Caro/img/logo.png");
	private JPanel p1, p2, p3;
	private SetImagePanel jpAbove;

	public CaroIntro() {
		super();
		add(mainpane);
		mainpane.setLayout(null);
		mainpane.add(p1 = new JPanel(new GridLayout()));
		mainpane.add(p2 = new JPanel(new GridLayout()));
		mainpane.add(p3 = new JPanel(new GridLayout()));
		createBt();
		setTitle("Tic Tac Toe");
		option.setVisible(false);
		setIconImage(logo.getImage());
		setSize(500, 500);
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void createBt() {
		p1.add(btStart = new JButton("Start"));
		btStart.setBounds(mainpane.getWidth() / 2 - 75, mainpane.getHeight() / 2, 150, 50);
		mainpane.add(p1);
		p2.add(btOp = new JButton("Option"));
		btOp.setBounds(mainpane.getWidth() / 2 - 75, mainpane.getHeight() / 2 + 40, 150, 50);
		mainpane.add(p2);
		p3.add(btQuit = new JButton("Quit"));
		btQuit.setBounds(mainpane.getWidth() / 2 - 75, mainpane.getHeight() / 2 + 80, 150, 50);
		mainpane.add(p3);
		btStart.addActionListener(this);
		btOp.addActionListener(this);
		btQuit.addActionListener(this);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//jpAbove.setBounds(0, 0, mainpane.getWidth(), mainpane.getHeight() / 3);
		p1.setBounds(mainpane.getWidth() / 2 - 165, mainpane.getHeight() / 3, 150, 50);
		p2.setBounds(mainpane.getWidth() / 2 - 165, mainpane.getHeight() / 3 + 80, 150, 50);
		p3.setBounds(mainpane.getWidth() / 2 - 165, mainpane.getHeight() / 3 + 160, 150, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btStart) {
			setVisible(false);
			if (option.gettheme().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel().getClass().getName());
				} catch (UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException
						| IllegalAccessException c) {
					c.printStackTrace();
				}
				new CaroGui(option.getmode());
			} else

			if (option.gettheme().equals("Window")) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				} catch (UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException
						| IllegalAccessException c) {
					c.printStackTrace();
				}
				new CaroGui(option.getmode());
			} else

			if (option.gettheme().equals("Motif")) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException
						| IllegalAccessException c) {
					c.printStackTrace();
				}
				new CaroGui(option.getmode());
			}
		}
		if (e.getSource() == btQuit) {
			System.exit(0);
		}
		if (e.getSource() == btOp) {
			option.setVisible(true);
			// System.out.println(option.getmode());
		}
	}
}

class SetImagePanel extends JPanel {
	private Image image;

	public SetImagePanel(Image image) {
		this.image = image;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}

class Option extends JFrame implements ActionListener {
	private static JRadioButton Nimbus, Window, Motif;
	private static String theme = "Nimbus";
	private static String[] mode = { "2 Player", "Easy Computer", "Normal Computer", "Hard Computer" };
	private static String ckmode = "2 Player";
	private static JList modelist = new JList(mode);
	private JButton OK;
	private static ButtonGroup bg = new ButtonGroup();

	public Option() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel pane = new JPanel();

		BoxLayout bl1 = new BoxLayout(p3, BoxLayout.Y_AXIS);
		BoxLayout bl3 = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(bl3);

		p1.add(modelist);
		p1.setBorder(new TitledBorder(""));
		p4.add(p1, BorderLayout.CENTER);

		p3.add(Nimbus = new JRadioButton("Nimbus theme"));
		p3.add(Window = new JRadioButton("Window theme"));
		p3.add(Motif = new JRadioButton("Motif theme"));
		bg.add(Nimbus);
		bg.add(Window);
		bg.add(Motif);
		setSelectheme();
		setSelecMode();
		Nimbus.addActionListener(this);
		Window.addActionListener(this);
		Motif.addActionListener(this);
		p3.setLayout(bl1);
		p4.add(p3);

		pane.add(p4);
		pane.add(p2);
		p2.add(OK = new JButton("OK"));
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		pane.add(p2);
		add(pane);
		setResizable(false);
		setLocationRelativeTo(null);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setVisible(true);
	}

	public static String getmode() {
		return (String) modelist.getSelectedValue();
	}

	public static String gettheme() {
		return theme;
	}

	private static void setSelectheme() {
		if (theme.equals("Nimbus")) {
			Nimbus.setSelected(true);
		} else if (theme.equals("Window")) {
			Window.setSelected(true);
		} else if (theme.equals("Motif")) {
			Motif.setSelected(true);
		}
	}

	private static void setSelecMode() {
		if (modelist.getSelectedIndex() == 0) {
			ckmode = "2 Player";
		} else if (modelist.getSelectedIndex() == 1) {
			ckmode = "Easy Computer";
		} else if (modelist.getSelectedIndex() == 2) {
			ckmode = "Normal Computer";
		} else if (modelist.getSelectedIndex() == 3) {
			ckmode = "Hard Computer";
		}
		if (ckmode.equals("2 Player")) {
			modelist.setSelectedIndex(0);
		} else if (ckmode.equals("Easy Computer")) {
			modelist.setSelectedIndex(1);
		} else if (ckmode.equals("Normal Computer")) {
			modelist.setSelectedIndex(2);
		} else if (ckmode.equals("Hard Computer")) {
			modelist.setSelectedIndex(3);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (Nimbus.isSelected()) {
			theme = "Nimbus";
		} else if (Window.isSelected()) {
			theme = "Window";
		} else if (Motif.isSelected()) {
			theme = "Motif";
		}

	}

}
