package curancy;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> they type of number that is used to store the currency value
 */
public abstract class Currency<Type extends Number>
{
	private Type amount;

	public Type getAmount()
	{
		return amount;
	}

	public void setAmount(Type amount)
	{
		this.amount = amount;
	}

	private static final String PREFIX_SYMBOL = "";
	private static final String POSTFIX_SYMBOL = "";

	protected abstract String getFormatString();

	@Override
	public abstract String toString();
}
