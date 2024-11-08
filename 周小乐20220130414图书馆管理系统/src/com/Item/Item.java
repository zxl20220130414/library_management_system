package com.Item;
import com.dao.Dao;
import com.model.BookType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Item {
	public String id;
	public String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return getName();
	}

	static Map map = new HashMap();

	public static Map getMap() {
		List list = Dao.selectBookCategory();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);
			Item item = new Item();
			item.setId(booktype.getId());
			item.setName(booktype.getTypeName());
			map.put(item.getId(), item);
		}
		return map;
	}
}

