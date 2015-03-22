package com.nn.math.activations

import scala.collection.mutable.ArrayBuffer

/**
 * A Rectified Linear Activation function.
 * Created by george on 3/22/15.
 */
class RectifiedLinearFunction extends ActivationFunction{

  override def activation(inputs: ArrayBuffer[Double], weights: Vector[Double]): Double = {
    require(inputs.size == weights.size)
    val dot = (for ((a, b) <- inputs zip weights) yield a * b) sum
    return Math.max(0,dot)
  }
}
