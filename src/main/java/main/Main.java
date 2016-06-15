package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pricechecker.GoGoGo;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
    	GoGoGo go = new GoGoGo();
    	go.go();
    }
}
