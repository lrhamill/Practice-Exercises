
public class Cell {
	
	int up = 0;
	int down = 0;
	int left = 0;
	int right = 0;
	
	int UR = 0;
	int UL = 0;
	int DR = 0;
	int DL = 0;
	
	boolean alive = true;
	
	public Cell(boolean life) {
		alive = life;
	}
	
	public String toString() {
		if (alive) { return "1"; }
		else { return "0"; }
	}
	
	public boolean getLife() {
		return alive;
	}
	
	public void neighbour(int direction) {
		switch (direction) {
			case 1: up = 1;
					break;
			case 2: down = 1;
					break;
			case 3: left = 1;
					break;
			case 4: right = 1;
					break;
			case 5: UR = 1;
					break;
			case 6: UL = 1;
					break;
			case 7: DR = 1;
					break;
			case 8: DL = 1;
					break;
		}
	}
	
	public boolean nextCell() {
		
		int totalNeighbour = up + down + left + right + UR + UL + DR + DL;
		
		if ( alive ) {
			if ( totalNeighbour < 2 || totalNeighbour > 3 ) {
				return false; // cell dies
			} else { return true; } // cell survives
		} else {
			if ( totalNeighbour == 3 ) {
				return true; // cell is born
			} else { return false; } // no new cell
		}
		
	}

}
