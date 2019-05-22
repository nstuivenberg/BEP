package nl.hu.bep.group4.bifi;

import nl.hu.bep.group4.bifi.lader.factories.FactuurLaderFactory;

/*import ADDRLOOKUPER.AddressLookerUPAlreadyCloosed;
import ADDRLOOKUPER.AddressLookerUPAlreadyLookinUP;
import ADDRLOOKUPER.AddressLookerUPAlreadyReadyToLookUP;
import ADDRLOOKUPER.AddressLookerUpInWrongStateException;
import ADDRLOOKUPER.LOOKUP_AdDDR;
/**
 * Hello world!
 *
 *
public class App 
{
    public static void main( String[] args ) throws AddressLookerUpInWrongStateException, AddressLookerUPAlreadyLookinUP, AddressLookerUPAlreadyReadyToLookUP, AddressLookerUPAlreadyCloosed
    {
    	LOOKUP_AdDDR.scanStart();
    	System.out.println(LOOKUP_AdDDR.scanForward("MOATA"));
    	LOOKUP_AdDDR.scanStop();
    }
}*/
public class App 
{
    public static void main(String[] args)
    {
    	//TODO: add factory for IEFExporter
    	new Controller(FactuurLaderFactory.createFactuurLader(), null).main(args);
    }
}