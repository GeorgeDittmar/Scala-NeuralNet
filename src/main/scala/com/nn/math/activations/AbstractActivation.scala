package com.nn.math.activations

import com.nn.Neuron

/**
 * Created by george on 12/8/14.
 */
abstract class AbstractActivation extends Neuron {
  def activation(x:Array[Double], w:Vector[Double], threshold: Double): Double
  def activation(x:Array[Double], w:Vector[Double]): Double

}
