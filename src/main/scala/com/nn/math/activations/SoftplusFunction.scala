package com.nn.math.activations

import java.awt.event.ActionEvent
import javax.swing.AbstractAction

import scala.collection.mutable.ArrayBuffer

/**
 * Softplus Activation Function
 * Created by george on 3/22/15.
 */
class SoftplusFunction extends ActivationFunction {
  override def activation(inputs: Vector[Double], weights: Vector[Double]): Double = {
    require(inputs.size == weights.size)
    val dot = (for ((a, b) <- inputs zip weights) yield a * b) sum
    return Math.max(0,Math.log(1+Math.exp(dot)))
  }
}
