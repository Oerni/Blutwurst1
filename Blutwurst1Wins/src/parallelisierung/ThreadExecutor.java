package parallelisierung;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import spieldaten.Spiel;
import spieldaten.Zug;
import javafx.collections.ObservableList;

public class ThreadExecutor {
	private static ThreadExecutor instance;
	private ExecutorService executorService;
	
	private ThreadExecutor(){
		this.executorService = Executors.newCachedThreadPool();
	}
	public static ThreadExecutor getInstance(){
		if(instance == null)
			instance = new ThreadExecutor();
		return instance;
	}
	public void execute(Runnable r){
		executorService.execute(r);
	}
	
	public Future<Number> getNumber(Callable<Number> c){
		return executorService.submit(c);
	}
	
	public Future<ObservableList<Spiel>> getSpieldaten(Callable<ObservableList<Spiel>> c){
		return executorService.submit(c);
	}
	
	public Future<Zug> getDurchgefuehrtenZug(Callable<Zug> c){
		return executorService.submit(c);
	}
	
	public Future<Spiel> getSpiel(Callable<Spiel> c){
		return executorService.submit(c);
	}
}
