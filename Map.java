import java.util.ArrayList;
import java.util.Random;

public class Map
{
    private Event map[][] = new Event[21][21];
    private int numberOfEvents;

    /**
     * Constructor for the map
     */
    Map()
    {
        for(int i = 0; i < 21; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                map[i][j] = new Event();
            }
        }
    }

    /**
     * Generates a random number of events based on the param
     *
     * @param number Number of events to be generated
     * @return True if data generated, false otherwise
     */

    public boolean generateData(int number)
    {
        numberOfEvents = number;
        if(number > (21 * 21))
        {
            return false;
        }
        Random random = new Random();
        int x;
        int y;
        for(int i = 1; i <= number; i++)
        {
            while(!isOccupied(x = random.nextInt(21), y = random.nextInt(21)))
            {
                map[x][y] = new Event(i, random.nextInt(99) + 1);
                i++;
            }
        }
        return true;
    }

    /**
     * Checks if location (x, y) on the map has an event on it
     *
     * @param x x parameter
     * @param y y parameter
     * @return True if it has an event, false otherwise
     */
    private boolean isOccupied(int x, int y)
    {
        return map[x][y].getId() != 0;
    }

    private int coord(int x)
    {
        return x + 10;
    }

    /**
     * Returns an ArrayList of the closest 5 locations or the total numbers of events if there are fewer than 5 events on the map
     *
     * @param x x parameter
     * @param y y parameter
     * @return ArrayList of the closest 5 locations or the total numbers of events if there are fewer than 5 events on the map
     */
    public ArrayList<Event> getCloseLocations(int x, int y)
    {
        map[x][y].setDistance(0);
        ArrayList<Event> events = new ArrayList<>();
        int number = 5;
        if(numberOfEvents < 5)
        {
            number = numberOfEvents;
        }

        diamondParse(x, y, events, number);
        return events;
    }

    /**
     * Returns an ArrayList of the closest locations based on *the number provided* or the total numbers of events if there are fewer than *the number provided* events on the map
     *
     * @param x      x parameter
     * @param y      y parameter
     * @param number The number of closest locations to be looked for
     * @return ArrayList of the closest locations based on *the number provided* or the total numbers of events if there are fewer than *the number provided* events on the map
     */
    public ArrayList<Event> getCloseLocations(int x, int y, int number)
    {
        map[x][y].setDistance(0);
        ArrayList<Event> events = new ArrayList<>();
        if(number > numberOfEvents)
        {
            number = numberOfEvents;
        }
        diamondParse(x, y, events, number);
        return events;
    }

    /**
     * Parses the matrix in a diamond shape style looking for events, this guarantees that the events found in order are the closest at the same time
     *
     * @param x      x parameter
     * @param y      y parameter
     * @param events The ArrayList in which the parse stores the results
     * @param number The number of closest locations to be looked for
     */
    private void diamondParse(int x, int y, ArrayList<Event> events, int number)
    {
        int limit = 1;
        if(isOccupied(x, y))
        {
            map[x][y].setDistance(0);
            events.add(map[x][y]);
        }
        while(events.size() < number)
        {
            for(int i = x, j = y - limit; j <= y; i--, j++)
            {
                if(i < 0 || j >= map[0].length || j < 0)
                {
                    continue;
                }
                map[i][j].setDistance(limit);
                if(isOccupied(i, j))
                {
                    events.add(map[i][j]);
                    if(events.size() == number)
                    {
                        return;
                    }
                }
            }
            for(int i = x - limit + 1, j = y + 1; i <= x; i++, j++)
            {
                if(i < 0 || j >= map[0].length)
                {
                    continue;
                }
                map[i][j].setDistance(limit);
                if(isOccupied(i, j))
                {
                    events.add(map[i][j]);
                    if(events.size() == number)
                    {
                        return;
                    }
                }
            }
            for(int i = x + 1, j = y + limit - 1; j >= y; i++, j--)
            {
                if(i < 0 || j >= map[0].length || i >= map.length)
                {
                    continue;
                }
                map[i][j].setDistance(limit);
                if(isOccupied(i, j))
                {
                    events.add(map[i][j]);
                    if(events.size() == number)
                    {
                        return;
                    }
                }
            }
            for(int i = x + limit - 1, j = y - 1; i >= x + 1; i--, j--)
            {
                if(i < 0 || j >= map[0].length || j < 0 || i >= map.length)
                {
                    continue;
                }
                map[i][j].setDistance(limit);
                if(isOccupied(i, j))
                {
                    events.add(map[i][j]);
                    if(events.size() == number)
                    {
                        return;
                    }
                }
            }
            limit++;
        }
    }

    /**
     * Returns the map under a string format as a matrix with spaces between event ids
     *
     * @return The map under a string format as a matrix with spaces between event ids
     */

    public String toString()
    {
        StringBuilder mapDisplay = new StringBuilder();
        for(int i = 0; i < 21; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                mapDisplay.append(map[i][j].getId());

                if(map[i][j].getId() / 10 == 0)
                {
                    mapDisplay.append("     ");
                }
                if(map[i][j].getId() / 10 >= 1 && map[i][j].getId() / 10 <= 9)
                {
                    mapDisplay.append("    ");
                }
                if(map[i][j].getId() / 100 >= 1 && map[i][j].getId() / 100 <= 9)
                {
                    mapDisplay.append("   ");
                }
            }
            mapDisplay.append("\n");
        }
        return mapDisplay.toString();
    }

    /**
     * (ONLY ON LINUX terminal!) Returns the map under a string format with colour palette (maximum distance 11 due to constraints of colours) based on the distance from location searched as a matrix with spaces between event ids
     *
     * @return The map under a string format with colour palette (maximum distance 11 due to constraints of colours) based on the distance from location searched as a matrix with spaces between event ids
     */
    public String toStringColour()
    {

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        StringBuilder mapDisplay = new StringBuilder();
        for(int i = 0; i < 21; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                switch(map[i][j].getDistance())
                {
                    case 0:
                        mapDisplay.append(ANSI_RED).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 1:
                        mapDisplay.append(ANSI_GREEN).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 2:
                        mapDisplay.append(ANSI_BLUE).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 3:
                        mapDisplay.append(ANSI_YELLOW).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 4:
                        mapDisplay.append(ANSI_PURPLE).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 5:
                        mapDisplay.append(ANSI_CYAN).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 6:
                        mapDisplay.append(ANSI_GREEN).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 7:
                        mapDisplay.append(ANSI_BLUE).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 8:
                        mapDisplay.append(ANSI_YELLOW).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 9:
                        mapDisplay.append(ANSI_PURPLE).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case 10:
                        mapDisplay.append(ANSI_CYAN).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    case -1:
                        mapDisplay.append(ANSI_BLACK).append(map[i][j].getId()).append(ANSI_RESET);
                        break;
                    default:
                        mapDisplay.append(map[i][j].getId());
                        break;
                }

                if(map[i][j].getId() / 10 == 0)
                {
                    mapDisplay.append("     ");
                }
                if(map[i][j].getId() / 10 >= 1 && map[i][j].getId() / 10 <= 9)
                {
                    mapDisplay.append("    ");
                }
                if(map[i][j].getId() / 100 >= 1 && map[i][j].getId() / 100 <= 9)
                {
                    mapDisplay.append("   ");
                }
            }
            mapDisplay.append("\n");
        }
        return mapDisplay.toString();
    }

    /**
     * Return the map as a Event matrix
     *
     * @return The map as a Event matrix
     */
    public Event[][] getMap()
    {
        return map;
    }
}
