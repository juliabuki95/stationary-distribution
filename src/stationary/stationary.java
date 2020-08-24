package stationary;

import java.io.IOException;
import java.util.Arrays;

public class stationary {
	

	public static double[] calc(double arr[][]) {
		
		//finds stationary distribution//
		
		double[] ret=new double[arr.length];
		
		
		for(int i=0;i<arr.length;i++) {
			arr[i][i]--;
		}
		
		//last row replaced by row of 1 (sum of stationary distribution is 1)//
		
		for(int i=0;i<arr.length;i++) {
			arr[arr.length-1][i]=1;
		}
		
		//inverse matrix 
		
		arr=inv(arr);
		
		
	//last column is the stationary distribution// 
		for(int i=0;i<arr.length;i++) {
			ret[i]=arr[i][arr.length-1];
		}
		return ret;
	}
	
	
	public static double[][] inv(double arr[][]) {
		
		//inverse matrix of arr//
		
		double inv[][]=new double[arr.length][arr.length];
		for(int i=0;i<arr.length;i++) {
			inv[i][i]=1;
		}
		for(int i=0;i<arr.length;i++) {
			int j=i;
			while(arr[j][i]==0) {
				j++;
			}
			swap(arr,i,j);
			swap(inv,i,j);

			for(int k=0;k<arr.length;k++) {
				if(k!=i) {
				double cons=arr[k][i]/arr[i][i];
				int r=0;
				for(j=0;j<arr.length;j++) {
					arr[k][j]=arr[k][j]-cons*arr[i][j];
					inv[k][j]=inv[k][j]-cons*inv[i][j];
					if(arr[k][j]==0) {
						r++;
					}
				}
				if(r==arr.length) {
					System.out.print("לא הפיכה");
					return null;
				}
				}
			}
			
		}
		for(int i=0;i<arr.length;i++) {
			double cons=1/arr[i][i];
			for(int j=0;j<arr.length;j++) {
				inv[i][j]=inv[i][j]*cons;

			}
		}
		
		return inv;
	}
	
	
	public static double[][] swap(double arr[][],int i,int j){
		
		// swaps two rows//
		
		double[] temp=new double[arr.length];
		temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		return arr;
	}
	
	
	public static double[][] trc(double arr[][]){
		
		//returns trace arr//
		
		double ret[][]=new double[arr.length][arr.length];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				ret[j][i]=arr[i][j];
			}
		}
		return ret;
	}

    public static void main(String[] args) throws IOException {
    	double[][] arr={{11.0/15,4.0/15,0},{6.0/15,8.0/15,1.0/15},{0,2.0/5,3.0/5},};
    	System.out.println(Arrays.deepToString((trc(arr))));

    	System.out.println(Arrays.toString(calc(trc(arr))));
    	
    }
}
