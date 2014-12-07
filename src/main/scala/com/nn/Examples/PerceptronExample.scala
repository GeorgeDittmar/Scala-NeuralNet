package com.nn.Examples

import java.io.FileInputStream

import com.nn.{PerceptronLearningTrait, NNetwork}

import scala.io.BufferedSource

/**
 * Example perceptron learning algorithm using the Scala-NeuralNetwork framework
 * Created by george on 10/19/14.
 */
object PerceptronExample extends App{

  var perceptron = new NNetwork() with PerceptronLearningTrait

  // simple perceptron with an input layer and one output layer
  perceptron.createInputLayer(784)
  perceptron.createOutputLayer(1)

  // creates the initial random weights in the network
  perceptron.init()

  println("Reading in training and testing data sets...")

  //TODO - change to use the scala Source lib
  perceptron.inputTraining(new BufferedSource(new FileInputStream("/home/george/Development/s-nn/Scala-NeuralNet/src/main/resources/train.csv")),",")
  perceptron.inputTest(new BufferedSource(new FileInputStream("/home/george/Development/s-nn/Scala-NeuralNet/src/main/resources/test.csv")),",")

  println("Perceptron initialized")

  // set the learning rate with the given number of epochs
  perceptron.learn(100)


}
