package exceptions;

import java.util.Date;

import play.Play;

public class DateOutOfBoundsException extends Exception
{

	Play play = null;
	Date date = null;
	public DateOutOfBoundsException(Play play)
	{
		this.play = play;
	}
	public DateOutOfBoundsException(Date date)
	{
		this.date = date;
	}
	public DateOutOfBoundsException(Date date, Play play)
	{
		this.date = date;
		this.play = play;
	}

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return the play
	 */
	public Play getPlay()
	{
		return play;
	}
	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

}
