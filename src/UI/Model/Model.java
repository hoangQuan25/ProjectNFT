package UI.Model;

import UI.View.ViewFactory;

public class Model {
	//Singleton Model a class has only one instance
	private static Model model;
	private final ViewFactory viewFactory;
	
	private Model() {
		this.viewFactory = new ViewFactory();
	}
	
	public static synchronized Model getInstance() {
		if (model ==null) {
			model = new Model();
		}
		return model;
	}
	
	public ViewFactory getViewFactory() {
		return viewFactory;
	}
}
