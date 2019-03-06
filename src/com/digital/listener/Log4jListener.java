
package com.digital.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Satyam Kumar
 *
 */
@WebListener
public class Log4jListener implements ServletContextListener {
	
	private static final Logger log = LoggerFactory.getLogger(Log4jListener.class);

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
