package naive_bayes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


// This is the naive bayes classifier for 2 classes label

public class NaiveBayes2Classifier {
	// store the prob of each class
	double prob_class_1;
	double prob_class_2;
	
	// store the count of each class
	int count_class_1;
	int count_class_2;
	int[] index_class_1;
	int[] index_class_2;
	
	// probabiblity of each feature given a class
    double[][] prob_feat_class_1;
    double[][] prob_feat_class_2;
    
    
    
    int numb_feature;
    int numb_train;
    int numb_test;
    int[] result;
    int[] classlabels;
    double[] prob_result;
    String name;
    int[][] train_data;
    int[][] predict_data;
    
    public NaiveBayes2Classifier(){}
    
    public NaiveBayes2Classifier(String name){
    	this.name = name;
    }
    
    // train the model with naive bayes algorithm 
    public boolean train(int[][] data){
    	this.train_data = data;
    	int m = data.length;
    	int n = data[0].length;
    	this.numb_train = m;
    	this.numb_feature = n-1;
    	int[] object_data = D2Array.getColumn(data, n-1);
    	// count the class label and store it in this
    	countClassLabel(object_data);
    	findIndexCL(object_data);
    	//compute the probablility given a class label
    	computeProbFeat(data);
    	return true;
    }
    
    // prediction with the classifier model with new data
    public int[] predict(int[][] predict_data){
    	this.predict_data = predict_data;
    	int m = predict_data.length;
    	int n = predict_data[0].length;
    	this.result = new int[m];
    	this.prob_result = new double[m];

    	double prob_1 = this.count_class_1;
    	double prob_2 = this.count_class_2;
    	
    	// compute the probability of pred_data
    	
    	for(int i =0; i < m; ++i){
    		for(int j = 0;j < n;j++){
    			int index = 0;
    			while(this.train_data[index][j]!=predict_data[i][j] || this.prob_feat_class_1[index][j]==0){
    				index ++;
    			}
    			prob_1 = prob_1*this.prob_feat_class_1[index][j];
     			while(this.train_data[index][j]!=predict_data[i][j] || this.prob_feat_class_2[index][j]==0){
    				index ++;
    			}
     			prob_2 = prob_2*this.prob_feat_class_2[index][j];
    		}
    		if(prob_1>prob_2){
    		   this.result[i] = this.classlabels[0];
    		   this.prob_result[i] = prob_1/(prob_1 + prob_2);
    		}
    		else{
    		   this.result[i] = this.classlabels[1];
   			   this.prob_result[i] = prob_2/(prob_1 + prob_2);
    		}
    		  
    		
    	}
    	
    	
    	
    	
    	
    	return this.result;
    }
    
    //change the label to be 0 and 1 (not implemented yet)
    public boolean changeto01(double[][] data){
    	if(data == null || data.length == 0 ){
    		return false;
    	}
    	int m = data.length;
    	int n = data[0].length;
    	return true;
    }
    
    // find the class label of data
    private void findClassL(int[] object_data){
    	if(object_data == null){
    		return;
    	}
    	int[] classlabels = new int[2];
    	classlabels[0] = object_data[0];
        int j = 0;
        while(object_data[j] == classlabels[0]){
        	j++;
        }
        classlabels[1] = object_data[j]; 
        this.classlabels = classlabels;
        }
    
    // count item in array
    private int count(int[] data, int item){
    	int count = 0 ;
    	for(int i = 0; i < data.length;++i){
    		if(data[i] == item){
    			count++;
    		}
    	}
    	return count;
    }
    
    // count the class labels 
    private void countClassLabel(int[] object_data){
    	findClassL(object_data);
    	this.count_class_1 = count(object_data,classlabels[0]);
    	this.count_class_2 = object_data.length - this.count_class_1;
    }
    
    // get index of different class
    private  void findIndexCL(int[] object_data){
    	this.index_class_1 = new int[this.count_class_1];
    	this.index_class_2 = new int[this.count_class_2];
    	int s = 0;
    	int t = 0;
    	for(int i = 0; i < object_data.length;++i){
    		if(this.classlabels[0] == object_data[i]){
    			this.index_class_1[s] = i;
    			s++;
    		}
    		else{
    			this.index_class_2[t] = i;
    			t++;
    		}
    	}
    	
    	return;
    }
    
    // compute probability of the feature given class label
    private void computeProbFeat(int[][] data){
    	int m = data.length;
    	int n = data[0].length;
    	this.prob_feat_class_1 = new double[this.numb_train][this.numb_feature];
    	this.prob_feat_class_2 = new double[this.numb_train][this.numb_feature];
		HashSet<Integer> index_class_1 = new HashSet<Integer>();
		HashSet<Integer> index_class_2 = new HashSet<Integer>();
    	for (int i = 0; i < this.index_class_1.length;++i){
    		index_class_1.add(this.index_class_1[i]);
    	}
    	for (int i = 0; i < this.index_class_2.length;++i){
    		index_class_2.add(this.index_class_2[i]);
    	}
    	for(int j = 0;j < n-1;j++){
    		HashMap<Integer,Integer> hash_index_1 = new HashMap<Integer,Integer>();
        	HashMap<Integer,Integer> hash_count_1 = new HashMap<Integer,Integer>();
        	HashMap<Integer,Integer> hash_index_2 = new HashMap<Integer,Integer>();
        	HashMap<Integer,Integer> hash_count_2 = new HashMap<Integer,Integer>();
        	int temp = 0;
    		for(int i = 0; i < m;++i){
    			if(index_class_1.contains(i)){
	    			if(!hash_index_1.containsKey(data[i][j])){
	    				hash_index_1.put(data[i][j], i);
	    				temp = 1;
	    				hash_count_1.put(data[i][j],temp);
	    				
	    			}
	    			else{
	    				temp = hash_count_1.get(data[i][j]);
	    				temp++;
	    				hash_count_1.remove(data[i][j]);
	    				hash_count_1.put(data[i][j], temp);
	    			}
    			}
    			else{
	    			if(!hash_index_2.containsKey(data[i][j])){
	    				hash_index_2.put(data[i][j], i);
	    				temp = 1;
	    				hash_count_2.put(data[i][j],temp);
	    				
	    			}
	    			else{
	    				temp = hash_count_2.get(data[i][j]);
	    				temp++;
	    				hash_count_2.remove(data[i][j]);
	    				hash_count_2.put(data[i][j], temp);
	    			}
    			}
    		}
            for(int i : hash_index_1.keySet()){
            	this.prob_feat_class_1[hash_index_1.get(i)][j] = (double)hash_count_1.get(i)/this.count_class_1;
            }
            for(int i : hash_index_2.keySet()){
            	this.prob_feat_class_2[hash_index_2.get(i)][j] = (double)hash_count_2.get(i)/this.count_class_2;
            }    		
    	}
    	
    }
    
	

}
