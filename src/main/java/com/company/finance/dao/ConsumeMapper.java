package com.company.finance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.company.finance.bean.Consume;

/**
 * 
* @ClassName: ConsumeMapper
* @Description: TODO(消费相关)
* @author linxiaomin@sina.cn
* @date 2014年5月29日 下午2:53:32
*
 */
@Repository(value="consumeMapper")
public interface ConsumeMapper extends BaseMapper<Consume>{
	
	/**
	 * 月度统计
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectConsumeGroupByMonth(Map<String, Object> map);

}
