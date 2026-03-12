/**
 * ============================================================
 * MAIN CLASS – UseCase2RoomInitialization
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * @author Developer
 * @version 2.1
 */

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 10;
        int doubleAvailability = 6;
        int suiteAvailability = 3;

        System.out.println("Hotel Room Initialization\n");

        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailability + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailability + "\n");

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailability + "\n");
    }
}