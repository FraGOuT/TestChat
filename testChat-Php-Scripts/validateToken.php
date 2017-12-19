<?php

function validateToken($token){
	require "config.php";
	
	$sql = "SELECT * FROM `users` WHERE `user_access_token` like '{$token}'";
	
	//echo $sql+"<br>";
	
	$result = $conn->query($sql);
	
	if($result->num_rows == 1){
		
		$row = $result->fetch_assoc();
		
		return $row["username"];
	}
	else{
		return null;
	}
}

//validateToken("343122016himanshu21935December21");

?>