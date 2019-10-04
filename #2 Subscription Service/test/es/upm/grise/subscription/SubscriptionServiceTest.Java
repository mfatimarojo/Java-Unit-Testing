/*
 * SubscriptionService
 * Author: Fátima Rojo del Prado 
 */

package es.upm.grise.subscription;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class SubscriptionServiceTest {
	
	private SubscriptionService s;
	
	/**
	 * Creates a SubscriptionService mock and initializes its subscribers list.
	 * It is executed before each test method
	 */
	@Before
	public void setUp() {
		 this.s = new SubscriptionService();// mock(SubscriptionService.class);
		 s.subscribers = new ArrayList<Client>();
	}
	
	
	/**
	 * Tests if adding a null client to the list throws a NullClientException.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test (expected = NullClientException.class)
	public void addNullClientToSubscribers() throws NullClientException, ExistingClientException {
		s.addSubscriber(null);
	}
	
	/**
	 * Tests if a new client is added successfully to the list.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void addNonNullClientToSubscribers() throws NullClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		s.addSubscriber(c1);
		assertEquals(1, s.subscribers.size());
	}
	
	/**
	 * Tests if adding an existing client to the list throws an ExistingClientExpeption.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test(expected = ExistingClientException.class)
	public void addExistingClientToSubscribers() throws NullClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		Client c2 = c1;
		s.addSubscriber(c1);
		assertEquals(c1, c2);
		s.addSubscriber(c2);	
	}
	
	/**
	 * Tests if different clients are added successfully to the list.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void addDifferentClientsToSubscribers() throws NullClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		Client c2 = mock(Client.class);
		Client c3 = mock(Client.class);
		s.addSubscriber(c1);
		s.addSubscriber(c2);
		s.addSubscriber(c3);
		
		assertEquals(3, s.subscribers.size());
	}
	
	
	/**
	 * Tests if removing a null client from the list throws a NullClientException.
	 * @throws NullClientException
	 * @throws NonExistingClientException
	 */
	@Test(expected = NullClientException.class)
	public void removeNullClientFromSubscribers() throws NullClientException, NonExistingClientException {
		s.removeSubscriber(null);
	}
	
	/**
	 * Tests if removing a non existing client throws a NonExistingClientException.
	 * @throws NullClientException
	 * @throws NonExistingClientException
	 */
	@Test(expected = NonExistingClientException.class)
	public void removeNonExistingClientFromSubscribers() throws NullClientException, NonExistingClientException {
		Client c1 = mock(Client.class);
		s.removeSubscriber(c1);
	}
	
	/**
	 * Tests if a client is removed successfully from the list.
	 * @throws NullClientException
	 * @throws NonExistingClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void removeExistingClientFromSubscribers() throws NullClientException, NonExistingClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		s.addSubscriber(c1);
		assertEquals(1, s.subscribers.size());
		
		s.removeSubscriber(c1);
		assertEquals(0, s.subscribers.size());		
	}
	
	/**
	 * Tests if removing the same client twice throws a NonExistingClientException.
	 * @throws NullClientException
	 * @throws NonExistingClientException
	 * @throws ExistingClientException
	 */
	@Test(expected = NonExistingClientException.class)
	public void removeTwiceExistingClientFromSubscribers() throws NullClientException, NonExistingClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		s.addSubscriber(c1);
		assertEquals(1, s.subscribers.size());
		
		s.removeSubscriber(c1);
		assertEquals(0, s.subscribers.size());
		s.removeSubscriber(c1);
	}
	
	/**
	 * Tests if different clients are removed successfully from the list.
	 * @throws NullClientException
	 * @throws NonExistingClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void removeDifferentClientsFromSubscribers() throws NullClientException, NonExistingClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		Client c2 = mock(Client.class);
		s.addSubscriber(c1);
		s.addSubscriber(c2);
		assertEquals(2, s.subscribers.size());
		
		s.removeSubscriber(c1);
		s.removeSubscriber(c2);
		assertEquals(0, s.subscribers.size());
	}
	
	/**
	 * Tests if all clients are removed successfully from the list.
	 * @throws NullClientException
	 * @throws NonExistingClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void removeAllClientsFromSubscribers() throws NullClientException, NonExistingClientException, ExistingClientException {
		for (int i = 0; i < 10; i++) {
			Client c = mock(Client.class);
			s.addSubscriber(c);
		}
		assertEquals(10, s.subscribers.size());
		
		while (s.subscribers.iterator().hasNext())
		{
			Client c = s.subscribers.iterator().next();
			s.removeSubscriber(c);
		}

		assertEquals(0, s.subscribers.size());
	}
	
	/**
	 * Tests a client with email receives the message.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void ExistingClientReceivesMessage() throws NullClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		when(c1.hasEmail()).thenReturn(true);
		assertEquals(true, c1.hasEmail());
		s.addSubscriber(c1);
		assertEquals(1, s.subscribers.size());
		Message msg = mock(Message.class);
		s.sendMessage(msg);
		verify(c1, times(1)).receiveMessage(msg);
	}
	
	/**
	 * Tests a client with no email does not receive the message. 
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void ExistingClientDoesNotReceiveMessage() throws NullClientException, ExistingClientException {
		Client c1 = mock(Client.class);
		when(c1.hasEmail()).thenReturn(false);
		assertEquals(false, c1.hasEmail());
		s.addSubscriber(c1);
		assertEquals(1, s.subscribers.size());
		Message msg = mock(Message.class);
		s.sendMessage(msg);
		verify(c1,times(0)).receiveMessage(msg);
	}
	
	/**
	 * Tests every client with email receives the message.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 */
	@Test
	public void ExistingClientsReceiveMessage() throws NullClientException, ExistingClientException {
		for (int i = 0; i < 10; i++) {
			Client c = mock(Client.class);
			when(c.hasEmail()).thenReturn(true);
			assertEquals(true, c.hasEmail());
			s.addSubscriber(c);
		}
		assertEquals(10, s.subscribers.size());
		
		Message msg = mock(Message.class);
		s.sendMessage(msg);
		
		for (Client c : s.subscribers) {
			verify(c,times(1)).receiveMessage(msg);
		}
	}
	
	
	/**
	 * Tests an ex-client does not receive the message.
	 * @throws NullClientException
	 * @throws ExistingClientException
	 * @throws NonExistingClientException
	 */
	@Test
	public void ExClientDoesNotReceiveMessage() throws NullClientException, ExistingClientException, NonExistingClientException {
		Client c1 = mock(Client.class);
		when(c1.hasEmail()).thenReturn(true);
		assertEquals(true, c1.hasEmail());
		s.addSubscriber(c1);
		assertEquals(1, s.subscribers.size());
		//First, we send a message to the list and check if c1 receives it
		Message msg = mock(Message.class);
		s.sendMessage(msg);
		verify(c1, times(1)).receiveMessage(msg);
		
		//Then, we remove the client from the list and check if c1 still receives the message.
		//doCallRealMethod().when(s).removeSubscriber(c1);
		s.removeSubscriber(c1);
		assertEquals(0, s.subscribers.size());
		s.sendMessage(msg);
		//If c1 was still part of the subscribers he received the second message,
		//however, as c1 is not a subscriber anymore, he will not receive the second message.
		verify(c1, times(1)).receiveMessage(msg);		
	}
}
