package controller;
  
import model.Model;
import view.View;
  
/**
* <h1>Controller</h1>
* The Controller interface represents our Controller layer
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public interface Controller {
	/**
    * This method is a setter for the model layer
    * @param m is the model layer.
    */
	void setModel(Model m);
	/**
	* This method is a setter for the view layer
	* @param v is the view layer.
	*/
	void setView(View v);
    /**
	* This method is used for getting updates from our model layer
	* when something happened 
	* @param obj is the object that we get from the model layer.
	*/
	void update(Object obj);
}
