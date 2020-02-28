package Lesson1.Task1;

public class Wall extends Barrier {

    private int heigth;

    public Wall(String name, int heigth) {
        super(name);

        this.heigth = heigth;
    }

    public int getHeigth() {
        return heigth;
    }

    @Override
    protected boolean moving(Competitor competitor) {
        System.out.println(super.getName() + " высотой: " + this.heigth);

        competitor.jump();

        if (getHeigth() <= competitor.getJumpHeight()) {
            System.out.println("Успешно перепрынул");

            return true;
        } else {
            System.out.println("не перепрыгнул");

            return false;
        }
    }
}

