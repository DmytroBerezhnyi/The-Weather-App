package com.example.android.test1.Test;

public class Sort {
    public int[] beadSort(int[] arr)
    {
        int max=0;
        for(int i=0;i<arr.length;i++)
            if(arr[i]>max)
                max=arr[i];

        //Set up abacus
        char[][] grid=new char[arr.length][max];
        int[] levelcount=new int[max];
        for(int i=0;i<max;i++)
        {
            levelcount[i]=0;
            for(int j=0;j<arr.length;j++)
                grid[j][i]='_';
        }
		/*
		display1D(arr);
		display1D(levelcount);
		display2D(grid);
		*/

        //Drop the beads
        for(int i=0;i<arr.length;i++)
        {
            int num=arr[i];
            for(int j=0;num>0;j++)
            {
                grid[levelcount[j]++][j]='*';
                num--;
            }
        }
        //Count the beads
        int[] sorted=new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            int putt=0;
            for(int j=0;j<max&&grid[arr.length-1-i][j]=='*';j++)
                putt++;
            sorted[i]=putt;
        }

        return sorted;
    }
}
