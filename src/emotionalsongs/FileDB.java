package emotionalsongs;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDB<T> implements InterfaceDB<T>
{
	private Path path;
	private File file;
	private FileInputStream input;
	private FileOutputStream output;
	
	public FileDB(String percorsoFile) throws IOException
	{
		if(percorsoFile == null)
			throw new NullPointerException("il percorso non puo' essere null");
		path = Paths.get(percorsoFile);
		file = path.toFile();
		if (!file.exists())
		{
			file.createNewFile();
		}
		if (file.canRead() && file.canWrite())
		{
			input = new FileInputStream(file);
			output = new FileOutputStream(file);
		}
		//else ecezzzione da creare...
	}
	
	public List getAll() throws IOException
	{
		ObjectInputStream objectstream = new ObjectInputStream(input);
		ArrayList<T> lista = new ArrayList<T>();
		try
		{
			T t = (T) objectstream.readObject();    // casting di persona o canzone o playlist
			while (t != null)                        //leggo il file fino a quando non trovo un oggetto null
			{
				lista.add(t);
				t = (T) objectstream.readObject();  // Rileggiamo il file con oggetti
			}
		}
		catch (ClassNotFoundException e)
		{}
		finally
		{
			objectstream.close();
		}
		return lista;
	}
	
	public Optional<T> get(Object id)
	{
		if(id == null)
			throw new NullPointerException("il dato non puo' essere null");
		return null;
	}
	
	public void save(T t) throws IOException
	{
		if(t == null)
			throw new NullPointerException("il dato non puo' essere null");

		ObjectOutputStream objectstream = new ObjectOutputStream(output);
		objectstream.writeObject(t);
		objectstream.flush();
		objectstream.close();
	}
	
	public void update(T base, T modificato)
	{
		if(base == null)
			throw new NullPointerException("il dato da modificare non puo' essere null");
		if(modificato == null)
			throw new NullPointerException("il dato da sostituire non puo' essere null");
	}
	
	public void delete(T t)
	{
	}
}
