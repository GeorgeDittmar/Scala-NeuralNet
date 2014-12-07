package com.nn.math

import com.nn.Neuron

/**
 * Implements the Heaviside step function
 * Created by george on 12/6/14.
 */
trait StepFunction extends Neuron {

  /**
   * Computes the activation for a neuron.
   * @param x
   * @param w
   * @return
   */
  def activation( x:Array[Double], w:Vector[Double]): Double ={
    // perform dot product between input and weights
    require(x.size == w.size)
    val dot = (for ((a, b) <- x zip w) yield a * b) sum

    if(dot > 0) {
      return 1.0
    }
    return -1.0
  }
}
