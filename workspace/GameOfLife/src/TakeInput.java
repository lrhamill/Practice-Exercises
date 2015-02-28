import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TakeInput {
	
	public static void main(String[] args) {
		try {
			// Open file
			BufferedReader input = new BufferedReader(new FileReader(args[0]));
			int generations = Integer.parseInt(args[1]);
			
			// Extract parameters from line 1
			String line1 = input.readLine();
			String[] parameters = line1.split(" "); // params as strings
			int[] params = new int[2];
			params[0] = Integer.parseInt(parameters[0]); // params as ints
			params[1] = Integer.parseInt(parameters[1]); 
			
			// Create a 2-dimensional array that corresponds
			// to the grid
			
			int[][] grid = new int[params[0]][params[1]];
			
			for ( int i = 0; i < params[0]; i++ ) {
				int columnLoc = 0;
				String nextLine = input.readLine();
				String[] row = nextLine.split(" ");
				
				for ( String item : row ) {
					grid[i][columnLoc] = Integer.parseInt(item);
					columnLoc += 1;
				}
			}
			
			// Close file
			
			input.close();
			
			// Translate grid into a grid of cell objects
			
			Cell[][] cellGrid = new Cell[params[0]][params[1]];
			
			for ( int i = 0; i < params[0]; i++ ) {
				for (int j = 0; j < params[1]; j++) {
					if ( grid[i][j] == 0 ) {
						cellGrid[i][j] = new Cell(false);
					} else {
						cellGrid[i][j] = new Cell(true);
					}					
				} 
			} 
			
			// Create generation object
					
			Generation gen = new Generation(cellGrid, params[0], params[1]);
			
			for ( int i = 0; i < generations; i++ ) {
				Cell[][] nextGen = gen.getNextGen();
				int x = nextGen.length;
				int y = nextGen.length;
				gen = new Generation(nextGen, x, y);
			}
			
			long output = gen.getSum();
			gen.printGen();
			System.out.println(output);
			
			// Exit
			
			System.exit(0);
			
		} catch (IOException ioe) {
			System.err.println( "Input/Ouput error" );
			System.exit(1);
		}
	}

}