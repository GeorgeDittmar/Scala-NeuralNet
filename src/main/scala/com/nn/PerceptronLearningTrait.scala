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
        sublist.foreach(elm => println(elm))
        // feed the input through the layers of the network starting with the input layer
        for ((neuron,inp) <- (neurons(0).neuralLayer zip sublist)) yield neuron.input(inp)
//        neurons(0).neuralLayer.foreach(inputNeuron =>
//          sublist.foreach(element =>
//            inputNeuron.input(element)
//          )
//        )

        var networkOuput : Vector[Double] = null
        // feed the input through the network
        neurons.foreach{layer =>
          if(layer.previousLayer != null){
            val output = layer.previousLayer.process()
            layer.neuralLayer.foreach(neuron => neuron.inputs(output))

            if(layer.isOutputLayer){
              // if we are at the output layer we process that layer
              networkOuput = layer.process()
            }
          }
        }

        // eval the output with the actual label
        System.out.println("OUTPUT IS " + networkOuput )

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
