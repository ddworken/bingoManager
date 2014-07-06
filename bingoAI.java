import acm.program.*;
import java.util.Random;
import java.util.ArrayList;


public class bingoAI extends ConsoleProgram{
	Random generator = new Random();
	int boardWidth = 5;
	int numberOfBoards;
	ArrayList<bingoBoard> listOfBoards = new ArrayList<bingoBoard>();
	
	public void run(){
		numberOfBoards = Integer.parseInt(readLine("How many boards would you like to track? "));
		if(readLine("Do you want random boards for debugging? (y/n) ").equals("n")){
			for(int i = 0; i < numberOfBoards; i++){
				listOfBoards.add(getBingoBoardFromInput());
			}
		}
		else{
			for(int i = 0; i < numberOfBoards; i++){
				listOfBoards.add(generateRandomBoard());
			}
		}
		//echoBingoBoardToOutput();
		promptForInputUntilWin();
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
		String str0 = readLine("Input the first line of values, separated by spaces:  ");
		String str1 = readLine("Input the second line of values, separated by spaces: ");
		String str2 = readLine("Input the third line of values, separated by spaces:  ");
		String str3 = readLine("Input the fourth line of values, separated by spaces: ");
		String str4 = readLine("Input the fifth line of values, separated by spaces:  ");
		bingoBoard board = new bingoBoard(str0, str1, str2, str3, str4, boardWidth);
		return board;
	}
	
	private void echoBingoBoardToOutput(bingoBoard output){
		println("");
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardWidth; j++){
				int outputNum = -2;
				if(output.getStatus(i,j) == true){
					outputNum = -1;
				}else{
					outputNum = output.getValue(i, j);
				}
				print(outputNum);
				String ofInt = outputNum + "";
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
	
	private void echoBingoBoardToOutput(){
		for(bingoBoard bb: listOfBoards){
			echoBingoBoardToOutput(bb);
		}
	}
	
	private void promptForInputUntilWin(){
		while(!anyBoardsWonYet()){
			String valueCalled = readLine("Input the value that was read for Bingo: ");
			if(valueCalled.equals("dump")){
				echoBingoBoardToOutput();
			}
			else{
				int valueCalledInt = Integer.parseInt(valueCalled);
				for(bingoBoard bb: listOfBoards){
					bb.valueCalled(valueCalledInt);
				}
			}
		}
		println("Winner!");
		dumpWinningBoard();
		/*while(!BBToTrack.getGameStatus()){
			BBToTrack.valueCalled(Integer.parseInt(readLine("Input the value that was read for Bingo: ")));
			System.out.println("done");
		}
		System.out.println(BBToTrack.getGameStatus());
		echoBingoBoardToOutput(BBToTrack);*/
	}
	
	private boolean anyBoardsWonYet(){
		for(bingoBoard bb: listOfBoards){
			if(bb.getGameStatus()){
				return true;
			}
		}
		return false;
	}
	
	private void dumpWinningBoard(){
		for(bingoBoard bb: listOfBoards){
			if(bb.getGameStatus()){
				for(int i = 0; i < boardWidth; i++){
					for(int j = 0; j < boardWidth; j++){
						print(bb.getValue(i, j));
						String ofInt = bb.getValue(i,j) + "";
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
				}			}
		}
	}
}
