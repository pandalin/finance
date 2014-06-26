package com.company.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.finance.bean.Consume;
import com.company.finance.bean.User;
/**
* @ClassName: ConsumeService
* @Description: TODO(消费记录业务接口)
* @author linxiaomin@sina.cn
* @date 2014年6月9日 下午1:22:49
*
*/
public interface ConsumeService {
	
	/**
	 * 月度统计
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectConsumeGroupByMonth(Map<String, Object> map);

	/**
	 * 新增、修改消费记录
	 * @param consume
	 * @param user 
	 */
	public void saveConsume(Consume consume, User user);

	/**
	 * 分页查询
	 * @param map
	 * @param offset
	 * @param limit
	 * @param page
	 * @return
	 */
	public Page<Consume> findAll(Map<String, Object> map, String offset, String limit, boolean page);
}
