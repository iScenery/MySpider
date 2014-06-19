package com.panpan.myspider.entity;

import java.io.Serializable;
import java.util.Date;

import com.sleepycat.je.utilint.Timestamp;

public class CrawUrl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oriUrl;//ԭʼURL��ַ����������������
	private String url;//URL��ֵ������������ip,Ϊ�˷�ֹ�ظ���������
	private int urlNo;//URL NUM
	private int statusCode;//��ȡURL���صĽ����
	private int hitNum;//�����±������������õĴ���
	private String charSet;//��URL��Ӧ���µĺ��ֱ���
	private String abstractText;//����ժҪ
	private String author;//����
	private int weight;//���µ�Ȩ�أ���������ʵ���Ϣ
	private String description;//���µ�����
	private int fileSize;//���µĴ�С
	private Timestamp lastUpdateTime;//���ĸ���ʱ��
	private Date timeToLive;//����ʱ��
	private String title;//��������
	private String type;//��������
	private String[] urlRefrences;//���õ�����
	private int layer;//��ȡ�Ĳ�Σ������ӿ�ʼ�����ǵ�0�㣬��1�㡣����
	public String getOriUrl() {
		return oriUrl;
	}
	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlNo() {
		return urlNo;
	}
	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getHitNum() {
		return hitNum;
	}
	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getUrlRefrences() {
		return urlRefrences;
	}
	public void setUrlRefrences(String[] urlRefrences) {
		this.urlRefrences = urlRefrences;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
