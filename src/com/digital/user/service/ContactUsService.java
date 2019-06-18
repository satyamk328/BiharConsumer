package com.digital.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.user.dao.ContactUsDao;
import com.digital.user.model.Contact;

@Service
public class ContactUsService {

	@Autowired
	private ContactUsDao contactUsDao;
	
	@Autowired
	private MailService mailService;

	public List<String> getAllType() {
		return contactUsDao.getAllType();
	}
	
	public List<Contact> getAllComplain() {
		return contactUsDao.getAllDetails();
	}

	public Contact getDetailById(Long compId) {
		return contactUsDao.getContactDetailsById(compId);
	}

	public Long addContact(Contact contact) {
		//mailService.sendEmail(con, emailTo, Content);
		return contactUsDao.addComplain(contact);
	}

	public int updateContact(Contact contact) {
		return contactUsDao.updateComplain(contact);
	}

	public int deleteContact(Long compId) {
		return contactUsDao.deleteComplain(compId);
	}
}
