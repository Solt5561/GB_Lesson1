package Lesson1.Task1;

import java.util.Random;

public class Start {
    public static void main (String[] args) {
        Random random = new Random();
        Competitor[] competitors = {
                new Human("Bob", random.nextInt(10),random.nextInt(10)),
                new Robot("01010101",random.nextInt(10),random.nextInt(10)),
                new Cat("Barsik", random.nextInt(10), random.nextInt(10))
        };

        Barrier[] barriers = new Barrier[6];

        boolean isRoad;
        for (int i = 0; i < barriers.length; i++) {
            int distance = random.nextInt(10);
            isRoad = random.nextBoolean();
            if (isRoad) {
                barriers[i] = new Road("Беговая дорожка", distance);
            } else {
                barriers[i] = new Wall("Стена", distance);
            }
        }

        for (int i = 0; i < competitors.length; i++) {
            boolean result = true;
            for (int j = 0; j < barriers.length; j++) {
                result = barriers[j].moving(competitors[i]);

                if (!result) {
                    break;
                }
            }

            if (result) {
                System.out.println("Успешно прошел испытания!!");
            } else {
                System.out.println("Провалился!!");
            }
        }
    }
}