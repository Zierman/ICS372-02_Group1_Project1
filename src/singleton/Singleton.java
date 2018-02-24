package singleton;
/**
 * 
 */

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * note: simply implementing this does not make it a singleton, it only forces a readResolve method.
 */
public interface Singleton<Type>
{
	public Type readResolve();
}
