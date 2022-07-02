<?php
    $servername = 'localhost';
    $username = 'root';
    $password = '';
    $port = '3306';
    $databaseName = 'mangayo_db';

    $connection = mysqli_connect($servername, $username, $password, $databaseName, $port);

    // Check connection
    if (!$connection) {
    die("Connection failed" . mysqli_connect_error());
    }
?>