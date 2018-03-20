package exceptions;

import java.util.Date;

import play.Play;

public class DateOutOfBoundsException extends Exception
{

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	Play play = null;
	Date date = null;

	public DateOutOfBoundsException(Date date)
	{
		this.date = date;
	}

	public DateOutOfBoundsException(Date date, Play play)
	{
		this.date = date;
		this.play = play;
	}

	public DateOutOfBoundsException(Play play)
	{
		this.play = play;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @return the play
	 */
	public Play getPlay()
	{
		return play;
	}

}
