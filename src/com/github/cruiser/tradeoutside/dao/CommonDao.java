package com.github.cruiser.tradeoutside.dao;

import java.util.List;
import com.github.cruiser.tradeoutside.model.Model;

public interface CommonDao<T extends Model> {

	/**
	 * 根据id来加载Model实例
	 * @param id 需加载实例的id值
	 * @return 指定id对应的Model实例
	 */
	Model get(Integer id);

	/**
	 * 持久化指定的Model实例
	 * @param Model 需要被持久化的Model实例
	 * @return 实例被持久化后的id值
	 */
	Integer save(T model);

	/**
	 * 修改指定的Model实例
	 * @param Model 需要被修改的Model实例
	 */
	void update(T model);

	/**
	 * 删除指定的Model实例
	 * @param Model 需要被删除的Model实例
	 */
	void delete(T model);

	/**
	 * 根据id删除Model实例
	 * @param id 需要被删除的Model实例
	 */
	void delete(Integer id);

	/**
	 * 查询全部的Model实例
	 * @return 数据库中全部Model实例
	 */
	List<T> findAll();

}
