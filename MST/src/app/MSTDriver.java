package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import structures.Graph;
import structures.Vertex;
import structures.PartialTree;
import structures.Arc;

public class MSTDriver {

	public static void main(String[] args) 
	throws IOException {
		
		String graphFile = "graph1.txt";
		Graph graphObj = new Graph(graphFile);
		PartialTreeList ptListObj = PartialTreeList.initialize(graphObj);

		ArrayList<Arc> mstArcs =  PartialTreeList.execute(ptListObj);
		System.out.println("MST: " + mstArcs);
		
		
		Iterator<PartialTree> ptIterator = ptListObj.iterator();
		while (ptIterator.hasNext())
			System.out.println("ptList: "+ptIterator.next());
		System.out.println("");
//		
//		ptIterator = ptListObj.iterator();
//		PartialTree ptToDelete = ptIterator.next();
//		Vertex vx2ToDelete;
//		Vertex vxToDelete = ptToDelete.getArcs().getMin().getv1();
//		//System.out.println("ptRoot "+ptToDelete.getRoot());
//		System.out.println("vxToDelete "+vxToDelete);
//		PartialTree ptDeleted =  ptListObj.removeTreeContaining(vxToDelete);
//		System.out.println("ptDeleted "+ptDeleted);
//		ptIterator = ptListObj.iterator();
//		while (ptIterator.hasNext())
//			System.out.println("ptList: "+ptIterator.next());
//		System.out.println("");
//		
//		ptIterator = ptListObj.iterator();
//		ptToDelete = ptIterator.next();
//		ptToDelete = ptIterator.next();
//		ptToDelete = ptIterator.next();
//		vxToDelete = ptToDelete.getArcs().getMin().getv1();
//		vx2ToDelete = ptToDelete.getArcs().getMin().getv2();
//		//System.out.println("ptRoot "+ptToDelete.getRoot());
//		System.out.println("vxToDelete "+vxToDelete);
//		ptDeleted =  ptListObj.removeTreeContaining(vxToDelete);
//		System.out.println("ptDeleted "+ptDeleted);
//		ptIterator = ptListObj.iterator();
//		while (ptIterator.hasNext())
//			System.out.println("ptList: "+ptIterator.next());
//		System.out.println("");
//		
//		System.out.println("vxToDelete "+vx2ToDelete);
//		ptDeleted =  ptListObj.removeTreeContaining(vx2ToDelete);
//		System.out.println("ptDeleted "+ptDeleted);
//		ptIterator = ptListObj.iterator();
//		while (ptIterator.hasNext())
//			System.out.println("ptList: "+ptIterator.next());
//		System.out.println("");
//		
//		ptIterator = ptListObj.iterator();
//		ptToDelete = ptIterator.next();
//		ptToDelete = ptIterator.next();
//		vxToDelete = ptToDelete.getArcs().getMin().getv1();
//		//System.out.println("ptRoot "+ptToDelete.getRoot());
//		System.out.println("vxToDelete "+vxToDelete);
//		ptDeleted =  ptListObj.removeTreeContaining(vxToDelete);
//		System.out.println("ptDeleted "+ptDeleted);
//		ptIterator = ptListObj.iterator();
//		while (ptIterator.hasNext())
//			System.out.println("ptList: "+ptIterator.next());
//		System.out.println("");
//		
//		ptIterator = ptListObj.iterator();
//		ptToDelete = ptIterator.next();
//		vxToDelete = ptToDelete.getArcs().getMin().getv1();
//		//System.out.println("ptRoot "+ptToDelete.getRoot());
//		System.out.println("vxToDelete "+vxToDelete);
//		ptDeleted =  ptListObj.removeTreeContaining(vxToDelete);
//		System.out.println("ptDeleted "+ptDeleted);
//		ptIterator = ptListObj.iterator();
//		while (ptIterator.hasNext())
//			System.out.println("ptList: "+ptIterator.next());
//		System.out.println("");
		
	}
}