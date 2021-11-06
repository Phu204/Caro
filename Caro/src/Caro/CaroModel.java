package Caro;

public class CaroModel {
	public static final int MAX_ROW = 12;
	public static final int MAX_COL = 12;
	

	
	private Node initialState;
	public CaroModel() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
	}
	
	public void resetBoard() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
	}

	public Node getInitialState() {
		return initialState;
	}
	
	
}
