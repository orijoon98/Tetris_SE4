package blocks;

import java.awt.*;

public class JBlock extends Block {
	
	public JBlock() {
		shape = new int[][] { 
				{1, 1, 1},
				{0, 0, 1}
		};
		color = Color.BLUE;
	}
}
