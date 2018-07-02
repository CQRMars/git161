package cn.jbit.cms.manager;

import java.util.*;
import cn.jbit.cms.dao.NewsDao;
import cn.jbit.cms.dao.impl.NewsDaoImpl;
import cn.jbit.cms.entity.News;
import cn.jbit.cms.util.FileIO;
//import cn.jbit.cms.util.*;

public class NewsManager {
	public void toHtml() {
		//1����ȡģ���ļ����ݣ������ļ������ַ���
		FileIO fileio=new FileIO();
		String templatestr=fileio.resdFile("F:\\�½��ļ���\\news.template");
		//2����ȡ���ݿ����ȡ�����б�
		NewsDao newsDao=new NewsDaoImpl();
		//���
//		News new1=new News(5, "title5", "author5", new Date(), "content5");
//		int result=newsDao.insert(new1);
//		System.out.println("��Ӱ������Ϊ��"+result);
		
		//ɾ��
//		News n=new News();
//		n.setId(4);
//		newsDao.del(n);
		
		//�޸�
//		News b=new News();
//		b.setId(5);
//		b.setTitle("��");
//		newsDao.update(b);
		List<News> newsList=newsDao.findAll();
		//3���滻ģ���ļ���Ϊÿһ�����Ŵ���һ��HTML�ļ���ʾ����Ϣ
		for (int i = 0; i < newsList.size(); i++) {
			//3.1��ȡһ������
			News news=newsList.get(i);
			//3.2ʹ�ø���������Ϣ�滻��Ӧռλ��
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