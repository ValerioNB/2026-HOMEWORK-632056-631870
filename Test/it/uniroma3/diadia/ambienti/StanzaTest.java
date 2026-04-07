package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	// 1. Dichiariamo le variabili qui fuori per renderle visibili a tutti i test
	private Stanza atrio;
	private Stanza salotto;
	private Attrezzo padella;

	@Before
	public void setUp() {
		this.atrio = new Stanza("Atrio");
		this.salotto = new Stanza("Salotto");
		this.padella = new Attrezzo("padella", 5);
	}

	/* --- Test per Imposta / Get Stanza Adiacente --- */

	@Test
	public void testStanzaIsolata() {
		// L'atrio appena creato nel setUp non ha porte impostate
		assertNull(this.atrio.getStanzaAdiacente("nord"));
	}

	@Test
	public void testImpostaStanzaAdiacente() {
		this.salotto.impostaStanzaAdiacente("nord", this.atrio);
		assertEquals(this.atrio, this.salotto.getStanzaAdiacente("nord"));
	}

	@Test
	public void testStanzaAdiacenteSbagliata() {
		this.salotto.impostaStanzaAdiacente("nord", this.atrio);
		// Abbiamo impostato la porta a nord, quindi a sud non deve esserci nulla
		assertNull(this.salotto.getStanzaAdiacente("sud"));
	}

	/* --- Test per Add / Has / Get Attrezzo --- */

	@Test
	public void testAddAttrezzo() {
		this.atrio.addAttrezzo(this.padella);
		assertTrue(this.atrio.hasAttrezzo("padella"));
	}

	@Test
	public void testAddAttrezzoNull() {
		// Verifica di sicurezza: il metodo deve restituire false se proviamo a inserire null
		assertFalse(this.atrio.addAttrezzo(null));
	}

	@Test
	public void testGetAttrezzoPresente() {
		this.atrio.addAttrezzo(this.padella);
		// assertNotNull controlla semplicemente che il risultato NON sia il vuoto
		assertNotNull(this.atrio.getAttrezzo("padella"));
	}

	@Test
	public void testGetAttrezzoAssente() {
		// Cerchiamo un attrezzo che non abbiamo mai inserito
		assertNull(this.atrio.getAttrezzo("spada"));
	}
}