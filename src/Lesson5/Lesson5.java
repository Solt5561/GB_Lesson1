package Lesson5;

import java.util.Arrays;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.System.currentTimeMillis;

public class Lesson5 {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        final int THREADS_COUNT = 4;

        final int PART_SIZE = SIZE / THREADS_COUNT;
        float[] mas = new float[SIZE];
        Arrays.fill(mas, 1f);
        long a = currentTimeMillis();
        final float[][] m = new float[THREADS_COUNT][PART_SIZE];
        Thread[] t = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            System.arraycopy(mas, PART_SIZE * i, m[i], 0, PART_SIZE);
            final int u = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int n = u * PART_SIZE;
                    for (int j = 0; j < PART_SIZE; j++, n++) {
                        m[u][j] = (float) (m[u][j] * sin(0.2f + n / 5) * cos(0.2f + n / 5) * cos(0.4f + n / 2));
                    }
                }
            });
            t[i].start();
        }
        try {
            for (int i = 0; i < THREADS_COUNT; i++) {
                t[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < THREADS_COUNT; i++) {
            System.arraycopy(m[i], 0, mas, i * PART_SIZE, PART_SIZE);
        }
        System.out.println(currentTimeMillis() - a);
    }
}