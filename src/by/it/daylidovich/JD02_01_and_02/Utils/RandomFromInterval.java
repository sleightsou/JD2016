package by.it.daylidovich.JD02_01_and_02.Utils;

import java.util.Random;

public class RandomFromInterval {
    public static int randomInterval(int start, int end){
        return new Random().nextInt(end - start +1) + start;
    }
}
