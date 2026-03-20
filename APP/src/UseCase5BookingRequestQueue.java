import java.util.LinkedList;
import java.util.Queue;

// Reservation (Actor)
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Booking Request Queue
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added for " + reservation.guestName);
    }

    // Display queue (no processing yet)
    public void displayQueue() {
        System.out.println("\n=== Booking Request Queue ===");

        if (queue.isEmpty()) {
            System.out.println("No requests");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        // Step 1: Guest requests
        bookingQueue.addRequest(new Reservation("Karthik", "Single"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double"));
        bookingQueue.addRequest(new Reservation("Anita", "Suite"));

        // Step 2: Show queue (FIFO order)
        bookingQueue.displayQueue();
    }
}