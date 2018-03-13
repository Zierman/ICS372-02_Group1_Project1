package exceptions;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class OutOfBoundsException extends Exception
{
	String message = null;

	public OutOfBoundsException(String message)
	{
		this.message = message;
	}

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

}
