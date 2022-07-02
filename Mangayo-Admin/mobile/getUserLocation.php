<?php

    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db'); $ok;
    $user_id = trim($_GET['user_id']);

	$result = mysqli_query($db, "SELECT * FROM user_location WHERE user_id = '$user_id'");

    if (mysqli_num_rows($result) > 0){
        while($row = mysqli_fetch_assoc($result)) {
            $rows[] = $row;
        }
         echo json_encode(array("user_location" =>$rows)); 

    }else{
         echo json_encode(array("user_location" => "No Transaction(s) found"));
    }exit();

?>