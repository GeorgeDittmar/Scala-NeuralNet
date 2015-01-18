package com.nn

import java.io.File

import com.nn.math.activations.AbstractActivation
import com.nn.utility.NeuronFactory

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource
import scala.reflect.ClassTag
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
  var inputTraining = ArrayBuffer[Array[Double]]()
  val inputTesting = ArrayBuffer[Array[Double]]()

  /**
   * Creates the input layer of n + 1 bias input neuron
   */
  def createInputLayer(numUnits: Int): Unit ={
    val inputLayer = new ArrayBuffer[Neuron]()
    for(x <- 1 to numUnits + 1){
      inputLayer.+=(new Neuron)
    }
    neurons.+=(inputLayer)
  }

  /**
   * This must be called last. If we have no neurons already in the network throws an illegal state exception
   * @param numUnits
   */
  def createOutputLayer[T](numUnits: Int, activationType: String): Unit = {
    if(neurons.length == 0){
      throw new IllegalStateException("Network does not appear to be initialized with any neurons.")
    }
    val outputLayer = new ArrayBuffer[Neuron]()
    for (x <- 1 to numUnits) {
      outputLayer.+=(NeuronFactory.createNeuronActivation(activationType))
    }
    neurons.+=(outputLayer)
  }

  /**
   * Creates a generic layer of the neural network.
   * @param numUnits
   */
  def createLayer(numUnits: Int, activationType: String): Unit = {
    var layer = new ArrayBuffer[Neuron]()

    for (elm <- 1 to numUnits) {
      layer.+=(NeuronFactory.createNeuronActivation(activationType))
    }
  }

  /**
   * Loads a single training example from meory into the networks input array
   * @param example
   */
  def loadTrainingExample(example: Array[Double]): Unit ={
    inputTraining.+=(example)
  }
  /**
   * Load an already processed data set from memory into the NN
   * @param data
   */
  def loadTrainingSet(data: ArrayBuffer[Array[Double]]): Unit ={
    inputTraining = data
  }

  /**
   * Load an already processed data set from memory into the NN
   * @param data
   */
  def loadTestSet(data: ArrayBuffer[Array[Double]]): Unit ={
    inputTraining = data
  }

  /**
   * Input the training/testing data to be read by the network. This function currently assumes the data is a csv file
   */
  def loadTrainingSet(data: BufferedSource, delim: String): Unit = {
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
        case ex: Exception => println("Some error has happend reading input data.")
      }
    }
  }

  def loadTestSet(data: BufferedSource, delim: String): Unit ={
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
    for(inputNeuron <- neurons(0)){
      inputNeuron.setIsInputNode(true)
      inputNeuron.setWeights(Vector.fill(1)((Random.nextDouble() * 1) + -1))
    }

    val rand = new Random()
    // initialize the weights through out the network
    for(layerInd <- 1 to neurons.length-1){
      // look back at the previous layer and get the number of neurons
      val sizePrevLayer = neurons(layerInd-1).length
      for(neuron <- neurons(layerInd)){
        // create new random weight vector to be used by this neurons inputs
        //rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        neuron.setWeights(Vector.fill(sizePrevLayer)((rand.nextDouble()*2)-1))
      }

    }
  }

}
