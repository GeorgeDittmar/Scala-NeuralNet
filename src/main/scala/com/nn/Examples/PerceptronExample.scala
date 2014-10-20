package com.nn.Examples

import com.nn.NNetwork

/**
 * Example perceptron learning algorithm using the Scala-NeuralNetwork framework
 * Created by george on 10/19/14.
 */
object PerceptronExample extends App{

  var perceptron = new NNetwork()

  // simple perceptron with an input layer and one output layer
  perceptron.createInputLayer(784)
  perceptron.createOutputLayer(1)

  // creates the initial random weights in the network
  perceptron.init()

  perceptron.inputTraining(null,"")
  perceptron.inputTest(null,"")

  println("Perceptron initialized")
}
