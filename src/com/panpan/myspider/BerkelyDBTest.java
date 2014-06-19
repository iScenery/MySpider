package com.panpan.myspider;

import java.io.File;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class BerkelyDBTest {
	private Environment myEnv;
	public BerkelyDBTest(){};
	public void setUp(File envHome,boolean readOnly){
		EnvironmentConfig myEnvConfig = new EnvironmentConfig();
		
		myEnvConfig.setReadOnly(readOnly);
		myEnvConfig.setAllowCreate(!readOnly);
		
		myEnv = new Environment(envHome, myEnvConfig);
		
	}
	//Getter Method
	public Environment getEnv(){
		return myEnv;
	}
	//Close the Environment
	public void close(){
		if(myEnv!=null){
			myEnv.close();
		}
	}

}
