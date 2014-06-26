package com.company.finance.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.company.finance.bean.Consume;
import com.company.finance.bean.User;
import com.company.finance.dao.ConsumeMapper;
import com.company.finance.service.ConsumeService;
/**
 * 
* @ClassName: ConsumeServiceImpl
* @Description: TODO(消费记录业务实现类)
* @author linxiaomin@sina.cn
* @date 2014年6月9日 下午1:26:42
*
 */
@Service(value = "consumeService")
public class ConsumeServiceImpl extends com.company.finance.service.impl.BaseService implements ConsumeService {

	@Autowired
	private ConsumeMapper	consumeMapper;

	public void saveConsume(Consume consume,User user) {
		if (StringUtils.isEmpty(consume.getConsume_id())) {
			consume.setConsume_user(user);
			consumeMapper.addEntity(consume);
		} else {
			consumeMapper.modifyEntity(consume);
		}
	}

	public Page<Consume> findAll(Map<String, Object> map, String offset, String limit, boolean page) {
		return page(map, ConsumeMapper.class, offset, limit, page);
	}

	public List<Map<String, Object>> selectConsumeGroupByMonth(Map<String, Object> map) {
		return consumeMapper.selectConsumeGroupByMonth(map);
	}

}
