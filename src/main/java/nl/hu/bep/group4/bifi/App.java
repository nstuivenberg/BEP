package nl.hu.bep.group4.bifi;

import java.io.IOException;
import java.sql.SQLException;

import nl.hu.bep.group4.bifi.exceptions.GarbageDataException;
import nl.hu.bep.group4.bifi.lader.factories.FactuurLaderFactory;

public class App 
{
    public static void main(String[] args) throws ClassNotFoundException, GarbageDataException, SQLException, IOException
    {
    	//TODO: add factory for IEFExporter
    	new Controller(FactuurLaderFactory.createFactuurLader(), null).main(args);
    }
}