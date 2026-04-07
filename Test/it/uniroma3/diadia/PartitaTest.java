package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita p;

	@Before
	public void setUp() {
		// Viene preparata una nuova partita "fresca" prima di ogni test
		this.p = new Partita();
	}

	/* --- Test per il metodo isFinita --- */

	@Test
	public void testPartitaNonFinitaAllInizio() {
		assertFalse(this.p.isFinita());
	}

	@Test
	public void testPartitaFinitaQuandoVinta() {
		// Trasportiamo magicamente il giocatore nella stanza vincente
		this.p.setStanzaCorrente(this.p.getLabirinto().getStanzaFinale());
		assertTrue(this.p.isFinita());
	}

	@Test
	public void testPartitaFinitaQuandoZeroCfu() {
		this.p.getGiocatore().setCfu(0);
		assertTrue(this.p.isFinita());
	}

	/* --- Test per il metodo vinta --- */

	@Test
	public void testVintaAllInizio() {
		// All'inizio il giocatore è nell'Atrio, la vincente è la Biblioteca
		assertFalse(this.p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteNonVincente() {
		Stanza stanzaQualsiasi = new Stanza("Aula a caso");
		this.p.setStanzaCorrente(stanzaQualsiasi);
		assertFalse(this.p.vinta());
	}

	@Test
	public void testVintaDopoSetStanzaCorrenteVincente() {
		this.p.setStanzaCorrente(this.p.getLabirinto().getStanzaFinale());
		assertTrue(this.p.vinta());
	}

	/* --- Test per i CFU (e altri metodi iniziali) --- */

	@Test
	public void testGetCfuAllInizio() {
		assertEquals(20, this.p.getGiocatore().getCfu());
	}

	@Test
	public void testGetCfuDopoSet() {
		this.p.getGiocatore().setCfu(15);
		assertEquals(15, this.p.getGiocatore().getCfu());
	}
	
	@Test
	public void testGetStanzaCorrenteAllInizio() {
		// Sappiamo che la partita inizia sempre nell'Atrio
		assertEquals("Atrio", this.p.getStanzaCorrente().getNome());
	}

}