package com.nn

import java.io.File

import com.nn.math.activations.{ActivationFunction, StepFunction}

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
  val neurons = ArrayBuffer[Layer]()
  // Input to the network. This can be either training or testing data
  var inputTraining = ArrayBuffer[Array[Double]]()
  var inputTesting = ArrayBuffer[Array[Double]]()

  /**
   * Creates the input layer of n + 1 bias input neuron
   */
  def createInputLayer(numUnits: Int, activationFunction : ActivationFunction): Unit ={
    val inputLayer: Layer = new Layer(numUnits,activationFunction,null)
    
    for(x <- 1 to numUnits + 1){
      inputLayer.layer.+=(new Neuron)
    }
    neurons.+=(inputLayer)
  }

  /**
   * This must be called last. If we have no neurons already in the network throws an illegal state exception
   * @param numUnits
   */
  def createOutputLayer[T](numUnits: Int, activationFunction: ActivationFunction): Unit = {
    if(neurons.length == 0){
      throw new IllegalStateException("Network does not appear to be initialized with any neurons.")
    }
    val outputLayer: Layer = new Layer(numUnits,activationFunction,null)

    for (x <- 1 to numUnits) {
      outputLayer.layer.+=(new Neuron)
    }
    neurons.+=(outputLayer)
  }

  /**
   * Creates a generic layer of the neural network.
   * @param numUnits
   */
  def createLayer(numUnits: Int, activationFunction: ActivationFunction): Unit = {
    if(neurons.length == 0){
      throw new IllegalStateException("Network does not appear to be initialized with any neurons.")
    }
    
    val layer: Layer = new Layer(numUnits,activationFunction,null)

    for (elm <- 1 to numUnits) {
      layer.layer.+=(new Neuron)
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
    for(inputNeuron <- neurons(0).layer){
      inputNeuron.setIsInputNode(true)
      inputNeuron.setWeights(Vector.fill(1)((Random.nextDouble() * 1) + -1))
    }

    val rand = new Random()
    // initialize the weights through out the network
    for(layerInd <- 1 to neurons.length-1){
      // look back at the previous layer and get the number of neurons
      val sizePrevLayer = neurons(layerInd-1).layer.length
      for(neuron <- neurons(layerInd).layer){
        // create new random weight vector to be used by this neurons inputs
        neuron.setWeights(Vector.fill(sizePrevLayer)((rand.nextDouble()*2)-1))
      }

    }
  }

  /**
   * Given example X to classify, produce a target Y as output of the network
   * @param example
   */
  def classify(example: ArrayBuffer[Double]): Unit ={

  }

}
