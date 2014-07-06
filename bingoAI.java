import acm.program.*;
import java.util.Random;

public class bingoAI extends ConsoleProgram{
	Random generator = new Random();
	int boardWidth = 5;
	
	public void run(){
		bingoBoard board = getBingoBoardFromInput();
		echoBingoBoardToOutput(board);
		promptForInputUntilWin(board);
	}
	
	private bingoBoard generateRandomBoard(){
		int[][] bingoArray = new int[boardWidth][boardWidth];
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardWidth; j++){
				bingoArray[i][j]=generator.nextInt(100);
			}
		}
		bingoBoard board = new bingoBoard(bingoArray, boardWidth);
		return board;
	}
	
	private bingoBoard getBingoBoardFromInput(){
		String str0 = readLine();
		String str1 = readLine();
		String str2 = readLine();
		String str3 = readLine();
		String str4 = readLine();
		bingoBoard board = new bingoBoard(str0, str1, str2, str3, str4, boardWidth);
		return board;
	}
	
	private void echoBingoBoardToOutput(bingoBoard output){
		println("");
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardWidth; j++){
				print(output.getValue(i, j));
				String ofInt = output.getValue(i,j) + "";
				if(ofInt.length() == 1){
					print("   ");
				}
				if(ofInt.length() == 2){
					print("  ");
				}
				if(ofInt.length() == 3){
					print(" ");
				}
			}
			println("");
		}
	}
	
	private void promptForInputUntilWin(bingoBoard BBToTrack){
		while(!BBToTrack.getGameStatus()){
			BBToTrack.valueCalled(Integer.parseInt(readLine("Input the value that was read for Bingo: ")));
			System.out.println("done");
		}
		System.out.println(BBToTrack.getGameStatus());
		echoBingoBoardToOutput(BBToTrack);
	}
}
