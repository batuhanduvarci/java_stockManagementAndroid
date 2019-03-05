<?php
include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();

	$json = $auth->getLogs();

	echo json_encode($json);