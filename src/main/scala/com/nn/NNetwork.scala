package com.nn

import java.io.File

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource
import scala.util.Random

/**
 * Base class for an NN artchitecture
 * Created by george on 10/11/14.
 */
class NNetwork {

  // An ANN consists of nodes and weighted edges.
  // Implementation represents a network as a matrix of nodes with the first row associated with the input nodes
  val neurons = scala.collection.mutable.ArrayBuffer[ArrayBuffer[Neuron]]()
  // Input to the network. This can be either training or testing data
  val inputTraining = ArrayBuffer[Array[Double]]()
  val inputTesting = ArrayBuffer[Array[Double]]()

  /**
   * Creates the input layer of n + 1 neurons
   */
  def createInputLayer(numUnits: Int): Unit ={
    val inputLayer = new ArrayBuffer[Neuron]()
    for(x <- 0 to numUnits + 1){
      inputLayer.+=(new Neuron)
    }
    neurons.+=(inputLayer)
  }

  /**
   * This must be called last. If we have no neurons already in the matrix throw error.
   * @param numUnits
   */
  def createOutputLayer[T](numUnits: Int): Unit = {
    if(neurons.length == 0){
      throw new IllegalStateException("Network does not appear to be initialized with any neurons.")
    }
    val outputLayer = new ArrayBuffer[Neuron]()
    for (x <- 0 to numUnits) {
      outputLayer.+=(new Neuron() with T)
    }
    neurons.+=(outputLayer)
  }

  /**
   * Creates a generic layer of the neural network.
   * @param numUnits
   */
  def createLayer[T](numUnits: Int): Unit = {
    var layer = new ArrayBuffer[Neuron]()
    for (elm <- 0 to numUnits + 1) {
      layer.+=(new Neuron() with T)
    }
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
        val example: Array[Double] = iter.next().map(_.toDouble)
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
      val example: Array[Double] = iter.next().map(_.toDouble)
      inputTesting.+=(example)
    }
  }

  /**
   * Initializes a new network with a random set of weights between -1 and 1
   */
  def init(): Unit = {
    // set the input layer nodes for the network
    for(inputNeuron <- 0 to neurons(0).length){
      neurons(0)(inputNeuron).setIsInput(true)
      neurons(0)(inputNeuron).setWeights(Vector.fill(1)((Random.nextDouble() * 1) + -1))
    }

    // initialize the weights through out the network
    for(layerInd <- 1 to neurons.length){
      // look back at the previous layer and get the number of neurons
      val sizePrevLayer = neurons(layerInd-1).length
      for(neuron <- neurons(layerInd)){
        // create new random weight vector to be used by this neurons inputs
        neuron.setWeights(Vector.fill(sizePrevLayer)((Random.nextDouble() * 1) + -1))
      }

    }
  }

}
