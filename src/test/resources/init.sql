CREATE TABLE IF NOT EXISTS car(id_car int(11) NOT NULL AUTO_INCREMENT, brandCar int(11), numberCar char(8), shassiCar char(50), yearCar int(11));
CREATE TABLE IF NOT EXISTS brand (brandID int(11) NOT NULL AUTO_INCREMENT, brandName char(50));
CREATE TABLE IF NOT EXISTS city (cityID int(11) NOT NULL AUTO_INCREMENT, cityName char(50));
CREATE TABLE IF NOT EXISTS clients (id int(10) NOT NULL AUTO_INCREMENT, Name varchar(50), OKPO int(8), Adress varchar(50), Phone char(50));
CREATE TABLE IF NOT EXISTS expenses (expensesID int(11) NOT NULL AUTO_INCREMENT, expensesName char(50));
CREATE TABLE IF NOT EXISTS route (routeID int(11)NOT NULL AUTO_INCREMENT, routeTitle char(100), departureCity char(50), arrivalCity char(50), distance int(11));
CREATE TABLE IF NOT EXISTS routeExpenses (routeExpencesId int(11)NOT NULL AUTO_INCREMENT, routeID int(11), expensesID int(11), expensesCoast float);