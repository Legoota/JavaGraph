sigma.classes.graph.addMethod('neighbors', function(nodeId) {
    var k,
        neighbors = {},
        index = this.allNeighborsIndex[nodeId] || {};

    for (k in index)
      neighbors[k] = this.nodesIndex[k];

    return neighbors;
  });


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

sigma.parsers.json(
    'data/grex.json',
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

        // Refresh the display:
        s.refresh();

        // ForceAtlas Layout
        s.startForceAtlas2({worker: true, barnesHutOptimize: false});
        setTimeout(() => {  s.stopForceAtlas2(); }, 1);
    }
);

// We first need to save the original colors of our
  // nodes and edges, like this:
  s.graph.nodes().forEach(function(n) {
    n.originalColor = n.color;
    console.log(n.originalColor);
  });
  s.graph.edges().forEach(function(e) {
    e.originalColor = e.color;
  });

  // When a node is clicked, we check for each node
  // if it is a neighbor of the clicked one. If not,
  // we set its color as grey, and else, it takes its
  // original color.
  // We do the same for the edges, and we only keep
  // edges that have both extremities colored.
  s.bind('clickNode', function(e) {
    var nodeId = e.data.node.id,
        toKeep = s.graph.neighbors(nodeId);
    toKeep[nodeId] = e.data.node;

    s.graph.nodes().forEach(function(n) {
      if (toKeep[n.id])
        n.color = '#ff4b19';
      else
        n.color = '#666';
    });

    s.graph.edges().forEach(function(e) {
      if (toKeep[e.source] && toKeep[e.target])
        e.color = '#ff4b19';
      else
        e.color = '#666';
    });

    // Since the data has been modified, we need to
    // call the refresh method to make the colors
    // update effective.
    s.refresh();
  });

  // When the stage is clicked, we just color each
  // node and edge with its original color.
  s.bind('clickStage', function(e) {
    s.graph.nodes().forEach(function(n) {
      n.color = '#ff4b19';
    });

    s.graph.edges().forEach(function(e) {
      e.color = '#ff4b19';
    });

    // Same as in the previous event:
    s.refresh();
  });