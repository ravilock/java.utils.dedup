package queue;

public class Queue<T> {
	private int size = 0;
	private Node<T> first;
	private Node<T> last;
	
	public Queue() {
		
	}

	public Queue(T first) {
		Node<T> node = new Node<T>(first);
		this.first = node;
		this.last = node;
		this.size++;
	}

	public Queue(Node<T> first) {
		this.first = first;
		this.last = first;
		this.size++;
	}

	public void enqueue(T element) {
		Node<T> node = new Node<T>(element);
		if (this.isEmpty()) {
			this.first = node;
			this.last = node;
			this.size++;
			return;
		}
		this.last.setNext(node);
		this.last = this.last.getNext();
		this.size++;
	}

	public T dequeue() {
		if (this.isEmpty()) {
			// TODO: Lançar erro;
			return null;
		}
		Node<T> result = this.first;
		this.size--;
		if (this.isEmpty()) {
			this.first = null;
			this.last = null;
		} else {
			this.first = this.first.getNext();
		}
		return result.getData();
	}

	public boolean isEmpty() {
		return this.size == 0;
	}
}

class Node<T> {
	private T data;
	private Node<T> next;

	public Node(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getNext() {
		return next;
	}
}