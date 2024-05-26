# Description

In "ServerTimes.java", it creates a server socket that listens on port 7772. When a client connects, it handles the client's request in a separate thread. It sends a list of connected clients to the client and receives the client's name. Then, it calculates and sends back the time the server has been running since it started.

In "ClientTimes.java", it creates a client socket to connect to the server running on localhost at port 7772. It reads the list of connected clients from the server, prompts the user to enter their name, sends the name to the server, and receives the time the server has been running. Finally, it displays the received time.

To use these files, you would compile and run "ServerTimes.java" to start the server. Then, you would compile and run "ClientTimes.java" to connect to the server as a client.
