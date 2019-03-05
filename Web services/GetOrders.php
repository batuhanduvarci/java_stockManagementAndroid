<?php

include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();

	$json = $auth->getOrders();

	echo json_encode($json);