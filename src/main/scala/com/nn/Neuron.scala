package com.nn

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Initial Building block of a neural network. Takes input and output
 * Created by george on 10/11/14.
 */
class Neuron {
  // scala is weird. This is initialized to some default for now

  var inputWeights: Vector[Double] = _
  var inputs: ArrayBuffer[Double] = _
  var activation : Double = _
  var isInputNode : Boolean = false

  def input(inputs: ArrayBuffer[Double]): Unit ={
    this.inputs = inputs
  }

  /**
   * lazilly build up the inputs for the neuron 1 element at a time. Might be good for iterative loading of data
   * @param x
   */
  def input(x: Int): Unit ={
    inputs.+=(x)
  }

  /**
   * used to set the weights of the neuron
   * @param weights
   */
  def setWeights(weights: Vector[Double]): Unit ={
    inputWeights = weights
  }

  /**
   * initializes the neuron with default values and if it is an input layer node or not
   */
  def setIsInputNode(isInputNode : Boolean): Unit ={
    this.isInputNode = isInputNode
  }

}
