package com.nn.Examples

import com.nn.NNetwork

/**
 * Created by george on 10/19/14.
 */
object PerceptronExample extends App{
  println("Hello World")

  var perceptron = new NNetwork()

  // simple perceptron with an input layer and one output layer
  perceptron.createInputLayer(784)
  perceptron.createOutputLayer(1)

  perceptron.init()

  println("Perceptron initialized")

}
