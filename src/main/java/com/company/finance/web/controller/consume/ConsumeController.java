package com.company.finance.web.controller.consume;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.finance.bean.Consume;
import com.company.finance.service.ConsumeService;
import com.company.finance.web.controller.BaseController;

/**
 * 
 * <p> 
 *  消费控制类
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月20日 下午3:38:29
 * @version v1.0
 */
@Controller
@RequestMapping(value = "/consume")
public class ConsumeController extends BaseController {

	@Autowired
	private ConsumeService	consumeService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Consume consume) {
		consumeService.saveConsume(consume,getUser());
		return "redirect:/consume/listConsume/%25/%25";
	}

	@RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable String id,ModelMap map) {
		return "consume/editConsume";
	}
	
	@RequestMapping(value = "/listConsume/{offset}/{limit}")
	public String toList(@PathVariable String offset,@PathVariable String limit,ModelMap model) {
			
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Consume> 	consumeList = consumeService.findAll(map, offset, limit,true);
		
		model.put("result", consumeList);
		
		return "consume/listConsume";
	} 
	
	@RequestMapping(value = "/selectConsumeGroupByMonth")
	@ResponseBody
	public Map<String, Object> selectConsumeGroupByMonth() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", DateFormatUtils.format(new Date(), "yyyy"));
		map.put("user_id", "u01");
		List<Map<String, Object>> reList = consumeService.selectConsumeGroupByMonth(map);
		
		List<Object> month = new ArrayList<Object>();
		List<Object> money = new ArrayList<Object>();

		Map<String, Object> re_map = new HashMap<String, Object>();
		
		if (!CollectionUtils.isEmpty(reList)) {
			for (Map<String, Object> remap : reList) {
				month.add(remap.get("month"));
				
				money.add(remap.get("money"));
				
				if (!re_map.containsKey("user_name")) {
					re_map.put("user_name", remap.get("user_name"));
				}
			}
		}
		
		re_map.put("month", month);
		re_map.put("money", money);
		
		return re_map;
	}
}
