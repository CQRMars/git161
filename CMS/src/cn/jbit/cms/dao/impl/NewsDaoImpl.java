package cn.jbit.cms.dao.impl;

import java.sql.*;
import java.util.*;
import cn.jbit.cms.dao.NewsDao;
import cn.jbit.cms.entity.News;
import cn.jbit.cms.util.BaseDao;

public class NewsDaoImpl extends BaseDao implements NewsDao{

	@Override
	public List<News> findAll() {
		Connection conn=this.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;	
		String sql="select * from news";
		List<News> newsList = new ArrayList<News>();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setTitle(rs.getString("title"));
				news.setAuthor(rs.getString("author"));				
				news.setCreateTime(rs.getDate("createtime"));
				news.setContent(rs.getString("content"));			
				newsList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeAll(conn, pstmt, rs);
		}
		return newsList;
	}

	@Override
	public int del(News news) {
		String sql="delete from news where id=?";
		Object[] param= {
				news.getId()
		};
		return this.executeUpdate(sql, param);
	}

	@Override
	public int insert(News news) {
		String sql="INSERT INTO `newsmgr`.`news`(`id`, `title`, `author`, `createTime`, `content`) "
				+ "VALUES (?,?,?,?,?)";
		Object[] param= {
				news.getId(),news.getTitle(),news.getAuthor(),news.getCreateTime(),news.getContent()
		};
		return this.executeUpdate(sql, param);
	}

	@Override
	public int update(News news) {
		String sql="update news set title=? where id=?";
		Object[] param= {
				news.getTitle(),news.getId()
		};
		return this.executeUpdate(sql, param);
	}
}
