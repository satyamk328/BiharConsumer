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
  PRIMARY KEY (`AmenityId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `amenity_master` */

insert  into `amenity_master`(`AmenityId`,`Name`,`Label`,`Icon`) values 
(1,'live_tracking','Live Tracking','https://d274ft55l0imju.cloudfront.net/bus-api/live_tracking.png'),
(2,'charging_point','Charging Point','https://d274ft55l0imju.cloudfront.net/bus-api/charging_point.png'),
(3,'wifi','Wifi','https://d274ft55l0imju.cloudfront.net/bus-api/wifi.png'),
(4,'tv','TV','https://d274ft55l0imju.cloudfront.net/bus-api/personal_tv.png'),
(5,'blanket','Blanket','https://d274ft55l0imju.cloudfront.net/bus-api/blanket.png'),
(6,'mticket','MTicket','https://d274ft55l0imju.cloudfront.net/bus-api/mticket.png'),
(7,'reading_light','Reading Light','https://d274ft55l0imju.cloudfront.net/bus-api/reading_light.png'),
(8,'water_bottle','Water Bottle','https://d274ft55l0imju.cloudfront.net/bus-api/water_bottle.png'),
(9,'snacks','Snacks','https://d274ft55l0imju.cloudfront.net/bus-api/snacks.png'),
(10,'pillow','Pillow','https://d274ft55l0imju.cloudfront.net/bus-api/pillow.png'),
(11,'newspaper','Newspaper','https://d274ft55l0imju.cloudfront.net/bus-api/news_paper.png'),
(12,'novel','Novel','https://d274ft55l0imju.cloudfront.net/bus-api/novel.png'),
(13,'emergency_exit','Emergency Exit','https://d274ft55l0imju.cloudfront.net/bus-api/emergency_exit.png'),
(14,'fire_extinguisher','Fire Extinguisher','https://d274ft55l0imju.cloudfront.net/bus-api/fire_extinguisher.png'),
(15,'first_aid_box','First Aid Box','https://d274ft55l0imju.cloudfront.net/bus-api/first_aid_box.png'),
(16,'hammer','Hammer (to break glass)','https://d274ft55l0imju.cloudfront.net/bus-api/hammer_to_break_glass.png'),
(17,'facial_tissues','Facial Tissues','https://d274ft55l0imju.cloudfront.net/bus-api/facial_tissues.png'),
(18,'washroom','Washroom','https://d274ft55l0imju.cloudfront.net/bus-api/washroom.png'),
(19,'headsets','Headsets','https://d274ft55l0imju.cloudfront.net/bus-api/headsets.png'),
(20,'vomiting_bag','Vomiting Bag','https://d274ft55l0imju.cloudfront.net/bus-api/vomiting_bag.png'),
(21,'heater','Heater','https://d274ft55l0imju.cloudfront.net/bus-api/heater.png'),
(22,'cctv','CCTV','https://d274ft55l0imju.cloudfront.net/bus-api/cctv.png'),
(23,'fan','Fan','https://d274ft55l0imju.cloudfront.net/bus-api/fan.png');

/*Table structure for table `api_response_log` */

DROP TABLE IF EXISTS `api_response_log`;

CREATE TABLE `api_response_log` (
  `SPApiResponseLogId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ApiName` varchar(50) DEFAULT NULL,
  `ConsumerId` bigint(20) unsigned DEFAULT NULL,
  `AccountNumber` varchar(16) DEFAULT NULL,
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
  `ResponseCode` varchar(16) DEFAULT NULL,
  `ResponseStatus` varchar(16) DEFAULT NULL,
  `ResponsePlatform` varchar(16) DEFAULT NULL,
  `ChannelType` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SPApiResponseLogId`)
) ENGINE=InnoDB AUTO_INCREMENT=20010661 DEFAULT CHARSET=latin1;

/*Data for the table `api_response_log` */

/*Table structure for table `bus_cancellation_policy` */

DROP TABLE IF EXISTS `bus_cancellation_policy`;

CREATE TABLE `bus_cancellation_policy` (
  `PolicyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusId` bigint(20) NOT NULL,
  `DepartureHeading` varchar(255) DEFAULT NULL,
  `PolicyHeading` varchar(255) DEFAULT NULL,
  `CreatedDate` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`PolicyId`),
  KEY `FK_BUS_CANCE` (`BusId`),
  CONSTRAINT `FK_BUS_CANCE` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_cancellation_policy` */

insert  into `bus_cancellation_policy`(`PolicyId`,`BusId`,`DepartureHeading`,`PolicyHeading`,`CreatedDate`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'Between 0 Hrs. to 12 Hrs.','0%',NULL,NULL,NULL,NULL),
(2,1,'Between 12 Hrs. to 24 Hrs.','50%',NULL,NULL,NULL,NULL),
(3,1,'Before 24 Hrs.','90%',NULL,NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_helpline_master` */

insert  into `bus_helpline_master`(`Id`,`BusId`,`ContactNumber`,`ContactPerson`,`Address`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'72899900566','Naiyan Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(2,1,'7290900866','Naiyan Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(4,1,'9136581950','Naiyan Jee','Hero Honda Chhok Gurgaon',NULL,NULL,NULL,NULL),
(5,6,'9811093825','Mukhar Jee',NULL,NULL,NULL,NULL,NULL),
(6,6,'9599088528','Mukhar Jee',NULL,NULL,NULL,NULL,NULL),
(7,6,'9599088529','Mukhar Jee',NULL,NULL,NULL,NULL,NULL),
(8,6,'9599088531','Mukhar Jee',NULL,NULL,NULL,NULL,NULL),
(9,6,'9599088532','Mukhar Jee',NULL,NULL,NULL,NULL,NULL);

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
  `TotalSeats` decimal(10,0) DEFAULT '0',
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
(2,4,1,NULL,'Maa Travels',NULL,NULL,0,0),
(3,5,1,NULL,'Mithila Travels',NULL,NULL,0,0),
(4,7,1,NULL,'Kristhi Travels',NULL,NULL,0,0),
(5,7,1,NULL,'Kristhi Travels',NULL,NULL,0,0),
(6,6,1,NULL,'Chandni Travels',NULL,NULL,0,0);

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
  `ConsumerId` bigint(20) unsigned DEFAULT NULL,
  `AccountNumber` varchar(16) DEFAULT NULL,
  `CreatedBy` varchar(64) DEFAULT NULL,
  `DateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(64) DEFAULT NULL,
  `DateModified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`LogId`)
) ENGINE=InnoDB AUTO_INCREMENT=932705 DEFAULT CHARSET=latin1;

/*Data for the table `central_logs` */

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city_master` */

insert  into `city_master`(`CityId`,`CityName`,`DisplayName`,`StateName`,`District`,`Country`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'Gurgaon','Gurgaon','Hariyana','Hariyana','INDIA','CURRENT_USER','2019-04-19 14:06:13','CURRENT_USER','2019-04-19 14:06:13'),
(2,'Agra','Agra','Uttar Pradesh','Agra','INDIA','CURRENT_USER','2019-04-19 14:07:20','CURRENT_USER','2019-04-19 14:07:20'),
(9,'Lucknow','Lucknow','Uttar Pradesh','Lucknow','INDIA','CURRENT_USER','2019-04-19 14:09:04','CURRENT_USER','2019-04-19 14:09:04'),
(10,'Gorakhpur ','Gorakhpur ','Uttar Pradesh','Gorakhpur ','INDIA','CURRENT_USER','2019-04-19 14:20:37','CURRENT_USER','2019-04-19 14:20:37'),
(11,'Siwan','Siwan','Bihar','Siwan','INDIA','CURRENT_USER','2019-04-19 14:21:08','CURRENT_USER','2019-04-19 14:21:08'),
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
(22,'Supol','Supol','Bihar','Supol','INDIA','CURRENT_USER()','2019-04-19 14:41:05','CURRENT_USER()','2019-04-19 14:41:05'),
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
  KEY `FK_CITY` (`CityId`),
  CONSTRAINT `FK_CITY` FOREIGN KEY (`CityId`) REFERENCES `city_master` (`CityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city_stop_master` */

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
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(100) DEFAULT NULL,
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
  PRIMARY KEY (`ScheduleId`),
  KEY `FK_BUS_SC` (`BusId`),
  KEY `FK_BUS_DES` (`DestinationCityId`),
  KEY `FK_CITY_SORC` (`SourceCityId`),
  CONSTRAINT `FK_BUS_DES` FOREIGN KEY (`DestinationCityId`) REFERENCES `city_master` (`CityId`),
  CONSTRAINT `FK_BUS_SC` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`),
  CONSTRAINT `FK_CITY_SORC` FOREIGN KEY (`SourceCityId`) REFERENCES `city_master` (`CityId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `schedule_master` */

insert  into `schedule_master`(`ScheduleId`,`BusId`,`SourceCityId`,`DestinationCityId`,`DepartureDate`,`DepartureTime`,`ArrivalDate`,`ArrivalTime`,`SleeperFare`,`SeaterFare`,`IsFixed`) values 
(3,1,1,23,'2019-04-30','12:30:00','2019-05-01',NULL,1250,1000,1);

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
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) DEFAULT NULL,
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
  `OperatorId` bigint(20) NOT NULL,
  `BusId` bigint(20) DEFAULT NULL,
  `PNR` varchar(200) DEFAULT NULL,
  `DBTripId` varchar(200) DEFAULT NULL,
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
  KEY `BookingId` (`TicketId`),
  KEY `FK_TICKET_USER` (`UserId`),
  KEY `FK_TICKET_BUS` (`BusId`),
  KEY `FK_TICKET_US` (`OperatorId`),
  KEY `FK_SCEDULE_TICK` (`ScheduleId`),
  CONSTRAINT `FK_SCEDULE_TICK` FOREIGN KEY (`ScheduleId`) REFERENCES `schedule_master` (`ScheduleId`),
  CONSTRAINT `FK_TICKET_BUS` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`),
  CONSTRAINT `FK_TICKET_US` FOREIGN KEY (`OperatorId`) REFERENCES `user_master` (`UserId`),
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
  `BaseFare` decimal(10,2) DEFAULT NULL,
  `Time` double DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  PRIMARY KEY (`TripId`),
  KEY `FK_SCH` (`ScheduleId`),
  KEY `FK_CITY_TRIP` (`CityId`),
  CONSTRAINT `FK_CITY_TRIP` FOREIGN KEY (`CityId`) REFERENCES `city_master` (`CityId`),
  CONSTRAINT `FK_SCH` FOREIGN KEY (`ScheduleId`) REFERENCES `schedule_master` (`ScheduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trip_master` */

insert  into `trip_master`(`TripId`,`ScheduleId`,`CityId`,`CitySequance`,`CityStops`,`BaseFare`,`Time`,`ArrivalDate`) values 
(1,3,1,1,NULL,NULL,NULL,'2019-04-30'),
(2,3,2,2,NULL,NULL,NULL,'2019-04-30'),
(3,3,9,3,NULL,NULL,NULL,'2019-04-30'),
(30,3,10,4,NULL,NULL,NULL,'2019-04-30'),
(31,3,11,5,NULL,NULL,NULL,'2019-04-30'),
(32,3,12,6,NULL,NULL,NULL,'2019-04-30'),
(33,3,13,7,NULL,NULL,NULL,'2019-04-30'),
(34,3,14,8,NULL,NULL,NULL,'2019-04-30'),
(35,3,15,9,NULL,NULL,NULL,'2019-05-01'),
(36,3,16,10,NULL,NULL,NULL,'2019-05-01'),
(37,3,17,11,NULL,NULL,NULL,'2019-05-01'),
(38,3,18,12,NULL,NULL,NULL,'2019-05-01'),
(39,3,19,13,NULL,NULL,NULL,'2019-05-01'),
(40,3,20,14,NULL,NULL,NULL,'2019-05-01'),
(41,3,21,15,NULL,NULL,NULL,'2019-05-01'),
(42,3,22,16,NULL,NULL,NULL,'2019-05-01'),
(44,3,23,17,NULL,NULL,NULL,'2019-05-01');

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
  PRIMARY KEY (`LoginId`),
  KEY `user_login_fk` (`UserId`),
  CONSTRAINT `user_login_fk` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_login` */

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
  `City` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `State` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Country` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IsLock` tinyint(1) NOT NULL DEFAULT '0',
  `Attempt` int(11) NOT NULL DEFAULT '0',
  `IsActive` tinyint(1) NOT NULL DEFAULT '1',
  `CreatedBy` varchar(255) DEFAULT NULL,
  `DateCreated` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DateModified` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  KEY `FK_ROLE` (`RoleId`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`RoleId`) REFERENCES `role_master` (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_master` */

insert  into `user_master`(`UserId`,`RoleId`,`Name`,`Email`,`Address`,`PhoneNumber`,`PanNumber`,`Password`,`City`,`State`,`Country`,`IsLock`,`Attempt`,`IsActive`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'Satyam','satyamk328@gmail.com','New Ashok Nagar','8130787891','CBGPK4395B','123','DELHI','DELHI','INDIA',0,0,1,NULL,'2019-04-14 12:02:54',NULL,'2019-04-14 12:02:54'),
(2,1,'Tej Narayan Shahu','www.digitalbihar.com','Sharhaul Sector 18 Gurgaon','8800359490','CFFFF23FG','123','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-14 12:04:39',NULL,'2019-04-14 12:04:39'),
(3,2,'Naiyan Jee','naiyan@gmail.com','Hero Honda Chhok Gurgaon','7289900566',NULL,NULL,'GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:36:37',NULL,'2019-04-19 13:36:37'),
(4,2,'Bhushan Kumar','bhushan@gmail.com','Hero Honda Chhok Gurgaon','8766224540',NULL,NULL,'GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:39:08',NULL,'2019-04-19 13:39:08'),
(5,2,'Raghunath Travel','raghunath@gmail.com','IFCO Chok Gurgaon','9968776600',NULL,NULL,'GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:43:18',NULL,'2019-04-19 13:43:18'),
(6,2,'Mukhtar Kumar','mukhtar@gmail.com','IFO Chok Gurgaon','9811093825',NULL,NULL,'GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:47:19',NULL,'2019-04-19 13:47:19'),
(7,2,'Navin Visthi','navin@gmail.com','IFCO Chok GURGAON','8929350567',NULL,NULL,'GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-19 13:48:52',NULL,'2019-04-19 13:48:52');

/*Table structure for table `wallet_master` */

DROP TABLE IF EXISTS `wallet_master`;

CREATE TABLE `wallet_master` (
  `WalletId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `AddedBalance` decimal(10,0) DEFAULT NULL,
  `CurrentBalance` decimal(10,0) DEFAULT NULL,
  `PreviousBalance` decimal(10,0) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `txid` varchar(200) DEFAULT NULL,
  `PaymentMode` varchar(200) DEFAULT NULL,
  `SenderName` varchar(200) DEFAULT NULL,
  `CreatedBy` varchar(200) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT NULL,
  KEY `WalletId` (`WalletId`),
  KEY `FK_USER_MASTER` (`UserId`),
  CONSTRAINT `FK_USER_MASTER` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `wallet_master` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
