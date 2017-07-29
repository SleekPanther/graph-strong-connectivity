import java.util.*;

public class StrongConnectivityGraph {
	public boolean isStronglyConnected(ArrayList<ArrayList<Integer>> graph){
		return breadthFirstSearch(graph, 0);
	}

	public boolean breadthFirstSearch(ArrayList<ArrayList<Integer>> graph, int startingVertex){
		Queue<Integer> vertexQueue = new LinkedList<Integer>();		//queue where new un-visited nodes are stored
		vertexQueue.add(startingVertex);		//initialize queue with the starting vertex
		
		boolean[] visitedNodes = new boolean[graph.size()];		
		ArrayList<Integer> nodesInVisitedOrder = new ArrayList<Integer>();	//list of nodes in the order that they are visited. Printed at the end of the algorithm
		
		while(!vertexQueue.isEmpty()){		//repeat the algorithm until queue is empty
			int nodeRemovedFromQueue = vertexQueue.remove();		//pick a node from the head of the queue
			
			if(!visitedNodes[nodeRemovedFromQueue]){		//If NOT visited
				visitedNodes[nodeRemovedFromQueue]=true;	//set to visited
				nodesInVisitedOrder.add(nodeRemovedFromQueue);	//append to list of nodes in order they are visited
				
				ArrayList<Integer> rowOfNodes = graph.get(nodeRemovedFromQueue);	//get a list of all nodes adjacent to the current Node
				for(int v: rowOfNodes){			//Iterate over all nodes adjacent to the current node
					if(!visitedNodes[v]){		//if it's NOT visited, add to queue
						vertexQueue.add(v);
					}
				}
			}
		}
		System.out.println("Breadth first search visitation order\n"+nodesInVisitedOrder+"\n");

		//Check to see if any vertex was NOT visited
		for(int i=0; i<visitedNodes.length; i++){
			if(!visitedNodes[i]){
				return false;
			}
		}
		return true;	//all were visited
	}
	
	public static void main(String[] args) {
		StrongConnectivityGraph stronlyConnectedFinder = new StrongConnectivityGraph();
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		graph.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
		graph.add(new ArrayList<Integer>(Arrays.asList(3, 4)));
		graph.add(new ArrayList<Integer>(Arrays.asList(0)));
		graph.add(new ArrayList<Integer>(Arrays.asList(2)));
		graph.add(new ArrayList<Integer>(Arrays.asList(3)));
		
		stronlyConnectedFinder.isStronglyConnected(graph);
	}
}