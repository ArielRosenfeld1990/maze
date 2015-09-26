package controller;

import java.io.IOException;
import java.util.HashMap;
import model.Model;
import view.CrossSectionDisplayer;
import view.Maze3dDisplayer;
import view.SolutionDisplayer;
import view.StringArrayDisplayer;
import view.StringDisplayer;
import view.View;

/**
* <h1>MyController</h1>
* The MyController class implements the Controller interface
* <p>
*
* @author  Ariel Rosenfeld,Ofir Calif
*
* 
*/
public class MyController implements Controller {
	private View view;
	private Model model;
	private HashMap<String, Command> commands;
	
	/**
	* constructor for MyController
	*/
	public MyController() {
		initilaizeCommands();
	}

    /**
	* this method initializing the command in our hashmap
	*/
	public void initilaizeCommands()
	{
		commands=new HashMap<String,Command>();
		commands.put("dir", new DirCommand());
		commands.put("generate3dMaze",new Generate3dMazeCommand());
		commands.put("display", new DisplayCommand());
		commands.put("displayCrossSection", new DisplayCrossSectionCommand());
		commands.put("saveMaze", new SaveMazeCommand());
		commands.put("loadMaze", new LoadMazeCommand());
		commands.put("mazeSize", new MazeSizeCommand());
		commands.put("fileSize", new FileSizeCommand());
		commands.put("solve", new SolveCommand());
		commands.put("displaySolution", new DisplaySolutionCommand());
		commands.put("exit", new ExitCommand());
	}

	/**
	* a getter for the view layer
	*@return view is the view layer
	*/
	public View getView() {
		return view;
	}
	/**
	* This method is a setter for the view layer
	* @param v is the view layer.
	*/
	public void setView(View view) {
		this.view = view;
		view.setCommands(commands);
	}
	/**
	* a getter for the model layer
	*@return model is the model layer
	*/
	public Model getModel() {
		return model;
	}
	/**
	  * This method is a setter for the model layer
	  * @param m is the model layer.
	  */
	public void setModel(Model model) {
		this.model = model;
	}
	/**
	* This method is used for getting updates from our model layer
	* when something happened 
	* @param obj is the object that we get from the model layer.
	*/
	@Override
	public void update(Object obj) {
		if(obj!=null)
		{
			switch (obj.getClass().getSimpleName()) {
			case "String":
				view.display(obj, new StringDisplayer());
				break;
			case "String[]":
				view.display(obj, new StringArrayDisplayer());
				break;
			case "Maze3d":
				view.display(obj, new Maze3dDisplayer());
				break;
			case "int[][]":
				view.display(obj, new CrossSectionDisplayer());
				break;
			case "Solution":
				view.display(obj, new SolutionDisplayer());
				break;
			default:
				break;
			}
		}
	}





	/**
	* <h1>Command</h1>
	* The Command interface represents a command
	* <p>
	* 
	*/
	public interface Command {
		void doCommand(String[] args );
	}
	
	/**
	* <h1>DirCommand</h1>
	* The DirCommand class implements our Command interface
	* for displaying the directory
	* <p>
	* 
	*/
	public class DirCommand implements Command { //ofir
		@Override
		public void doCommand(String[] args) {
			try {
				 model.dir(args[0]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("must insert a path");}
		}
	}
	
	/**
	* <h1>Generate3dMazeCommand</h1>
	* The Generate3dMazeCommand class implements our Command interface
	* for generating 3d maze
	* <p>
	* 
	*/
	public class Generate3dMazeCommand implements Command{
		@Override
		public void doCommand(String[] args) {
			try{
				model.generate3dMaze(args[0],args[1],Integer.decode(args[2]),Integer.decode(args[3]),Integer.decode(args[4]));
			}
			catch(ArrayIndexOutOfBoundsException e){
				update("paramters missing"); }
			catch (NumberFormatException e) {
				update("invalid paramters");}
		}}

	/**
	* <h1>DisplayCommand</h1>
	* The DisplayCommand class implements our Command interface
	* for displaying the maze
	* <p>
	* 
	*/
	public class DisplayCommand implements Command	{
		@Override
		public void doCommand(String[] args) {
			try {
				model.getMazeByName(args[0]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("must insert maze name");}
		}				
	}

	/**
	* <h1>DisplayCrossSectionCommand</h1>
	* The DisplayCrossSectionCommand class implements our Command interface
	* for displaying a cross section for our 3d maze
	* <p>
	* 
	*/
	public class DisplayCrossSectionCommand implements Command	{
		@Override
		public void doCommand(String[] args) {
			try {
				model.getCrossSection(args[0].charAt(0),Integer.decode(args[1]),args[2]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("paramters missing");}
			catch (NumberFormatException e) {
				update("invalid paramters");}
		}				
	}

	/**
	* <h1>SaveMazeCommand</h1>
	* The SaveMazeCommand class implements our Command interface
	* for saving the maze
	* <p>
	* 
	*/
	public class SaveMazeCommand implements Command	{
		@Override
		public void doCommand(String[] args) {
			try{
				model.saveMaze(args[0], args[1]);	
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("paramters missing");
			} 
			catch (IOException e) {
				update(e.getMessage());
			}
		}
	}

	/**
	* <h1>LoadMazeCommand</h1>
	* The LoadMazeCommand class implements our Command interface
	* for loading the maze
	* <p>
	* 
	*/
	public class LoadMazeCommand implements Command	{
		@Override
		public void doCommand(String[] args) {
			try{
				model.loadMaze(args[0], args[1]);	
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("paramters missing");
			}
			catch (IOException e) {
				update(e.getMessage());
			}
		}
	}

	/**
	* <h1>MazeSizeCommand</h1>
	* The MazeSizeCommand class implements our Command interface
	* for displaying the maze size
	* <p>
	* 
	*/
	public class MazeSizeCommand implements Command
	{
		@Override
		public void doCommand(String[] args) {
			try {
				model.getMazeSize(args[0]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("must insert maze name");}
		}				
	}
	
	/**
	* <h1>FileSizeCommand</h1>
	* The FileSizeCommand class implements our Command interface
	* for displaying the size of a file
	* <p>
	* 
	*/
	public class FileSizeCommand implements Command
	{
		@Override
		public void doCommand(String[] args) {
			try {
				model.getFileSize(args[0]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("must insert file name");}
		}				
	}
	
	/**
	* <h1>SolveCommand</h1>
	* The SolveCommand class implements our Command interface
	* for solving the maze for the different algorithms
	* <p>
	* 
	*/
	public class SolveCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			try{
				model.solve(args[0],args[1]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("paramters missing");
			}
		}
	}

	/**
	* <h1>DisplaySolutionCommand</h1>
	* The DisplaySolutionCommand class implements our Command interface
	* for displaying the solution for a maze
	* <p>
	* 
	*/
	public class DisplaySolutionCommand implements Command	{
		@Override
		public void doCommand(String[] args) {
			try {
				model.getSolutionForName(args[0]);
			}
			catch(ArrayIndexOutOfBoundsException e)	{
				update("must insert maze name");}
		}				
	}
	
	/**
	* <h1>ExitCommand</h1>
	* The ExitCommand class implements our Command interface
	* for safely exiting the program
	* <p>
	* 
	*/
	public class ExitCommand implements Command{
		@Override
		public void doCommand(String[] args) {
			model.close();
			view.close();
		}
	}
}
