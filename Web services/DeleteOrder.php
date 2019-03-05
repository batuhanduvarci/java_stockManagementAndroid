<?php

include_once 'Authentication.php';
use user\Authentication;

	$auth = new Authentication();
	$auth->prepareDeleteOrder($_POST);
	$deleteOrder = $auth->deleteOrder();
	$orderStatus = $auth->deleteOrderControl();

	if($orderStatus){
		$json['success'] = 1;
		$json['message'] = 'Sipariş iptal edildi';
	}else{
		$json['success'] = 0;
		$json['message'] = 'İptal edilirken hata oluştu!';
	}

echo json_encode($json);