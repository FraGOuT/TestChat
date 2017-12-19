<?php
	//header("Content-Disposition: attachment; name=\"file\" filename=\"" . "uploads/hello.txt" . "\"");
	//echo "File requested = "."hello.txt"."\n";
	$file_path = $_POST["file"];//"uploads/hello.txt";
	//echo $file_path;
	$content = file_get_contents($file_path);
	echo $content;
	
	unlink($file_path);
	
 ?>