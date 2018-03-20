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
public class Dollar extends Currency<Dollar, Double>
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
	 * The max decimal places to be displayed is 2
	 */
	private static final int MAX_DECIMAL_PLACES = 2;

	/**
	 * The format will be #...#.##
	 */
	private static final String FORMAT_STRING = "%." + MAX_DECIMAL_PLACES + "f";

	public static void main(String[] args)
	{
		System.out.println(new Dollar(10.00));
	}

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
	public Dollar(Double dollars) // unintended Trigun reference (^_^)
	{
		this.amount = dollars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#addTogether(currency.CurrencyHelper)
	 */
	@Override
	public Dollar addTogether(Dollar currency)
	{
		return new Dollar(this.amount + currency.getAmount());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#getAmount()
	 */
	public Double getAmount()
	{
		return this.amount;
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
	 * @see currency.Currency#half()
	 */
	@Override
	public Dollar half()
	{
		return new Dollar(this.amount / 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#multiplyBy(double)
	 */
	@Override
	public Dollar multiplyBy(double d)
	{
		return new Dollar(this.amount * d);
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
	 * @see currency.Currency#subtract(currency.CurrencyHelper)
	 */
	@Override
	public Dollar subtract(Dollar currency)
	{
		return new Dollar(this.amount - currency.getAmount());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see currency.Currency#toString()
	 */
	@Override
	public String toString()
	{
		return PREFIX_SYMBOL + String.format(getFormatString(), this.amount);
	}
}
