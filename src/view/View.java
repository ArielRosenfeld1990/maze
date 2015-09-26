package view;

import java.util.HashMap;

import controller.MyController.Command;
   
/**
* <h1>View</h1>
* The View interface represents our View layer
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public interface View {
	/**
	* this method is used for starting interaction between the user and the CLI
	*/
	void start();
	/**
	* This method is for displaying the object
	* @param obj is the object that we need to display.
	* @param out is from where we are going to display it.
	*/
	void display(Object obj,Displayer displayer);
	/**
	* This method is for setting the commands
	* @param commands is the hashmap that maps between the string and the command itself.
	*/
	void setCommands(HashMap<String, Command> commands);
	/**
	 * this method is for closing the view
	 */
	void close();
}
