package nl.hu.bep.group4.bifi;

import ADDRLOOKUPER.AddressLookerUPAlreadyCloosed;
import ADDRLOOKUPER.AddressLookerUPAlreadyLookinUP;
import ADDRLOOKUPER.AddressLookerUPAlreadyReadyToLookUP;
import ADDRLOOKUPER.AddressLookerUpInWrongStateException;
import ADDRLOOKUPER.LOOKUP_AdDDR;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws AddressLookerUpInWrongStateException, AddressLookerUPAlreadyLookinUP, AddressLookerUPAlreadyReadyToLookUP, AddressLookerUPAlreadyCloosed
    {
    	LOOKUP_AdDDR.scanStart();
    	System.out.println(LOOKUP_AdDDR.scanForward("MOATA"));
    	LOOKUP_AdDDR.scanStop();
    }
}
