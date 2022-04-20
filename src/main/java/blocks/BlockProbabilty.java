package blocks;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

import setting.UserSetting;

public class BlockProbabilty {

    private static final Random random = new Random();

    
    private final int O = 0;
    private final int I = 1 ;
    private final int S = 2 ;
    private final int Z = 3 ;
    private final int L = 4 ;
    private final int J = 5 ;
    private final int T = 6 ;
    
    private static int blockProbArray[] = new int[7];
    
    private static ArrayList<Integer> rate = new ArrayList<Integer>();
    
    private static int rateLength;


    public BlockProbabilty() {
    	setBlockProbability();
    }
    
    public int randomProb() {
    	return rate.get(random.nextInt(rateLength));
    }
    

    private void setBlockProbability() {
    	setBlockProbArray();
    	
    	for(int k = 0; k < blockProbArray.length; k++) {
    		for(int m = 0; m < blockProbArray[k]; m++) {
    			rate.add(k);
    		}
    	}
    }
    
    private void setBlockProbArray() {
    	
    	UserSetting userSetting = new UserSetting();
    	
    	if (userSetting.getDifficultyLevel() == "EASY") {
        	blockProbArray[O] = 10;
        	blockProbArray[I] = 12;
        	blockProbArray[S] = 10;
        	blockProbArray[Z] = 10;
        	blockProbArray[L] = 10;
        	blockProbArray[J] = 10;
        	blockProbArray[T] = 10;    		
    	}
    	else if (userSetting.getDifficultyLevel() == "NORMAL") {
        	blockProbArray[O] = 10;
        	blockProbArray[I] = 10;
        	blockProbArray[S] = 10;
        	blockProbArray[Z] = 10;
        	blockProbArray[L] = 10;
        	blockProbArray[J] = 10;
        	blockProbArray[T] = 10;    		
    	}
    	else if (userSetting.getDifficultyLevel() == "HARD") {
        	blockProbArray[O] = 10;
        	blockProbArray[I] = 8;
        	blockProbArray[S] = 10;
        	blockProbArray[Z] = 10;
        	blockProbArray[L] = 10;
        	blockProbArray[J] = 10;
        	blockProbArray[T] = 10;    		
    	}
    	
    	for (int k =0; k<7; k++) {
    		rateLength = rateLength + blockProbArray[k];
    	}
    }


}
