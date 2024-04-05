# Optimal-Route-Planner
This Java program implements a route planner for finding the shortest path between two intersections in a traffic network. The program uses a graph data structure to represent the network and applies Dijkstra's algorithm to find the optimal path.



#Features
Creates a graph representing the traffic network.
Calculates the shortest path between two intersections.
Updates the traffic information and recalculates the shortest path based on the updated information.


#Usage
Compile the RoutePlanner.java file: javac RoutePlanner.java
Run the compiled program: java RoutePlanner
Follow the on-screen instructions to enter the matrix representing the traffic network, starting and destination intersections, and traffic updates.

#Input Format
The size of the matrix (N), representing the number of intersections.
The matrix representing the traffic network, where each cell represents the time (weight) to travel between two intersections. Enter the values separated by spaces, row by row.
The starting intersection (S) and destination intersection (D).
The number of traffic updates.
For each traffic update, enter the from intersection, to intersection, and the traffic time to be added to the original travel time.
