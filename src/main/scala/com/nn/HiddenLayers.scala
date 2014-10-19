package com.nn

import scala.collection.mutable.ArrayBuffer

/**
 * Created by george on 10/19/14.
 */
class HiddenLayers {

  /**
   * Creates a hidden layer in the network
   * @param numUnits
   * @return
   */
  def createHiddenLayer(numUnits: Int): ArrayBuffer[Neuron] ={
    val layer = new ArrayBuffer[Neuron]()
    for(x <- numUnits){
      layer.+=(new Neuron)
    }
    return layer
  }

}
