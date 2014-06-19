package com.panpan.myspider;

import java.util.LinkedList;

public class Queue {
	//用 LinkedList实现队列
	private LinkedList queue = new LinkedList();
	//入队列
	public void enQueue(Object t){
		queue.addLast(t);
	}
	//出队列
	public Object deQueue(){
		return queue.removeFirst();
	}
	//判断队列是否为空
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	//判断队列是否包含t
	public boolean contains(Object t){
		return queue.contains(t);
	}
	//判断队列是否为空 看看git的输出
	public boolean empty(){
		return queue.isEmpty();
	}
}
