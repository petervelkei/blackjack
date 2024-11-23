package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = new Dealer();
		dealer.jatekIndit();
	}

	@Test
	void testOsztas() {
		dealer.osztas();
		assertEquals(2, dealer.getJatekosKez().getKartyakMeret());
	}
	
	@Test
	void testHuzas() {
		dealer.osztas();
		dealer.huzas();
		assertEquals(3, dealer.getJatekosKez().getKartyakMeret());
	}

}