package com.panpan.myspider;

import java.util.LinkedList;

public class Queue {
	//�� LinkedListʵ�ֶ���
	private LinkedList queue = new LinkedList();
	//�����
	public void enQueue(Object t){
		queue.addLast(t);
	}
	//������
	public Object deQueue(){
		return queue.removeFirst();
	}
	//�ж϶����Ƿ�Ϊ��
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	//�ж϶����Ƿ����t
	public boolean contains(Object t){
		return queue.contains(t);
	}
	//�ж϶����Ƿ�Ϊ�� ����git�����
	public boolean empty(){
		return queue.isEmpty();
	}
}
