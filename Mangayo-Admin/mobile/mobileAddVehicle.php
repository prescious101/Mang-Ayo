<?php

	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;

	
	$vehicle_name = trim($_GET['name']);
	$vehicle_type = trim($_GET['type']);
	$registration_num = trim($_GET['regNum']);
	$vehicle_model = trim($_GET['model']);
	$chassis_num = trim($_GET['chassis']);
	$fuel_type = trim($_GET['fuel']);

	$ok = mysqli_query($db, "INSERT INTO vehicle (vehicle_name, vehicle_type, registration_num, vehicle_model, chassis_num, fuel_type) 
		VALUES ('$vehicle_name','$vehicle_type','$registration_num','$vehicle_model','$chassis_num','$fuel_type')");

	$message = ($ok)?"SUCCESSFULLY ADDED":"ADDING DATA FAILED";
	echo $message;



?>