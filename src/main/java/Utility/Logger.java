package Utility;

public class Logger {

    public static void getTiming(long start, String name) {
        System.out.println(name + " timing: " + (System.nanoTime() - start)/1000000 + " ms");
    }

    public static void getTiming(long start) {
        System.out.println("Timing: " + (System.nanoTime() - start)/1000000 + " ms");
    }
}
