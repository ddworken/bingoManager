
public class bingoBoard{
	int boardWidth;
	int[][] bingoArray; 
	
	public bingoBoard(int[][] inBingoArray, int inBoardWidth){
		boardWidth=inBoardWidth;
		bingoArray=new int[boardWidth][boardWidth];
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardWidth; j++){
				bingoArray[i][j]=inBingoArray[i][j];
			}
		}
	}
	
	public bingoBoard(String str0, String str1, String str2, String str3, String str4, int inBoardWidth){
		boardWidth = inBoardWidth;
		bingoArray=new int[boardWidth][boardWidth];
		String[] split0 = str0.split(" ");
		String[] split1 = str1.split(" ");
		String[] split2 = str2.split(" ");
		String[] split3 = str3.split(" ");
		String[] split4 = str4.split(" ");
		String[][] splitAll = { split0, split1, split2, split3, split4};
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardWidth; j++){
				bingoArray[i][j]=Integer.parseInt(splitAll[i][j]);
			}
		}
	}
	
	public int getValue(int x, int y){
		return bingoArray[x][y];
	}
	
	public void valueCalled(int numberRead){
		System.out.println(numberRead);
		for(int i = 0; i < boardWidth; i++){
			for(int j = 0; j < boardWidth; j++){
				if(bingoArray[i][j] == numberRead){
					System.out.println("Found!");
					bingoArray[i][j] = -1;
				}
			}
		}
	}
	
	public void setValue(int x, int y, int value){
		bingoArray[x][y]=value;
	}
	
	public boolean getGameStatus(){
		if(isVerticalWin()){
			return true;
		}
		if(isHorizontalWin()){
			return true;
		}
		if(isDiagonalWin()){
			return true;
		}
		return false;
	}
	
	private boolean isVerticalWin(){
		for(int y = 0; y < boardWidth; y++){
			boolean status = true;
			for(int x = 0; x < boardWidth; x++){
				if(bingoArray[x][y] != -1){
					status = false;
				}
			}
			if(status){
				return true;
			}
		}
		return false;
	}
	
	private boolean isHorizontalWin(){
		for(int x = 0; x < boardWidth; x++){
			boolean status = true;
			for(int y = 0; y < boardWidth; y++){
				if(bingoArray[x][y] != -1){
					status = false;
				}
			}
			if(status){
				return true;
			}
		}
		return false;
	}
	
	private boolean isDiagonalWin(){
		boolean status = true;
		for(int i = 0; i < boardWidth; i++){
			if(bingoArray[i][i] != -1){
				status = false;
			}
		}
		if(status){
			return true;
		}
		status = true;
		int x = boardWidth-1;
		int y = 0;
		for(int i = 0; i < 5; i++){
			int tempX = x-i;
			int tempY = y+i;
			if(bingoArray[tempX][tempY] != -1){
				status = false;
			}
		}
		if(status){
			return true;
		}
		return false;
	}
}
