package main.java;
import main.java.adapter.Adapter;
import main.java.model.Model;
import main.java.view.View;

public class Main {

	public static void main(String[] args)
	{
		//Dependency-injection
		//Life-time of the objects is controlled by main
		
		View view = new View();
		Model model = new Model();
		Adapter adapter = new Adapter(view, model);
		
		view.startView();
		
		
		//TODO: start swing (view)

	}

}

