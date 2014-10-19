package com.nn
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

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
   * Create the input layer of the network with the given number of neurons
   */
  def createInputLayer(numUnits:Int): Unit ={
    val inputLayer = ArrayBuffer[Neuron]()

    for(x <- numUnits){
      inputLayer.+=(new Neuron())
    }

    neurons.insert(0,inputLayer)
  }

  def createOutputLayer(numUnits:Int): Unit ={
    val outputLayer = ArrayBuffer[Neuron]()
    for(x <-numUnits){
      outputLayer.+=(new Neuron())
    }
  }

  /**
   * Creates a generic layer of the neural network.
   * @param numUnits
   */
  def createLayer(numUnits:Int): Unit = {
    var layer = new ArrayBuffer[Neuron]()
    for(elm <- numUnits){
      layer.+=(new Neuron())
    }

    return layer
  }

  /**
   * Input the training data to be read by the network
   */
  def inputData(data:mutable.Seq[Object]): Unit ={
    for(elm <- data){
      input.+=(elm)
    }
  }

}
