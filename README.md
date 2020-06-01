# JavaGraph<img align="right" width="100" height="100" src="https://github.com/Legoota/JavaGraph/blob/master/logo.png" alt="Logo JavaGraph">

*Network simulation using Graphs in Java.*

---
### Summary

3<sup>rd</sup> year project at the Polytech Nancy's Engineering School.
The main goal was to realise a network simulation (DHCP server, routing table)
using the Java language and graph theory.

---
### Features

###### TBA:
* Use *Composite design pattern* to create a network of networks (network of routers)
* Add ping method using network ip addresses
* Refine shortest path algorithm (currently not working everytime)

###### Working:
* Graph using adjacency matrices
* Graph using Node class
* Shortest paths between nodes algorithms
* JSON import and export
* JavaScript viewer (using sigma.js)
* BFS Algorithm
* DFS Algorithm
* Global main class for performance testing
* IP class
* Machine class (IP) extending Sommet
* DHCP Server
* Machines and networks logs
* Machine to machine instant messaging
* Shortest path algorithm (based on BFS)

---
### Installation

###### Java project:
* Clone the repository
* Install Gson-2.8.5.jar dependency (for JSON export)
* Launch using a Java compiler
###### JS Visualizer:
Tools needed : [Node.js](https://nodejs.org/en/), [gjslint](https://developers.google.com/closure/utilities/docs/linter_howto?hl=en), [Yarn](https://yarnpkg.com/) or [npm](https://www.npmjs.com/)
* Clone the repository
* Install Gson-2.8.5.jar dependency (for JSON export)
* Install dependencies using *yarn* or *npm install* in the visualizer directory
* Launch an http-server with *yarn start* or *npm start* in the visualizer directory
* Place the JSON file in /graphVisualizer/visualizer/data/ folder
* Choose which JSON to display by changing line 36 in /graphVisualizer/visualizer/visualizer.js
* Go to the [visualizer.html](http://localhost:8000/visualizer/visualizer.html) page

---
### Authors
* Léo Boulard
* Léo Krebs
###### Dependencies:
* [Gson](https://github.com/google/gson)
* [Sigma.js](https://github.com/jacomyal/sigma.js)
