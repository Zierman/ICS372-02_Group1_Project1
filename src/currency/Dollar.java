package currency;


/**
 * Represents United States Dollars
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Dollar extends Currency<Double>
{
	/**
	 * serialization version indicator
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The '$' symbol is the prefix
	 */
	private static final String PREFIX_SYMBOL = "$";

	/**
	 * There is no postfix
	 */
	private static final String POSTFIX_SYMBOL = "";

	/**
	 * The max decimal places to be displayed is 2
	 */
	private static final int MAX_DECIMAL_PLACES = 2;

	/**
	 * The format will be #...#.##
	 */
	private static final String FORMAT_STRING = "%." + MAX_DECIMAL_PLACES + "f";

	/**
	 * The amount of money in United States Dollars
	 */
	private Double amount;

	/**
	 * Constructs a <code>Dollar</code> object
	 * 
	 * @param dollars
	 *            the amount of money in United States Dollars.
	 */
	public Dollar(Double dollars)
	{
		this.amount = dollars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#getAmount()
	 */
	public Double getAmount()
	{
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#getFormatString()
	 */
	@Override
	protected String getFormatString()
	{
		return FORMAT_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#setAmount(java.lang.Number)
	 */
	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#toString()
	 */
	@Override
	public String toString()
	{
		return PREFIX_SYMBOL + String.format(getFormatString(), amount)
				+ POSTFIX_SYMBOL;
	}
}
