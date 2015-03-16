

public class Generation {
	
	Cell[][] currentGen;
	int rows;
	int columns;
	
	public void printGen() {
		
		// Prints currentGen to console.
		// Useful for debugging. Not used in the final program.
		
		for ( Cell[] row : currentGen ) {
			for ( Cell item : row ) {
				System.out.print( item + " " );
			}System.out.println("");
		} System.out.println("");
	}
	
	
	public Generation( Cell[][] input, int x, int y ) {
		
		rows = x;
		columns = y;
		
		// See if we need to expand 
		boolean expandUp = false;
		boolean expandDown = false;
		boolean expandLeft = false;
		boolean expandRight = false;
		
		for ( Cell item : input[0] ) {
			if ( item.getLife() ) { 
				expandUp = true; 
				rows += 1;
				break; 
				}
		}
		
		for ( Cell item : input[x-1] ) {
			if ( item.getLife() ) {
				expandDown = true;
				rows += 1;
				break; 
				}
		}
		
		for ( int i = 0; i < x; i++ ) {
			if ( input[i][0].getLife() && ! expandLeft ) {
				expandLeft = true;
				columns += 1; 
				}
			if ( input [i][y-1].getLife() && ! expandRight ) {
				expandRight = true; 
				columns += 1;
				}
			if ( expandLeft && expandRight ) {
				break;
			}
		}
		
		currentGen = new Cell[rows][columns];
		
		// Add a row of dead cells to the top and bottom if needed

		if ( expandUp || expandDown ) {
		
			for ( int i = 0; i < columns; i++ ) {
				
				if ( expandUp ) {
					currentGen[0][i] = new Cell(false);
				}
					
				if ( expandDown ) {
					currentGen[rows - 1][i] = new Cell(false);
				}
			}
		}
		
		// Set up offset values to insert input into currentGen
		
		int leftOffset = ( expandLeft ) ? 1 : 0;
		int topOffset = ( expandUp ) ? 1 : 0;
		
		// Add rows from input
		for ( int i = 0; i < x;  i++ ) {
			for ( int j = 0; j < y; j++ ) {
				
				// Put entry from input into currentGen
				currentGen[i + topOffset][j + leftOffset] = input[i][j];
				
				// Additionally, add a dead cell to the start of the row if needed
				if ( j == 0 && expandLeft ) {
					currentGen[i + topOffset][0] = new Cell(false);
				}
				
				// Additionally, add a dead cell to the end of the row if needed
				else if ( j == y - 1 && expandRight ) {
					currentGen[i + topOffset][columns - 1] = new Cell(false);
				}
			}	
		}
		
	}
	
	private Cell getNewCell( Cell target, int rowLoc, int columnLoc ) {
		
		// Clarify whether there are rows/columns adjacent to target
		
		boolean left = true;
		boolean right = true;
		boolean down = true;
		boolean up = true;
		
		if ( columnLoc == 0 ) { left = false; }
		if ( columnLoc == columns - 1 ) { right = false; }
		if ( rowLoc == 0 ) { up = false; }
		if ( rowLoc == rows - 1 ) { down = false; }
		
		// Check 4 cardinal directions
		
		if ( left ) {
			if ( currentGen[rowLoc][columnLoc - 1].getLife() ) { target.neighbour(3); }
		}
		if ( right ) {
			if ( currentGen[rowLoc][columnLoc + 1].getLife() ) { target.neighbour(4); }
		}
		if ( down ) {
			if ( currentGen[rowLoc + 1][columnLoc].getLife() ) { target.neighbour(2); }
		}
		if ( up ) {
			if ( currentGen[rowLoc - 1][columnLoc].getLife() ) { target.neighbour(1); }
		}
		
		// Check 4 diagonal directions
		
		if ( up && right ) {
			if ( currentGen[rowLoc - 1][columnLoc + 1].getLife() ) { target.neighbour(5); }
		}
		if ( up && left ) {
			if ( currentGen[rowLoc - 1][columnLoc - 1].getLife() ) { target.neighbour(6); }
		}
		if ( down && right ) {
			if ( currentGen[rowLoc + 1][columnLoc + 1].getLife() ) { target.neighbour(7); }
		}
		if ( down && left ) {
			if ( currentGen[rowLoc + 1][columnLoc - 1].getLife() ) { target.neighbour(8); }
		}
		
		// Find out if cell is alive
		boolean survival;
		survival = target.nextCell();		
		
		return new Cell(survival);
		
	}
	
	public Cell[][] getNextGen() {
		
		Cell[][] nextGen = new Cell[rows][columns];			
		
		for ( int i = 0; i < rows; i++ ) {
			for ( int j = 0; j < columns; j++ ) {
				nextGen[i][j] = getNewCell( currentGen[i][j], i, j );
			}
		}
		
		// Return new cell array
		
		return nextGen;
		
	}
	
	public long getSum() {
		// Returns the number of live cells inside the generation
		// The return type is long to support huge generations
		
		long sum = 0;
				
		for ( Cell[] item : currentGen ) {
			for ( Cell entry : item) {
				sum += Integer.parseInt(entry.toString());
				}
			}
		return sum;
	}	
}
