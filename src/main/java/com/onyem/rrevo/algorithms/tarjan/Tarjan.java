package com.onyem.rrevo.algorithms.tarjan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of <a href=
 * "http://en.wikipedia.org/wiki/Tarjan's_strongly_connected_components_algorithm"
 * >Tarjan's strongly connected components algorithm</a>
 * 
 * <p>
 * 
 * The algorithm finds <a
 * href="http://en.wikipedia.org/wiki/Strongly_connected_components">Strongly
 * connected components (SCC)</a> in a graph.
 * 
 * A list of SCC's is returned and each SCC is a Set of Vertices.
 * 
 * <p>
 * If the Graph is a <a
 * href="http://en.wikipedia.org/wiki/Directed_acyclic_graph">DAG</a>, each
 * vertex in the Graph is a SCC by itself. Hence the number of SCC's returned is
 * equal to the number of Vertices and each SCC consists of a single Vertex.
 * 
 * <p>
 * For a DAG, no strongly connected component will be identified before any of
 * its successors. Hence the order of the SCC is a reverse <a
 * href="http://en.wikipedia.org/wiki/Topological_ordering">topological sort</a>
 * of the DAG
 * 
 */
class Tarjan {

  public static final Integer UNDEFINED = -1;

  public List<Set<Vertex>> process(Graph graph) {
    AtomicInteger index = new AtomicInteger();
    Stack<Vertex> s = new Stack<Vertex>();
    List<Set<Vertex>> sccs = new ArrayList<Set<Vertex>>();

    for (Vertex v : graph.getVertices()) {
      if (v.index == UNDEFINED) {
        process(v, index, s, sccs);
      }
    }
    return Collections.unmodifiableList(sccs);
  }

  private void process(Vertex v, AtomicInteger index, Stack<Vertex> s,
      List<Set<Vertex>> sccs) {
    // set the depth index for v to the smallest unused index
    v.index = index.get();
    v.lowLink = index.get();
    index.incrementAndGet();

    s.push(v);
    v.inStack = true;

    // consider successors of v
    for (Vertex n : v.getNeighbours()) {
      if (n.index == UNDEFINED) {
        // n has not been discovered. hence recurse
        process(n, index, s, sccs);
        v.lowLink = Math.min(v.lowLink, n.lowLink);
      } else if (n.inStack) {
        // successor is in stack and hence in current SCC
        v.lowLink = Math.min(v.lowLink, n.index);
      }
    }

    // if v is the root not then pop the stack and generate the SCC
    if (v.lowLink == v.index) {
      Set<Vertex> scc = new LinkedHashSet<Vertex>();

      while (true) {
        Vertex n = s.pop();
        n.inStack = false;
        scc.add(n);
        if (n.equals(v)) {
          break;
        }
      }
      sccs.add(scc);
    }
  }
}
