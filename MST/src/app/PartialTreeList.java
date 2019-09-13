package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Arc;
import structures.Graph;
import structures.MinHeap;
import structures.PartialTree;
import structures.Vertex;

/**
 * Stores partial trees in a circular linked list
 * 
 */
public class PartialTreeList implements Iterable<PartialTree> {

	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;

		/**
		 * Next node in linked list
		 */
		public Node next;

		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;

	/**
	 * Number of nodes in the CLL
	 */
	private int size;

	/**
	 * Initializes this list to empty
	 */
	public PartialTreeList() {
		rear = null;
		size = 0;
	}

	/**
	 * Adds a new tree to the end of the list
	 * 
	 * @param tree Tree to be added to the end of the list
	 */
	public void append(PartialTree tree) {
		Node ptr = new Node(tree);
		if (rear == null) {
			ptr.next = ptr;
		} else {
			ptr.next = rear.next;
			rear.next = ptr;
		}
		rear = ptr;
		size++;
	}

	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {

		PartialTreeList L = new PartialTreeList();

		for(int i=0;i<graph.vertices.length; i++){
			PartialTree tree = new PartialTree(graph.vertices[i]);
			graph.vertices[i].parent = tree.getRoot();
			MinHeap<Arc> priorityQ = tree.getArcs();
			for(Vertex.Neighbor n = graph.vertices[i].neighbors; n!= null; n=n.next){
				Vertex vertex1 = graph.vertices[i];
				Vertex vertex2 = n.vertex;
				Arc a = new Arc(vertex1, vertex2, n.weight);
				priorityQ.insert(a);
			}
			L.append(tree);
		}
		return L;
	}


	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * for that graph
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<Arc> execute(PartialTreeList ptlist) {

		ArrayList<Arc> arc = new ArrayList<Arc>();
		PartialTree x, y;
		Vertex vertex1 = null, vertex2 = null;
		Arc priorityArc = null;

		while(ptlist.size()>1) {
			x = ptlist.remove(); 

			while(!x.getArcs().isEmpty()) {
				priorityArc = x.getArcs().deleteMin();
				vertex1 = priorityArc.getv1();
				vertex2 = priorityArc.getv2();
				if (!vertex2.getRoot().equals(vertex1.getRoot()))
					break;
				else
					priorityArc = null;
			}
			
			if (!priorityArc.equals(null)){
				arc.add(priorityArc);
				y = ptlist.removeTreeContaining(vertex2.getRoot()); 
				x.merge(y);
				ptlist.append(x);	
			}
			else
				continue;
		}
		return arc;
	}

	/**
	 * Removes the tree that is at the front of the list.
	 * 
	 * @return The tree that is removed from the front
	 * @throws NoSuchElementException If the list is empty
	 */
	public PartialTree remove() 
			throws NoSuchElementException {

		if (rear == null) {
			throw new NoSuchElementException("list is empty");
		}
		PartialTree ret = rear.next.tree;
		if (rear.next == rear) {
			rear = null;
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return ret;

	}

	/**
	 * Removes the tree in this list that contains a given vertex.
	 * 
	 * @param vertex Vertex whose tree is to be removed
	 * @return The tree that is removed
	 * @throws NoSuchElementException If there is no matching tree
	 */
	public PartialTree removeTreeContaining(Vertex vertex) 
			throws NoSuchElementException {

		if(rear.equals(null)){
			throw new NoSuchElementException();
		}

		PartialTreeList.Node current = rear.next;
		PartialTreeList.Node previous = null;

		do{
			if(current.tree.getRoot().equals(vertex.parent)){

				if(size==1){
					rear = null;
				}
				else if(rear.next == current){
					rear.next = current.next;
				}
				else if(rear == current){
					previous.next = rear.next;
					rear = previous;
				}
				else{
					previous.next = current.next;
				}
				size--;
				return current.tree;
			}

			previous = current;
			current = current.next;

		}while(current!=rear.next);

		throw new NoSuchElementException();
	}

	/**
	 * Gives the number of trees in this list
	 * 
	 * @return Number of trees
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an Iterator that can be used to step through the trees in this list.
	 * The iterator does NOT support remove.
	 * 
	 * @return Iterator for this list
	 */
	public Iterator<PartialTree> iterator() {
		return new PartialTreeListIterator(this);
	}

	private class PartialTreeListIterator implements Iterator<PartialTree> {

		private PartialTreeList.Node ptr;
		private int rest;

		public PartialTreeListIterator(PartialTreeList target) {
			rest = target.size;
			ptr = rest > 0 ? target.rear.next : null;
		}

		public PartialTree next() 
				throws NoSuchElementException {
			if (rest <= 0) {
				throw new NoSuchElementException();
			}
			PartialTree ret = ptr.tree;
			ptr = ptr.next;
			rest--;
			return ret;
		}

		public boolean hasNext() {
			return rest != 0;
		}

		public void remove() 
				throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}
}


