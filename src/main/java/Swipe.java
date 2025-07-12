import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;

/**
 * Helper class for performing swipe gestures in Appium tests.
 * Supports multiple directions and swipe speeds.
 *
 * <p><b>Directions:</b> LEFT, RIGHT, UP, DOWN</p>
 * <p><b>Speed:</b> VERYFAST, FAST, NORMAL, SLOW</p>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * swipeHelper.performSwipe(Directions.RIGHT, Speed.NORMAL);
 * </pre>
 */
public class Swipe {

    private final AppiumDriver driver;

    /**
     * Initializes the SwipeHelper with the given Appium driver.
     *
     * @param driver AppiumDriver instance.
     */
    public Swipe(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Enumeration of swipe directions.
     */
    public enum Directions {
        LEFT, RIGHT, UP, DOWN
    }

    /**
     * Enumeration of swipe speeds.
     */
    public enum Speed {
        VERY_FAST, FAST, NORMAL, SLIGHTLY_SLOW,SLOW
    }

    /**
     * Performs a single swipe in the specified direction and speed.
     * Swipe distance is fixed (80% to 20% of screen).
     *
     * @param direction Direction of the swipe.
     * @param speed     Speed of the swipe.
     */
    public void swipe(Directions direction, Speed speed) {
        int[] coords = getSwipeCoordinates(direction);
        Duration duration = getDurationForSpeed(speed);
        swipe(coords[0], coords[1], coords[2], coords[3], duration);
    }

    /**
     * Performs multiple swipe gestures in the specified direction and speed.
     *
     * @param direction   Direction of the swipe.
     * @param speed       Speed of the swipe.
     * @param repeatCount Number of times to repeat the swipe. Must be >= 1.
     * @throws IllegalArgumentException if repeatCount is less than 1.
     */
    public void swipeMultiple(Directions direction, Speed speed, int repeatCount) {
        if (repeatCount < 1) {
            throw new IllegalArgumentException("repeatCount must be >= 1");
        }

        int[] coords = getSwipeCoordinates(direction);
        Duration duration = getDurationForSpeed(speed);

        for (int i = 0; i < repeatCount; i++) {
            swipe(coords[0], coords[1], coords[2], coords[3], duration);
            try {
                Thread.sleep(300); // Optional delay between swipes
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Executes a swipe gesture from start coordinates to end coordinates with the specified duration.
     *
     * @param startX   Starting X coordinate.
     * @param startY   Starting Y coordinate.
     * @param endX     Ending X coordinate.
     * @param endY     Ending Y coordinate.
     * @param duration Duration of the swipe.
     */
    private void swipe(int startX, int startY, int endX, int endY, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Returns the swipe coordinates (startX, startY, endX, endY) for the specified direction.
     *
     * @param direction Direction of the swipe.
     * @return An int array containing startX, startY, endX, endY.
     */
    private int[] getSwipeCoordinates(Directions direction) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int midX = width / 2;
        int midY = height / 2;

        int startX = 0, startY = 0, endX = 0, endY = 0;

        switch (direction) {
            case LEFT:
                startX = (int) (width * 0.8);
                endX = (int) (width * 0.2);
                startY = endY = midY;
                break;
            case RIGHT:
                startX = (int) (width * 0.2);
                endX = (int) (width * 0.8);
                startY = endY = midY;
                break;
            case UP:
                startY = (int) (height * 0.8);
                endY = (int) (height * 0.2);
                startX = endX = midX;
                break;
            case DOWN:
                startY = (int) (height * 0.2);
                endY = (int) (height * 0.8);
                startX = endX = midX;
                break;
        }

        return new int[]{startX, startY, endX, endY};
    }

    /**
     * Returns the swipe duration for the specified speed.
     *
     * @param speed Swipe speed.
     * @return Duration of the swipe.
     */
    private Duration getDurationForSpeed(Speed speed) {
        switch (speed) {
            case VERY_FAST:
                return Duration.ofMillis(100);
            case FAST:
                return Duration.ofMillis(250);
            case NORMAL:
                return Duration.ofMillis(500);
            case SLIGHTLY_SLOW:
                return Duration.ofMillis(750);
            case SLOW:
                return Duration.ofMillis(1000);
            default:
                throw new IllegalArgumentException("Unsupported speed: " + speed);
        }
    }
}
