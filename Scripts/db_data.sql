-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: acme-dancer
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Dumping data for table `academia`
--

LOCK TABLES `academia` WRITE;
/*!40000 ALTER TABLE `academia` DISABLE KEYS */;
INSERT INTO `academia` VALUES (86,0,'Perez','20002','fernadito12@gmail.com','Fernando','987654321',82,'los chichos'),(87,0,'Perez','20000','Pepito@gmail.com','Juan Alberto','982354321',83,'los Pepos');
/*!40000 ALTER TABLE `academia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `academia_curso`
--

LOCK TABLES `academia_curso` WRITE;
/*!40000 ALTER TABLE `academia_curso` DISABLE KEYS */;
INSERT INTO `academia_curso` VALUES (86,102),(87,103),(87,104);
/*!40000 ALTER TABLE `academia_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `academia_tutorial`
--

LOCK TABLES `academia_tutorial` WRITE;
/*!40000 ALTER TABLE `academia_tutorial` DISABLE KEYS */;
INSERT INTO `academia_tutorial` VALUES (86,90),(86,91);
/*!40000 ALTER TABLE `academia_tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `actor_actor`
--

LOCK TABLES `actor_actor` WRITE;
/*!40000 ALTER TABLE `actor_actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `actor_comentario`
--

LOCK TABLES `actor_comentario` WRITE;
/*!40000 ALTER TABLE `actor_comentario` DISABLE KEYS */;
INSERT INTO `actor_comentario` VALUES (85,89);
/*!40000 ALTER TABLE `actor_comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (85,0,'Medina','20001','antonio12@gmail.com','Antonio','123456789',81);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (88,0,'Gonzalez','19200','fal12@gmail.com','Alex','321456789',84,2025,256,'XML',6,'4444333322221111','Juan perez');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (89,0,'2024-03-05 12:00:00','Este es el texto de referencia');
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (102,0,'LUNES','2025-10-03','2024-10-03',12,20,'PRINCIPIANTE','Barchata para Principiantes',92),(103,0,'MARTES','2025-10-03','2024-10-03',12,20,'AVANZADO','Baile Avanzado',95),(104,0,'MARTES','2025-10-03','2024-10-03',12,20,'INTERMEDIO','Baile de Prueba',95);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estilo`
--

LOCK TABLES `estilo` WRITE;
/*!40000 ALTER TABLE `estilo` DISABLE KEYS */;
INSERT INTO `estilo` VALUES (92,0,'estilo romano','Bachata'),(93,0,'Baile social latino con raíces cubanas, conocido por sus movimientos rápidos y giros.','Salsa'),(94,0,'Baile cubano con ritmo rápido, similar a la salsa pero con pausas en el paso.','Mambo'),(95,0,'Baile cubano festivo y alegre, con pasos rápidos y movimientos de cadera.','Pachanga'),(96,0,'Baile español de influencia taurina, con pasos firmes y estilo dramático','Pasodoble'),(97,0,'Baile folclórico andaluz, alegre y rítmico, con palmas y giros.','Sevillana'),(98,0,'Baile argentino apasionado y elegante, conocido por su abrazo cerrado y pasos precisos.','Tango'),(99,0,'Baile cubano animado con un ritmo sincopado distintivo y movimientos de cadera.','Chachachá'),(100,0,'Baile afro-cubano lento y sensual, con énfasis en movimientos de cadera.','Rumba'),(101,0,'Baile angoleño sensual y lento, caracterizado por un abrazo íntimo y movimientos suaves.','Kizomba');
/*!40000 ALTER TABLE `estilo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estilo_imagenes`
--

LOCK TABLES `estilo_imagenes` WRITE;
/*!40000 ALTER TABLE `estilo_imagenes` DISABLE KEYS */;
INSERT INTO `estilo_imagenes` VALUES (92,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/PNG_transparency_demonstration_1.png/640px-PNG_transparency_demonstration_1.png'),(93,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/PNG_transparency_demonstration_1.png/640px-PNG_transparency_demonstration_1.png'),(93,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/PNG_transparency_demonstration_1.png/640px-PNG_transparency_demonstration_1.png'),(94,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/PNG_transparency_demonstration_1.png/640px-PNG_transparency_demonstration_1.png');
/*!40000 ALTER TABLE `estilo_imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estilo_videos`
--

LOCK TABLES `estilo_videos` WRITE;
/*!40000 ALTER TABLE `estilo_videos` DISABLE KEYS */;
INSERT INTO `estilo_videos` VALUES (92,'https://www.youtube.com/embed/K7b4DjtP_hI?si=wqZ7vt_yYSOr9vLP'),(94,'https://www.youtube.com/embed/K7b4DjtP_hI?si=wqZ7vt_yYSOr9vLP');
/*!40000 ALTER TABLE `estilo_videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (105,0,'PENDIENTE','2024-03-05 16:23:00',88,102),(106,0,'ACEPTADO','2024-03-05 16:23:00',88,103);
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
INSERT INTO `tutorial` VALUES (90,0,'tutorial que te muestra lo que debes saber para bailar','Aprender a bailar','https://www.youtube.com/watch?v=lGCo8ILvauI',0),(91,0,'tutorial que te muestra lo que debes saber para bailar','Aprender Chachacha con Juan','https://www.youtube.com/watch?v=lGCo8ILvauI',50);
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (81,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(82,0,'ce4c50f1b5f38d6888f89f33268f75a7','academia1'),(83,0,'cc09f0bf5c14afa1f29a48121286ba25','academia2'),(84,0,'c0eecfa0c829380ba0f0ac67a8d0db7b','alumno2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (81,'ADMIN'),(82,'ACADEMIA'),(83,'ACADEMIA'),(84,'ALUMNO');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-05 16:20:13
