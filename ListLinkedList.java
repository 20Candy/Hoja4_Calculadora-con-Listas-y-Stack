import java.util.LinkedList;

public class ListLinkedList<E> extends AbstractList<E> {

	protected LinkedList<E> core;
	
	public ListLinkedList() {
		core = new LinkedList<E>();
	}
	
	public boolean insert(E element) {
		return core.add(element);
	}

	public boolean insertAt(E element, int index) {
		core.add(index, element);
		return true; //If no issue appear, add the element
	}

	public boolean insertAtStart(E element) {
		core.addFirst(element);
		return false;
	}

	public boolean insertAtEnd(E element) {
		core.addLast(element);
		return false;
	}

	public E removeAt(int index) {
		return core.remove(index);
	}

	public E removeAtStart() {
		return core.removeFirst();
	}

	public E removeAtEnd() {
		return core.removeLast();
	}

	public int count() {
		return core.size();
	}

	public boolean isEmpty() {
		return core.isEmpty();
	}

}