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
      for(neuron <- nLayer.neuralLayer){
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
        val sublist = example.slice(1, example.length)

        // feed the input through the layers of the network starting with the input layer
        neurons(0).neuralLayer.foreach(inputNeuron =>
          sublist.foreach(element =>
            inputNeuron.input(element)
          )
        )

        // feed the input through the network
        neurons.foreach{layer => layer.process()

        }

      }
    }
  }

  /**
   * feeds the input layer into the neuron layers.
   * @param inputLayer
   * @param neurons
   */
  private def feedForward(inputLayer: Layer, neurons: ArrayBuffer[Layer]): Unit ={

  }
}
