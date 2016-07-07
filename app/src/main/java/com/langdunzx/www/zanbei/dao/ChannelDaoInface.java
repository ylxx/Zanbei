package com.langdunzx.www.zanbei.dao;

import java.util.List;
import java.util.Map;



import android.content.ContentValues;

import com.langdunzx.www.zanbei.newsUtils.ChannelItem;

public interface ChannelDaoInface {
	public boolean addCache(ChannelItem item);

	public boolean deleteCache(String whereClause, String[] whereArgs);

	public boolean updateCache(ContentValues values, String whereClause,
							   String[] whereArgs);

	public Map<String, String> viewCache(String selection,
										 String[] selectionArgs);

	public List<Map<String, String>> listCache(String selection,
											   String[] selectionArgs);

	public void clearFeedTable();
}