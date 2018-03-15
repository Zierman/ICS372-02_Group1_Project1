package visitor;

/**
 * Interfacefor Visitable objects
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Visitable
{
	/**
	 * accepts a visitor
	 * @param visitor the visitor to accept
	 */
	public void accept(Visitor visitor);
}
