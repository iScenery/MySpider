package com.panpan.myspider;

import java.util.Set;

public class MyCrawler {
	/**
	 * 使用种子初始化URL队列
	 * @author fengyuepan
	 * @return 
	 * @param seeds种子URL
	 *
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for(int i=0;i<seeds.length;i++){
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}
	public void crawling(String[] seeds){
		//定义过滤器 ，提取以http://www.lietu.com开头的链接
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
		//初始化url队列
		initCrawlerWithSeeds(seeds);
		//待抓取的链接不为空且抓取次数不多于1000
		while(!LinkQueue.unVisitedUrlEmpty()&&LinkQueue.getVisitedUrlNum()<1000){
			//url出队列
			String visitUrl = (String)LinkQueue.unVisitedUrlDequeue();
			if(visitUrl == null)
				continue;
			DownLoadFile downLoadFile = new DownLoadFile();
			//下载网页
			downLoadFile.downLoadFile(visitUrl);
			//该url放入访问过的队列
			LinkQueue.addVisitedUrl(visitUrl);
			Set<String> links = HtmlParserTool.extractLinks(visitUrl, filter);
			//新的未访问过的url入列
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
