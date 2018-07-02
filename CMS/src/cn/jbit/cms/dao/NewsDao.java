package cn.jbit.cms.dao;

import java.util.List;

import cn.jbit.cms.entity.News;
/**
 * 文章数据操作接口
 * @author caiqirui
 *
 */
public interface NewsDao {
	/**
	 * 读取文章内容
	 * @return
	 */
	public List<News> findAll();
	/**
	 * 删除数据，并且删除相应的文件
	 * @param news
	 * @return
	 */
	int del(News news);
	
	/**
	 * 添加文章
	 * @param news
	 * @return
	 */
	int insert(News news);
	
	/**
	 * 修改文章
	 * @param news
	 * @return
	 */
	int update(News news);
}
