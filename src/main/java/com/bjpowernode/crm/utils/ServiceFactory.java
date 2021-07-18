package com.bjpowernode.crm.utils;

public class ServiceFactory {
	
	public static Object getService(Object service){
		//return "123456";
		//System.out.println("0");
		return new TransactionInvocationHandler(service).getProxy();
		
	}
	
}
