/** ISURU SAPUMAL (w1583345//2015562 )
 * Created by Isuru on 4/2/2017.
 */
public class StopWatchNano {
    private final long start;

    public StopWatchNano() {
        start = System.nanoTime();
    }

    public double elapsedTime() {
        long now = System.nanoTime();
        return (now - start)/1000;
    }



}
