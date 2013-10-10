import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestKlasse {
	public static void main(String[] args){
//		for(int i=0;i<128;i++){
//			System.out.println("main: " + i);
//			if(i==1){
//				Thread thread = new Thread(new TestKlasse());
//				thread.start();
//			}
//			try{
//				Thread.sleep(30);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}
		Runnable r1 = new Runnable(){
			public void run(){
				for(int i=0;i<128;i++){
					System.out.println("Thread 1: " + i);
					try{
						Thread.sleep(30);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		};
		Runnable r2 = new Runnable(){
			public void run(){
				for(int i=0;i<128;i++){
					System.out.println("Thread 2: " + i);
					try{
						Thread.sleep(30);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		};
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(r1);
		executor.execute(r2);
		
//		for(int j=0;j<128;j++){
//			System.out.println("Main: " + j);
//			if(j==1)
//				executor.execute(r1);
//			try{
//				Thread.sleep(30);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}
		
	}

//	@Override
//	public void run() {
//		for(int i=63;i<(128+63);i++){
//			char c = (char)i;
//			System.out.println("Thread: " + c);
//			
//			try{
//				Thread.sleep(30);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}
//	}
}




















