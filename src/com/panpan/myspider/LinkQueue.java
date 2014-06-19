package com.panpan.myspider;

import java.util.HashSet;
import java.util.Set;

/**
 * 1 数据信息不能重复
 * 2 查询速度要快
 * @author liangying
 *
 */
public class LinkQueue {
	//已访问的url集合
	private static Set visitedUrl = new HashSet();
	//待访问的url集合
	private static Queue unVisitedUrl = new Queue();
	//获得url队列
	public static Queue getUnVistedUrl (){
		return unVisitedUrl;
	}
	//添加到访问过的url队列中
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	//移除访问过的url
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	//未访问过的url出列
	public static Object unVisitedUrlDequeue(){
		return unVisitedUrl.deQueue();
	}
	//保证每个url只访问一次
	public static void addUnvisitedUrl(String url){
		if(url != null && !url.trim().equals("") && !unVisitedUrl.contains(url) && !visitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
			
	}
	//获得已经访问过的url的数目
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	//判断未访问的队列中是否为空
	public static boolean unVisitedUrlEmpty(){
		return unVisitedUrl.empty();
	}
}






