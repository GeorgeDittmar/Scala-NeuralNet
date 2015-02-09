package com.nn.math.activations

import scala.collection.mutable.ArrayBuffer

/**
 * Created by george on 2/1/15.
 */
trait NetworkActivations {


  trait SigmoidActivation{
    def activation(){}
  }

  trait StepActivation{
    def activation(){}
  }
}
