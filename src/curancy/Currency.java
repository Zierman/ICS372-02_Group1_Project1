package curancy;

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
