package com.nn.math.activations

import com.nn.Neuron

/**
 * Implements the Heaviside step function
 * Created by george on 12/6/14.
 */
trait StepFunction extends AbstractActivation {



  /**
   * Computes the step activation for a neuron given the input data
   * @param x
   * @param w
   * @return
   */
  override def activation( x:Array[Double], w:Vector[Double], threshold: Double): Unit ={
    // perform dot product between input and weights
    require(x.size == w.size)
    val dot = (for ((a, b) <- x zip w) yield a * b) sum

    if(dot >= threshold) {
      activation = 1.0
    }

  }

}
