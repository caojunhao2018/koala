package org.openkoala.gqc.controller.generalquery;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.openkoala.gqc.core.domain.DynamicQueryCondition;
import org.openkoala.gqc.core.domain.GeneralQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dayatang.querychannel.support.Page;

/**
 * 查询控制器
 * 
 * @author zyb
 * @since 2013-7-11 下午8:00:25
 */
@Controller
public class QueryController {

	/**
	 * 生成查询页面
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/query/{id}")
	public String queryPage(@PathVariable Long id, ModelMap modelMap) {
		GeneralQuery generalQuery = GeneralQuery.get(GeneralQuery.class, id);
		modelMap.addAttribute("gq", generalQuery);
		modelMap.addAttribute("gqId", id);
		return "query";
	}

	/**
	 * 分页查询数据
	 * 
	 * @param id
	 * @param page
	 * @param pagesize
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/search/{id}")
	public Map<String, Object> search(@PathVariable Long id, @RequestParam int page,
			@RequestParam int pagesize, HttpServletRequest request) {
		GeneralQuery generalQuery = GeneralQuery.get(GeneralQuery.class, id);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object[]> params = request.getParameterMap();
		String startValueTag = "Start@";
		String endValueTag = "End@";
		
		for (String key : params.keySet()) {
			if (!"page".equals(key) && !"pagesize".equals(key)) {
				DynamicQueryCondition dqc = null;
				if (key.endsWith(startValueTag)) {
					String fieldName = key.replace(startValueTag, "");
					dqc = generalQuery.getDynamicQueryConditionByFieldName(fieldName);
					if (dqc != null) {
						dqc.setStartValue((String) params.get(key)[0]);
					}
					continue;
				}
				
				if (key.endsWith(endValueTag)) {
					String fieldName = key.replace(endValueTag, "");
					dqc = generalQuery.getDynamicQueryConditionByFieldName(fieldName);
					if (dqc != null) {
						dqc.setEndValue((String) params.get(key)[0]);
					}
					continue;
				}
				
				dqc = generalQuery.getDynamicQueryConditionByFieldName(key);
				if (dqc != null) {
					dqc.setValue((String) params.get(key)[0]);
				}
			}
		}

		Page<Map<String, Object>> data = generalQuery.pagingQueryPage(page, pagesize);

		result.put("Rows", data.getResult());
		result.put("start", page * pagesize - pagesize);
		result.put("limit", pagesize);
		result.put("Total", data.getTotalCount());
		return result;
	}

}