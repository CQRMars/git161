package cn.jbit.cms.manager;

import java.util.*;
import cn.jbit.cms.dao.NewsDao;
import cn.jbit.cms.dao.impl.NewsDaoImpl;
import cn.jbit.cms.entity.News;
import cn.jbit.cms.util.FileIO;
//import cn.jbit.cms.util.*;

public class NewsManager {
	public void toHtml() {
		//1、读取模板文件内容，返回文件内容字符串
		FileIO fileio=new FileIO();
		String templatestr=fileio.resdFile("F:\\新建文件夹\\news.template");
		//2、读取数据库表，获取新闻列表
		NewsDao newsDao=new NewsDaoImpl();
		//添加
//		News new1=new News(5, "title5", "author5", new Date(), "content5");
//		int result=newsDao.insert(new1);
//		System.out.println("受影响行数为："+result);
		
		//删除
//		News n=new News();
//		n.setId(4);
//		newsDao.del(n);
		
		//修改
//		News b=new News();
//		b.setId(5);
//		b.setTitle("雷");
//		newsDao.update(b);
		List<News> newsList=newsDao.findAll();
		//3、替换模板文件，为每一条新闻创建一个HTML文件显示其信息
		for (int i = 0; i < newsList.size(); i++) {
			//3.1获取一条新闻
			News news=newsList.get(i);
			//3.2使用该条新闻信息替换对应占位符
			String replacestr=new String();
			replacestr=templatestr;
			replacestr=replacestr.replace("{title}", news.getTitle());
			replacestr=replacestr.replace("{author}", news.getAuthor());
			replacestr=replacestr.replace("{createTime}", news.getCreateTime().toString());
			replacestr=replacestr.replace("{content}", news.getContent());
			String filePath="F:\\"+i+".html";
			fileio.writeFile(filePath, replacestr);
		}
	}
}