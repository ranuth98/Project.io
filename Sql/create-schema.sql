create database courier_tracking_system;
-- ------------------------------------------------
-- Employee table 
-- ------------------------------------------------
CREATE  TABLE IF NOT EXISTS `courier_tracking_system`.`employee` (
  `em_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `em_user_name` VARCHAR(15) NOT NULL UNIQUE,
  `em_first_name` VARCHAR(50) NOT NULL ,
  `em_last_name` VARCHAR(50) NOT NULL ,
  `em_gender` VARCHAR(7) NOT NULL ,
  `em_email` VARCHAR(50) NULL ,
  `em_contact_number` DECIMAL(10) NOT NULL ,
  `em_password` VARCHAR(15) NOT NULL,
  `em_salary` DECIMAL(10,0) NOT NULL,
  `em_designation` VARCHAR(50) NOT NULL,
  `em_permanent_address` VARCHAR(100) NULL,
  `em_correspondence_address` VARCHAR(100) NOT NULL ,
  `em_login_type` CHAR(1) NOT NULL ,
  `em_active` char(1) NOT NULL ,
  PRIMARY KEY (`em_id`) ,
  UNIQUE INDEX `em_id_UNIQUE` (`em_id` ASC),
 INDEX `em_user_name_UNIQUE` (`em_user_name` ASC) )
ENGINE = InnoDB;
ALTER TABLE `courier_tracking_system`.`employee` AUTO_INCREMENT=1; 

-- ------------------------------------------------
-- User table
-- ------------------------------------------------
CREATE  TABLE IF NOT EXISTS `courier_tracking_system`.`user` (
  `us_id` BIGINT(10) NOT NULL AUTO_INCREMENT ,
  `us_first_name` VARCHAR(50) NOT NULL ,
  `us_last_name` VARCHAR(50) NULL ,
  `us_gender` VARCHAR(7) NOT NULL ,
  `us_email` VARCHAR(50) NULL ,
  `us_contact_number` DECIMAL(10) NOT NULL ,
  `us_user_name` VARCHAR(15) NOT NULL ,
  `us_password` VARCHAR(15) NOT NULL ,
`us_active` char(1) NOT NULL ,
  PRIMARY KEY (`us_id`) ,
  UNIQUE INDEX `us_user_name_UNIQUE` (`us_user_name` ASC) )
ENGINE = InnoDB;

-- ---------------------------------
-- package----------------
-- ---------------------------------
CREATE  TABLE IF NOT EXISTS `courier_tracking_system`.`package` (
  `pa_id` BIGINT(10) NOT NULL AUTO_INCREMENT ,
  `pa_user_name` VARCHAR(45) NOT NULL ,
  `pa_parcel_type` VARCHAR(45) NOT NULL ,
  `pa_book_date` DATE NOT NULL ,
  `pa_weight` FLOAT NOT NULL ,
  `pa_distance` INT(6) NOT NULL ,
  `pa_sender_address` VARCHAR(245) NOT NULL ,
  `pa_receiver_address` VARCHAR(245) NOT NULL ,
  `pa_cost` FLOAT NOT NULL ,
  `pa_warehouse_location` VARCHAR(45) NOT NULL ,
  `pa_status` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`pa_id`) ,
  INDEX `pa_user_name_idx` (`pa_user_name` ASC) ,
  CONSTRAINT `pa_user_name`
    FOREIGN KEY (`pa_user_name` )
    REFERENCES `courier_tracking_system`.`user` (`us_user_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8;

-- ------------------------------------------------
-- Quotation
-- ------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`quotation` (
                `qu_id` BIGINT(5) NOT NULL AUTO_INCREMENT ,
                `qu_distance` INT(5) UNIQUE NOT NULL ,
                `qu_price` DECIMAL(6,2) NOT NULL,
                PRIMARY KEY(`qu_id`))
ENGINE = InnoDB;
-- ------------------------------------------------
-- Parcel Type
-- ------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`parcel_type` (
                `pa_ty_id` BIGINT(5) NOT NULL AUTO_INCREMENT ,
                `pa_ty_name` VARCHAR(30) UNIQUE NOT NULL ,
                `pa_ty_price` DECIMAL(6,2) NOT NULL,
                PRIMARY KEY(`pa_ty_id`))
ENGINE = InnoDB;
-- ---------------------------------
-- warehouse table-------
-- ---------------------------------
CREATE  TABLE IF NOT EXISTS `courier_tracking_system`.`warehouse` (
  `wa_id` BIGINT(10) NOT NULL AUTO_INCREMENT ,
  `wa_name` VARCHAR(45) NOT NULL ,
  `wa_capacity` DECIMAL(10,0) NOT NULL ,
  `wa_location` VARCHAR(50) NOT NULL ,
  `wa_em_id` BIGINT(10) NOT NULL ,
  PRIMARY KEY (`wa_id`) ,
  INDEX `pk_manager_id_idx` (`wa_em_id` ASC) ,
  CONSTRAINT `pk_manager_id`
    FOREIGN KEY (`wa_em_id` )
    REFERENCES `courier_tracking_system`.`employee` (`em_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

-- ---------------------------------
-- consignment table----------------
-- ---------------------------------
CREATE  TABLE IF NOT EXISTS `courier_tracking_system`.`consignment` (
  `co_id` BIGINT(10) NOT NULL AUTO_INCREMENT ,
  `co_from_warehouse_id` BIGINT(10) NOT NULL ,
  `co_to_warehouse_id` BIGINT(10) NOT NULL ,
  `co_consignment_status` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`co_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8;
-- ----------------------------------
-- consignment list table -----
-- -------------------------------
CREATE  TABLE IF NOT EXISTS `courier_tracking_system`.`consignment_list` (
  `co_li_id` BIGINT(16) NOT NULL AUTO_INCREMENT ,
  `co_li_package_status` BOOLEAN NOT NULL ,
  `co_li_pa_id` BIGINT(10) NOT NULL ,
  `co_li_co_id` BIGINT(10) NOT NULL ,
  PRIMARY KEY (`co_li_id`) ,
  INDEX `fk_consignment_list_package1_idx` (`co_li_pa_id` ASC) ,
  INDEX `fk_consignment_list_consignment1_idx` (`co_li_co_id` ASC) ,
  CONSTRAINT `fk_consignment_list_package1`
    FOREIGN KEY (`co_li_pa_id` )
    REFERENCES `courier_tracking_system`.`package` (`pa_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consignment_list_consignment1`
    FOREIGN KEY (`co_li_co_id` )
    REFERENCES `courier_tracking_system`.`consignment` (`co_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE UNIQUE INDEX combined_unique_name ON consignment_list(co_li_pa_id, co_li_co_id);
-- ------------------------------------------------
-- Policy
-- ------------------------------------------------
CREATE TABLE IF NOT EXISTS `courier_tracking_system`.`policy` (
                `po_id` BIGINT(5) NOT NULL AUTO_INCREMENT ,
                `po_name` VARCHAR(500) NOT NULL ,
                PRIMARY KEY(`po_id`))
ENGINE = InnoDB;



-- ------------------------------------------------
-- data entries

-- ------------------------------------------------
use courier_tracking_system;

-- employee data
insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("admin","raghunath","S","Male",
"admin@cts.com",9876543210,"Sadmin@123",50000,"CEO","AdminAdd","AdminAdd",'a','y');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("admin1","tharini","T","Female",
"admin@cts.com",9876543210,"admin@123",30000,"Snr.Manager","AdminAdd","AdminAdd",'a','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff1","Ben10","CN","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff2","IronMan","Dc","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff3","Cap.America","Marvel","Male",
"staff@cts.com",9875646257,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff4","BalckWidow","Marvel","Female",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff5","Hulk","Marvel","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff6","Thor","Marvel","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff7","Batman","Dc","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff8","Spiderman","Dc","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff9","Loki","Marvel","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff10","Flash","Dc","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');

insert into employee(em_user_name ,em_first_name, em_last_name, 
em_gender,em_email, em_contact_number, em_password, em_salary,
 em_designation,em_permanent_address, em_correspondence_address,
 em_login_type, em_active) values("staff12","Cyborg","DC","Male",
"staff@cts.com",9876543210,"staff123",0,"Delivery Boy","staffAdd","staffAdd",'s','w');




-- user details ------

insert into user (us_user_name,us_first_name,us_last_name,
us_gender,us_email,us_contact_number,us_password,us_active)
values("Ranuth","Raghu","S","Male",
"user@cts.com",9876543210,"user123",'y');
insert into user (us_user_name,us_first_name,us_last_name,
us_gender,us_email,us_contact_number,us_password,us_active)
values("Likama","Kamali","B","Female",
"user@cts.com",9876543210,"user123",'y');
insert into user (us_user_name,us_first_name,us_last_name,
us_gender,us_email,us_contact_number,us_password,us_active)values("Siruthai","SreeNithi","S.G","Female",
"user@cts.com",9876543210,"user123",'y');
INSERT INTO `courier_tracking_system`.`user` (`us_id`, `us_first_name`, `us_last_name`, `us_gender`, `us_email`,
 `us_contact_number`, `us_user_name`, `us_password`, `us_active`)
 VALUES ('2', 'Akila', 'akila', 'Female', 'user@cts.com', '9876543210', 'laki', 'user123', 'y');

-- ------------------------------------------------
-- ParcelType insertion
-- ------------------------------------------------

INSERT INTO parcel_type(`pa_ty_name`,`pa_ty_price`) VALUES('Standard Parcel',10);
INSERT INTO parcel_type(`pa_ty_name`,`pa_ty_price`) VALUES('Express Parcel',20);
INSERT INTO parcel_type(`pa_ty_name`,`pa_ty_price`) VALUES('Super Fast Parcel',30);
INSERT INTO parcel_type(`pa_ty_name`,`pa_ty_price`) VALUES('Special Delivery',40);


-- ------------------------------------------------
-- Quotation insertion
-- ------------------------------------------------
INSERT INTO quotation(`qu_distance`,`qu_price`) VALUES(10,10);
INSERT INTO quotation(`qu_distance`,`qu_price`) VALUES(20,20);
INSERT INTO quotation(`qu_distance`,`qu_price`) VALUES(35,35);
INSERT INTO quotation(`qu_distance`,`qu_price`) VALUES(50,50);
INSERT INTO quotation(`qu_distance`,`qu_price`) VALUES(75,70);
-- ------------------------------------------------
-- Queries for warehouse------------
-- ------------------------------------------------
INSERT into warehouse values(1,'Raja',20,'Chennai',15);
INSERT into warehouse values(2,'Ganga',20,'Kolkata',16);
INSERT into warehouse values(3,'Cauvery',20,'Coimbatore',17);
INSERT into warehouse values(4,'Yamuna',20,'Delhi',18);
INSERT into warehouse values(5,'Mahanadhi',20,'Mumbai',19);
INSERT into warehouse values(6,'Krishna',20,'Bangalore',20);
INSERT into warehouse values(7,'Yamuna',20,'Cochin',21);
INSERT into warehouse values(8,'Mahanadhi',20,'Hyderabad',22);
INSERT into warehouse values(9,'Krishna',20,'Salem',23);

-- ------------------------------------------------
-- Queries for consignment----------
-- ------------------------------------------------
insert into consignment values(100,1,9,'In Transit');
insert into consignment values(101,1,9,'In Transit');
insert into consignment values(102,9,3,'In Transit');
insert into consignment values(103,9,3,'Booked');
insert into consignment values(104,3,6,'In Transit');
insert into consignment values(105,3,7,'Booked');
-- ------------------------------------------------
-- Package insertion
-- ------------------------------------------------
-- --- 1st consignment 
 INSERT INTO `courier_tracking_system`.`package` (`pa_id`, 
`pa_book_date`, `pa_weight`, `pa_cost`, `pa_sender_address`, 
`pa_receiver_address`, `pa_status`, `pa_user_name`, `pa_parcel_type`, 
`pa_distance`, `pa_warehouse_location`) VALUES ('100', '2020-01-30', '12', '123', '3,Indira Nagar, Chennai', 'Kuniyamuthur, Covai', 'In Transit', 'Ranuth', 'Fast Delivery', '23', 'Chennai'); 
-- 101
 INSERT INTO `courier_tracking_system`.`package` ( 
`pa_book_date`, `pa_weight`, `pa_cost`, `pa_sender_address`, 
`pa_receiver_address`, `pa_status`, `pa_user_name`, `pa_parcel_type`, 
`pa_distance`, `pa_warehouse_location`) VALUES ( '2022-10-09', '12', '150', 'Nungapakkam, Chennai', 'Saravanampatti, kovai', 'In Transit', 'Laki', 'Fast Delivery', '23', 'Chennai'); 
-- 102
 INSERT INTO `courier_tracking_system`.`package` ( 
`pa_book_date`, `pa_weight`, `pa_cost`, `pa_sender_address`, 
`pa_receiver_address`, `pa_status`, `pa_user_name`, `pa_parcel_type`, 
`pa_distance`, `pa_warehouse_location`) VALUES ( '2020-10-09', '12', '200', 'West Tambaram, Chennai', 'Rasipuram, Salem', 'In Transit', 'Likama', 'Standard Delivery', '23', 'Chennai'); 
-- 103
 INSERT INTO `courier_tracking_system`.`package` (
`pa_book_date`, `pa_weight`, `pa_cost`, `pa_sender_address`, 
`pa_receiver_address`, `pa_status`, `pa_user_name`, `pa_parcel_type`, 
`pa_distance`, `pa_warehouse_location`) VALUES ('2020-10-10', '20', '300', 'East Tambaram, Chennai', 'New bus stand, Salem', 'In Transit', 'Siruthai', 'Special Delivery', '25', 'Chennai'); 
-- 104
 INSERT INTO `courier_tracking_system`.`package` ( 
`pa_book_date`, `pa_weight`, `pa_cost`, `pa_sender_address`, 
`pa_receiver_address`, `pa_status`, `pa_user_name`, `pa_parcel_type`, 
`pa_distance`, `pa_warehouse_location`) VALUES ( '2020-10-11', '30', '500', 'Egmore, Chennai', ' Ukkadam, Coimbatore', 'In Transit', 'beku', 'Express Delivery', '15', 'Chennai'); 

-- ------------------------------------------------
-- Consignment list queries
-- ------------------------------------------------
INSERT INTO  `courier_tracking_system`.`consignment_list` (co_li_pa_id, co_li_co_id, co_li_package_status) 
values( '100','1','1');
INSERT INTO  `courier_tracking_system`.`consignment_list` (co_li_pa_id, co_li_co_id, co_li_package_status) 
values( '101','1','1');
INSERT INTO  `courier_tracking_system`.`consignment_list` (co_li_pa_id, co_li_co_id, co_li_package_status) 
values( '102','1','1');
INSERT INTO  `courier_tracking_system`.`consignment_list` (co_li_pa_id, co_li_co_id, co_li_package_status) 
values( '103','','1');
INSERT INTO  `courier_tracking_system`.`consignment_list` (co_li_pa_id, co_li_co_id, co_li_package_status) 
values( '104','2','1');

-- ------------------------------------------------
-- Policy insertion
-- ------------------------------------------------

INSERT INTO policy(po_name) 
value(
'1.
2.
3.
4.
5.);
