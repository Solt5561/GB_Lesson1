package Lesson1.Task1;

public class Road extends Barrier {
    private int length;

    public Road(String name, int length) {
        super(name);

        this.length = length;
    }

    public int getLength() {
        return length;
    }

    @Override
    protected boolean moving(Competitor competitor) {
        System.out.println(super.getName() + " длинной: " + this.length);

        competitor.run();

        if (getLength() <= competitor.getRunDistance()) {
            System.out.println("Успешно пробежал");

            return true;
        } else {
            System.out.println("Не пробежал");

            return false;
        }
    }
}
