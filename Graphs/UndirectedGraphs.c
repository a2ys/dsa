#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_VERTICES 5

typedef struct Graph {
  int numVertices;
  int** adjMatrix;
} Graph;

// Creating a graph
struct Graph* createGraph(int V) {
  Graph* graph = (Graph*) malloc(sizeof(Graph));
  graph->numVertices = V;

  graph->adjMatrix = (int**) malloc(V * sizeof(int*));

  for (int i = 0; i < V; i++) {
    graph->adjMatrix[i] = (int*) malloc(V * sizeof(int));
    for (int j = 0; j < V; j++) {
      graph->adjMatrix[i][j] = 0;
    }
  }

  return graph;
}

// Adding an edge
void addEdge(Graph* graph, int source, int destination) {
  graph->adjMatrix[source][destination] = 1;
  graph->adjMatrix[destination][source] = 1;
}

// Depth-first Search
void DFS(Graph* graph, int vertex, bool visited[]) {
  visited[vertex] = true;
  printf("%d ", vertex);

  for (int i = 0; i < graph->numVertices; i++) {
    if (graph->adjMatrix[vertex][i] == 1 && !visited[i]) {
      DFS(graph, i, visited);
    }
  }
}

// Breadth-first search
void BFS(Graph* graph, int startVertex) {
  bool visited[MAX_VERTICES] = {false};
  int queue[graph->numVertices];
  int front = 0, rear = 0;

  visited[startVertex] = true;
  queue[rear++] = startVertex;

  while (front < rear) {
    int currentVertex = queue[front++];
    printf("%d ", currentVertex);

    for (int i = 0; i < graph->numVertices; i++) {
      if (graph->adjMatrix[currentVertex][i] == 1 && !visited[i]) {
        queue[rear++] = i;
        visited[i] = true;
      }
    }
  }
}

// Parts of Dijkstra's Algorithm
int findMinDistance(int distance[], int visited[]) {
  int min = INT_MAX, minIndex;

  for (int v = 0; v < MAX_VERTICES; v++) {
    if (!visited[v] && distance[v] <= min) {
      min = distance[v];
      minIndex = v;
    }
  }

  return minIndex;
}

void dijkstra(Graph* graph, int source) {
  int distance[MAX_VERTICES];
  int visited[MAX_VERTICES];

  for (int i = 0; i < MAX_VERTICES; i++) {
    distance[i] = INT_MAX;
    visited[i] = false;
  }

  distance[source] = 0;
}

void printAdjMatrix(Graph* graph) {
  for (int i = 0; i < graph->numVertices; i++) {
    for (int j = 0; j < graph->numVertices; j++) {
      printf("%d ", graph->adjMatrix[i][j]);
    }

    printf("\n");
  }
}

int main() {
    struct Graph* graph = createGraph(MAX_VERTICES);
    addEdge(graph, 0, 1);
    addEdge(graph, 0, 4);
    addEdge(graph, 1, 2);
    addEdge(graph, 1, 3);
    addEdge(graph, 1, 4);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 4);

    bool visited[MAX_VERTICES] = {false};
    int vertex = 0;

    printf("DFS starting from vertex 0:\n");
    DFS(graph, 0, visited);

    printf("\n");
  
    printf("BFS starting from vertex 0:\n");
    BFS(graph, 0);
    
    return 0;
}
