package com.bjpowernode.crm.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

public class TransactionInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	public TransactionInvocationHandler(Object target){
		//System.out.println("1");
		this.target = target;
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//System.out.println("2");
		SqlSession session = null;
		
		Object obj;
		
		try{
		//	System.out.println("3");
			session = SqlSessionUtil.getSqlSession();
			
			obj = method.invoke(target, args);
			
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
			
			//处理的是什么异常，继续往上抛什么异常
			throw e.getCause();
		}finally{
			SqlSessionUtil.myClose(session);
		}
		
		return obj;
	}
	
	public Object getProxy(){
	//	System.out.println("4");
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
		
	}
	
}











































