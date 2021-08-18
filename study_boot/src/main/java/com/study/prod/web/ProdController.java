package com.study.prod.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.prod.service.ProductList;
import com.study.prod.vo.ProdVO;

@Controller
public class ProdController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/prod/list")
	public String list(Model model) {
		
		List<ProdVO> prods = ProductList.getProductList();
		logger.debug("size= " + prods.size());
		model.addAttribute("prods", prods);
		return "list";
	}
	//@PathVariable("id") 
	@RequestMapping("/prod/view/{prodId}")
	public String view(@PathVariable("prodId") String  prodId,Model model) {
		logger.debug("prodId={}",prodId );
		
		ProdVO prod = ProductList.getProduct(prodId);
		model.addAttribute("prod",prod);
		return "view";
	}
	
	@RequestMapping("/prod/view")
	public String view2(String prodId,Model model) {
		logger.debug("prodId={}",prodId );
		
		ProdVO prod = ProductList.getProduct(prodId);
		model.addAttribute("prod",prod);
		return "view";
	}
}
