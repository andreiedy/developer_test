import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Create a new map
        Map map = new Map();
        // Random number generator
        Random random = new Random();
        // Generate data for a -10 to +10 map
        map.generateData(random.nextInt(21 * 21));

        // Scanner for input
        Scanner scanner = new Scanner(System.in);
        // Get the input
        String location = scanner.next();
        // Parse for x
        int x = Integer.parseInt(location.split("[,]+")[0]) + 10;
        // Parse for y
        int y = Integer.parseInt(location.split("[,]+")[1]) + 10;
        // Parse for number of closest locations if set
        int number;
        if(location.split("[,]+").length == 3)
        {
            number = Integer.parseInt(location.split("[,]+")[2]);
        }
        else
        {
            number = 5;
        }

        // Used to search for closest 5 locations
        //ArrayList<Event> events = map.getCloseLocations(x, y);

        // Used if you want to search for a different number of locations than 5
        ArrayList<Event> events = map.getCloseLocations(x, y, number);

        // Prints out the map and the locations' Event ids
        //System.out.println(map.toString());

        // Prints out the map and the locations' Event ids using a colour palette (only on Linux terminal!)
        System.out.println(map.toStringColour());

        System.out.println("Closest Events to (" + (x - 10) + "," + (y - 10) + "):");
        for(Event event : events)
        {
            event.getTickets().sort(Ticket.ticketComparatorAsc);
            System.out.println("Event " + event.getId() + " - " + event.getTickets().get(0).getPrice().getCurrency() + event.getTickets().get(0).getPrice().getValue() + ", Distance " + event.getDistance());
        }
    }
}