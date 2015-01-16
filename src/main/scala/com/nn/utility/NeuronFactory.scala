package com.nn.utility

import com.nn.Neuron
import com.nn.math.activations.{BipolarSigmoidFunction, StepFunction}

/**
 * Factory class to create neurons with particular activation functions
 * Created by George Dittmar on 12/28/14.
 * GeorgeDittmar@gmail.com
 */
object NeuronFactory {

  def createNeuronActivation(traitType : String): Neuron={
    if(traitType.equalsIgnoreCase("Step")){
      return new Neuron() with StepFunction
    }else if (traitType.equalsIgnoreCase("Sigmoid")){
      return new Neuron() with BipolarSigmoidFunction
    }else{
      throw new IllegalArgumentException("Unknown activation function: "+traitType)
    }

  }

}
