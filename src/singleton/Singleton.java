package singleton;
/**
 * 
 */

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Singleton<Type>
{
	public Type readResolve();
}
