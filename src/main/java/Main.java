import adapter.Adapter;
import model.Model;
import view.View;

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

