package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = new Dealer();
	}

	@Test
	void testDeal() {
		dealer.deal();
		assertEquals(2, dealer.getHand().getSize());
	}


	@Test
	void testHit() {
		dealer.deal();
		dealer.hit();
		assertEquals(3, dealer.getHand().getSize());
	}

	@Test
	void testSurrender() {
		dealer.deal();
		dealer.surrender();
		assertEquals(1975, dealer.getBalance());
	}
}