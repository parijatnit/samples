package com.practice;

import org.junit.Test;

;import java.io.File;

/**
 * Created by pbhattacharya on 2/23/18.
 */
public class IDPSBugTest {

	static class KeyMgr {

		protected Boolean bool = new Boolean(false);

		public void readKeyFromCache() {
			if (this.bool) {
				return;
			}
			synchronized (this.bool) {
				if (!this.bool) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//bool = true;
			}
		}
	}


	@Test
	public void testIDPSBug() throws InterruptedException {
		Runnable r = ()-> {
			long time = System.currentTimeMillis();
			System.out.println(String.format("Getting key, threadId=%1$s", Thread.currentThread().getId()));
			KeyMgr keyMgr = new KeyMgr();
			keyMgr.readKeyFromCache();
			System.out.println(String.format("Done. threadId=%1$s time=%2$s", Thread.currentThread().getId(), (System.currentTimeMillis() - time)/1000));
		};

		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	@Test
	public void testBoolean() {

	}



}
