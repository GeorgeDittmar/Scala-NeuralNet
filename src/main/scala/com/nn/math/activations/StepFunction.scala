package com.nn.math.activations

import com.nn.Neuron

import scala.collection.mutable.ArrayBuffer

/**
 * Implements the Heaviside step function
 * Created by george on 12/6/14.
 */
trait StepFunction{

  this: Neuron =>
  /**
   * Computes the step activation for a neuron given the input data
   * @param x
   * @param w
   * @return
   */
  def activation( x:Array[Double], w:Vector[Double], threshold: Double): Double ={
    // perform dot product between input and weights
    require(x.size == w.size)
    val dot = (for ((a, b) <- x zip w) yield a * b) sum

    if(dot >= threshold) {
      return 1.0
    }
    return 0.0
  }

}
