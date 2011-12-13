package com.mdm.processor.controller;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mdm.processor.service.IMDMRecordsCount;
import com.mdm.processor.service.IPMEngineNew;

import com.mdm.processor.service.IReadCSVfromSourceTempData;
import com.mdm.processor.service.IManageMDMLiveData;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	IMDMRecordsCount objMRC;

	
	

	@Autowired
	IPMEngineNew objPME;

	@Autowired
	IReadCSVfromSourceTempData objReadCSV;
	
	@Autowired
	IManageMDMLiveData	objMML;

	private long numberOfSteps = 0;
	
	private long total = 0;

	private String oldPercentage = "";

	// @Inject
	// MDProcessorMainClazz mDProcessorMainClazz;

	
	public HomeController()
	{
		
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("requesting home");
		
		
		return "home";
	}

	

	/**
	 * Start the process manually
	 */
	@RequestMapping(value = "/startProcess", method = RequestMethod.GET)
	public @ResponseBody
	String startProcess() {
		logger.info("Start process manually " + new Date());
		// gUIHelperImpl.getPieChartData();
		objPME.process();
		//
		return "TRUE";
	}

	@RequestMapping(value = "/loadData", method = RequestMethod.GET)
	public @ResponseBody
	String loadData() {
		logger.info("Started  loadData processing...." + new Date());
		// Autowire and call method
		// Bind your code
		objMRC.showDataVolumeinSFDC();
		
		objMML.deleteLastSessionPartyData();
		objReadCSV.deleteLastSessionTempData();
		objReadCSV.fetchnInsertMDMTempData();
		objReadCSV.fetchnInsertMDMClassificationTempData();
		objReadCSV.fetchnInsertMDMAssociationTempData();
		objMRC.showDataVolumeinSFDC();
	
		logger.info(" loadData total..Temp Records.." + total);
		logger.info(" Loading data completes ");
		return "TRUE";
	}

	
	

	
}
