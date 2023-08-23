CREATE DATABASE  IF NOT EXISTS `gogo` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gogo`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: gogo
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productid` varchar(45) NOT NULL,
  `category` varchar(90) DEFAULT NULL,
  `productname` varchar(90) DEFAULT NULL,
  `description` varchar(120) DEFAULT NULL,
  `sales` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('456','','','',NULL,NULL,'',NULL,'',''),('5','','','',NULL,NULL,'',NULL,'',''),('5,5','','','',1,NULL,'',NULL,'',''),('gogop23000P','','','',NULL,NULL,'',NULL,'',''),('gogop23000Q','','','',NULL,NULL,'',NULL,'',''),('gogop23000T','1','1','1',1,1,'1',1,'1','1'),('gogop2308210001','purse','Aglea','手提包無背帶',1,5280,'black',30,'x','./images/imgf01.jpg'),('gogop2308210002','shoulder bag','Thalia','多層次夾層收納',1,3980,'brown',25,'x','./images/imgf22.jpg'),('gogop2308210003','purse','Canvas bag','編織可調整背帶',1,3680,'skin color',35,'x','./images/imgf36.jpg'),('gogop2308210004,456','bag','Urbano','直式托特包',1,40889,'dark brown',20,'x','./images/img143.jpg'),('gogop2308210004,gogop2308210004,456','bag','Urbano','直式托特包',1,40889,'dark brown',20,'x','./images/img143.jpg'),('gogop2308210005','backpack','Vainno','商務筆電後背包',1,28431,'black',38,'x','./images/img319.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-23 18:27:38
