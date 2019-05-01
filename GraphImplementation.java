import java.util.*;

public class GraphImplementation implements Graph{
	//an implementation of a directed and unweighted graph stored in the form of an adjecency matrix

	//our instances
	int arr[][],size;

	//constructor
	public GraphImplementation(int v_num){

		size = v_num;
		arr = new int [size][size]; 
		for (int r=0;r<size;r++)
			Arrays.fill(arr[r],0);

		
	}

	//adding a directed edge between two specified verticies
	public void addEdge(int v1, int v2){

		if (v1 <0 || v1>=size || v2<0 || v2>=size) // if any of the passed ID's does not match an existing vertex ID
			return; 
		arr[v1][v2] = 1; // each index that contains a 1 indicates an edge between the source (v1) to destination (v2)
	}

	//returns a list of IDs that are direct destinations from vertex
	public int[] neighbors(int vertex){

		if (vertex <0 || vertex>=size) // if vertex does not match an existing vertex ID
			return null; 

		int temp_size=0;
		for (int c=0; c<size;c++){
			if (arr[vertex][c] !=0){
				temp_size++;
				// store the amount of existing edges from vertex (which we use to initalize the neighbor array)
			}
		}

		int temp[] = new int [temp_size];
		Arrays.fill(temp,0);
		int i=0;

		for (int c=0; c<size;c++){
			if (arr[vertex][c] !=0){
				temp[i++]=c;
				// store the direct destination verticies that our source vertex is related to
			}
		}
		return temp;
	}

	//order elements with priority to v1 over v2, where v1 points to v2
	public List<Integer> topologicalSort(){
		if (size==0)
			return null;
		
		List<Integer> ordered = new ArrayList<Integer>();
		int visited []= new int [size]; 
		int temp[];
		Arrays.fill(visited,0);

		for (int r=0; r<size;r++){
			for (int c=0; c<size;c++){
				/*this sums up all the rows of a specific column and places it in position c of our visited array
				this expresses the amount of edges that point to our destination vertex (and the max amount of visits we have for each vertex)*/
				visited[c] += arr[r][c];
			}
		}
		for (int i=0;i<size;i++){
			for (int j=0; j<size;j++){
				if (visited[j] == 0){

					// here if the vertex is not visited we call its neighbors 
					temp = neighbors(j);
					for(int k=0; k<temp.length;k++){ 
					// iterate through each neighbor and decrease the amount of total visits remaining
						visited[temp[k]]--;
					}

					// we add the vertex with 0 remaining visits to our ordered list and set it to -1 (to avoid revisiting its neighbors)
					ordered.add(j); 
					visited[j]=-1; 
				}
			}
		}
		
		System.out.print(ordered);
		System.out.println();

		return ordered;
}


}
