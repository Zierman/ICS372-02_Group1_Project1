package currency;

import java.io.Serializable;

/**
 * An abstract class that is used when creating other types of currency. The
 * separation of the helper class was needed because of an issue I was having
 * when working in a single class.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type>
 *            they type of number that is used to store the currency value
 */
public abstract class CurrencyHelper<Type extends Number & Comparable<Type>>
		implements Serializable, Comparable<CurrencyHelper<Type>>
{
	/**
	 * the version of the serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the numeric value that represents the amount of the currency.
	 */
	private Type amount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CurrencyHelper<Type> arg)
	{
		return this.getAmount().compareTo(arg.getAmount());
	}

	/**
	 * Gets the amount of currency.
	 * 
	 * @return the amount of currency.
	 */
	public Type getAmount()
	{
		return amount;
	}

	/**
	 * a string that determines how the numeric value will be converted to a
	 * <code>String</code>. Uses the same format string as
	 * {@link String#format(String, Object...)}.
	 * 
	 * @return
	 */
	protected abstract String getFormatString();

	/**
	 * Sets the amount of currency.
	 * 
	 * @param amount
	 *            the amount of currency.
	 */
	public void setAmount(Type amount)
	{
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
}
