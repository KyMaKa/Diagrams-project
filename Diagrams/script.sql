DROP DATABASE IF EXISTS umls; 
CREATE DATABASE umls;
\c umls
CREATE TABLE Entity (
attribute INT PRIMARY KEY,
 attribute TEXT ,
 attribute bool ,
 attribute bool ,
 attribute INT ,
 CONSTRAINT fk_parent FOREIGN KEY (attribute) REFERENCES Entity1 (attribute)); 
CREATE TABLE Entity1 (
attribute INT PRIMARY KEY); 
CREATE TABLE Entity2 (
attribute INT PRIMARY KEY,
 attribute INT ,
 FOREIGN KEY (attribute) REFERENCES Entity (attribute) ON DELETE CASCADE); 
