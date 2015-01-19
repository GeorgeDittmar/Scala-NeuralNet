import com.nn.math.activations.StepFunction
import com.nn.{PerceptronLearningTrait, NNetwork}
import org.scalatest._
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

import scala.util.Random

/**
 * Created by george on 10/11/14.
 */
class TestPerceptron extends AssertionsForJUnit {
  var neural_net: NNetwork = _
  /**
   * Intialize the neural network for the tests
   */
  @Before def initialize(): Unit ={
    neural_net = new NNetwork with PerceptronLearningTrait
  }


  @Test
  def testInvalidOutputLayer(): Unit ={
    try {
      neural_net.createOutputLayer(10,"step")

      // fail if we succeed in creating the output layer
      fail()
    }
    catch {
      case e: IllegalStateException => // expected error
    }

  }

  @Test
  def testLayerCreation(): Unit ={
    neural_net.createInputLayer(10)
    neural_net.createOutputLayer(1,"step")
    neural_net.init()

    val neuralMap = neural_net.neurons
    assertTrue(neuralMap(0).size > 0 && neuralMap(0).size == 11)
    assertTrue(neuralMap(1).size > 0 && neuralMap(1).size == 1)
  }



  /**
   * Make sure the framework is correctly loading and storing the input data for the network
   */
  @Test
  def testNNInputData(): Unit ={
    val testInput = Array.fill[Double](11)(Random.nextInt());
    neural_net.loadTrainingExample(testInput)
    neural_net.createInputLayer(10)
    neural_net.createOutputLayer(1,"step")
    assertTrue(neural_net.inputTraining.size == 1 && neural_net.inputTraining(0) == testInput)
  }

  @Test
  def testFeedingExampleData(): Unit ={
    val testInput = Array.fill[Double](11)(Random.nextInt());
    neural_net.loadTrainingExample(testInput)
    neural_net.createInputLayer(10)
    neural_net.createOutputLayer(1,"step")

    // Initialize the network with random weights between -1 and 1
    neural_net.init()

  }

}
