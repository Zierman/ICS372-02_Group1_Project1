package currency;

import java.io.Serializable;

/**
 * An abstract class that is used when creating other types of currency.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type>
 *            they type of number that is used to store the currency value
 */
public abstract class Currency<CurrencyType extends CurrencyHelper<Type>, Type extends Number & Comparable<Type>>
		extends CurrencyHelper<Type>
		implements Serializable, Comparable<CurrencyHelper<Type>>
{

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Adds the two currencies together
	 * 
	 * @param currency
	 *            the currency to add
	 * @return a new Currency object that holds the sum of the two that where
	 *         added
	 */
	public abstract CurrencyType addTogether(CurrencyType currency);

	/**
	 * halves this currency
	 * 
	 * @return a new currency object that has a value half the operand
	 */
	public abstract CurrencyType half();

	/**
	 * Multiplies this currency by a double
	 * 
	 * @param d
	 *            the double that this currency is multiplied by
	 * @return a new currency object that has an amount equal to this' amount
	 *         multiplied by d.
	 */
	public abstract CurrencyType multiplyBy(double d);

	/**
	 * Subtracts a currency from this
	 * 
	 * @param currency
	 *            the currency to subtract from this
	 * @return the new currency object that has an amount equal to this' amount
	 *         minus the amount of the parameter currency
	 */
	public abstract CurrencyType subtract(CurrencyType currency);

}
