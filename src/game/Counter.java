// ID: 315126433
package game;

/**
 * @author Adi Yadlin
 * Counter.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     * @param counter initial value for counter
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * add number to current count.
     * @param number the number to add
     */
    public void increase(int number) {
        this.counter = counter + number;
    }

    /**
     * subtract number from current count.
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.counter = counter - number;
    }

    /**
     * get current count.
     * @return the currents counter
     */
    public int getValue() {
        return this.counter;
    }
}
