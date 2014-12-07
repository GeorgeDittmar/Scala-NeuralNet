package com.nn

import java.io.File

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource
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

  // Input to the network. This can be either training or testing data
  val inputTraining = ArrayBuffer[Array[Int]]()
  val inputTesting = ArrayBuffer[Array[Int]]()

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

  /**
   * This must be called last. If we have no neurons already in the matrix throw error.
   * @param numUnits
   */
  def createOutputLayer(numUnits: Int): Unit = {
    val outputLayer = ArrayBuffer[Neuron]()
    for (x <- 0 to numUnits) {
      outputLayer.+=(new Neuron())
    }
    neurons.+=(outputLayer)
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
   * Input the training/testing data to be read by the network. This function currently assumes the data is a csv
   */
  def inputTraining(data: BufferedSource, delim: String): Unit = {
    val iter = data.getLines().drop(1).map(_.split(delim))

    // read in each example and convert to integer arrays
    while(iter.hasNext){
      try {
        val example: Array[Int] = iter.next().map(_.toInt)
        inputTraining.+=(example)
      }catch{
        case numFE: NumberFormatException => {
          println("Failed to load example...")
        }
        case ex: Exception => println("Some error has happend reading input data")
      }
    }
  }

  def inputTest(data: BufferedSource, delim: String): Unit ={
    val iter = data.getLines().drop(1).map(_.split(delim))
    // read in each example and convert to integer arrays
    while(iter.hasNext){
      val example: Array[Int] = iter.next().map(_.toInt)
      inputTesting.+=(example)
    }
  }

  /**
   * Initializes a new network with a random set of weights between -1 and 1
   */
  def init(): Unit = {
    for (layer <- neurons) {
      // weights are mutable data until the training of the network is compelte
      var weight = Vector.fill(layer.length)((Random.nextDouble() * 1) + -1)
      weights.+=(weight)
    }
  }

}
