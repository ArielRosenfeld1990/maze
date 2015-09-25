package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.MyController.Command;

/**
* <h1>CLI</h1>
* The CLI class represents our Command Line Interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class CLI extends Thread {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;
	Thread mainThread;
	volatile boolean stop;
	
	/**
	* constructor for CLI
	*/
	public CLI(InputStream in,OutputStream out,HashMap<String, Command> commands) {
		this.in=new BufferedReader(new InputStreamReader(in));
		this.out=new PrintWriter(out);
		this.commands=commands;
	}
	/**
	* this method is used to start the main loop of the cli
	*/
	public void start()
	{//run in thread
		mainThread = new Thread(new Runnable() {
			@Override
			public void run() {
				String inputString;
				try {
					while (!stop) {
						inputString = in.readLine();
						String[] inputStrings=inputString.split(" ");
						if(commands.containsKey(inputStrings[0]))
							commands.get(inputStrings[0]).doCommand(extractParamters(inputStrings, 1));
						else
						{
							out.println("not a valid command");
							out.flush();
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mainThread.start();
	}
	/**
	* This method is for displaying the result 
	* in the CLI
	* @param obj is the object that we got.
	* @param displayer is the kind of displayer we need to show in the CLI.
	*/
	public void display(Object obj,Displayer displayer)
	{
		displayer.display(obj, out);
	}
	/**
	 * this method stops the main loop
	 */
	public void close()
	{
		stop=true;
		out.println("bye");
		out.flush();
	}
	/**
	* This method is for seperating between the command and the parameters
	* @param stringArray is the full command that was inserted.
	* @param numberOfcellsToIgnore is the number of cells in the array
	* that we need to ignore in order to perform the seperation.
	*/
	private String[] extractParamters(String[] stringArray,int numberOfcellsToIgnore)
	{
		String[] params=new String[stringArray.length-numberOfcellsToIgnore];
		for (int i = numberOfcellsToIgnore; i < stringArray.length; i++) {
			params[i-numberOfcellsToIgnore] = stringArray[i];
		}
		return params;
	}
}
