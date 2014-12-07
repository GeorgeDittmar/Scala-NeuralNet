package com.nn

import scala.collection.mutable.ArrayBuffer

/**
 * The learning rule for the perceptron algorithm.
 * Created by george on 10/26/14.
 */
trait PerceptronLearningTrait extends NNetwork{

  // Learn the error of the algorithms prediction and return the delta we need to change our weights by
  def calcError(lRate: Double, target: Int, output: Int, example: Double): Double ={
    return lRate*(target-output)*example
  }

  // Adjusts the weights matrix
  def adjustWeights(eRate: Double,weights: ArrayBuffer[Vector[Double]]): Unit ={
    weights.foreach(elm => applyDelta(eRate,elm))
  }

  def applyDelta(eRate: Double, elm: Vector[Double]): Unit ={

  }

  /**
   * Invokes the learning method on a set of training data for a given number of epochs
   * @param epochs
   */
  def learn(epochs: Int): Unit ={

    for(x <- 0 until epochs){

      // loop over each example
      for(example <- super.inputTraining){

        var label = example.head
        var sublist = example.slice(1,example.length)



      }

    }

  }
}
