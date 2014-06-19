package com.panpan.myspider;

import java.util.HashSet;
import java.util.Set;

/**
 * 1 ������Ϣ�����ظ�
 * 2 ��ѯ�ٶ�Ҫ��
 * @author liangying
 *
 */
public class LinkQueue {
	//�ѷ��ʵ�url����
	private static Set visitedUrl = new HashSet();
	//�����ʵ�url����
	private static Queue unVisitedUrl = new Queue();
	//���url����
	public static Queue getUnVistedUrl (){
		return unVisitedUrl;
	}
	//��ӵ����ʹ���url������
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	//�Ƴ����ʹ���url
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	//δ���ʹ���url����
	public static Object unVisitedUrlDequeue(){
		return unVisitedUrl.deQueue();
	}
	//��֤ÿ��urlֻ����һ��
	public static void addUnvisitedUrl(String url){
		if(url != null && !url.trim().equals("") && !unVisitedUrl.contains(url) && !visitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
			
	}
	//����Ѿ����ʹ���url����Ŀ
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	//�ж�δ���ʵĶ������Ƿ�Ϊ��
	public static boolean unVisitedUrlEmpty(){
		return unVisitedUrl.empty();
	}
}






