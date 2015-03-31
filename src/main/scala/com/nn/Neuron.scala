package com.nn


import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Initial Building block of a neural network. Takes input and output
 * Created by george on 10/11/14.
 */
class Neuron {
  // scala is weird. This is initialized to some default for now

  var inputWeights = Vector.empty[Double]
  var inputs = Vector.empty[Double]

  def inputs(inputs: Vector[Double]): Unit ={
    this.inputs = inputs
  }

  /**
   * lazilly build up the inputs for the neuron 1 element at a time. Might be good for iterative loading of data
   * @param x
   */
  def input(x: Double): Unit ={
    this.inputs = inputs :+ x
  }

  /**
   * used to set the weights of the neuron
   * @param weights
   */
  def setWeights(weights: Vector[Double]): Unit ={
    this.inputWeights = weights
  }

}
