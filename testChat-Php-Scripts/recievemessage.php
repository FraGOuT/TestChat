<?php

//echo "Server recieved message - ".$_GET["username"]."<br>";
require "config.php";
require "validateToken.php";
$username = validateToken($_POST["access_token"]);

if($username == null){
	exit;
}

$sql = "SELECT * FROM usermessage where touser='".$username."'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    //echo "Message Sent Successfull";
    while($row = $result->fetch_assoc()){
		echo $row["fromuser"]."##".$row["message"]."####";
    }
} else {
    echo "0";
}

$sql = "DELETE FROM usermessage where touser='".$username."'";
if($conn->query($sql) === TRUE){
	//echo "Delete success";
}
else{
	//echo "ERROR deleting messages.";
}

$conn->close();
?>