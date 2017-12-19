<?php

require "config.php";

$sql = "Select * from users where username='{$_GET["username"]}' and password='{$_GET["password"]}'";

$result = $conn->query($sql);

if($result->num_rows >= 1){
	//this means that the username and password are correct.
	//Now we will be generating a token for all further acccess for that client.
	
	include "generateToken.php";
	$token = generateToken($_GET["username"]);

	//Query to update the user's database entry with the token.
	$update_token_sql = "UPDATE `users` SET `user_access_token`='{$token}' WHERE `username`='{$_GET["username"]}'";
	
	if($conn->query($update_token_sql) === TRUE){
		//Token Update Successfull
		echo $token;
	}
	else{
		//Token Update unsuccessfull
		echo "0";
	}
}
else{
	//Username and password are not correct.
	echo "0";
}

?>