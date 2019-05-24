package nl.hu.bep.group4.bifi;

import nl.hu.bep.group4.bifi.interfaces.FactuurLader;
import nl.hu.bep.group4.bifi.interfaces.IEFExporter;

public class Controller {
	private FactuurLader factuurLader;
	private IEFExporter iefExporter;
	
	public Controller(FactuurLader factuurLader, IEFExporter iefExporter) {
		this.factuurLader = factuurLader;
		this.iefExporter = iefExporter;
	}
	
	public void main(String[] args) {
		int maandNummer = Integer.parseInt(args[0]);
		this.iefExporter.exportFacturen(this.factuurLader.getFacturenVoorMaand(maandNummer));
	}
}
