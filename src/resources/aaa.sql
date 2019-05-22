/*
SQLyog Community v13.1.0 (64 bit)
MySQL - 8.0.14 : Database - digitalbihar
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`digitalbihar` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `digitalbihar`;

/*Table structure for table `amenity_mapping` */

DROP TABLE IF EXISTS `amenity_mapping`;

CREATE TABLE `amenity_mapping` (
  `AmenityMappingId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusId` bigint(20) NOT NULL,
  `AmenityId` bigint(20) NOT NULL,
  PRIMARY KEY (`AmenityMappingId`),
  KEY `FK_BUS_MAS` (`BusId`),
  KEY `FK_AM` (`AmenityId`),
  CONSTRAINT `FK_AM` FOREIGN KEY (`AmenityId`) REFERENCES `amenity_master` (`AmenityId`),
  CONSTRAINT `FK_BUS_MAS` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `amenity_mapping` */

insert  into `amenity_mapping`(`AmenityMappingId`,`BusId`,`AmenityId`) values 
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,6);

/*Table structure for table `amenity_master` */

DROP TABLE IF EXISTS `amenity_master`;

CREATE TABLE `amenity_master` (
  `AmenityId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `SelectedIcon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AmenityId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `amenity_master` */

insert  into `amenity_master`(`AmenityId`,`Name`,`Label`,`Icon`,`SelectedIcon`) values 
(1,'live_tracking','Live Tracking','https://d274ft55l0imju.cloudfront.net/bus-api/live_tracking.png',NULL),
(2,'charging_point','Charging Point','https://d274ft55l0imju.cloudfront.net/bus-api/charging_point.png',NULL),
(3,'wifi','Wifi','https://d274ft55l0imju.cloudfront.net/bus-api/wifi.png',NULL),
(4,'tv','TV','https://d274ft55l0imju.cloudfront.net/bus-api/personal_tv.png',NULL),
(5,'blanket','Blanket','https://d274ft55l0imju.cloudfront.net/bus-api/blanket.png',NULL),
(6,'mticket','MTicket','https://d274ft55l0imju.cloudfront.net/bus-api/mticket.png',NULL),
(7,'reading_light','Reading Light','https://d274ft55l0imju.cloudfront.net/bus-api/reading_light.png',NULL),
(8,'water_bottle','Water Bottle','https://d274ft55l0imju.cloudfront.net/bus-api/water_bottle.png',NULL),
(9,'snacks','Snacks','https://d274ft55l0imju.cloudfront.net/bus-api/snacks.png',NULL),
(10,'pillow','Pillow','https://d274ft55l0imju.cloudfront.net/bus-api/pillow.png',NULL),
(11,'newspaper','Newspaper','https://d274ft55l0imju.cloudfront.net/bus-api/news_paper.png',NULL),
(12,'novel','Novel','https://d274ft55l0imju.cloudfront.net/bus-api/novel.png',NULL),
(13,'emergency_exit','Emergency Exit','https://d274ft55l0imju.cloudfront.net/bus-api/emergency_exit.png',NULL),
(14,'fire_extinguisher','Fire Extinguisher','https://d274ft55l0imju.cloudfront.net/bus-api/fire_extinguisher.png',NULL),
(15,'first_aid_box','First Aid Box','https://d274ft55l0imju.cloudfront.net/bus-api/first_aid_box.png',NULL),
(16,'hammer','Hammer (to break glass)','https://d274ft55l0imju.cloudfront.net/bus-api/hammer_to_break_glass.png',NULL),
(17,'facial_tissues','Facial Tissues','https://d274ft55l0imju.cloudfront.net/bus-api/facial_tissues.png',NULL),
(18,'washroom','Washroom','https://d274ft55l0imju.cloudfront.net/bus-api/washroom.png',NULL),
(19,'headsets','Headsets','https://d274ft55l0imju.cloudfront.net/bus-api/headsets.png',NULL),
(20,'vomiting_bag','Vomiting Bag','https://d274ft55l0imju.cloudfront.net/bus-api/vomiting_bag.png',NULL),
(21,'heater','Heater','https://d274ft55l0imju.cloudfront.net/bus-api/heater.png',NULL),
(22,'cctv','CCTV','https://d274ft55l0imju.cloudfront.net/bus-api/cctv.png',NULL),
(23,'fan','Fan','https://d274ft55l0imju.cloudfront.net/bus-api/fan.png',NULL);

/*Table structure for table `api_response_log` */

DROP TABLE IF EXISTS `api_response_log`;

CREATE TABLE `api_response_log` (
  `LogId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ApiName` varchar(50) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `DurationMilliSeconds` int(11) DEFAULT NULL,
  `RequestHttpMethod` varchar(16) DEFAULT NULL,
  `RequestUrl` varchar(1024) DEFAULT NULL,
  `RequestBody` mediumtext,
  `ResponseHttpCode` int(11) DEFAULT NULL,
  `ResponseBody` mediumtext,
  `RequestHeader` varchar(1024) DEFAULT NULL,
  `ResponseHeader` varchar(1024) DEFAULT NULL,
  `ChannelType` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`LogId`)
) ENGINE=InnoDB AUTO_INCREMENT=20010661 DEFAULT CHARSET=latin1;

/*Data for the table `api_response_log` */

/*Table structure for table `bus_cancellation_policy` */

DROP TABLE IF EXISTS `bus_cancellation_policy`;

CREATE TABLE `bus_cancellation_policy` (
  `PolicyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusId` bigint(20) NOT NULL,
  `DepartureHeading` varchar(255) DEFAULT NULL,
  `PolicyHeading` varchar(255) DEFAULT NULL,
  `ApplyPriority` bigint(20) DEFAULT NULL,
  `CreatedDate` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`PolicyId`),
  KEY `FK_BUS_CANCE` (`BusId`),
  CONSTRAINT `FK_BUS_CANCE` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_cancellation_policy` */

insert  into `bus_cancellation_policy`(`PolicyId`,`BusId`,`DepartureHeading`,`PolicyHeading`,`ApplyPriority`,`CreatedDate`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'Between 0 Hrs. to 12 Hrs.','0%',NULL,NULL,NULL,NULL,NULL),
(2,1,'Between 12 Hrs. to 24 Hrs.','50%',NULL,NULL,NULL,NULL,NULL),
(3,1,'Remaining 24 Hrs.','90%',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `bus_helpline_master` */

DROP TABLE IF EXISTS `bus_helpline_master`;

CREATE TABLE `bus_helpline_master` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusId` bigint(20) NOT NULL,
  `ContactNumber` varchar(200) DEFAULT NULL,
  `ContactPerson` varchar(200) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `CreatedBy` varchar(200) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(200) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_BUS_MASTER_HELP` (`BusId`),
  CONSTRAINT `FK_BUS_MASTER_HELP` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_helpline_master` */

insert  into `bus_helpline_master`(`Id`,`BusId`,`ContactNumber`,`ContactPerson`,`Address`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'72899900566','Naiyan Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(2,1,'7290900866','Naiyan Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(4,1,'9136581950','Naiyan Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(5,6,'9811093825','Mukhar Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(6,6,'9599088528','Mukhar Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(7,6,'9599088529','Mukhar Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(8,6,'9599088531','Mukhar Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(9,6,'9599088532','Mukhar Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(10,2,'8766224540','Bhushan kumar','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(11,2,'8766269128','Bhushan kumar','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(12,2,'8766247181','Bhushan kumar','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL);

/*Table structure for table `bus_layout_master` */

DROP TABLE IF EXISTS `bus_layout_master`;

CREATE TABLE `bus_layout_master` (
  `LayoutId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Layout` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BusType` varchar(200) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `IsSeater` tinyint(1) DEFAULT '0',
  `IsSleeper` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`LayoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_layout_master` */

insert  into `bus_layout_master`(`LayoutId`,`Layout`,`BusType`,`Description`,`IsSeater`,`IsSleeper`) values 
(1,'1X1','Single-deckers',NULL,0,0),
(2,'2X2','Double-deckers',NULL,0,0),
(3,'2X1','Double-deckers',NULL,0,0);

/*Table structure for table `bus_master` */

DROP TABLE IF EXISTS `bus_master`;

CREATE TABLE `bus_master` (
  `BusId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `LayoutId` bigint(20) NOT NULL,
  `BusNumber` varchar(255) DEFAULT NULL,
  `TravelsName` varchar(100) DEFAULT NULL,
  `RegistationNumber` varchar(100) DEFAULT NULL,
  `Color` varchar(255) DEFAULT NULL,
  `TotalSeats` bigint(20) DEFAULT '0',
  `IsAc` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`BusId`),
  KEY `FK_USER_MASTER_BUS` (`UserId`),
  KEY `FK_LAYOUT_MASTER` (`LayoutId`),
  CONSTRAINT `FK_LAYOUT_MASTER` FOREIGN KEY (`LayoutId`) REFERENCES `bus_layout_master` (`LayoutId`),
  CONSTRAINT `FK_USER_MASTER_BUS` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_master` */

insert  into `bus_master`(`BusId`,`UserId`,`LayoutId`,`BusNumber`,`TravelsName`,`RegistationNumber`,`Color`,`TotalSeats`,`IsAc`) values 
(1,2,1,NULL,'Shri 108 Shambhu Nath Travels',NULL,NULL,40,0),
(2,4,1,NULL,'Maa Travels',NULL,NULL,40,0),
(3,5,1,NULL,'Mithila Travels',NULL,NULL,40,0),
(4,7,1,NULL,'Kristhi Travels',NULL,NULL,40,0),
(5,7,1,NULL,'Kristhi Travels',NULL,NULL,40,0),
(6,6,1,NULL,'Chandni Travels',NULL,NULL,40,0);

/*Table structure for table `bus_type` */

DROP TABLE IF EXISTS `bus_type`;

CREATE TABLE `bus_type` (
  `TypeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusType` varchar(200) DEFAULT NULL,
  KEY `TypeId` (`TypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_type` */

insert  into `bus_type`(`TypeId`,`BusType`) values 
(1,'Single-deckers'),
(2,'Double-deckers'),
(3,'Articulated_buses'),
(4,'Low-floor_buses');

/*Table structure for table `cancel_ticket_master` */

DROP TABLE IF EXISTS `cancel_ticket_master`;

CREATE TABLE `cancel_ticket_master` (
  `TicketId` bigint(20) NOT NULL DEFAULT '0',
  `ScheduleId` bigint(20) DEFAULT NULL,
  `UserId` bigint(20) NOT NULL,
  `OperatorId` bigint(20) NOT NULL,
  `BusId` bigint(20) DEFAULT NULL,
  `PNR` varchar(200) DEFAULT NULL,
  `SeatId` bigint(20) DEFAULT NULL,
  `TripId` varchar(200) DEFAULT NULL,
  `TravelName` varchar(200) DEFAULT NULL,
  `BusType` varchar(200) DEFAULT NULL,
  `IsAc` tinyint(1) DEFAULT '0',
  `BoadingPoint` varchar(200) DEFAULT NULL,
  `DroppingPoint` varchar(200) DEFAULT NULL,
  `DepartureDate` date DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `SeatType` varchar(200) DEFAULT NULL,
  `SeatNumber` varchar(200) DEFAULT NULL,
  `SeatName` varchar(200) DEFAULT NULL,
  `IsLowerBerth` tinyint(1) DEFAULT NULL,
  `TotalFare` decimal(10,0) DEFAULT NULL,
  `ChartStatus` varchar(200) DEFAULT NULL,
  `CustomerName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Age` bigint(20) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `PhoneNumber` bigint(20) DEFAULT NULL,
  `IdType` varchar(200) DEFAULT NULL,
  `IdNumber` varchar(200) DEFAULT NULL,
  `IsLicence` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cancel_ticket_master` */

/*Table structure for table `cancellation_ticket_master` */

DROP TABLE IF EXISTS `cancellation_ticket_master`;

CREATE TABLE `cancellation_ticket_master` (
  `TicketId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CustId` bigint(20) NOT NULL,
  `OperatorId` bigint(20) NOT NULL,
  `BusId` bigint(20) DEFAULT NULL,
  `RouteId` bigint(20) DEFAULT NULL,
  `PNR` varchar(200) DEFAULT NULL,
  `DBTripId` varchar(200) DEFAULT NULL,
  `TripId` varchar(200) DEFAULT NULL,
  `TravelName` varchar(200) DEFAULT NULL,
  `BusType` varchar(200) DEFAULT NULL,
  `IsAc` tinyint(1) DEFAULT '0',
  `IsSleeper` tinyint(1) DEFAULT '0',
  `IsSeater` tinyint(1) DEFAULT '0',
  `BoadingPoint` varchar(200) DEFAULT NULL,
  `DroppingPoint` varchar(200) DEFAULT NULL,
  `DepartureDate` date DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `SeatType` varchar(200) DEFAULT NULL,
  `SeatNumber` varchar(200) DEFAULT NULL,
  `SeatName` varchar(200) DEFAULT NULL,
  `IsLowerBerth` tinyint(1) DEFAULT NULL,
  `TotalFare` decimal(10,0) DEFAULT NULL,
  `ChartStatus` varchar(200) DEFAULT NULL,
  `CustName` varchar(200) DEFAULT NULL,
  `Age` bigint(20) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `PhoneNumber` bigint(20) DEFAULT NULL,
  `IdType` varchar(200) DEFAULT NULL,
  `IdNumber` varchar(200) DEFAULT NULL,
  `IsLicence` tinyint(1) DEFAULT '0',
  `BookingTime` timestamp NULL DEFAULT NULL,
  `CancelTime` timestamp NULL DEFAULT NULL,
  `PolicyId` bigint(20) DEFAULT NULL,
  `RefundAmount` decimal(20,0) DEFAULT NULL,
  KEY `TicketId` (`TicketId`),
  KEY `FK_CANCELL_USER` (`CustId`),
  KEY `FK_CANCELL_BUS` (`BusId`),
  KEY `FK_CANCELL_USER_OP` (`OperatorId`),
  CONSTRAINT `FK_CANCELL_BUS` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`),
  CONSTRAINT `FK_CANCELL_USER` FOREIGN KEY (`CustId`) REFERENCES `user_master` (`UserId`),
  CONSTRAINT `FK_CANCELL_USER_OP` FOREIGN KEY (`OperatorId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cancellation_ticket_master` */

/*Table structure for table `central_logs` */

DROP TABLE IF EXISTS `central_logs`;

CREATE TABLE `central_logs` (
  `LogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `AppName` varchar(32) DEFAULT NULL,
  `LogLevel` varchar(32) DEFAULT NULL,
  `LogTimeStamp` datetime DEFAULT CURRENT_TIMESTAMP,
  `LogMessage` text,
  `CreatedBy` varchar(64) DEFAULT NULL,
  `DateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(64) DEFAULT NULL,
  `DateModified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ChannelType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LogId`)
) ENGINE=InnoDB AUTO_INCREMENT=932713 DEFAULT CHARSET=latin1;

/*Data for the table `central_logs` */

insert  into `central_logs`(`LogId`,`AppName`,`LogLevel`,`LogTimeStamp`,`LogMessage`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`,`ChannelType`) values 
(932705,'Digital-Bihar','ERROR','2019-05-17 14:21:39','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alive1Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:21:45',NULL,'2019-05-17 14:21:45',NULL),
(932706,'Digital-Bihar','ERROR','2019-05-17 14:24:26','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:24:35',NULL,'2019-05-17 14:24:35',NULL),
(932707,'Digital-Bihar','ERROR','2019-05-17 14:25:30','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:25:30',NULL,'2019-05-17 14:25:30',NULL),
(932708,'Digital-Bihar','ERROR','2019-05-17 14:26:02','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:26:02',NULL,'2019-05-17 14:26:02',NULL),
(932709,'Digital-Bihar','ERROR','2019-05-17 14:28:41','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:28:40',NULL,'2019-05-17 14:28:40',NULL),
(932710,'Digital-Bihar','ERROR','2019-05-17 14:30:52','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:30:52',NULL,'2019-05-17 14:30:52',NULL),
(932711,'Digital-Bihar','ERROR','2019-05-17 14:33:15','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, bren-US,en;q=0.9java.lang.NullPointerException\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:47)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\n',NULL,'2019-05-17 14:33:15',NULL,'2019-05-17 14:33:15',NULL),
(932712,'Digital-Bihar','ERROR','2019-05-21 22:02:51','\n BODY: \n METHOD: GET\n URL: http://localhost:8080/BiharConsumer/api/v0/bus/route/2/9/2019-05-30\nQueryParams: null\n HEADER: localhost:8080keep-alivemax-age=01Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3gzip, deflate, brhi-IN,hi;q=0.9,en-US;q=0.8,en;q=0.7org.springframework.web.util.NestedServletException: Handler dispatch failed; nested exception is java.lang.Error: Unresolved compilation problem: \n	The method setIsFixedFare(Boolean) in the type BusScheduleDetails is not applicable for the arguments (int)\n\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1006)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:978)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:870)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:855)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.PerformanceFilter.doFilterInternal(PerformanceFilter.java:33)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.digital.config.CorsFilter.doFilterInternal(CorsFilter.java:38)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:200)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:490)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:668)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:834)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1415)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:745)\r\nCaused by: java.lang.Error: Unresolved compilation problem: \n	The method setIsFixedFare(Boolean) in the type BusScheduleDetails is not applicable for the arguments (int)\n\r\n	at com.digital.model.extrator.BusTripDetailsExtrator.extractData(BusTripDetailsExtrator.java:29)\r\n	at com.digital.model.extrator.BusTripDetailsExtrator.extractData(BusTripDetailsExtrator.java:1)\r\n	at org.springframework.jdbc.core.JdbcTemplate$1.doInPreparedStatement(JdbcTemplate.java:667)\r\n	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:605)\r\n	at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:657)\r\n	at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:682)\r\n	at org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.query(NamedParameterJdbcTemplate.java:171)\r\n	at com.digital.dao.BusTripDao.searchTripBySrcDescAndDate(BusTripDao.java:109)\r\n	at com.digital.dao.BusTripDao$$FastClassBySpringCGLIB$$1ccab82c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:747)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor$$Lambda$159/115344394.proceedWithInvocation(Unknown Source)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\r\n	at com.digital.dao.BusTripDao$$EnhancerBySpringCGLIB$$ae2aa928.searchTripBySrcDescAndDate(<generated>)\r\n	at com.digital.service.BusTripService.searchBusScheduleDetails(BusTripService.java:44)\r\n	at com.digital.controller.BusTripController.searchBusScheduletDetails(BusTripController.java:48)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:483)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:870)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:776)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	... 36 more\r\n',NULL,'2019-05-21 22:02:50',NULL,'2019-05-21 22:02:50',NULL);

/*Table structure for table `city_master` */

DROP TABLE IF EXISTS `city_master`;

CREATE TABLE `city_master` (
  `CityId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CityName` varchar(255) NOT NULL,
  `DisplayName` varchar(255) NOT NULL,
  `StateName` varchar(255) NOT NULL,
  `District` varchar(255) NOT NULL,
  `Country` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'INDIA',
  `CreatedBy` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CityId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city_master` */

insert  into `city_master`(`CityId`,`CityName`,`DisplayName`,`StateName`,`District`,`Country`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'Gurgaon','Gurgaon','Hariyana','Hariyana','INDIA','CURRENT_USER','2019-04-19 14:06:13','CURRENT_USER','2019-04-19 14:06:13'),
(2,'Agra','Agra','Uttar Pradesh','Agra','INDIA','CURRENT_USER','2019-04-19 14:07:20','CURRENT_USER','2019-04-19 14:07:20'),
(9,'Lucknow','Lucknow','Uttar Pradesh','Lucknow','INDIA','CURRENT_USER','2019-04-19 14:09:04','CURRENT_USER','2019-04-19 14:09:04'),
(10,'Gorakhpur ','Gorakhpur ','Uttar Pradesh','Gorakhpur ','INDIA','CURRENT_USER','2019-04-19 14:20:37','CURRENT_USER','2019-04-19 14:20:37'),
(11,'Siwan','Siwan','Bihar','Siwan','INDIA',NULL,'2019-04-28 07:49:53',NULL,NULL),
(12,'Pipra Kothi','Pipra Kothi','Bihar','Pipra Kothi','INDIA','CURRENT_USER','2019-04-19 14:22:27','CURRENT_USER','2019-04-19 14:22:27'),
(13,'Muzaffarpur','Muzaffarpur','Bihar','Muzaffarpur','INDIA','CURRENT_USER','2019-04-19 14:23:06','CURRENT_USER','2019-04-19 14:23:06'),
(14,'Garbhanga','Garbhanga','Bihar','Garbhanga','INDIA','CURRENT_USER','2019-04-19 14:23:41','CURRENT_USER','2019-04-19 14:23:41'),
(15,'Jhanjharpur','Jhanjharpur','Bihar','Jhanjharpur','INDIA','CURRENT_USER','2019-04-19 14:24:24','CURRENT_USER','2019-04-19 14:24:24'),
(16,'Araria','Araria','Bihar','Araria','INDIA','CURRENT_USER','2019-04-19 14:25:03','CURRENT_USER','2019-04-19 14:25:03'),
(17,'Khopa','Khopa','Bihar','Khopa','INDIA','CURRENT_USER','2019-04-19 14:25:38','CURRENT_USER','2019-04-19 14:25:38'),
(18,'Phulparas','Phulparas','Bihar','Phulparas','INDIA','CURRENT_USER','2019-04-19 14:26:24','CURRENT_USER','2019-04-19 14:26:24'),
(19,'Narahia','Narahia','Bihar','Narahia','INDIA','CURRENT_USER()','2019-04-19 14:33:09','CURRENT_USER()','2019-04-19 14:33:09'),
(20,'Bhutha chowk','Bhutha chowk','Bihar','Bangawan','INDIA','CURRENT_USER()','2019-04-19 14:34:42','CURRENT_USER()','2019-04-19 14:34:42'),
(21,'MOHANIA','MOHANIA','Bihar','MOHANIA','INDIA','CURRENT_USER()','2019-04-19 14:37:14','CURRENT_USER()','2019-04-19 14:37:14'),
(22,'Supol','Supol','Bihar','Supol','INDIA',NULL,'2019-05-22 11:33:00',NULL,'2019-05-22 11:33:00'),
(23,'Manipur','Manipur','Bihar','Manipur','INDIA','CURRENT_USER()','2019-04-19 14:45:55','CURRENT_USER()','2019-04-19 14:45:55');

/*Table structure for table `city_stop_master` */

DROP TABLE IF EXISTS `city_stop_master`;

CREATE TABLE `city_stop_master` (
  `CityStopId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CityId` bigint(20) NOT NULL,
  `LocationName` varchar(255) DEFAULT NULL,
  `LocationAddress` varchar(255) DEFAULT NULL,
  `LandMark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Lat` decimal(10,2) DEFAULT '0.00',
  `Lng` decimal(10,2) DEFAULT '0.00',
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CityStopId`),
  KEY `CityStopId` (`CityStopId`),
  KEY `FKB6F1A86B22157871` (`CityId`),
  CONSTRAINT `FKB6F1A86B22157871` FOREIGN KEY (`CityId`) REFERENCES `city_master` (`CityId`),
  CONSTRAINT `FK_CITY` FOREIGN KEY (`CityId`) REFERENCES `city_master` (`CityId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city_stop_master` */

insert  into `city_stop_master`(`CityStopId`,`CityId`,`LocationName`,`LocationAddress`,`LandMark`,`Lat`,`Lng`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'IFCO CHOK','IFCO CHOK',NULL,0.00,0.00,NULL,'2019-04-29 05:52:41',NULL,'2019-04-29 05:52:41'),
(3,1,'satyam','sdfsd','SATYAM',NULL,NULL,NULL,'2019-05-22 18:33:17',NULL,'2019-05-22 18:33:17');

/*Table structure for table `incr` */

DROP TABLE IF EXISTS `incr`;

CREATE TABLE `incr` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `incr` */

insert  into `incr`(`Id`) values 
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30);

/*Table structure for table `menu_structure_master` */

DROP TABLE IF EXISTS `menu_structure_master`;

CREATE TABLE `menu_structure_master` (
  `MenuStructureId` bigint(10) NOT NULL AUTO_INCREMENT,
  `MenuName` varchar(200) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Url` varchar(200) DEFAULT NULL,
  `MenuType` varchar(200) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT '1',
  `CreatedBy` varchar(200) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(200) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MenuStructureId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `menu_structure_master` */

insert  into `menu_structure_master`(`MenuStructureId`,`MenuName`,`Description`,`Url`,`MenuType`,`IsActive`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'Bus',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),
(2,'Cab',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL);

/*Table structure for table `privilege_master` */

DROP TABLE IF EXISTS `privilege_master`;

CREATE TABLE `privilege_master` (
  `PrivilegeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `PrivilegeName` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `PrivilegeId` (`PrivilegeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `privilege_master` */

insert  into `privilege_master`(`PrivilegeId`,`PrivilegeName`,`Description`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'Read',NULL,NULL,'2019-04-14 11:01:40',NULL,'2019-04-14 11:01:40'),
(2,'Write',NULL,NULL,'2019-04-14 11:01:46',NULL,'2019-04-14 11:01:46');

/*Table structure for table `role_master` */

DROP TABLE IF EXISTS `role_master`;

CREATE TABLE `role_master` (
  `RoleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(255) NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT '1',
  `CreatedBy` varchar(255) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role_master` */

insert  into `role_master`(`RoleId`,`RoleName`,`Description`,`IsActive`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'ADMIN',NULL,1,NULL,NULL,NULL,NULL),
(2,'RETAILER',NULL,1,NULL,NULL,NULL,NULL),
(3,'CUSTOMER',NULL,1,NULL,NULL,NULL,NULL);

/*Table structure for table `role_privilege_mapping` */

DROP TABLE IF EXISTS `role_privilege_mapping`;

CREATE TABLE `role_privilege_mapping` (
  `RolePrivilegeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `PrivilegeId` bigint(20) NOT NULL,
  `RoleId` bigint(20) NOT NULL,
  `MenuId` bigint(20) NOT NULL,
  PRIMARY KEY (`RolePrivilegeId`),
  KEY `FK_PRIVILEGE` (`PrivilegeId`),
  KEY `FK_ROLE_MASTER` (`RoleId`),
  KEY `FK_MENU` (`MenuId`),
  CONSTRAINT `FK_MENU` FOREIGN KEY (`MenuId`) REFERENCES `menu_structure_master` (`MenuStructureId`),
  CONSTRAINT `FK_PRIVILEGE` FOREIGN KEY (`PrivilegeId`) REFERENCES `privilege_master` (`PrivilegeId`),
  CONSTRAINT `FK_ROLE_MASTER` FOREIGN KEY (`RoleId`) REFERENCES `role_master` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role_privilege_mapping` */

insert  into `role_privilege_mapping`(`RolePrivilegeId`,`PrivilegeId`,`RoleId`,`MenuId`) values 
(2,1,1,1),
(3,2,1,1);

/*Table structure for table `route_master` */

DROP TABLE IF EXISTS `route_master`;

CREATE TABLE `route_master` (
  `RouteId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Source` varchar(255) DEFAULT NULL,
  `Destination` varchar(255) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `Distance` double DEFAULT NULL,
  `Duration` double DEFAULT NULL,
  `BaseFare` double DEFAULT NULL,
  `CreatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RouteId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `route_master` */

insert  into `route_master`(`RouteId`,`Source`,`Destination`,`Description`,`Distance`,`Duration`,`BaseFare`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'1','2',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:53:31',NULL,'2019-04-19 14:53:31'),
(2,'2','9',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:55:10',NULL,'2019-04-19 14:55:10'),
(3,'9','10',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:56:00',NULL,'2019-04-19 14:56:00'),
(4,'10','11',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:56:30',NULL,'2019-04-19 14:56:30'),
(5,'12','13',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:56:45',NULL,'2019-04-19 14:56:45'),
(6,'13','14',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:57:03',NULL,'2019-04-19 14:57:03'),
(7,'14','15',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:57:48',NULL,'2019-04-19 14:57:48'),
(8,'16','17',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:58:02',NULL,'2019-04-19 14:58:02'),
(9,'17','18',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:58:22',NULL,'2019-04-19 14:58:22'),
(10,'18','19',NULL,NULL,NULL,NULL,NULL,'2019-04-19 14:59:42',NULL,'2019-04-19 14:59:42'),
(11,'19','20',NULL,NULL,NULL,NULL,NULL,'2019-04-19 15:00:06',NULL,'2019-04-19 15:00:06'),
(12,'20','21',NULL,NULL,NULL,NULL,NULL,'2019-04-19 15:00:34',NULL,'2019-04-19 15:00:34'),
(13,'21','22',NULL,NULL,NULL,NULL,NULL,'2019-04-19 15:00:47',NULL,'2019-04-19 15:00:47'),
(14,'22','23',NULL,NULL,NULL,NULL,NULL,'2019-04-19 15:01:06',NULL,'2019-04-19 15:01:06');

/*Table structure for table `schedule_master` */

DROP TABLE IF EXISTS `schedule_master`;

CREATE TABLE `schedule_master` (
  `ScheduleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusId` bigint(20) NOT NULL,
  `SourceCityId` bigint(20) NOT NULL,
  `DestinationCityId` bigint(20) DEFAULT NULL,
  `DepartureDate` date DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `SleeperFare` decimal(10,0) DEFAULT NULL,
  `SeaterFare` decimal(10,0) DEFAULT NULL,
  `IsFixed` tinyint(1) DEFAULT '0',
  `DriverName` varchar(100) DEFAULT NULL,
  `DriverNumber` bigint(20) DEFAULT NULL,
  `ConductorName` varchar(100) DEFAULT NULL,
  `ConductorNumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ScheduleId`),
  KEY `FK_BUS_SC` (`BusId`),
  KEY `FK_BUS_DES` (`DestinationCityId`),
  KEY `FK_CITY_SORC` (`SourceCityId`),
  CONSTRAINT `FK_BUS_DES` FOREIGN KEY (`DestinationCityId`) REFERENCES `city_master` (`CityId`),
  CONSTRAINT `FK_BUS_SC` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`),
  CONSTRAINT `FK_CITY_SORC` FOREIGN KEY (`SourceCityId`) REFERENCES `city_master` (`CityId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `schedule_master` */

insert  into `schedule_master`(`ScheduleId`,`BusId`,`SourceCityId`,`DestinationCityId`,`DepartureDate`,`DepartureTime`,`ArrivalDate`,`ArrivalTime`,`SleeperFare`,`SeaterFare`,`IsFixed`,`DriverName`,`DriverNumber`,`ConductorName`,`ConductorNumber`) values 
(3,2,1,23,'2019-04-30','12:30:00','2019-05-01',NULL,1250,1000,1,NULL,NULL,NULL,NULL);

/*Table structure for table `seat_master` */

DROP TABLE IF EXISTS `seat_master`;

CREATE TABLE `seat_master` (
  `SeatId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusLayoutId` bigint(20) NOT NULL,
  `RowName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ColumnName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Width` bigint(20) NOT NULL DEFAULT '1',
  `Length` bigint(20) NOT NULL DEFAULT '0',
  `SeatType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SeatName` varchar(100) DEFAULT NULL,
  `SeatNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IsAvailable` tinyint(1) NOT NULL DEFAULT '0',
  `IsLadiesSeat` tinyint(1) NOT NULL DEFAULT '0',
  `IsLowerBerth` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateModified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`SeatId`),
  KEY `FK_LAYOUT` (`BusLayoutId`),
  CONSTRAINT `FK_LAYOUT` FOREIGN KEY (`BusLayoutId`) REFERENCES `bus_layout_master` (`LayoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `seat_master` */

insert  into `seat_master`(`SeatId`,`BusLayoutId`,`RowName`,`ColumnName`,`Width`,`Length`,`SeatType`,`SeatName`,`SeatNumber`,`IsAvailable`,`IsLadiesSeat`,`IsLowerBerth`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,2,'0','0',1,1,'SS','1','1',0,0,1,NULL,NULL,NULL,NULL),
(3,2,'0','1',1,1,'SS','3','3',0,0,1,NULL,NULL,NULL,NULL),
(4,2,'0','2',1,1,'SS','5','5',0,0,1,NULL,NULL,NULL,NULL),
(5,2,'0','3',1,1,'SS','7','7',0,0,1,NULL,NULL,NULL,NULL),
(6,2,'0','4',1,1,'SS','9','9',0,0,1,NULL,NULL,NULL,NULL),
(7,2,'0','5',1,1,'SS','11','11',0,0,1,NULL,NULL,NULL,NULL),
(8,2,'0','6',1,2,'SL','L1','L1',0,0,1,NULL,NULL,NULL,NULL),
(9,2,'0','8',1,2,'SL','L3','L3',0,0,1,NULL,NULL,NULL,NULL),
(10,2,'0','10',1,2,'SL','L5','L5',0,0,1,NULL,NULL,NULL,NULL),
(11,2,'1','0',1,1,'SS','2','2',0,0,1,NULL,NULL,NULL,NULL),
(12,2,'1','1',1,1,'SS','4','4',0,0,1,NULL,NULL,NULL,NULL),
(13,2,'1','2',1,1,'SS','6','6',0,0,1,NULL,NULL,NULL,NULL),
(14,2,'1','3',1,1,'SS','8','8',0,0,1,NULL,NULL,NULL,NULL),
(15,2,'1','4',1,1,'SS','10','10',0,0,1,NULL,NULL,NULL,NULL),
(16,2,'1','5',1,1,'SS','12','12',0,0,1,NULL,NULL,NULL,NULL),
(17,2,'1','6',1,2,'SL','L2','L2',0,0,1,NULL,NULL,NULL,NULL),
(18,2,'1','8',1,2,'SL','L4','L4',0,0,1,NULL,NULL,NULL,NULL),
(19,2,'1','10',1,2,'SL','L6','L6',0,0,1,NULL,NULL,NULL,NULL),
(20,2,'4','0',1,2,'SL','L7','L7',0,0,1,NULL,NULL,NULL,NULL),
(21,2,'4','2',1,2,'SL','L8','L8',0,0,1,NULL,NULL,NULL,NULL),
(22,2,'4','4',1,2,'SL','L9','L9',0,0,1,NULL,NULL,NULL,NULL),
(25,2,'4','6',1,2,'SL','L10','L10',0,0,1,NULL,NULL,NULL,NULL),
(26,2,'4','8',1,2,'SL','L11','L11',0,0,1,NULL,NULL,NULL,NULL),
(27,2,'4','10',1,2,'SL','L12','L12',0,0,1,NULL,NULL,NULL,NULL),
(28,2,'0','0',1,2,'SL','U1','U1',0,0,0,NULL,NULL,NULL,NULL),
(29,2,'0','2',1,2,'SL','U3','U3',0,0,0,NULL,NULL,NULL,NULL),
(30,2,'0','4',1,2,'SL','U5','U5',0,0,0,NULL,NULL,NULL,NULL),
(31,2,'0','6',1,2,'SL','U7','U7',0,0,0,NULL,NULL,NULL,NULL),
(32,2,'0','8',1,2,'SL','U9','U9',0,0,0,NULL,NULL,NULL,NULL),
(33,2,'0','10',1,2,'SL','U11','U11',0,0,0,NULL,NULL,NULL,NULL),
(34,2,'1','0',1,2,'SL','U2','U2',0,0,0,NULL,NULL,NULL,NULL),
(35,2,'1','2',1,2,'SL','U4','U4',0,0,0,NULL,NULL,NULL,NULL),
(36,2,'1','4',1,2,'SL','U6','U6',0,0,0,NULL,NULL,NULL,NULL),
(37,2,'1','6',1,2,'SL','U8','U8',0,0,0,NULL,NULL,NULL,NULL),
(38,2,'1','8',1,2,'SL','U10','U10',0,0,0,NULL,NULL,NULL,NULL),
(39,2,'1','10',1,2,'SL','U12','U12',0,0,0,NULL,NULL,NULL,NULL),
(40,2,'4','0',1,2,'SL','U13','U13',0,0,0,NULL,NULL,NULL,NULL),
(41,2,'4','2',1,2,'SL','U14','U14',0,0,0,NULL,NULL,NULL,NULL),
(42,2,'4','4',1,2,'SL','U15','U15',0,0,0,NULL,NULL,NULL,NULL),
(43,2,'4','6',1,2,'SL','U16','U16',0,0,0,NULL,NULL,NULL,NULL),
(44,2,'4','8',1,2,'SL','U17','U17',0,0,0,NULL,NULL,NULL,NULL),
(45,2,'4','10',1,2,'SL','U18','U18',0,0,0,NULL,NULL,NULL,NULL),
(50,1,'0','0',1,1,'SS','1','1',0,0,0,NULL,NULL,NULL,NULL),
(51,1,'0','1',1,1,'SS','3','3',0,0,0,NULL,NULL,NULL,NULL),
(52,1,'0','2',1,1,'SS','5','5',0,0,0,NULL,NULL,NULL,NULL),
(53,1,'0','3',1,1,'SS','7','7',0,0,0,NULL,NULL,NULL,NULL),
(54,1,'0','4',1,1,'SS','9','9',0,0,0,NULL,NULL,NULL,NULL),
(55,1,'0','5',1,1,'SS','11','11',0,0,0,NULL,NULL,NULL,NULL),
(56,1,'0','6',1,1,'SS','13','13',0,0,0,NULL,NULL,NULL,NULL),
(57,1,'0','7',1,1,'SS','15','15',0,0,0,NULL,NULL,NULL,NULL),
(58,1,'0','8',1,1,'SS','17','17',0,0,0,NULL,NULL,NULL,NULL),
(59,1,'0','9',1,1,'SS','19','19',0,0,0,NULL,NULL,NULL,NULL),
(60,1,'4','0',1,1,'SS','22','22',0,0,0,NULL,NULL,NULL,NULL),
(61,1,'1','0',1,1,'SS','2','2',0,0,0,NULL,NULL,NULL,NULL),
(62,1,'1','1',1,1,'SS','4','4',0,0,0,NULL,NULL,NULL,NULL),
(63,1,'1','2',1,1,'SS','6','6',0,0,0,NULL,NULL,NULL,NULL),
(64,1,'1','3',1,1,'SS','8','8',0,0,0,NULL,NULL,NULL,NULL),
(65,1,'1','4',1,1,'SS','10','10',0,0,0,NULL,NULL,NULL,NULL),
(66,1,'1','5',1,1,'SS','12','12',0,0,0,NULL,NULL,NULL,NULL),
(67,1,'1','6',1,1,'SS','14','14',0,0,0,NULL,NULL,NULL,NULL),
(68,1,'1','7',1,1,'SS','16','16',0,0,0,NULL,NULL,NULL,NULL),
(69,1,'1','8',1,1,'SS','18','18',0,0,0,NULL,NULL,NULL,NULL),
(70,1,'1','9',1,1,'SS','20','20',0,0,0,NULL,NULL,NULL,NULL),
(71,1,'4','1',1,1,'SS','24','24',0,0,0,NULL,NULL,NULL,NULL),
(72,1,'3','0',1,1,'SS','21','21',0,0,0,NULL,NULL,NULL,NULL),
(73,1,'3','1',1,1,'SS','23','23',0,0,0,NULL,NULL,NULL,NULL),
(74,1,'3','2',1,1,'SS','25','25',0,0,0,NULL,NULL,NULL,NULL),
(75,1,'3','3',1,1,'SS','27','27',0,0,0,NULL,NULL,NULL,NULL),
(76,1,'3','4',1,1,'SS','29','29',0,0,0,NULL,NULL,NULL,NULL),
(77,1,'3','5',1,1,'SS','31','31',0,0,0,NULL,NULL,NULL,NULL),
(78,1,'3','6',1,1,'SS','33','33',0,0,0,NULL,NULL,NULL,NULL),
(79,1,'3','7',1,1,'SS','35','35',0,0,0,NULL,NULL,NULL,NULL),
(80,1,'3','8',1,1,'SS','37','37',0,0,0,NULL,NULL,NULL,NULL),
(81,1,'3','9',1,1,'SS','39','39',0,0,0,NULL,NULL,NULL,NULL),
(82,1,'4','2',1,1,'SS','26','26',0,0,0,NULL,NULL,NULL,NULL),
(83,1,'2','10',1,1,'SS','41','41',0,0,0,NULL,NULL,NULL,NULL),
(84,1,'4','3',1,1,'SS','28','28',0,0,0,NULL,NULL,NULL,NULL),
(85,1,'4','4',1,1,'SS','30','30',0,0,0,NULL,NULL,NULL,NULL),
(86,1,'4','5',1,1,'SS','32','32',0,0,0,NULL,NULL,NULL,NULL),
(87,1,'4','6',1,1,'SS','34','34',0,0,0,NULL,NULL,NULL,NULL),
(88,1,'4','7',1,1,'SS','36','36',0,0,0,NULL,NULL,NULL,NULL),
(89,1,'4','8',1,1,'SS','38','38',0,0,0,NULL,NULL,NULL,NULL),
(90,1,'4','9',1,1,'SS','40','40',0,0,0,NULL,NULL,NULL,NULL);

/*Table structure for table `ticket_master` */

DROP TABLE IF EXISTS `ticket_master`;

CREATE TABLE `ticket_master` (
  `TicketId` bigint(20) NOT NULL AUTO_INCREMENT,
  `ScheduleId` bigint(20) DEFAULT NULL,
  `UserId` bigint(20) NOT NULL,
  `BusId` bigint(20) DEFAULT NULL,
  `PNR` varchar(200) DEFAULT NULL,
  `SeatId` bigint(20) DEFAULT NULL,
  `TripId` varchar(200) DEFAULT NULL,
  `TravelName` varchar(200) DEFAULT NULL,
  `BusType` varchar(200) DEFAULT NULL,
  `IsAc` tinyint(1) DEFAULT '0',
  `BoadingPoint` varchar(200) DEFAULT NULL,
  `DroppingPoint` varchar(200) DEFAULT NULL,
  `DepartureDate` date DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `SeatType` varchar(200) DEFAULT NULL,
  `SeatNumber` varchar(200) DEFAULT NULL,
  `SeatName` varchar(200) DEFAULT NULL,
  `IsLowerBerth` tinyint(1) DEFAULT NULL,
  `TotalFare` decimal(10,0) DEFAULT NULL,
  `ChartStatus` varchar(200) DEFAULT NULL,
  `CustomerName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Age` bigint(20) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `PhoneNumber` bigint(20) DEFAULT NULL,
  `IdType` varchar(200) DEFAULT NULL,
  `IdNumber` varchar(200) DEFAULT NULL,
  `IsLicence` tinyint(1) DEFAULT '0',
  `BookingTime` timestamp NULL DEFAULT NULL,
  KEY `BookingId` (`TicketId`),
  KEY `FK_TICKET_USER` (`UserId`),
  KEY `FK_TICKET_BUS` (`BusId`),
  KEY `FK_SCEDULE_TICK` (`ScheduleId`),
  CONSTRAINT `FK_SCEDULE_TICK` FOREIGN KEY (`ScheduleId`) REFERENCES `schedule_master` (`ScheduleId`),
  CONSTRAINT `FK_TICKET_BUS` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`),
  CONSTRAINT `FK_TICKET_USER` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ticket_master` */

/*Table structure for table `trip_master` */

DROP TABLE IF EXISTS `trip_master`;

CREATE TABLE `trip_master` (
  `TripId` bigint(20) NOT NULL AUTO_INCREMENT,
  `ScheduleId` bigint(20) NOT NULL,
  `CityId` bigint(20) NOT NULL,
  `CitySequance` int(11) NOT NULL,
  `CityStops` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `BaseFare` decimal(10,0) DEFAULT NULL,
  `Distance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`TripId`),
  KEY `FK_SCH` (`ScheduleId`),
  KEY `FK_CITY_TRIP` (`CityId`),
  CONSTRAINT `FK_CITY_TRIP` FOREIGN KEY (`CityId`) REFERENCES `city_master` (`CityId`),
  CONSTRAINT `FK_SCH` FOREIGN KEY (`ScheduleId`) REFERENCES `schedule_master` (`ScheduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trip_master` */

insert  into `trip_master`(`TripId`,`ScheduleId`,`CityId`,`CitySequance`,`CityStops`,`ArrivalDate`,`ArrivalTime`,`BaseFare`,`Distance`) values 
(1,3,1,1,NULL,'2019-05-30',NULL,NULL,NULL),
(2,3,2,2,NULL,'2019-05-30',NULL,NULL,NULL),
(3,3,9,3,NULL,'2019-05-30',NULL,NULL,NULL),
(30,3,10,4,NULL,'2019-05-30',NULL,NULL,NULL),
(31,3,11,5,NULL,'2019-05-30',NULL,NULL,NULL),
(32,3,12,6,NULL,'2019-05-30',NULL,NULL,NULL),
(33,3,13,7,NULL,'2019-05-30',NULL,NULL,NULL),
(34,3,14,8,NULL,'2019-05-30',NULL,NULL,NULL),
(35,3,15,9,NULL,'2019-05-30',NULL,NULL,NULL),
(36,3,16,10,NULL,'2019-05-30',NULL,NULL,NULL),
(37,3,17,11,NULL,'2019-05-30',NULL,NULL,NULL),
(38,3,18,12,NULL,'2019-05-30',NULL,NULL,NULL),
(39,3,19,13,NULL,'2019-05-30',NULL,NULL,NULL),
(40,3,20,14,NULL,'2019-05-30',NULL,NULL,NULL),
(41,3,21,15,NULL,'2019-05-30',NULL,NULL,NULL),
(42,3,22,16,NULL,'2019-05-30',NULL,NULL,NULL),
(44,3,23,17,NULL,'2019-05-30',NULL,NULL,NULL);

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `LoginId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(255) DEFAULT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `State` varchar(255) DEFAULT NULL,
  `SessionId` varchar(500) DEFAULT NULL,
  `LoginDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Address` varchar(500) DEFAULT NULL,
  `LogoutDate` timestamp NULL DEFAULT NULL,
  `ClientIp` varchar(500) DEFAULT NULL,
  `ClientHost` varchar(500) DEFAULT NULL,
  `Sate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LoginId`),
  KEY `user_login_fk` (`UserId`),
  CONSTRAINT `user_login_fk` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_login` */

insert  into `user_login`(`LoginId`,`UserId`,`UserName`,`City`,`State`,`SessionId`,`LoginDate`,`Address`,`LogoutDate`,`ClientIp`,`ClientHost`,`Sate`) values 
(2,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 51, 102, 55, 49, 56, 98, 57, 100, 44, 32, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 06:57:07','IFCO Chok GURGAON','2019-04-23 06:57:07',NULL,NULL,NULL),
(3,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 54, 55, 56, 54, 99, 51, 54, 97, 44, 32, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 07:09:00','IFCO Chok GURGAON','2019-04-23 07:09:00',NULL,NULL,NULL),
(4,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 53, 56, 54, 100, 102, 101, 55, 57, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 07:35:52','IFCO Chok GURGAON','2019-04-23 07:35:52',NULL,NULL,NULL),
(5,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 98, 53, 99, 49, 53, 51, 98, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 20:38:02','IFCO Chok GURGAON','2019-04-23 20:38:02',NULL,NULL,NULL),
(6,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 53, 52, 53, 101, 56, 102, 50, 52, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 20:55:30','IFCO Chok GURGAON','2019-04-23 20:55:30',NULL,NULL,NULL),
(7,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 53, 55, 100, 97, 98, 56, 97, 99, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 21:37:44','Hero Honda Chhok Gurgaon','2019-04-23 21:37:44',NULL,NULL,NULL),
(8,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 49, 53, 57, 56, 102, 52, 99, 48, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:00:36','Hero Honda Chhok Gurgaon','2019-04-23 23:00:36',NULL,NULL,NULL),
(9,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 51, 51, 99, 49, 49, 57, 101, 54, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:07:06','Hero Honda Chhok Gurgaon','2019-04-23 23:07:06',NULL,NULL,NULL),
(10,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 51, 99, 97, 55, 102, 97, 49, 51, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:08:48','Hero Honda Chhok Gurgaon','2019-04-23 23:08:48',NULL,NULL,NULL),
(11,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 52, 49, 48, 99, 100, 57, 55, 51, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:11:10','Hero Honda Chhok Gurgaon','2019-04-23 23:11:10',NULL,NULL,NULL),
(12,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 53, 100, 52, 54, 102, 53, 48, 101, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:17:10','Hero Honda Chhok Gurgaon','2019-04-23 23:17:10',NULL,NULL,NULL),
(13,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 101, 98, 54, 57, 54, 99, 53, 125]','2019-04-23 23:28:55','Hero Honda Chhok Gurgaon','2019-04-23 23:28:55',NULL,NULL,NULL),
(14,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 52, 99, 57, 100, 99, 56, 102, 100, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:30:50','Hero Honda Chhok Gurgaon','2019-04-23 23:30:50',NULL,NULL,NULL),
(15,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 49, 56, 102, 51, 100, 98, 54, 97, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:33:18','Hero Honda Chhok Gurgaon','2019-04-23 23:33:18',NULL,NULL,NULL),
(16,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 51, 102, 102, 55, 48, 56, 100, 50, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-23 23:39:26','Hero Honda Chhok Gurgaon','2019-04-23 23:39:26',NULL,NULL,NULL),
(17,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 53, 48, 49, 97, 99, 53, 48, 51, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-24 00:40:45','Hero Honda Chhok Gurgaon','2019-04-24 00:40:45',NULL,NULL,NULL),
(18,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 54, 101, 100, 98, 56, 52, 51, 51, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-24 06:42:52','Hero Honda Chhok Gurgaon','2019-04-24 06:42:52',NULL,NULL,NULL),
(19,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 51, 53, 52, 52, 57, 102, 49, 102, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-25 16:51:33','IFCO Chok GURGAON','2019-04-25 16:51:33',NULL,NULL,NULL),
(20,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 52, 52, 100, 50, 97, 99, 53, 51, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-25 16:56:43','IFCO Chok GURGAON','2019-04-25 16:56:43',NULL,NULL,NULL),
(21,7,'Navin Visthi',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 49, 51, 102, 56, 53, 48, 56, 56, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-04-25 16:58:14','IFCO Chok GURGAON','2019-04-25 16:58:14',NULL,NULL,NULL),
(22,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 49, 101, 49, 102, 55, 100, 53, 98, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-05-08 05:20:39','Hero Honda Chhok Gurgaon','2019-05-08 05:20:39',NULL,NULL,NULL),
(23,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 50, 50, 56, 53, 99, 52, 48, 56, 44, 32, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-05-08 05:26:24','Hero Honda Chhok Gurgaon','2019-05-08 05:26:24',NULL,NULL,NULL),
(24,4,'Bhushan Kumar',NULL,NULL,'[123, 117, 115, 101, 114, 116, 121, 112, 101, 61, 82, 69, 84, 65, 73, 76, 69, 82, 44, 32, 117, 115, 101, 114, 61, 99, 111, 109, 46, 100, 105, 103, 105, 116, 97, 108, 46, 117, 115, 101, 114, 46, 85, 115, 101, 114, 64, 52, 101, 52, 55, 52, 100, 102, 99, 44, 32, 116, 105, 116, 108, 101, 61, 68, 105, 103, 105, 116, 97, 108, 32, 66, 105, 104, 97, 114, 44, 32, 97, 117, 116, 104, 101, 110, 116, 105, 99, 97, 116, 101, 100, 61, 116, 114, 117, 101, 125]','2019-05-08 05:33:05','Hero Honda Chhok Gurgaon','2019-05-08 05:33:05',NULL,NULL,NULL);

/*Table structure for table `user_master` */

DROP TABLE IF EXISTS `user_master`;

CREATE TABLE `user_master` (
  `UserId` bigint(255) NOT NULL AUTO_INCREMENT,
  `RoleId` bigint(20) NOT NULL,
  `Name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Email` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `PhoneNumber` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PanNumber` varchar(500) DEFAULT NULL,
  `Password` varchar(500) DEFAULT NULL,
  `City` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `State` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Country` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'INDIA',
  `IsLock` tinyint(1) NOT NULL DEFAULT '0',
  `Attempt` int(11) NOT NULL DEFAULT '0',
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `CreatedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateModified` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  KEY `FKEBC671569231D967` (`RoleId`),
  CONSTRAINT `FKEBC671569231D967` FOREIGN KEY (`RoleId`) REFERENCES `role_master` (`RoleId`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`RoleId`) REFERENCES `role_master` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_master` */

insert  into `user_master`(`UserId`,`RoleId`,`Name`,`Email`,`Address`,`PhoneNumber`,`PanNumber`,`Password`,`City`,`State`,`Country`,`IsLock`,`Attempt`,`IsActive`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'Satyam','satyamk328@gmail.com','New Ashok Nagar','8130787891','CBGPK4395B','MTIz','DELHI','DELHI','INDIA',0,0,1,NULL,'2019-04-14 12:02:54',NULL,'2019-04-14 12:02:54'),
(2,1,'Tej Narayan Shahu','www.digitalbihar.com','Sharhaul Sector 18 Gurgaon','8800359490','CFFFF23FG','MTIz','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-14 12:04:39',NULL,'2019-04-14 12:04:39'),
(3,2,'Naiyan Jee','naiyan@gmail.com','Hero Honda Chhok Gurgaon','7289900566',NULL,'MTIz','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:36:37',NULL,'2019-04-19 13:36:37'),
(4,2,'Bhushan Kumar','bhushan@gmail.com','Hero Honda Chhok Gurgaon','9973443421','aaaaaaaaaaaaa','MTIz','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:39:08',NULL,'2019-04-19 13:39:08'),
(5,2,'Raghunath Travel','raghunath@gmail.com','IFCO Chok Gurgaon','9968776600',NULL,'MTIz','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:43:18',NULL,'2019-04-19 13:43:18'),
(6,2,'Mukhtar Kumar','mukhtar@gmail.com','IFO Chok Gurgaon','9811093825',NULL,'MTIz','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:47:19',NULL,'2019-04-19 13:47:19'),
(7,2,'Navin Visthi','navin@gmail.com','IFCO Chok GURGAON','8929350567',NULL,'MTIz','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:48:52',NULL,'2019-04-19 13:48:52'),
(13,2,'saaaaaaa','sa@sa.com','B-604 Sarhaul Gurgaon Sector 18 HariyanaNear Pooja Medical','8130787892','ASDSD','MTIz',NULL,NULL,NULL,0,0,1,NULL,'2019-05-21 14:02:56',NULL,'2019-05-21 14:02:56');

/*Table structure for table `wallet_master` */

DROP TABLE IF EXISTS `wallet_master`;

CREATE TABLE `wallet_master` (
  `WalletId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `AddedBalance` decimal(10,0) DEFAULT NULL,
  `CurrentBalance` decimal(10,0) DEFAULT NULL,
  `PreviousBalance` decimal(10,0) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `txId` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `PaymentMode` varchar(200) DEFAULT NULL,
  `SenderName` varchar(200) DEFAULT NULL,
  `CreatedBy` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER()',
  `DateModified` timestamp NULL DEFAULT NULL,
  KEY `WalletId` (`WalletId`),
  KEY `FK_USER_MASTER` (`UserId`),
  CONSTRAINT `FK_USER_MASTER` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wallet_master` */

/* Procedure structure for procedure `dowhile` */

/*!50003 DROP PROCEDURE IF EXISTS  `dowhile` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `dowhile`()
BEGIN
  DECLARE v1 INT DEFAULT 30;
  WHILE v1 > 0 DO
    INSERT incr VALUES (NULL);
    SET v1 = v1 - 1;
  END WHILE;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
