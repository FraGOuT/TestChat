<?php

require "config.php";


require "validateToken.php";
$fromuser = validateToken($_POST["access_token"]);


if($fromuser != null){
	$sql = "INSERT INTO usermessage (fromuser, touser, message) VALUES ('".$fromuser."','".$_POST["touser"]."','".$_POST["message"]."')";

	if ($conn->query($sql) === TRUE) {
		echo "1";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
	//$sql1 = "00241df61dd8  Ce3FGxLpdjpWUDYR"
	$conn->close();	
}
else{
	echo "Unauthorized access";
}

?>