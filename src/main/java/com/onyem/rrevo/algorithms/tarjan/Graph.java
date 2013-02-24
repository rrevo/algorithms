package com.onyem.rrevo.algorithms.tarjan;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

class Graph {

  private Set<Vertex> vs;

  public Graph() {
    vs = new LinkedHashSet<Vertex>();
  }

  public Set<Vertex> getVertices() {
    return Collections.unmodifiableSet(vs);
  }

  public Vertex addVertex(String name) {
    Vertex vertex = new Vertex(name);
    if (!vs.add(vertex)) {
      throw new IllegalArgumentException();
    }
    return vertex;
  }

  public void addEdge(Vertex source, Vertex destination) {
    if (!vs.contains(source) || !vs.contains(destination)) {
      throw new IllegalArgumentException();
    }
    source.addNeighbour(destination);
  }

  @Override
  public String toString() {
    return "Graph [vs=" + vs + "]";
  }

}
