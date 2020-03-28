# JavaGraph
*Network simulation using Graphs in Java.*

---
### Summary

3<sup>rd</sup> year project at the Polytech Nancy's Engineering School.
The main goal was to realise a network simulation (DHCP server, routing table)
using the Java language and graph theory.

---
### Features

###### TBA:
* DHCP Server
* BFS Algorithm
* DFS Algorithm
* IP class
* Machine class (IP, logs, auth with ENUM) extending Sommet
* Global main class for performance testing
* Routing
* GEXF or JSON import
###### Working:
* Graph using adjacency matrices
* Graph using Node class
* Shortest paths between nodes algorithms
* JSON export
* JavaScript viewer (using sigma.js)

---
### Installation

###### Java:
* Clone the repository
* Install Gson-2.8.5.jar dependency (for JSON export)
* Launch using a Java compiler
###### Visualizer:
Tools needed : [Node.js](https://nodejs.org/en/), [gjslint](https://developers.google.com/closure/utilities/docs/linter_howto?hl=en), [Yarn](https://yarnpkg.com/) or [npm](https://www.npmjs.com/)
* Clone the repository
* Install Gson-2.8.5.jar dependency (for JSON export)
* Install dependencies using *yarn* or *npm install*
* Launch an http-server with *yarn start* or *npm start*
* Place the JSON file in /graphVisualizer/visualizer/data/ folder
* Go to the [visualizer.html](http://localhost:8000/visualizer/visualizer.html) page

---
### Authors
* Léo Boulard
* Léo Krebs
###### Dependencies:
* [Gson](https://github.com/google/gson)
* [Sigma.js](https://github.com/jacomyal/sigma.js)