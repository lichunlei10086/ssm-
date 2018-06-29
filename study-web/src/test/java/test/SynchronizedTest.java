package test;

public class SynchronizedTest {
		
			public static synchronized void testSyncOnStaticMethod(){
				System.out.println("静态方法上");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}

			
			public static void testSyncOnClass(){
				synchronized (SynchronizedTest.class) {
					System.out.println("类上");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			public synchronized void testSyncOnMethod(){
				System.out.println("加在普通方法上");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			public void testSyncOnThis() {
				synchronized (this) {
					System.out.println("加在 this 上");
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
				}
			}

			
			
			public static void casel(){
				final SynchronizedTest s1  = new SynchronizedTest();
				new Thread(new Runnable() {
					public void run() {
						s1.testSyncOnThis();
					}
				}).start();
				
				
				new Thread(new Runnable() {
					public void run() {
						s1.testSyncOnMethod();
					}
				}).start();

			}
			
			
			public static void case2() {
				new Thread(new Runnable() {
		 
					public void run() {
						SynchronizedTest.testSyncOnClass();
					}
				}).start();
		 
				new Thread(new Runnable() {
		 
					public void run() {
						SynchronizedTest.testSyncOnStaticMethod();
					}
				}).start();
			}

			
			public static void main(String[] args){
				casel();
				case2();

			}
			
			
			
			
}
