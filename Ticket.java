import java.util.Comparator;
import java.util.Random;

/**
 *
 */
public class Ticket
{
    private int id;
    private Price price;

    /**
     * Constructor for a default ticket
     */
    Ticket()
    {
        id = 0;
        price = new Price(0, "$");
    }

    /**
     * Constructor for a ticket given the id and generating its price randomly in US $
     *
     * @param i Id for the ticket
     */
    public Ticket(int i)
    {
        this.id = i;
        Random random = new Random();
        price = new Price(random.nextInt(200) + 10, "$");
    }

    /**
     * Constructor for a ticket given the id, value and currency
     *
     * @param i        Id of the ticket
     * @param value    Price of the ticket
     * @param currency Currency of the ticket
     */
    public Ticket(int i, int value, String currency)
    {
        this.id = i;
        price = new Price(value, currency);
    }

    /**
     * Returns the price object for the ticket
     *
     * @return Price object for the ticket
     */
    public Price getPrice()
    {
        return price;
    }

    /**
     * Comparator for Collections.sort sorting tickets ArrayList in ascending order based on ticket price
     */
    public static Comparator<Ticket> ticketComparatorAsc = (o1, o2) ->
    {
        int price1 = o1.getPrice().getValue();
        int price2 = o2.getPrice().getValue();

        return price1 - price2;
    };

    /**
     * Comparator for Collections.sort sorting tickets ArrayList in descending order based on ticket price
     */
    public static Comparator<Ticket> ticketComparatorDesc = (o1, o2) ->
    {
        int price1 = o1.getPrice().getValue();
        int price2 = o2.getPrice().getValue();

        return price2 - price1;
    };
}
