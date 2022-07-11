<?php

    $db = mysqli_connect('localhost', 'root', "", 'mangayo_db');


 	$mechanic_id = trim($_GET["mechanic_id"]);
    $userType= trim($_GET["userType"]);
 
    if($userType=="USER"){
           $result = mysqli_query($db, "SELECT * FROM users WHERE user_id ='$user_id'");
       }
    else if($userType=="MECHANIC"){
            $result = mysqli_query($db, "SELECT * FROM mechanic WHERE mechanic_id ='$mechanic_id' ");
        }
 
    if (mysqli_num_rows($result) > 0){
        if($userType=="USER"){
            while($row = mysqli_fetch_assoc($result)) {
                $rows[] = $row;
            }
             echo json_encode(array("user" => $rows));
         }
        else {
            while($row = mysqli_fetch_assoc($result)) {
                $rows[] = $row;
            }
                echo json_encode(array("mechanic" => $rows));
            }
    }else{
        echo json_encode(array("status" => "error","message" => "User not found"));
    }


?>
