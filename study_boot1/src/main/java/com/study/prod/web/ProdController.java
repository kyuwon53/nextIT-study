package com.study.prod.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.prod.service.ProdServiceImpl;
import com.study.prod.service.ProductList;
import com.study.prod.vo.ProdVO;

@Controller
public class ProdController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProdServiceImpl prodService;
	
	@RequestMapping("/prod/list.ajax")
	@ResponseBody
	public List<ProdVO> listAjax() {
		List<ProdVO> prods = prodService.getProdList();
		logger.debug("size= " + prods.size());
		return prods;
	}
	
	@RequestMapping("/prod/list")
	public String list(Model model) {
		List<ProdVO> prods = prodService.getProdList();
		logger.debug("size= " + prods.size());
		model.addAttribute("prods", prods);
		return "list";
	}
	//@PathVariable("id") 
	@RequestMapping("/prod/view/{prodId}")
	public String view(@PathVariable("prodId") String  prodId,Model model) {
		logger.debug("prodId={}",prodId );
		
		ProdVO prod = prodService.getProd(prodId);
		model.addAttribute("prod",prod);
		return "view";
	}
	
	@RequestMapping("/prod/view")
	public String view2(String prodId,Model model) {
		logger.debug("prodId={}",prodId );
		
		ProdVO prod = prodService.getProd(prodId);
		model.addAttribute("prod",prod);
		return "view";
	}
}
