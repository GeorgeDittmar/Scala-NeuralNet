/**
 * Created by george on 10/11/14.
 */
trait StepActivation {

  //Calculates the activation of a neuron given a list of inputs and a list of weights
  def stepActivation(x: List[Double],w: List[Double], threshold:Double): Double ={
    // perform the dot product by sum x_i*w_i
    var act = 0.0;
    for (elm <-0 to x.length){
       act += (x(elm) * w(elm))
    }

    // logic for the firing of the neuron as activated or not
    // Just a simple postive or negative check
    if ( act > threshold ) {
      return act
    }
    return 0.0
  }

}
