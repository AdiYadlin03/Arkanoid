// ID: 315126433
package logic;

/**
 * @author Adi Yadlin
 * This represents a notifier for the hits.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener
     */
    void removeHitListener(HitListener hl);
}
