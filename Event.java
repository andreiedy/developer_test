import java.util.ArrayList;

public class Event
{
    private int id;
    private int distance;
    private ArrayList<Ticket> tickets;

    /**
     * Constructor for a default event
     */
    Event()
    {
        id = 0;
        distance = -1;
    }

    /**
     * Constructor for an event with a given id and a given number of tickets
     *
     * @param id The id given to the event
     * @param i  The number of tickets to be created for this event
     */
    Event(int id, int i)
    {
        this.id = id;
        distance = -1;
        generateTickets(i);
    }

    /**
     * Generates tickets for this event
     *
     * @param i Number of tickets to be created
     */
    private void generateTickets(int i)
    {
        tickets = new ArrayList<>();
        for(int j = 0; j < i; j++)
        {
            tickets.add(new Ticket(j + 1));
        }
    }

    /**
     * Returns id of the event
     *
     * @return Id of the event
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns distance of the event from searched location
     *
     * @return Distance of the event from searched location
     */
    public int getDistance()
    {
        return distance;
    }

    /**
     * Returns the ticket ArrayList for this event
     *
     * @return ArrayList of tickets for this event
     */
    public ArrayList<Ticket> getTickets()
    {
        return tickets;
    }

    /**
     * Sets the distance from searched location for this event
     *
     * @param distance Distance from searched location for this event
     */
    public void setDistance(int distance)
    {
        this.distance = distance;
    }
}
