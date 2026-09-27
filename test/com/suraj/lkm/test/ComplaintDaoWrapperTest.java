package com.suraj.lkm.test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.suraj.lkm.business.bean.ComplaintBean;
import com.suraj.lkm.dao.ComplaintDaoWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/cst-root-ctx.xml" })
@Transactional
public class ComplaintDaoWrapperTest {

	@Autowired
	private ComplaintDaoWrapper complaintDaoWrapper;

	/**
     * To-Do Item 1.14: Test method for registering complaint details.
     *
     * Steps to Implement:
     * 1. Initialize a ComplaintBean object with relevant data.
     * 2. Call complaintDaoWrapper.registerComplaintDetails() with the initialized ComplaintBean.
     * 3. Assert that the method returns an ID greater than 10003.
     *
     * @throws Exception if an error occurs during the test.
     */
	@Test
	public void registerComplaintDetails() throws Exception {
		ComplaintBean bean = new ComplaintBean();
		
		bean.setCustomerName("JAS");
		bean.setComplaintTypeId(1001);
		bean.setDescription("Quality Issue");
		//bean.setDateOfIncidence(02-mar-2002);
		bean.setAmount(6000.0);
		
		int id = complaintDaoWrapper.registerComplaintDetails(bean);
		
		Assertions.assertTrue(id>10003);
	}

	
		
}
