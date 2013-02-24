package com.onyem.rrevo.algorithms.tarjan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Vertex {

  public final String name;
  private final List<Vertex> ns;

  // State for Tarjan's algorithm
  int index = Tarjan.UNDEFINED;
  int lowLink = Tarjan.UNDEFINED;
  boolean inStack = false;

  public Vertex(String name) {
    this.name = name;
    ns = new ArrayList<Vertex>();
  }

  public void addNeighbour(Vertex vertex) {
    ns.add(vertex);
  }

  public List<Vertex> getNeighbours() {
    return Collections.unmodifiableList(ns);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Vertex other = (Vertex) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vertex [name=" + name + ", ns=[");
    for (Vertex n : ns) {
      sb.append(n.name).append(", ");
    }
    sb.append("]]");
    return sb.toString();
  }
}
