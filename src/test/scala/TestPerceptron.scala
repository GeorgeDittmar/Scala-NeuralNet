import com.nn.math.activations.StepFunction
import com.nn.{PerceptronLearningTrait, NNetwork}
import org.scalatest._
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Created by george on 10/11/14.
 */

class TestPerceptron extends AssertionsForJUnit {
  var neural_net: NNetwork with PerceptronLearningTrait = _
  /**
   * Intialize the neural network for the tests
   */
  @Before def initialize(): Unit ={
    neural_net = new NNetwork with PerceptronLearningTrait
  }


  @Test
  def testInvalidOutputLayer(): Unit ={
    try {
      //neural_net.createOutputLayer(10,"step")

      // fail if we succeed in creating the output layer
      fail()
    }
    catch {
      case e: IllegalStateException => // expected error
    }

  }

  @Test
  def testLayerCreation(): Unit ={
    //neural_net.createInputLayer(12,"step")
    //neural_net.createOutputLayer(1,"step")
    //neural_net.init()

    val neuralMap = neural_net.neurons
//    assertTrue(neuralMap(0).size > 0 && neuralMap(0).size == 11)
//    assertTrue(neuralMap(1).size > 0 && neuralMap(1).size == 1)
  }



  /**
   * Make sure the framework is correctly loading and storing the input data for the network
   */
  @Test
  def testNNInputData(): Unit ={
    val testInput = Array.fill[Double](12)(Random.nextInt());
    neural_net.loadTrainingExample(testInput)
    neural_net.createInputLayer(10,new StepFunction(0.0))
    neural_net.createOutputLayer(1,new StepFunction(0.0))
    assertTrue(neural_net.inputTraining.size == 1 && neural_net.inputTraining(0) == testInput)
  }

  @Test
  def testFeedingTrainingData(): Unit ={
    neural_net = new NNetwork with PerceptronLearningTrait
    val l: ArrayBuffer[NNetwork]= new ArrayBuffer[NNetwork]().+=(neural_net)
    l(0).isInstanceOf[NNetwork with PerceptronLearningTrait]
    val testInput = Array.fill[Double](12)(Random.nextInt());
    neural_net.loadTrainingExample(testInput)
    neural_net.createInputLayer(10,new StepFunction(0.0))
    neural_net.createOutputLayer(1,new StepFunction(0.0))

    // Initialize the network with random weights between -1 and 1
    neural_net.init()

    neural_net.learn(1,1)

    val neurons = neural_net.neurons

    // check that our output nodes have input data
//    assertTrue(neurons(0).forall(x=> x.inputs.size > 0))
  }

}
