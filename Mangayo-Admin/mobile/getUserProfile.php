<?php
    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db');


 	$email = trim($_GET["email"]);
    $userType= trim($_GET["userType"]);
 
    if($userType=="USER"){
           $result = mysqli_query($db, "SELECT * FROM users WHERE email ='$email'");
       }
    else if($userType=="MECHANIC"){
            $result = mysqli_query($db, "SELECT * FROM mechanic WHERE email ='$email' ");
        }
 
    if (mysqli_num_rows($result) > 0){
        $row = mysqli_fetch_object($result);
        if($userType=="USER") echo json_encode(array("user" => $row));
        else echo json_encode(array("mechanic" => $row));
    }else{
        echo json_encode(array("status" => "error","message" => "User not found"));
    }


?>