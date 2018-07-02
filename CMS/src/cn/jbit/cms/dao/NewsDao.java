package cn.jbit.cms.dao;

import java.util.List;

import cn.jbit.cms.entity.News;
/**
 * �������ݲ����ӿ�
 * @author caiqirui
 *
 */
public interface NewsDao {
	/**
	 * ��ȡ��������
	 * @return
	 */
	public List<News> findAll();
	/**
	 * ɾ�����ݣ�����ɾ����Ӧ���ļ�
	 * @param news
	 * @return
	 */
	int del(News news);
	
	/**
	 * �������
	 * @param news
	 * @return
	 */
	int insert(News news);
	
	/**
	 * �޸�����
	 * @param news
	 * @return
	 */
	int update(News news);
}
