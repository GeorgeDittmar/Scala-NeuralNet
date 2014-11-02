package com.nn

import scala.collection.mutable.ArrayBuffer

/**
 * The learning rule for the perceptron algorithm.
 * Created by george on 10/26/14.
 */
trait PerceptronLearningTrait {

  // Learn the error of the algorithms prediction and return the delta we need to change our weights by
  def calcError(lRate: Double, target: Int, output: Int, example: Double): Double ={
    return lRate*(target-output)*example
  }

  // Adjusts the weights matrix
  def adjustWeights(eRate: Double,weights: ArrayBuffer[Vector[Double]]): Unit ={
    weights.foreach(elm => applyDelta(eRate,elm))
  }

  def applyDelta(eRate: Double, elm: Vector[Double]): Unit ={
    elm.length
  }
}
