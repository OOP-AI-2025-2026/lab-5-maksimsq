package ua.opnu;

public class TimeSpan {

    private int hours;
    private int minutes;

    // Конструктор без аргументів
    public TimeSpan() {
        this(0, 0);
    }

    // Конструктор з 1 аргументом (хвилини)
    public TimeSpan(int minutes) {
        this(minutes / 60, minutes % 60);
    }

    // Конструктор з 2 аргументами (години та хвилини)
    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            this.hours = 0;
            this.minutes = 0;
        } else {
            this.hours = hours;
            this.minutes = minutes;
        }
    }

    // Конструктор з 1 аргументом типу TimeSpan
    public TimeSpan(TimeSpan other) {
        this(other.getHours(), other.getMinutes());
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    // Метод додавання: 2 аргументи (години та хвилини)
    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            return;
        }

        int totalMinutes = this.minutes + minutes;
        this.hours += hours + totalMinutes / 60;
        this.minutes = totalMinutes % 60;
    }

    // Метод додавання: 1 аргумент (хвилини)
    public void add(int minutes) {
        if (minutes < 0) return;
        add(minutes / 60, minutes % 60);
    }

    // Метод додавання: 1 аргумент типу TimeSpan
    public void add(TimeSpan timespan) {
        add(timespan.getHours(), timespan.getMinutes());
    }

    public double getTotalHours() {
        return hours + minutes / 60.0;
    }

    public int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    // Метод віднімання: 1 аргумент типу TimeSpan
    public void subtract(TimeSpan span) {
        subtract(span.getHours(), span.getMinutes());
    }

    // Метод віднімання: 1 аргумент (хвилини)
    public void subtract(int minutes) {
        if (minutes < 0) return;
        subtract(minutes / 60, minutes % 60);
    }

    // Метод віднімання: 2 аргументи (години та хвилини)
    public void subtract(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            return;
        }

        int totalCurrent = getTotalMinutes();
        int totalSubtract = hours * 60 + minutes;

        if (totalSubtract > totalCurrent) {
            return;
        }

        int result = totalCurrent - totalSubtract;
        this.hours = result / 60;
        this.minutes = result % 60;
    }

    public void scale(int factor) {
        if (factor <= 0) return;

        int total = getTotalMinutes() * factor;
        this.hours = total / 60;
        this.minutes = total % 60;
    }
}