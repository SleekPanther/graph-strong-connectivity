import java.util.*;

public class StrongConnectivity {
	public boolean isStronglyConnected(ArrayList<ArrayList<Integer>> graph){
		int startingVertex = 0;

		System.out.println("Original Graph");
		boolean allVerticesReachableInOriginal = breadthFirstSearch(graph, startingVertex);
		
		System.out.println("Reversed Graph");
		ArrayList<ArrayList<Integer>> reversedGraph = reverseGraphEdges(graph);
		boolean allVerticesReachableInReversed = breadthFirstSearch(reversedGraph, startingVertex);
		
		return allVerticesReachableInOriginal && allVerticesReachableInReversed;
	}

	private ArrayList<ArrayList<Integer>> reverseGraphEdges(ArrayList<ArrayList<Integer>> graph){
		ArrayList<ArrayList<Integer>> reversedGraph = new ArrayList<ArrayList<Integer>>();
		//Add placeholders for each vertex in the adjacency List
		for(int u=0; u<graph.size(); u++){
			reversedGraph.add(new ArrayList<Integer>());
		}

		//Reverse edge direction in new graph using the original
		for(int u=0; u<graph.size(); u++){
			for(int v : graph.get(u)){
				reversedGraph.get(v).add(u);
			}
		}
		return reversedGraph;
	}

	public boolean breadthFirstSearch(ArrayList<ArrayList<Integer>> graph, int startingVertex){
		Queue<Integer> vertexQueue = new LinkedList<Integer>();		//queue where new un-visited vertices are stored
		vertexQueue.add(startingVertex);		//initialize queue with the starting vertex
		
		boolean[] visitedNodes = new boolean[graph.size()];
		ArrayList<Integer> nodesInVisitedOrder = new ArrayList<Integer>();	//Keep track of visitation order
		
		while(!vertexQueue.isEmpty()){
			int nodeRemovedFromQueue = vertexQueue.remove();		//pick a node from the head of the queue
			
			if(!visitedNodes[nodeRemovedFromQueue]){		//If NOT visited
				visitedNodes[nodeRemovedFromQueue]=true;	//set to visited
				nodesInVisitedOrder.add(nodeRemovedFromQueue);	//append to list of vertices in order they are visited
				
				ArrayList<Integer> incidentVertices = graph.get(nodeRemovedFromQueue);	//get a list of all vertices adjacent to the current Node
				for(int v: incidentVertices){			//Iterate over all vertices adjacent to the current node
					if(!visitedNodes[v]){		//if it's NOT visited, add to queue
						vertexQueue.add(v);
					}
				}
			}
		}
		System.out.println("Breadth first search visitation order:   "+nodesInVisitedOrder);

		//Check to see if any vertex was NOT visited
		for(int i=0; i<visitedNodes.length; i++){
			if(!visitedNodes[i]){
				return false;	//break early if any vertex is NOT visited
			}
		}
		return true;	//all were visited
	}
	
	public static void main(String[] args) {
		StrongConnectivity strongConnectivityFinder = new StrongConnectivity();

		ArrayList<ArrayList<Integer>> graph1 = new ArrayList<ArrayList<Integer>>();
		graph1.add(new ArrayList<Integer>(Arrays.asList(1, 3)));
		graph1.add(new ArrayList<Integer>(Arrays.asList(3, 4)));
		graph1.add(new ArrayList<Integer>(Arrays.asList(0)));
		graph1.add(new ArrayList<Integer>(Arrays.asList(2)));
		graph1.add(new ArrayList<Integer>(Arrays.asList(3)));
		
		System.out.println("Graph 1");
		boolean isGraph1StronglyConnected = strongConnectivityFinder.isStronglyConnected(graph1);
		System.out.println("Graph 1 strongly connected?  " + isGraph1StronglyConnected);


		ArrayList<ArrayList<Integer>> graph2 = new ArrayList<ArrayList<Integer>>();
		graph2.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		graph2.add(new ArrayList<Integer>(Arrays.asList(3, 4)));
		graph2.add(new ArrayList<Integer>());
		graph2.add(new ArrayList<Integer>(Arrays.asList(2)));
		graph2.add(new ArrayList<Integer>(Arrays.asList(3)));
		
		System.out.println("\nGraph 2");
		boolean isGraph2StronglyConnected = strongConnectivityFinder.isStronglyConnected(graph2);
		System.out.println("Graph 2 strongly connected?  " + isGraph2StronglyConnected);
	}
}