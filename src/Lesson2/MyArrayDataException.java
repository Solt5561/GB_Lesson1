package Lesson2;

public class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException (int i , int f){
        super("Не удалось преобразовать элемент массива к числу (" + i + "," + f + ")");
    }
}
