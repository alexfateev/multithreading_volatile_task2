import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    static LongAdder money = new LongAdder();
    static Random random = new Random();

    static int[] getArrayRandom() {
        int[] arr = new int[random.nextInt(10, 20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100, 200);
        }
        return arr;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            int[] arr = getArrayRandom();
            Arrays.stream(arr).forEach(x -> money.add(x));
        });
        Thread thread2 = new Thread(() -> {
            int[] arr = getArrayRandom();
            Arrays.stream(arr).forEach(x -> money.add(x));
        });
        Thread thread3 = new Thread(() -> {
            int[] arr = getArrayRandom();
            Arrays.stream(arr).forEach(x -> money.add(x));
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Общая выручка: " + money.sum());
    }
}