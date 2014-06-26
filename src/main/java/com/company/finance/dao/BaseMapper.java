package com.company.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
/**
 * 
* @ClassName: BaseMapper
* @Description: TODO(基础Mapper)
* @author linxiaomin@sina.cn
* @date 2014年5月28日 下午2:42:11
*
* @param <T>
 */
public interface BaseMapper<T> {
	
	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	public List<T> queryAll();
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteEntity(String id);
	
	/**
	 * 修改
	 * @param t
	 */
	public void modifyEntity(T t);
	
	/**
	 * 单条记录
	 * @param id
	 * @return
	 */
	public T getObjectById(String id);
	
	/**
	 * 新增
	 * @param t
	 */
	public void addEntity(T t);
	
	/**
	 * 分页
	 * @param rowBounds
	 * @param model
	 * @return
	 */
	public List<T> findAll(RowBounds rowBounds,Map<String, Object> model);
	
	/**
	 * 总数
	 * @param model
	 * @return
	 */
	public int getCounts(Map<String, Object> model);
}
