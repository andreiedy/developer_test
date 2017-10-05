public class Price
{
    //public static String US_CURRENCY = "$";
    //public static String UK_CURRENCY = "£";

    private int value;
    private String currency;

    /**
     * Contructor for default ticket price
     */
    Price()
    {
        value = 0;
        currency = "$";
    }

    /**
     * Contstructor for a price with given value and currency
     *
     * @param value
     * @param currency
     */
    public Price(int value, String currency)
    {
        this.value = value;
        this.currency = currency;
    }

    /**
     * Returns the value of the price
     *
     * @return Value of the price
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Returns the currency of the price
     *
     * @return Currency of the price
     */
    public String getCurrency()
    {
        return currency;
    }
}
