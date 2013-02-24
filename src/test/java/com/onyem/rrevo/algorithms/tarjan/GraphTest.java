package com.onyem.rrevo.algorithms.tarjan;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {

  // Graph with 1 Strongly connected component
  // v1------>v2
  //  ^        |
  //  |        |
  //  |        |
  //  |        |
  //  |---v3<--|
  @Test
  public void testCycle() {
    Graph g = new Graph();
    Vertex v1 = g.addVertex("v1");
    Vertex v2 = g.addVertex("v2");
    Vertex v3 = g.addVertex("v3");

    g.addEdge(v1, v2);
    g.addEdge(v2, v3);
    g.addEdge(v3, v1);

    Tarjan tarjan = new Tarjan();
    List<Set<Vertex>> sccs = tarjan.process(g);
    Assert.assertEquals(1, sccs.size());

    assertComponent(sccs, 0, v3, v2, v1);
  }

  // Graph with 2 Strongly connected components
  // v1------>v2         v5------>v6 
  //  ^        |          ^        | 
  //  |        |          |        | 
  //  |        |          |        | 
  //  |        |          |        | 
  //  |---v3<--|          |---v4<--| 
  //       |                   ^
  //       --------------------|
  @Test
  public void testTwoCycle() {
    Graph g = new Graph();
    Vertex v1 = g.addVertex("v1");
    Vertex v2 = g.addVertex("v2");
    Vertex v3 = g.addVertex("v3");

    Vertex v4 = g.addVertex("v4");
    Vertex v5 = g.addVertex("v5");
    Vertex v6 = g.addVertex("v6");

    g.addEdge(v1, v2);
    g.addEdge(v2, v3);
    g.addEdge(v3, v1);

    g.addEdge(v4, v5);
    g.addEdge(v5, v6);
    g.addEdge(v6, v4);

    g.addEdge(v3, v4);

    Tarjan tarjan = new Tarjan();
    List<Set<Vertex>> sccs = tarjan.process(g);
    Assert.assertEquals(2, sccs.size());

    assertComponent(sccs, 0, v6, v5, v4);
    assertComponent(sccs, 1, v3, v2, v1);
  }

  //  Graph with NO Strongly connected component
  //   v1 -------------------> v2
  //   |\                     /|
  //   | \                   / |
  //   |  \                 /  |
  //   |   \               /   |
  //   |    \-->v3<-------/    |
  //   |        |              |
  //   |        |              |
  //   |    |-----> v5--->v6   |
  //   |    |                  |
  //   |--->v4<-----------------
  //
  @Test
  public void testDAG() {
    Graph g = new Graph();
    Vertex v1 = g.addVertex("v1");
    Vertex v2 = g.addVertex("v2");
    Vertex v3 = g.addVertex("v3");
    Vertex v4 = g.addVertex("v4");
    Vertex v5 = g.addVertex("v5");
    Vertex v6 = g.addVertex("v6");

    g.addEdge(v1, v2);
    g.addEdge(v1, v3);
    g.addEdge(v1, v4);
    g.addEdge(v2, v3);
    g.addEdge(v2, v4);
    g.addEdge(v3, v5);
    g.addEdge(v4, v5);
    g.addEdge(v5, v6);

    Tarjan tarjan = new Tarjan();
    List<Set<Vertex>> sccs = tarjan.process(g);
    Assert.assertEquals(6, sccs.size());

    assertComponent(sccs, 0, v6);
    assertComponent(sccs, 1, v5);
    assertComponent(sccs, 2, v3);
    assertComponent(sccs, 3, v4);
    assertComponent(sccs, 4, v2);
    assertComponent(sccs, 5, v1);
  }

  private void assertComponent(List<Set<Vertex>> sccs, int index, Vertex... vs) {
    Set<Vertex> scc = sccs.get(index);
    Iterator<Vertex> iterator = scc.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      Vertex vertex = iterator.next();
      Assert.assertEquals(vs[count], vertex);
      count++;
    }
    Assert.assertEquals(vs.length, count);
  }
}
