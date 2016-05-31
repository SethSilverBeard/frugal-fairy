package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.amazon.mws.Credentials;

public class Main {
	
	static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        configureLogging();
        
    }
    
    private static void configureLogging() throws Exception {
    	System.setProperty("java.util.logging.config.class", SethsLog.class.getName());
        LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("seths-log.properties"));
    }
}
