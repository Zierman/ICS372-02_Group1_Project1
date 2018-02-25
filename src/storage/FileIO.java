package storage;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class that makes FileIO simpler.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class FileIO implements Closeable
{
	/**
	 * a stream that is used to input objects.
	 */
	private ObjectInputStream objectInputStream = null;

	/**
	 * a stram that is used to output objects.
	 */
	private ObjectOutputStream objectOutputStream = null;

	/**
	 * Creates a FileIOobject for input
	 * @param objectInputStream the stream to be used for inputing objects
	 */
	private FileIO(ObjectInputStream objectInputStream)
	{
		this.objectInputStream = objectInputStream;
	}

	/**Creates a FileIOobject for output
	 * @param objectOutputStream the stream to be used for outputing objects
	 */
	private FileIO(ObjectOutputStream objectOutputStream)
	{
		this.objectOutputStream = objectOutputStream;
	}

	/**
	 * Creates a new FileIO object for reading
	 * @param filename a <code>String</code> that represents the name of the file objects will be read from.
	 * @return a FileIO object set up to read from a file with the given filename
	 * @throws FileNotFoundException if the file was not found.
	 * @throws IOException if there is a problem during FileIO.
	 */
	public static FileIO startRead(String filename)
			throws FileNotFoundException, IOException
	{
		FileInputStream tmpFileInputStream = new FileInputStream(filename);
		ObjectInputStream tmpObjectInputStream = new ObjectInputStream(
				tmpFileInputStream);
		return new FileIO(tmpObjectInputStream);
	}
	
	/**
	 * Reads an object from the file.
	 * @return an <code>Object</code> that was read from the file
	 * @throws IOException if there is a problem during FileIO.
	 * @throws ClassNotFoundException if there is a problem with finding the class
	 */
	public Object read() throws IOException, ClassNotFoundException
	{
		return objectInputStream.readObject();
	}
	
	/**
	 * Creates a <code>FileIO</code> object to write objects to storage with.
	 * @param filename a <code>String</code> that holds the filename that the objects will be written to.
	 * @return a FileIO object set up for output.
	 * @throws FileNotFoundException if the file was not found.
	 */
	public static FileIO startWrite(String filename)
			throws FileNotFoundException, IOException
	{
		FileOutputStream tmpFileOutputStream = new FileOutputStream(filename);
		ObjectOutputStream tmpObjectOutputStream = new ObjectOutputStream(
				tmpFileOutputStream);
		return new FileIO(tmpObjectOutputStream);
	}

	/**
	 * Writes an <code>Object</code> to the file
	 * @param object the <code>Object</code> to be written to file.
	 * @throws IOException if there is a problem during FileIO.
	 */
	public void write(Object object) throws IOException
	{
		objectOutputStream.writeObject(object);
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException
	{
		if (objectInputStream != null)
		{
			objectInputStream.close();
		}
		if (objectOutputStream != null)
		{
			objectOutputStream.close();
		}

	}
}
