package com.nn.Examples

import java.io.FileInputStream
import com.nn.math.activations.{BipolarSigmoidFunction, ActivationFunction, StepFunction}
import com.nn.{Layer, Neuron, PerceptronLearningTrait, NNetwork}

import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource

/**
 * Example perceptron learning algorithm using the Scala-NeuralNetwork framework
 * Created by george on 10/19/14.
 */
object PerceptronExample extends App{

  var perceptron = new NNetwork() with PerceptronLearningTrait

  // simple perceptron with an input layer of 784 units and output layer of 1 unit
  perceptron.createInputLayer(784,new StepFunction())

  // Creates the single output node using a neuron with a StepActivation function
  perceptron.createOutputLayer(1,new BipolarSigmoidFunction())

  // creates the initial random weights in the network
  perceptron.init()

  println("Reading in training and testing data sets...")

  //TODO - change to use the scala Source lib
  perceptron.loadTrainingSet(new BufferedSource(new FileInputStream("./src/main/resources/train.csv")),",")
  perceptron.loadTestSet(new BufferedSource(new FileInputStream("./src/main/resources/test.csv")),",")

  println("Perceptron initialized")

  // set the learning rate with the given number of epochs and which digit to learn
  perceptron.learn(10,1)



}
