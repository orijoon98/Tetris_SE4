package blocks;

import java.awt.*;

public class LBlock extends Block {
	
	public LBlock() {
		shape = new int[][] { 
			{1, 1, 1},
			{1, 0, 0}
		};
		color = Color.ORANGE;
	}
}
