package com.nn

import com.nn.math.activations.ActivationFunction

import scala.collection.immutable.VectorBuilder
import scala.collection.mutable.ArrayBuffer

/**
 * Base class to store a layer of the network along with the activation function to use
 * Created by George Dittmar on 2/1/15.
 */
class Layer (size: Int, function : ActivationFunction, prev: Layer=null) {
  val neuralLayer:ArrayBuffer[Neuron] = new ArrayBuffer[Neuron](size)
  val activationFunction : ActivationFunction = function
  val previousLayer : Layer = prev
  var isInputLayer: Boolean = false
  var isOutputLayer: Boolean = false

  /**
   * function that processes the current layer of neurons and generates a vector of outputs to be processed by the next layer
   */
  def process(): Vector[Double] ={

    var outputBuilder = new VectorBuilder[Double]()
    if( isInputLayer ){
      // just pas through all of the inputs
      neuralLayer.foreach( neuron => outputBuilder.+=(neuron.inputs(0)) )
    }else {
      neuralLayer.foreach(neuron => outputBuilder += activationFunction.activation(neuron.inputs, neuron.inputWeights))
    }
    return outputBuilder.result()
  }
}
