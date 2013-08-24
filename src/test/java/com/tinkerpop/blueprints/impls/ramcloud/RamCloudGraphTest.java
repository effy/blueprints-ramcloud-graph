package com.tinkerpop.blueprints.impls.ramcloud;

import java.lang.reflect.Method;

import com.tinkerpop.blueprints.EdgeTestSuite;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphTestSuite;
import com.tinkerpop.blueprints.IndexTestSuite;
import com.tinkerpop.blueprints.IndexableGraphTestSuite;
import com.tinkerpop.blueprints.KeyIndexableGraphTestSuite;
import com.tinkerpop.blueprints.TestSuite;
import com.tinkerpop.blueprints.VertexTestSuite;
import com.tinkerpop.blueprints.impls.GraphTest;
import com.tinkerpop.blueprints.util.io.gml.GMLReaderTestSuite;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReaderTestSuite;
import com.tinkerpop.blueprints.util.io.graphson.GraphSONReaderTestSuite;

public class RamCloudGraphTest extends GraphTest {

  public RamCloudGraphTest() {
    
  }

  public void testVertexTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new VertexTestSuite(this));
    printTestPerformance("VertexTestSuite", this.stopWatch());
  }

  public void testEdgeTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new EdgeTestSuite(this));
    printTestPerformance("EdgeTestSuite", this.stopWatch());
  }

  public void testGraphTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new GraphTestSuite(this));
    printTestPerformance("GraphTestSuite", this.stopWatch());
  }

  public void testKeyIndexableGraphTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new KeyIndexableGraphTestSuite(this));
    printTestPerformance("KeyIndexableGraphTestSuite", this.stopWatch());
  }

  public void testIndexableGraphTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new IndexableGraphTestSuite(this));
    printTestPerformance("IndexableGraphTestSuite", this.stopWatch());
  }

  public void testIndexTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new IndexTestSuite(this));
    printTestPerformance("IndexTestSuite", this.stopWatch());
  }

  public void testGraphMLReaderTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new GraphMLReaderTestSuite(this));
    printTestPerformance("GraphMLReaderTestSuite", this.stopWatch());
  }

  public void testGMLReaderTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new GMLReaderTestSuite(this));
    printTestPerformance("GMLReaderTestSuite", this.stopWatch());
  }

  public void testGraphSONReaderTestSuite() throws Exception {
    this.stopWatch();
    doTestSuite(new GraphSONReaderTestSuite(this));
    printTestPerformance("GraphSONReaderTestSuite", this.stopWatch());
  }
  
  @Override
  public void doTestSuite(TestSuite testSuite) throws Exception {
    for (Method method : testSuite.getClass().getDeclaredMethods()) {
      if (method.getName().startsWith("test")) {
        System.out.println("Testing " + method.getName() + "...");
        method.invoke(testSuite);
      }
    }
  }

  @Override
  public Graph generateGraph() {
    return new RamCloudGraph("infrc:host=192.168.1.101,port=12246");
  }

  @Override
  public Graph generateGraph(String graphDirectoryName) {
    return new RamCloudGraph("infrc:host=192.168.1.101,port=12246");
  }

}
