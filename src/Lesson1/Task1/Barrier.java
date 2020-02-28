package Lesson1.Task1;

public abstract class Barrier {
    private String name;

    public Barrier(String name) {
        this.name = name;
    }

    protected abstract boolean moving(Competitor competitor);

    public String getName() {
        return name;
    }
}

