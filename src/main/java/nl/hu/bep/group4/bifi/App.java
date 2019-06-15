package nl.hu.bep.group4.bifi;

import java.io.IOException;
import java.sql.SQLException;

import nl.hu.bep.group4.bifi.exceptions.GarbageDataException;
import nl.hu.bep.group4.bifi.lader.factories.FactuurLaderFactory;
import nl.hu.bep.group4.bifi.exporter.factories.IEFExporterFactory;

public class App 
{
    public static void main(String[] args) throws ClassNotFoundException, GarbageDataException, SQLException, IOException
    {
    	Controller controller = new Controller(FactuurLaderFactory.createFactuurLader(), IEFExporterFactory.createIEFExporter());
        controller.main(args);
    }
}