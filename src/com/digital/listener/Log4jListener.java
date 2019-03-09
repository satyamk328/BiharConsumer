
package com.digital.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Satyam Kumar
 *
 */
@WebListener
@Slf4j
public class Log4jListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// initialize log4j
		ServletContext context = event.getServletContext();
		String fullPath = context.getRealPath("") + File.separator + "WEB-INF/classes/log4j.properties";
		PropertyConfigurator.configure(fullPath);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("{}", sce);
	}

}
