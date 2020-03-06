package Lesson3;

import java.util.TreeMap;

public class Lesson3 {
    public static void main(String[] args) {
        String [] mass = new String[10];
        mass[0] = "567";
        mass[1] = "555";
        mass[2] = "jsahf";
        mass[3] = "567";
        mass[4] = "fhjkawh";
        mass[5] = "ahk";
        mass[6] = "555";
        mass[7] = "qwwrtr";
        mass[8] = "fhjkawh";
        mass[9] = "3741";

        Task1(mass);

        Task2();

    }

    public static void Task1(String[] mass){
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < mass.length; i++){
            if (treeMap.containsKey(mass[i])){
                treeMap.put(mass[i],treeMap.get(mass[i])+1);
            }else {
                treeMap.put(mass[i], 1);
            }
        }
        System.out.println(treeMap);
    }

    public static void Task2(){

        PhoneList phoneList = new PhoneList();

        phoneList.add("Петя","89542355267");
        phoneList.add("Вася","89542355256");
        phoneList.add("Миша","89545622214");
        phoneList.add("Миша","89542987585");
        phoneList.add("Миша","89542666632");
        phoneList.add("Петя","89542487141");
        phoneList.add("Вася","89542447845");
        phoneList.add("Петя","89547778544");

        System.out.println(phoneList.get("Петя"));
        System.out.println(phoneList.get("Вася"));
        System.out.println(phoneList.get("Миша"));
    }
}
