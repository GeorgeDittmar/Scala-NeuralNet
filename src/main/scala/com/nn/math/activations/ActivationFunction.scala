package com.nn.math.activations

import scala.collection.mutable.ArrayBuffer

/**
 * Parent Abstract class to represent our activation functions
 * Created by george on 12/8/14.
 */
abstract class ActivationFunction {
  def activation(inputs : Vector[Double], weights: Vector[Double]): Double
}
