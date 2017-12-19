<?php

//Format of the token is ---- [yday][mon][year][username][minutes][mday][seconds][month][hours]

function generateToken($username){
	
	$mydate = getdate();
	
	$token = $mydate["yday"].$mydate["mon"].$mydate["year"].$username.$mydate["minutes"].$mydate["mday"].$mydate["seconds"].$mydate["month"].$mydate["hours"];
	
	return $token;
}

//echo generateToken("himanshu");


?>