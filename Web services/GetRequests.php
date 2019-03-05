<?php

include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();

	$json = $auth->getRequests();

	echo json_encode($json);