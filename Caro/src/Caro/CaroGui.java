package Caro;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class CaroGui extends JFrame {
	private Container pane;
	private String currentPlayer;
	private JButton[][] board;
	private boolean hasWinner;
	private JMenu menu;
	private JMenuBar menubar;
	private JMenuItem quit;
	private JMenuItem newGame, backMenu;
	
	private CaroModel model = new CaroModel();
	
	
	public CaroGui(String mode) {
		super();
		pane = getContentPane();
		pane.setLayout(new GridLayout(12, 12));
		currentPlayer = "x";
		board = new JButton[12][12];
		hasWinner = false;
		setIconImage(CaroIntro.logo.getImage());
		intializeBoard(mode);
		intializeMenubar();
		setTitle("Caro game");
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void intializeMenubar() {
		menubar = new JMenuBar();
		menu = new JMenu("File");
		newGame = new JMenuItem("New game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlayer = "x";
				hasWinner = false;
				model.resetBoard();
			}
		});
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		backMenu = new JMenuItem("Back to Menu");
		backMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CaroIntro();
			}
		});
		menu.add(newGame);
		menu.add(backMenu);
		menu.add(quit);
		menubar.add(menu);
		setJMenuBar(menubar);
	}

	

	private void intializeBoard(String mode) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				JButton btn = new JButton("");
				btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				board[i][j] = btn;

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (((JButton) e.getSource()).getText().equals("") && hasWinner == false) {

							if (mode.equals("2 Player")) {
								btn.setText(currentPlayer);
								updateInitialState();
								checkWinner();
								togglePlayer();
							}
							if (mode.equals("Easy Computer")) {
								btn.setText(currentPlayer);
								updateInitialState();
								checkWinner();
								EasyCom();
								checkWinner();
								togglePlayer();
							}
							if (mode.equals("Normal Computer")) {
								btn.setText(currentPlayer);
								checkWinner();
								NormalCom();
								checkWinner();
								togglePlayer();
							}
							if (mode.equals("Hard Computer")) {
								btn.setText(currentPlayer);
								checkWinner();
								HardCom();
								checkWinner();
								togglePlayer();
							}
						}
					}

					
				});
				pane.add(btn);
			}

		}

	}

	private void updateInitialState() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				String s = board[i][j].getText();
				int value ;
				switch (s) {
				case "x":
					value = 1;
					break;
				case "o":
					value = 2;
					break;
				default:
					value = 0;
				}
				model.getInitialState().updateTile(i, j, value);
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(model.getInitialState().getTile(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	private void togglePlayer() {
		if (currentPlayer.equals("x")) {
			currentPlayer = "o";
		} else {
			currentPlayer = "x";
		}
	}

	private void BufferCom() {
		Random rd = new Random();
		int i = rd.nextInt(12);
		int j = rd.nextInt(12);
		if (hasWinner) {
			return;
		}
		if (board[i][j].getText().equals("")) {
			board[i][j].setText(currentPlayer);
			checkWinner();
		} else {
			BufferCom();
		}
	}

	private void EasyCom() {
		int countTemp = countStep();
		if(!hasWinner) {
		
		if (countStep() == countTemp) {
			togglePlayer();
			BufferCom();
		}
	}}

	private void NormalCom() {
			
	}

	private void HardCom() {
		
	}

	private void checkWinner() {
		for (int i = 0; i < board.length - 4; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].getText().equals(board[i + 1][j].getText())
						&& board[i][j].getText().equals(board[i + 2][j].getText())
						&& board[i][j].getText().equals(board[i + 3][j].getText())
						&& board[i][j].getText().equals(board[i + 4][j].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}

			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length - 4; j++) {
				if (board[i][j].getText().equals(board[i][j + 1].getText())
						&& board[i][j].getText().equals(board[i][j + 2].getText())
						&& board[i][j].getText().equals(board[i][j + 3].getText())
						&& board[i][j].getText().equals(board[i][j + 4].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}
			}
		}

		for (int i = 0; i < board.length-4; i++) {
			for (int j = 0; j < board.length-4; j++) {
				if (board[i][j].getText().equals(board[i + 1][j + 1].getText())
						&& board[i][j].getText().equals(board[i + 2][j + 2].getText())
						&& board[i][j].getText().equals(board[i + 3][j + 3].getText())
						&& board[i][j].getText().equals(board[i + 4][j + 4].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}
			}
		}
		
		for (int i = 0; i < board.length-4; i++) {
			for (int j = board.length-1; j >3; j--) {
				if (board[i][j].getText().equals(board[i+1][j-1].getText())
						&& board[i][j].getText().equals(board[i+2][j-2].getText())
						&& board[i][j].getText().equals(board[i+3][j-3].getText())
						&& board[i][j].getText().equals(board[i+4][j-4].getText())
						&& board[i][j].getText().equals(currentPlayer) && !hasWinner) {
					JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
					hasWinner = true;
				}
			}
		}

	}

	private int countStep() {
		int count =0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!board[i][j].getText().equals("")) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		new CaroGui("Easy Computer");
	}
}
