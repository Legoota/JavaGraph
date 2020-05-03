// NEIGHBORS METHOD
sigma.classes.graph.addMethod('neighbors', function(nodeId) {
    var k,
        neighbors = {},
        index = this.allNeighborsIndex[nodeId] || {};

    for (k in index)
      neighbors[k] = this.nodesIndex[k];

    return neighbors;
 });
 // END OF NEIGHBORS METHOD

// SIGMA INSTANCE INITIALIZATION
var g = {
        nodes: [],
        edges: []
        };

var s = new sigma({
    graph: g,
    container: 'graph-container',
    renderer: {
        container: document.getElementById('graph-container'),
        type: 'webgl'
    },
    settings: {
        minNodeSize: 8,
        maxNodeSize: 16
    }
});
// END OF SIGMA INSTANCE INITIALIZATION

// SIGMA JSON IMPORT PART
sigma.parsers.json(
    'data/reseau.json',
    s,
    function() {
        // this is needed in case the original JSON doesn't have color / size / x-y attributes
        var i,
            nodes = s.graph.nodes(),
            len = nodes.length;

        for (i = 0; i < len; i++) {
            nodes[i].x = Math.random();
            nodes[i].y = Math.random();
            nodes[i].size = s.graph.degree(nodes[i].id);
            nodes[i].color = nodes[i].center ? '#ff4b19' : '#ff4b19';
        }
        s.refresh();

        // ForceAtlas spacing algorithm
        s.startForceAtlas2({worker: true, barnesHutOptimize: true});
        setTimeout(() => {  s.stopForceAtlas2(); }, 1); // Stop FA 1ms afterwards (or else it keeps moving)
});
// END OF SIGMA JSON IMPORT PART

// NEIGHBOR COLORING PART
// Saving original nodes & edges colors
  s.graph.nodes().forEach(n => n.originalColor = n.color);
  s.graph.edges().forEach(e => e.originalColor = e.color);

  // CLICK A NODE NEIGHBORS COLORING METHOD
  s.bind('clickNode', function(e) {
    var nodeId = e.data.node.id,
        toKeep = s.graph.neighbors(nodeId);
    toKeep[nodeId] = e.data.node; // toKeep contains all the neighbouring nodes

    // Change node color to gray if not in toKeep, orange if in toKeep
    s.graph.nodes().forEach(n => toKeep[n.id] ? n.color = '#ff4b19' : n.color = '#666');
    // Change edge color to gray if not in toKeep, orange if in toKeep
    s.graph.edges().forEach(e => toKeep[e.source] && toKeep[e.target] ? e.color = '#ff4b19' : e.color = '#666');
    // Refresh to get updated colors
    s.refresh();
  });

  // CLICK THE BACKGROUND COLORING METHOD
  s.bind('clickStage', function(e) {
    // Reset all nodes to same color
    s.graph.nodes().forEach(n => n.color = '#ff4b19');
    // Reset all edges to same color
    s.graph.edges().forEach(e => e.color = '#ff4b19');
    // Refresh to get updated colors
    s.refresh();
  });
// END OF NEIGHBOR COLORING PART