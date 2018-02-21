package curancy;

public class Dollar extends Currency<Double>
{
	private Double amount;
	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	private static final String PREFIX_SYMBOL = "$";
	private static final String POSTFIX_SYMBOL = "";
	private static final int MAX_DECIMAL_PLACES = 2;
	private static final String FORMAT_STRING = "%." + MAX_DECIMAL_PLACES + "f";
	
	public Dollar(Double dollars)
	{
		this.amount = dollars;
	}
	
	@Override
	protected String getFormatString()
	{
		return FORMAT_STRING;
	}
	
	@Override
	public String toString()
	{
		return PREFIX_SYMBOL + String.format(getFormatString(), amount) + POSTFIX_SYMBOL;
	}
}
