package main;

import java.text.DecimalFormat;

public class test {
    public void compute() {
        Integer i = 2630;
        Integer j = 1000;
        DecimalFormat df = new DecimalFormat("0.000");
        String s = df.format((float)i/j);
        System.out.println(s);
    }
    
    public static void main(String[] args) {
        test q4 = new test();
        q4.compute();
    }
}
