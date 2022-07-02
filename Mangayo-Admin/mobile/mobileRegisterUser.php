<?php
	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;$message;

	
	$Fname = trim($_GET['Fname']);
	$Lname = trim($_GET['Lname']);
	$email = trim($_GET['email']);
	$phone_num = trim($_GET['phone_num']);
	$password = trim($_GET['password']);
	$address = trim($_GET['address']);
	$userType = trim($_GET['userType']);
	$specialty = trim($_GET['specialty']);

	if($userType == 'VEHICLE OWNER'){
		$ok = mysqli_query($db, "INSERT INTO users (Fname, Lname, email, address,phone_num, password) VALUES ('$Fname','$Lname','$email','$address','$phone_num',
			'$password')");
	}else{
		$ok = mysqli_query($db, "INSERT INTO mechanic (Fname, Lname, email, phone_num, password, address, specialty) VALUES ('$Fname','$Lname','$email','$phone_num','$password','$address','$specialty')");
	}

	$message = ($ok)?"SUCCESSFULLY ADDED":"ADDING DATA FAILED";
	echo $message;

?>