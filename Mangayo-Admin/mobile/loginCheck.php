<?php 

	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;
	$username = trim($_GET['username']);
	$password = trim($_GET['password']);
	$userType = trim($_GET['userType']);
	
	if($userType != "MECHANIC"){
		$result = mysqli_query($db, "SELECT * FROM users WHERE email ='$username' AND password ='$password' ");
		}
	else $result = mysqli_query($db, "SELECT * FROM mechanic WHERE email ='$username' AND password ='$password' ");

	$message = (mysqli_num_rows($result) == 1)?"LOGIN ACCEPTED":"LOGIN FAILED";
	echo $message;

 ?>