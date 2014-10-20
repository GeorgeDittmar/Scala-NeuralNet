package com.nn

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Base class for storing neurons in a neural network.
 * Created by george on 10/11/14.
 */
class NNetwork {

  // An ANN consists of nodes and weighted edges.
  // Implementation represents a network as a matrix of nodes and a matrix of weights
  val neurons = scala.collection.mutable.ArrayBuffer[ArrayBuffer[Neuron]]()
  val weights = scala.collection.mutable.ArrayBuffer[Vector[Double]]()

  val input = ArrayBuffer[Object]()

  /**
   * Create the input layer of the network with the given number of neurons. We add one extra neuron at the end as a bias
   * node.
   */
  def createInputLayer(numUnits: Int): Unit = {
    val inputLayer = ArrayBuffer[Neuron]()

    for (x <- 0 to numUnits+1) {
      inputLayer.+=(new Neuron())
    }

    neurons.insert(0, inputLayer)
  }

  def createOutputLayer(numUnits: Int): Unit = {
    val outputLayer = ArrayBuffer[Neuron]()
    for (x <- 0 to numUnits) {
      outputLayer.+=(new Neuron())
    }
  }

  /**
   * Creates a generic layer of the neural network.
   * @param numUnits
   */
  def createLayer(numUnits: Int): Unit = {
    var layer = new ArrayBuffer[Neuron]()
    for (elm <- 0 to numUnits+1) {
      layer.+=(new Neuron())
    }
    var weights = Vector.fill(layer.length)((Random.nextDouble() * 1) + -1)
    return layer
  }

  /**
   * Input the training data to be read by the network
   */
  def inputData(data: mutable.Seq[Object]): Unit = {
    for (elm <- data) {
      input.+=(elm)
    }
  }

  /**
   * Initializes a new network given a random set of weights on each edge between the nodes between -1 and 1
   */
  def init(): Unit = {
    for (layer <- neurons) {
      // weights are mutable data until the training is complete
      var weight = Vector.fill(layer.length)((Random.nextDouble() * 1) + -1)
      weights.+=(weight)
    }

    println("Perceptron initialized with random weights")
  }

}
