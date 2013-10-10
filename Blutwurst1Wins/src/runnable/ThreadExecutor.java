package runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
}
