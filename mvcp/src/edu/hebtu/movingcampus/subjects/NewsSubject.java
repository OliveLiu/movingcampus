package edu.hebtu.movingcampus.subjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import edu.hebtu.movingcampus.biz.NewsDao;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.enums.NewsType;
import edu.hebtu.movingcampus.subject.base.Subject;
import edu.hebtu.movingcampus.subject.base.TitleNews;

/**
 * @author hippo
 * @version 1.0
 * @created 14-Nov-2013 9:13:32 AM
 */
public class NewsSubject extends Subject implements Serializable, TitleNews {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewsType type;
	private List<NewsShort> list = new ArrayList<NewsShort>();

	public NewsSubject(NewsType type, Activity ac) {
		super(ac);
		this.type = type;
	}

	/**
	 * notification center,news,notifications when have more news
	 */
	@Override
	public Boolean mesureChange() {
		ArrayList<NewsShort> res;
		res = new NewsDao(ac).mapperJson(true, type + "", (dump().size() + 1)
				+ "", null);
		if (res != null) {
			list.addAll(res);
			return true;
		}
		return false;
	}

	@Override
	public List<NewsShort> dump() {
		return list;
	}

	@Override
	public long getId() {
		return type.ordinal();
	}

	@Override
	public int getIcon() {
		return type.getIconResource();
	}

	@Override
	public String getDesc() {
		return type.getDesc();
	}

	@Override
	public void clear() {
		list.clear();
	}
}