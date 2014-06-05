package naive_bayes;

import java.util.Arrays;

//This is Demo for NaiveBayes classifier 

public class NaiveBayesDemo {
	public static void main(String[] args)
	{
		// initial the train and test data
		int[][] train = {{1,1,1},{1,1,0},{0,1,1},{0,0,1},{1,0,0},{1,0,0},{0,1,0},{0,0,0},{1,0,0}};
		int[][] data_to_pred = {{0,0},{1,1},{1,0}};
		
		// print the train data to visualize 
        D2Array.print(train);
        
        // initialize naive bayes classifier and train model
        NaiveBayes2Classifier clf = new NaiveBayes2Classifier("simple_demo");
        clf.train(train);
        clf.predict(data_to_pred);
        
        //print the result we got
        print(Arrays.toString(clf.result));
        print(Arrays.toString(clf.prob_result));
        
		
		
	}
	
	// Simplify the system.out.println with this atlas
	public static void print(Object oj){
		System.out.println(oj);
	}
	

}
