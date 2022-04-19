package blocks;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
// 총합 100으로 O, I, S, Z, L, J, T 순서대로 확률값을 넣으주면 됨
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
    
    private static int rate[] = new int[100];


    public BlockProbabilty(int o, int i, int s, int z, int l, int j, int t) {
    	setBlockProbArray(o, i, s, z, l, j, t);
    	setBlockProbability(o, i, s, z, l, j, t);
    }
    
    public int randomProb() {
    	return rate[random.nextInt(rate.length)];
    }
    

    private void setBlockProbability(int o, int i, int s, int z, int l, int j, int t) {
    	setBlockProbArray(o, i, s, z, l, j, t);
    	
    	int nth = 0;
    	for(int k = 0; k < blockProbArray.length; k++) {
    		for(int m = 0; m < blockProbArray[k]; m++) {
    			rate[nth] = k;
    			nth++;
    		}
    	}
    }
    
    private void setBlockProbArray(int o, int i, int s, int z, int l, int j, int t) {
    	blockProbArray[O] = o;
    	blockProbArray[I] = i;
    	blockProbArray[S] = s;
    	blockProbArray[Z] = z;
    	blockProbArray[L] = l;
    	blockProbArray[J] = j;
    	blockProbArray[T] = t;
    }


}
