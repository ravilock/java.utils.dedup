package queue;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class QueueTest {

	@Test
	void testQueue() {
		Queue<Integer> q = new Queue<Integer>();
		assertTrue(q.isEmpty());
	}

	@Test
	void testQueueT() {
		Queue<Integer> q = new Queue<Integer>(1);
		assertFalse(q.isEmpty());
	}

	@Test
	void testEnqueue() {
		Queue<Integer> q = new Queue<Integer>();
		assertTrue(q.isEmpty());
		q.enqueue(1);
		assertFalse(q.isEmpty());
	}

	@Test
	void testEnqueueWhenNotEmpty() {
		Queue<Integer> q = new Queue<Integer>(1);
		assertFalse(q.isEmpty());
		q.enqueue(2);
		assertFalse(q.isEmpty());
	}
	
	@Test
	void testDequeue() {
		Queue<Integer> q = new Queue<Integer>();
		assertTrue(q.isEmpty());
		q.enqueue(1);
		assertFalse(q.isEmpty());
		int first = q.dequeue();
		assertEquals(first, 1);
	}

	@Test
	void testFIFO() {
		Queue<Integer> q = new Queue<Integer>(1);
		q.enqueue(2);
		assertFalse(q.isEmpty());
		int first = q.dequeue();
		assertEquals(first, 1);
		int second = q.dequeue();
		assertEquals(second, 2);
		assertTrue(q.isEmpty());
	}
}
