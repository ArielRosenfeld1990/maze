package view;

import java.util.HashMap;
import controller.Controller;
import controller.MyController.Command;
/** 
* <h1>MyView</h1>
* The MyView class implements our View interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class MyView implements View{
	private Controller controller;
	private HashMap<String, Command> commands;
	private CLI ui;
	/**
	* constructor for MyView
	*/
	public MyView(Controller controller) {
		this.controller=controller;
	}
	/**
	* this method is used for starting interaction between the user and the CLI
	*/
	@Override
	public void start() {
		ui=new CLI(System.in, System.out, commands);
		ui.start();
	}
	/**
	* This method is for setting the commands
	* @param commands is the hashmap that maps between the string and the command itself.
	*/
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		this.commands=commands;
		
	}
	/**
	* This method is for displaying the object
	* @param obj is the object that we need to display.
	* @param out is from where we are going to display it.
	*/
	@Override
	public void display(Object obj,Displayer displayer) {
		ui.display(obj,displayer);
	}
	/**
	 * this method close the view
	 */
	@Override
	public void close() {
		ui.close();
	}

}
