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
  `AmenityMappingId` bigint(20) NOT NULL,
  `BusId` bigint(20) NOT NULL,
  `AmenityId` bigint(20) NOT NULL,
  PRIMARY KEY (`AmenityMappingId`),
  KEY `FK_BUS_MAS` (`BusId`),
  KEY `FK_AM` (`AmenityId`),
  CONSTRAINT `FK_AM` FOREIGN KEY (`AmenityId`) REFERENCES `amenity_master` (`AmenityId`),
  CONSTRAINT `FK_BUS_MAS` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `amenity_mapping` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_cancellation_policy` */

/*Table structure for table `bus_layout_master` */

DROP TABLE IF EXISTS `bus_layout_master`;

CREATE TABLE `bus_layout_master` (
  `LayoutId` bigint(20) NOT NULL AUTO_INCREMENT,
  `LayoutType` varchar(200) NOT NULL,
  `BusType` varchar(200) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `IsSeater` tinyint(1) DEFAULT '0',
  `IsSleeper` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`LayoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_layout_master` */

insert  into `bus_layout_master`(`LayoutId`,`LayoutType`,`BusType`,`Description`,`IsSeater`,`IsSleeper`) values 
(1,'1','1',NULL,0,0);

/*Table structure for table `bus_master` */

DROP TABLE IF EXISTS `bus_master`;

CREATE TABLE `bus_master` (
  `BusId` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `LayoutId` bigint(20) NOT NULL,
  `BusNumber` varchar(255) DEFAULT NULL,
  `TravelsName` varchar(100) DEFAULT NULL,
  `RegistationNumber` varchar(100) DEFAULT NULL,
  `ContactNumber` varchar(200) DEFAULT NULL,
  `Color` varchar(255) DEFAULT NULL,
  `TotalSeats` decimal(10,0) DEFAULT '0',
  `IsAc` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`BusId`),
  KEY `FK_USER_MASTER_BUS` (`UserId`),
  KEY `FK_LAYOUT_MASTER` (`LayoutId`),
  CONSTRAINT `FK_LAYOUT_MASTER` FOREIGN KEY (`LayoutId`) REFERENCES `bus_layout_master` (`LayoutId`),
  CONSTRAINT `FK_USER_MASTER_BUS` FOREIGN KEY (`UserId`) REFERENCES `user_master` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bus_master` */

insert  into `bus_master`(`BusId`,`UserId`,`LayoutId`,`BusNumber`,`TravelsName`,`RegistationNumber`,`ContactNumber`,`Color`,`TotalSeats`,`IsAc`) values 
(1,2,1,NULL,'Maa Travels',NULL,'8130787891',NULL,0,0);

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
  `CreatedBy` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER',
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'CURRENT_USER',
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CityId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city_master` */

insert  into `city_master`(`CityId`,`CityName`,`DisplayName`,`StateName`,`District`,`Country`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'New Delhi','New Delhi','Delhi','New Delhi','INDIA',NULL,'2019-04-14 10:39:43',NULL,'2019-04-14 10:39:43'),
(2,'Agra','Agra','Uttar Pradesh','Agra','INDIA','CURRENT_USER','2019-04-14 10:41:03','CURRENT_USER','2019-04-14 10:41:03'),
(3,'Kanpur','Kanpur','Uttar Pradesh','Kanpur','INDIA','CURRENT_USER','2019-04-14 10:41:47','CURRENT_USER','2019-04-14 10:41:47'),
(4,'Allahabad','Allahabad','Uttar Pradesh','Allahabad','INDIA','CURRENT_USER','2019-04-14 10:42:20','CURRENT_USER','2019-04-14 10:42:20'),
(5,'Mughalsarai','Mughalsarai','Uttar Pradesh','Mughalsarai','INDIA','CURRENT_USER','2019-04-14 10:42:56','CURRENT_USER','2019-04-14 10:42:56'),
(6,'Gaya','Gaya','Bihar','Gaya','INDIA','CURRENT_USER','2019-04-14 10:43:16','CURRENT_USER','2019-04-14 10:43:16');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city_stop_master` */

insert  into `city_stop_master`(`CityStopId`,`CityId`,`LocationName`,`LocationAddress`,`LandMark`,`Lat`,`Lng`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'New Delhi Railway Station','New Delhi Railway Station','Metro Station',0.00,0.00,NULL,'2019-04-14 12:34:04',NULL,'2019-04-14 12:34:04'),
(2,1,'Akshardham Mandir','Akshardham Mandir','Akshardham Mandir',0.00,0.00,NULL,'2019-04-14 12:39:11',NULL,'2019-04-14 12:39:11'),
(3,1,'Phase 3 Fly Over Delhi','Phase 3 Fly Over Delhi','Phase III',0.00,0.00,NULL,'2019-04-14 12:40:06',NULL,'2019-04-14 12:40:06'),
(4,2,'Bus Stand Agra','Bus Stand Agra','Bus Stand Agra',0.00,0.00,NULL,'2019-04-14 12:40:48',NULL,'2019-04-14 12:40:48'),
(5,2,'Fly Over Agra','Fly Over Agra','Fly Over Agra',0.00,0.00,NULL,'2019-04-14 12:41:40',NULL,'2019-04-14 12:41:40'),
(6,3,'Railway Station Kanpur','Railway Station Kanpur','Railway Station',0.00,0.00,NULL,'2019-04-14 12:42:24',NULL,'2019-04-14 12:42:24'),
(7,3,'Bus Ada Kanpur','Bus Ada Kanpur','Bus Ada',0.00,0.00,NULL,'2019-04-14 12:49:41',NULL,'2019-04-14 12:49:41');

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

/*Table structure for table `route_city_stop_mapping` */

DROP TABLE IF EXISTS `route_city_stop_mapping`;

CREATE TABLE `route_city_stop_mapping` (
  `StopId` bigint(20) NOT NULL AUTO_INCREMENT,
  `RouteId` bigint(20) NOT NULL,
  `CityId` bigint(20) NOT NULL,
  `StopNumber` int(11) NOT NULL,
  `Distance` decimal(10,2) DEFAULT '0.00',
  `Duration` decimal(10,2) DEFAULT '0.00',
  `BaseFare` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`StopId`),
  KEY `FK_ROUTE_M` (`RouteId`),
  KEY `FK_CITY_M` (`CityId`),
  CONSTRAINT `FK_CITY_M` FOREIGN KEY (`CityId`) REFERENCES `city_master` (`CityId`),
  CONSTRAINT `FK_ROUTE_M` FOREIGN KEY (`RouteId`) REFERENCES `route_master` (`RouteId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `route_city_stop_mapping` */

insert  into `route_city_stop_mapping`(`StopId`,`RouteId`,`CityId`,`StopNumber`,`Distance`,`Duration`,`BaseFare`) values 
(1,1,1,1,0.00,0.00,0.00),
(2,1,2,2,200.00,200.00,120.00),
(3,1,3,3,220.00,300.00,220.00),
(4,1,4,4,150.00,220.00,350.00),
(5,1,5,5,300.00,400.00,450.00),
(6,1,6,6,500.00,500.00,430.00);

/*Table structure for table `route_master` */

DROP TABLE IF EXISTS `route_master`;

CREATE TABLE `route_master` (
  `RouteId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Source` varchar(255) DEFAULT NULL,
  `Destination` varchar(255) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RouteId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `route_master` */

insert  into `route_master`(`RouteId`,`Source`,`Destination`,`Description`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,'New Delhi','Gaya',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `schedule_master` */

DROP TABLE IF EXISTS `schedule_master`;

CREATE TABLE `schedule_master` (
  `ScheduleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusId` bigint(20) NOT NULL,
  `RouteId` bigint(20) NOT NULL,
  `DepartureDate` date DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `ArrivalDate` date DEFAULT NULL,
  `ArrivalTime` time DEFAULT NULL,
  `SleeperFare` decimal(10,0) DEFAULT NULL,
  `SeaterFare` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ScheduleId`),
  KEY `FK_ROUTER_MASTER` (`RouteId`),
  KEY `FK_BUS_SC` (`BusId`),
  CONSTRAINT `FK_BUS_SC` FOREIGN KEY (`BusId`) REFERENCES `bus_master` (`BusId`),
  CONSTRAINT `FK_ROUTER_MASTER` FOREIGN KEY (`RouteId`) REFERENCES `route_master` (`RouteId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `schedule_master` */

insert  into `schedule_master`(`ScheduleId`,`BusId`,`RouteId`,`DepartureDate`,`DepartureTime`,`ArrivalDate`,`ArrivalTime`,`SleeperFare`,`SeaterFare`) values 
(1,1,1,'2019-04-30','12:30:00',NULL,NULL,2,1);

/*Table structure for table `seat_master` */

DROP TABLE IF EXISTS `seat_master`;

CREATE TABLE `seat_master` (
  `SeatId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BusLayoutId` bigint(20) NOT NULL,
  `RowName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ColumnName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Width` decimal(10,0) NOT NULL DEFAULT '0',
  `Length` decimal(10,0) NOT NULL DEFAULT '0',
  `SeatType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SeatName` varchar(100) DEFAULT NULL,
  `SeatNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IsAvailable` tinyint(1) NOT NULL DEFAULT '0',
  `IsLadiesSeat` tinyint(1) NOT NULL DEFAULT '0',
  `IsMenSeat` tinyint(1) NOT NULL DEFAULT '0',
  `IsLowerBerth` tinyint(1) NOT NULL DEFAULT '0',
  `CreatedBy` varchar(100) DEFAULT NULL,
  `DateCreated` timestamp NULL DEFAULT NULL,
  `ModifiedBy` varchar(100) DEFAULT NULL,
  `DateModified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`SeatId`),
  KEY `FK_LAYOUT` (`BusLayoutId`),
  CONSTRAINT `FK_LAYOUT` FOREIGN KEY (`BusLayoutId`) REFERENCES `bus_layout_master` (`LayoutId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `seat_master` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_master` */

insert  into `user_master`(`UserId`,`RoleId`,`Name`,`Email`,`Address`,`PhoneNumber`,`PanNumber`,`Password`,`City`,`State`,`Country`,`IsLock`,`Attempt`,`IsActive`,`CreatedBy`,`DateCreated`,`ModifiedBy`,`DateModified`) values 
(1,1,'Satyam','satyamk328@gmail.com','New Ashok Nagar','8130787891','CBGPK4395B','123','DELHI','DELHI','INDIA',0,0,1,NULL,'2019-04-14 12:02:54',NULL,'2019-04-14 12:02:54'),
(2,1,'Tej Narayan Shahu','www.digitalbihar.com','Sharhaul Sector 18 Gurgaon','8800359490','CFFFF23FG','123','GURGAON','HARIYANA','INDIA',0,0,1,NULL,'2019-04-14 12:04:39',NULL,'2019-04-14 12:04:39');

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
