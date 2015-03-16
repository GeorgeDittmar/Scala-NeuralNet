package com.nn

import scala.collection.mutable.ArrayBuffer

/**
 * The learning rule for the perceptron algorithm.
 * Created by george on 10/26/14.
 */
trait PerceptronLearningTrait {
  this: NNetwork =>

  // Adjusts the weights in the network for the perceptron learning rule
  def adjustWeights(lRate: Double, target: Int, output: Int, elm: Double): Unit ={
    // grab each neuron and adjust the weights individually
    for(nLayer <- neurons){
      for(neuron <- nLayer.layer){
        neuron.inputWeights = neuron.inputWeights.map{w => w+lRate*(target-output)*elm }
      }
    }
  }

  /**
   * Invokes the learning method on a set of training data for a given number of epochs
   * @param epochs
   */
  def learn(epochs: Int, target:Int): Unit ={

    for(x <- 0 until epochs){

      // loop over each example
      for(example <- inputTraining){

        var label = example.head
        val sublist = example.slice(1, example.length)

        // feed the input through the layers of the network starting with the input layer
        // TODO - probably good idea to rethink how the input layer is setup since this feels hacky and not functional
        for(inputNeuron <- neurons(0).layer){
          sublist.foreach(example => inputNeuron.input(new ArrayBuffer[Double].+=(example)))
//          for(i <- 0 to sublist.length-1){
//            neuralLayer.input(new ArrayBuffer[Double]().+=(sublist(i)))
//          }
        }

        // feed the input layer to the next layer in the network

      }

    }

  }

}
