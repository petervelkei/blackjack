package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = new Dealer();
		dealer.play();
	}

	@Test
	void testDeal() {
		dealer.deal();
		assertEquals(2, dealer.getHand().getKartyakMeret());
	}


	@Test
	void testHit() {
		dealer.deal();
		dealer.hit();
		assertEquals(3, dealer.getHand().getKartyakMeret());
	}

	@Test
	void testSurrender() {
		dealer.deal();
		dealer.surrender();
		assertEquals(1975, dealer.getBalance());
	}
}