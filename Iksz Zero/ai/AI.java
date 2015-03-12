package ai;

import java.util.Random;

public class AI{
	private static char[] cell = new char[9];
	private static char playing = 'O';
	
	public static void main(String args[]){
		AI.setAs('O');
		System.out.println(AI.nextMove());
	}
	
	public AI(){
		for (int i = 0; i < 9; i++){
			cell[i] = 'N';
		}
		print();
	}
	
	public static char getPlaying(){
		return playing;
	}
	
	public static void setAs(char value){
		playing = value;
		print();
	}
	
	public static void setCell(int index, char value){
		cell[index] = value;
		print();
	}
	
	public static int nextMove(){
		int result = new Random().nextInt(9);
		while (cell[result] != 'N'){
			result = new Random().nextInt(9);
		}
		cell[result] = playing;
		print();
		return result;
	}
	
	public static void  clearBoard(){
		for (int i = 0; i < 9; i++){
			cell[i] = 'N';
		}
	}
	
	public static void print(){
		for (int i = 0; i < 9; i++){
			System.out.print(cell[i] + " ");
			if ((i + 1) % 3 == 0){
				System.out.println();
			}
		}
		System.out.println();
	}
}
