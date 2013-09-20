package controller;

import model.Model;
import view.SpielView;

public class SpielVC {
	private Model model;
	private SpielView view;
	
	public SpielVC(Model model){
		this.model = model;
		this.view = new SpielView();
	}
	
	public void show(){
		view.show(model.getPrimaryStage());
	}
}
