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
public abstract class Currency<CurrencyType extends CurrencyHelper<Type>, Type extends Number & Comparable<Type>> extends CurrencyHelper<Type> implements Serializable, Comparable<CurrencyHelper<Type>> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract CurrencyType addTogether(CurrencyType currency);
	public abstract CurrencyType half();
	public abstract CurrencyType multiplyBy(double d);
	public abstract CurrencyType subtract(CurrencyType currency);
	
}
