package com.panpan.myspider;

import java.util.Set;

public class MyCrawler {
	/**
	 * ʹ�����ӳ�ʼ��URL����
	 * @author fengyuepan
	 * @return 
	 * @param seeds����URL
	 *
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for(int i=0;i<seeds.length;i++){
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}
	public void crawling(String[] seeds){
		//��������� ����ȡ��http://www.lietu.com��ͷ������
		LinkFilter filter = new LinkFilter(){

			@Override
			public boolean accept(String url) {
				if(url.startsWith("http://www.lietu.com")){
					return true;
				}else{
					return false;					
				}

			}
			
		};
		//��ʼ��url����
		initCrawlerWithSeeds(seeds);
		//��ץȡ�����Ӳ�Ϊ����ץȡ����������1000
		while(!LinkQueue.unVisitedUrlEmpty()&&LinkQueue.getVisitedUrlNum()<1000){
			//url������
			String visitUrl = (String)LinkQueue.unVisitedUrlDequeue();
			if(visitUrl == null)
				continue;
			DownLoadFile downLoadFile = new DownLoadFile();
			//������ҳ
			downLoadFile.downLoadFile(visitUrl);
			//��url������ʹ��Ķ���
			LinkQueue.addVisitedUrl(visitUrl);
			Set<String> links = HtmlParserTool.extractLinks(visitUrl, filter);
			//�µ�δ���ʹ���url����
			for(String link:links){
				LinkQueue.addUnvisitedUrl(link);
			}
		}
	}
	public static void main(String[] args) {
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[]{"http://www.zhihu.com"});
	}
}
