<?php
	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;

	
	$latitude = trim($_GET['latitude']);
	$longitude = trim($_GET['longitude']);
	$address = trim($_GET['address']);
	$mechanic_id = trim($_GET['mechanic_id']);
	$email = trim($_GET['email']);
	
	$result =  mysqli_query($db, " SELECT * FROM mechanics WHERE email ='$mechanic_id'");


	$ok = mysqli_query($db, "INSERT INTO mechanic_location ( latitude, longitude, address, result) 
			VALUES ('$latitude','$longitude','$address','$mechanic_id') ");
	
	$message = ($ok)?"SUCCESSFULLY ADDED LOCATION":"FAILED TO ADD LOCATION";
	echo $message;
	


?>				