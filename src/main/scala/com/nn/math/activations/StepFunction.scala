package com.nn.math.activations

import com.nn.Neuron

import scala.collection.mutable.ArrayBuffer

/**
 * Implements the Heaviside step function
 * Created by george on 12/6/14.
 */
class StepFunction(threshold:Double = 0.0) extends ActivationFunction {

  /**
   * Computes the step activation for a neuron given the input data and weights
   * @param x
   * @param w
   * @return
   */
  override def activation( x:ArrayBuffer[Double], w:Vector[Double] ): Double ={
    // perform dot product between input and weights
    require(x.size == w.size)
    val dot = (for ((a, b) <- x zip w) yield a * b) sum

    if(dot > 0.0) {
      return 1.0
    }
    return 0.0
  }

}
