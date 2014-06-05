package naive_bayes;

// common function for dim 2 array 
public class D2Array {
	
	// print a 2D array
    public static void print(double[][] data){
    	int m = data.length;
    	int n = data[0].length;
    	for(int i = 0; i < m; ++i){
    		for(int j = 0; j < n; j++){
    			System.out.print(data[i][j] + "  ");
    		}
    		System.out.println();
    	}
    	
    }
    
    // overload
    public static void print(int[][] data){
    	int m = data.length;
    	int n = data[0].length;
    	for(int i = 0; i < m; ++i){
    		for(int j = 0; j < n; j++){
    			System.out.print(data[i][j] + "  ");
    		}
    		System.out.println();
    	}
    	
    }
    
    
    
  // get a column of a 2D array
    public static Object[] getColumn(Object[][] data, int index){
    	int m = data.length;
    	int n = data[0].length;
    	Object[] column = new Object[m];
    	for(int i =0; i < m; ++i){
    		column[i] = data[index][i];
    	}
    	return column;
    }
    // overload the function 
    public static int[] getColumn(int[][] data, int index){
    	int m = data.length;
    	int n = data[0].length;
        int[] column = new int[m];
    	for(int i =0; i < m; ++i){
    		column[i] = data[i][index];
    	}
    	return column;
    }
}
