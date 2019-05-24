package nl.hu.bep.group4.bifi;

import nl.hu.bep.group4.bifi.lader.factories.FactuurLaderFactory;

public class App 
{
    public static void main(String[] args)
    {
    	//TODO: add factory for IEFExporter
    	new Controller(FactuurLaderFactory.createFactuurLader(), null).main(args);
    }
}