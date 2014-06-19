package com.panpan.myspider;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParserTool {
	public static Set<String> extractLinks(String url,LinkFilter filter){
		Set<String> links =new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("utf-8");
			NodeFilter frameFilter = new NodeFilter(){

				@Override
				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src=")){
						return true;
					}else{
						return false;
					}
				
				}
			};
			//OrFilter �������ù���<a>��<frame>��ǩ
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);	
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for(int i = 0;i<list.size();i++){
				Node tag = list.elementAt(i);
				if(tag instanceof LinkTag){
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();
					links.add(linkUrl);
				}else{
					//��ȡfarme���src���Ե����ӣ���<frame src = "test.html"/>
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame.substring(start);
					int end = frame.indexOf(" ");
					if(end ==-1){
						end = frame.indexOf(">");
					}
					String 	frameUrl = frame.substring(start,end-1);
					if(filter.accept(frameUrl)){
						links.add(frameUrl);
					}
				}
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;	
	}
}