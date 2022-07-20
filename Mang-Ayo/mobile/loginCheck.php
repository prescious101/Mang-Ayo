<?php 

if ($_SERVER['REQUEST_METHOD']=='POST') {

	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;
	$username = trim($_POST['username']);
	$password = trim($_POST['password']);
	$userType = trim($_POST['userType']);
	
	if($userType != "MECHANIC"){
		$result = mysqli_query($db, "SELECT * FROM users WHERE email ='$username' AND password ='$password' ");
		}
	else $result = mysqli_query($db, "SELECT * FROM mechanic WHERE email ='$username' AND password ='$password' ");

	$message = (mysqli_num_rows($result) == 1)?"success":"failed";
	echo $message;
	}
 ?>