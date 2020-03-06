package Lesson2;

import java.util.Scanner;

public class Lesson2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число строк: ");
        int i = scanner.nextInt();
        System.out.println("Введите число столбцов: ");
        int f = scanner.nextInt();

        String[][] str = new String[i][f];
        for (i = 0; i < i; i++) {
            for (f = 0; f < f; f++) {
                System.out.println("Введите строку ["+i+","+f+"]: ");
                str [i][f] = scanner.next();

            }

        }
        getArraySumm(str);
    }

    private static void getArraySumm(String[][] str) {
        if (str.length != 4){
            throw new MySizeArrayException();
        }
        for (int i = 0; i < str.length; i++){
            if (str[i].length !=4){
                throw new MySizeArrayException();
            }
        }

        int sum = 0;
        for (int i = 0; i < str.length; i++){
            for (int f = 0; f < str[i].length; f++){
                inputData(str[i][f],i,f);
                sum += Integer.parseInt(str[i][f]);
            }
        }
        System.out.println("Результат: ");
        System.out.println(sum);
    }

    private static void inputData(String str, int x, int y) {
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) < '0' || str.charAt(i) > '9'){
                throw  new MyArrayDataException(x,y);
            }
        }
    }

}
