package currency;

import java.io.Serializable;

import misc.Parsable;

/**
 * An abstract class that is used when creating other types of currency.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type>
 *            they type of number that is used to store the currency value
 */
public abstract class Currency<Type extends Number> implements Serializable, Parsable<Type>
{
	/**
	 * the version of the serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * a <code>String</code> that goes in front of the amount when converting to
	 * <code>String</code>.
	 */
	private static final String PREFIX_SYMBOL = "";

	/**
	 * a <code>String</code> that goes in front of the amount when converting to
	 * <code>String</code>.
	 */
	private static final String POSTFIX_SYMBOL = "";

	/**
	 * the numeric value that represents the amount of the currency.
	 */
	private Type amount;

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
	 * <code>String</code>. Uses the same format string as {@link String#format(String, Object...)}.
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
