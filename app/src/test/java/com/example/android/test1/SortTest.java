package com.example.android.test1;

import com.example.android.test1.Test.Sort;

import static org.junit.Assert.*;
import org.junit.Test;


public class SortTest {
    Sort sort = new Sort();

    @Test
    public void test1 ()
    {
        int [] list = {1,4,3,2,5,6};
        String result = new String();
        sort.beadSort(list);
        for(int i = 0; i<list.length; i ++)
            result = result + " " + list[i];

        assertEquals(" 1 2 3 4 5 6", result);
    }

    @Test
    public void test2 ()
    {
        int [] list = { };
        String result = new String();
        sort.beadSort(list);
        for(int i = 0; i<list.length; i ++)
            result = result + " " + list[i];
        assertEquals("", result);
    }

    @Test
    public void test3 ()
    {
        int [] list = {8,7,6,5,4,3,2,1};
        String result = new String();
        sort.beadSort(list);
        for(int i = 0; i<list.length; i ++)
            result = result + " " + list[i];
        assertEquals(" 1 2 3 4 5 6 7 8", result);
    }

    @Test
    public void test4 ()
    {
        int [] list = null;
        String result = new String();
        sort.beadSort(list);
        for(int i = 0; i<list.length; i ++)
            result = result + " " + list[i];
        assertEquals("", result);
    }

}