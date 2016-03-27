package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;

@Controller
@RequestMapping(value = "/chance")
public class SalesChanceHandler {
	@Autowired
	private SalesChanceService salesChanceService;

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id,RedirectAttributes attributes) {
		salesChanceService.delete(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/chance/list";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(SalesChance chance,RedirectAttributes attributes) {
		salesChanceService.save(chance);
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/chance/list";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		SalesChance chance = new SalesChance();
		map.put("chance", chance);
		return "input";
	}

	/**
	 * 显示机会列表
	 * 
	 * @param pageNoStr
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getChangelist(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Page<SalesChance> page = salesChanceService.getChanceList(pageNo);
		map.put("page", page);
		return "salesChanceList";
	}
}
