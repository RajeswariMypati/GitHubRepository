/**
 * 
 */
package com.online.groceries.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.groceries.service.GroceryService;
import com.online.groceries.util.GroceryServiceException;
import com.online.groceries.web.vo.GroceryVO;

/**
 * @author ravikumv
 *
 */
@Controller
public class GroceryController {
	
	@Autowired
	GroceryService groceryService;

	private static final Logger logger = LoggerFactory.getLogger(GroceryController.class);

	private String loggerPrString = "GroceryController.class ";

	@RequestMapping(value = "/showGroceryUpload")
	public String showGroceryUpload(Map<String, Object> map){
		logger.debug(loggerPrString+" showGroceryUpload ======> ");
		return "uploadGrocery";
	}

	@RequestMapping(value = "/uploadGrocery", method = RequestMethod.POST)
	public String uploadGrocery(@RequestParam("file") MultipartFile file){
		logger.debug(loggerPrString+" uploadGrocery ======> ");
		String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                File fileObj = new File("C:/Softwares/logs/" + fileName);
                fileObj.createNewFile();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(fileObj));
                buffStream.write(bytes);
                buffStream.close();
                
                ObjectMapper mapper = new ObjectMapper();
                List<GroceryVO> groceryVOList = mapper.readValue(new File("C:/Softwares/logs/" + fileName), new TypeReference<List<GroceryVO>>(){});
                logger.debug(loggerPrString+"  JSon Object List:  ="+groceryVOList);
                groceryService.batchGroceryInsert(groceryVOList);
            } catch(Exception e) {
            	logger.error(loggerPrString+" uploadGrocery ======> "+e.getMessage());    	
           	}
        }
		return "redirect:/getGroceryList";
	}
	
	@RequestMapping(value = "/showAddGrocery")
	public String showAddGrocery(Map<String, Object> map){
		logger.debug(loggerPrString+" showAddGrocery ======> ");
		GroceryVO vo = new GroceryVO();
		map.put("grocery",vo);
		return "addGroceryToStore";
	}

	@RequestMapping(value = "/addGrocery", method = RequestMethod.POST)
	public String addGrocery(@ModelAttribute("grocery")
	GroceryVO groceryVO){
		try {
			logger.debug(loggerPrString+" addGrocery ======> ");
			groceryService.addGrocery(groceryVO);	
		} catch(GroceryServiceException gse) {
			logger.error(loggerPrString+" addGrocery ********* ");
			return "redirect:/addGrocery";
		}
		return "redirect:/getGroceryList";
	}
	

	@RequestMapping(value = "/showUpdateGrocery/{groceryById}")
	public String showUpdateGrocery(@PathVariable("groceryById")Integer groceryById,Map<String, Object> map){
		logger.debug(loggerPrString+" showUpdateGrocery ======> ");
		GroceryVO vo;
		try {
			vo = groceryService.getGroceryById(groceryById);
			logger.debug(loggerPrString+" showUpdateGrocery ======> "+vo);
			map.put("grocery",vo);
		} catch (GroceryServiceException gse) {
			logger.error(loggerPrString+" showUpdateGrocery ======> ");
		}
		return "updateGrocery";
	}
	
	@RequestMapping(value = "/updateGrocery", method = RequestMethod.POST)
	public String updateGrocery(@ModelAttribute("grocery")
	GroceryVO groceryVO){
		try {
			logger.debug(loggerPrString+" updateGrocery ======> "+groceryVO);
			groceryService.updateGrocery(groceryVO);	
		} catch(GroceryServiceException gse) {
			logger.error(loggerPrString+" updateGrocery ********* "+gse.getMessage());
			return "updateGrocery";
		}
		return "redirect:/getGroceryList";
	}

	@RequestMapping(value = "/getGroceryList")
	public String getGroceryList(Map<String, Object> map){
		logger.debug(loggerPrString+" getGroceryList with multiple content types, ======> ");
		List<GroceryVO> groceryInvList = new ArrayList<GroceryVO>();
		try {
			groceryInvList = groceryService.getGroceriesList();	
		} catch(GroceryServiceException gse) {
			logger.error(loggerPrString+" getGroceryList ********* "+gse.getMessage());
		}
		map.put("groceryInvList",groceryInvList);
		return "viewGroceryInventory";
	}


	@RequestMapping(value = "/getGroceryListByName")
	public String getGroceryListByName(Map<String, Object> map,@RequestParam String groceryName){
		List<GroceryVO> groceryInvList = new ArrayList<GroceryVO>();
		logger.debug(loggerPrString+" getGroceryListByName ======> ");
		try {
			groceryInvList = groceryService.filterGroceryByName(groceryName);
		} catch(GroceryServiceException gse) {
			logger.error(loggerPrString+" getGroceryListByName ********* "+gse.getMessage());
		}
		map.put("groceryInvList",groceryInvList);
		return "viewGroceryInventory";		
	}
	
	
	/*
	 *  updateGrocery in JSon format
	 */
	/*
	@RequestMapping(value = "/showUpdateGroceryJSon/{groceryById}", produces={"application/xml", "application/json"})
	public @ResponseBody GroceryVO showUpdateGroceryJSon(@PathVariable("groceryById")	Integer groceryById,Map<String, Object> map){
		logger.debug(loggerPrString+" showUpdateGroceryJSon ======> ");
		GroceryVO vo = new GroceryVO();
		try {
			vo = groceryService.getGroceryById(groceryById);
		} catch (GroceryServiceException gse) {
			logger.error(loggerPrString+" showUpdateGroceryJSon ======> ");
		}
		return vo;
	}
	*/
	

	/*
	 *  getGroceryList in JSon format
	 */
	/*
	@RequestMapping(value = "/getGroceryListJson", produces={"application/xml", "application/json"})
	public @ResponseBody List<GroceryVO> getGroceryListJSon(Map<String, Object> map){
		logger.debug(loggerPrString+" getGroceryList with multiple content types, ======> ");
		List<GroceryVO> groceryInvList = new ArrayList<GroceryVO>();
		try {
			groceryInvList = groceryService.getGroceriesList();	
		} catch(GroceryServiceException gse) {
			logger.error(loggerPrString+" getGroceryList ********* "+gse.getMessage());
		}
		return groceryInvList;
	}
	*/
	
}
