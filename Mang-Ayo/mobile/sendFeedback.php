<?php

	$db = mysqli_connect('localhost','root','','mangayo_db');$ok;

	
	$mechanic_id = trim($_GET['mechanic_id']);
	$user_id = trim($_GET['user_id']);
	$feedback_date = trim($_GET['feedback_date']);
	$feedback_description = trim($_GET['feedback_description']);
	$feedback_score = trim($_GET['feedback_score']);

	$sql = "INSERT INTO feedback (feedback_description, feedback_score, feedback_date, user_id	, mechanic_id) VALUES ('$feedback_description','$feedback_score','$feedback_date','$user_id','$mechanic_id')";
	
	echo $sql;
	if($mechanic_id!=null && $user_id!=null){
		$ok = mysqli_query($db, $sql);
	}

	$message = ($ok)?"SUCCESSFULLY ADDED":"ADDING DATA FAILED";
	
	echo $message;
?>