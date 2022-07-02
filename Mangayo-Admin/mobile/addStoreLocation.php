<?php
	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;

	
	$latitude = trim($_GET['latitude']);
	$longitude = trim($_GET['longitude']);
	$address = trim($_GET['address']);
	$mechanic_id = trim($_GET['mechanic_id']);
	
	// $hashPassword = password_hash($password, PASSWORD_DEFAULT);
	$ok = mysqli_query($db, "INSERT INTO mechanic_location ( latitude, longitude, address,mechanic_id) VALUES ('$latitude','$longitude','$address','$mechanic_id')");


	$message = ($ok)?"SUCCESSFULLY ADDED STORE":"ADDING DATA FAILED";
	echo $message;


?>				