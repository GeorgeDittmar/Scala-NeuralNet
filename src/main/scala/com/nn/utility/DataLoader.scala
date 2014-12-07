package com.nn.utility

import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource

/**
 * Created by george on 12/6/14.
 */
object DataLoader {

  /**
   * Input the training/testing data to be read by the network. This function currently assumes the data is a csv
   */
  def loadCsvData(data: BufferedSource, delim: String): ArrayBuffer[Array[Int]] = {
    val iter = data.getLines().drop(1).map(_.split(delim))
    var dataSet = new ArrayBuffer[Array[Int]]()
    // read in each example and convert to integer arrays
    while(iter.hasNext){
      try {
        val example: Array[Int] = iter.next().map(_.toInt)
        dataSet.+=(example)
      }catch{
        case numFE: NumberFormatException => {
          println("Failed to load example...")
        }
        case ex: Exception => println("Some error has happend reading input data")
      }
    }
    return dataSet
  }

}
