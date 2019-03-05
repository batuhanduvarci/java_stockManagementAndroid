<?php

namespace user;
 
 
class Authentication
{
    private $username2 = "";
    private $password2 = "";
    private $tickettype = "";
    private $ticketinfo = "";
    private $ticketid = "";

    private $ID = "";
    private $URUN_KODU = "";
    private $URUN_ADI = "";
    private $URETIM_TARIHI = "";
    private $SON_TUKETIM_TARIHI = "";
    private $ADET = "";
    private $BIRIM_FIYAT = "";
    private $DURUM = "";
    private $KAYIT = "";
    private $TARIH = "";
    private $SIRKET_ADI = "";
    private $AD = "";
    private $SOYAD = "";
    private $E_POSTA = "";
    private $KULLANICI_ADI = "";
    private $SIFRE = "";
    private $ID2 = "";


 
    private $DB_CONNECTION;
    private $servername = "localhost";
    private $username = "root";
    private $password = "";
    private $dbname = "test";
 
    function __construct()
    {
        $this->DB_CONNECTION = mysqli_connect($this->servername, $this->username,
            $this->password, $this->dbname);
    }
 
    public function prepare($data)
    {
        if (array_key_exists('KULLANICI_ADI', $data))
            $this->username2 = $data['KULLANICI_ADI'];
 
        if (array_key_exists('SIFRE', $data))
            $this->password2 = $data['SIFRE'];

       /* if(array_key_exists('usertype', $data))
            $this->usertype = $data['usertype']; */
 
    }

    public function prepareProduct($data){
        if(array_key_exists('URUN_KODU', $data))
            $this->URUN_KODU = $data['URUN_KODU'];

        if(array_key_exists('URUN_ADI', $data))
            $this->URUN_ADI = $data['URUN_ADI'];

        if(array_key_exists('URETIM_TARIHI', $data))
            $this->URETIM_TARIHI = $data['URETIM_TARIHI'];

        if(array_key_exists('SON_TUKETIM_TARIHI', $data))
            $this->SON_TUKETIM_TARIHI = $data['SON_TUKETIM_TARIHI'];

        if(array_key_exists('ADET', $data))
            $this->ADET = $data['ADET'];

        if(array_key_exists('BIRIM_FIYAT', $data))
            $this->BIRIM_FIYAT = $data['BIRIM_FIYAT'];
    }

    public function prepareDeleteProduct($data){
        if(array_key_exists('ID', $data))
            $this->ID = $data['ID'];
    }

    public function prepareUpdateProduct($data){
        if(array_key_exists('URUN_KODU', $data))
            $this->URUN_KODU = $data['URUN_KODU'];
    }

    public function prepareOrder($data){
        if(array_key_exists('URUN_KODU', $data))
            $this->URUN_KODU = $data['URUN_KODU'];

        if(array_key_exists('URUN_ADI', $data))
            $this->URUN_ADI = $data['URUN_ADI'];

        if(array_key_exists('ADET', $data))
            $this->ADET = $data['ADET'];

        if(array_key_exists('BIRIM_FIYAT', $data))
            $this->BIRIM_FIYAT = $data['BIRIM_FIYAT'];

        if(array_key_exists('DURUM', $data))
            $this->DURUM = $data['DURUM'];
    }

    public function prepareDeleteOrder($data){
        if(array_key_exists('ID', $data))
            $this->ID = $data['ID'];
    }

    public function prepareAccept($data){
        if(array_key_exists('ID', $data))
            $this->ID = $data['ID'];

        if(array_key_exists('SIRKET_ADI', $data))
            $this->SIRKET_ADI = $data['SIRKET_ADI'];

        if(array_key_exists('AD', $data))
            $this->AD = $data['AD'];

        if(array_key_exists('SOYAD', $data))
            $this->SOYAD = $data['SOYAD'];

        if(array_key_exists('E_POSTA', $data))
            $this->E_POSTA = $data['E_POSTA'];

        if(array_key_exists('KULLANICI_ADI', $data))
            $this->KULLANICI_ADI = $data['KULLANICI_ADI'];

        if(array_key_exists('SIFRE', $data))
            $this->SIFRE = $data['SIFRE'];
    }

    public function prepareDecline($data){
        if(array_key_exists('ID2', $data))
            $this->ID2 = $data['ID2'];
    }
 
    function isUserExisted() {
        $sql = "SELECT 'KULLANICI_ADI' FROM `users` WHERE KULLANICI_ADI = '". $this->username2."' ";
 
        $result = mysqli_query($this->DB_CONNECTION, $sql);
 
        if(mysqli_num_rows($result) > 0) {
            return true;
        }else {
            return false;
        }
    }
 
    function isUserValidToLogin() {
        $sql = "SELECT * FROM users WHERE KULLANICI_ADI = '".$this->username2."' AND SIFRE = '".$this->password2."'";
 
        $result = mysqli_query($this->DB_CONNECTION, $sql);
 
        if(mysqli_num_rows($result) > 0) {
            return true;
        }else {
            return false;
        }
    }

    function getStock(){
        $sql = "SELECT * FROM products ORDER BY ID DESC";
        $response = array();
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        while($value = mysqli_fetch_array($result)){
            $products = array(
                'ID' => $value['ID'],
                'URUN_KODU' => $value['URUN_KODU'],
                'URUN_ADI' => $value['URUN_ADI'],
                'URETIM_TARIHI' => $value['URETIM_TARIHI'],
                'SON_TUKETIM_TARIHI' => $value['SON_TUKETIM_TARIHI'],
                'ADET' => $value['ADET'],
                'BIRIM_FIYAT' => $value['BIRIM_FIYAT']    
                );
            array_push($response, $products);
        }

        return $response;

    }

    function getDecreasing(){
        $sql = "SELECT * FROM products WHERE ADET <= 10 ORDER BY ID DESC";
        $response = array();
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        while($value = mysqli_fetch_array($result)){
            $products = array(
                'ID' => $value['ID'],
                'URUN_KODU' => $value['URUN_KODU'],
                'URUN_ADI' => $value['URUN_ADI'],
                'URETIM_TARIHI' => $value['URETIM_TARIHI'],
                'SON_TUKETIM_TARIHI' => $value['SON_TUKETIM_TARIHI'],
                'ADET' => $value['ADET'],
                'BIRIM_FIYAT' => $value['BIRIM_FIYAT']    
                );
            array_push($response, $products);
        }

        return $response;
    }

    function getOrders(){
        $sql = "SELECT * FROM orders ORDER BY ID DESC";
        $response = array();
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        while($value = mysqli_fetch_array($result)){
            $products = array(
                'ID' => $value['ID'],
                'URUN_KODU' => $value['URUN_KODU'],
                'URUN_ADI' => $value['URUN_ADI'],
                'ADET' => $value['ADET'],
                'BIRIM_FIYAT' => $value['BIRIM_FIYAT'],
                'DURUM' => $value['DURUM']    
                );
            array_push($response, $products);
        }
        return $response;
    }

    function getRequests(){
        $sql = "SELECT * FROM requests ORDER BY ID DESC";
        $response = array();
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        while($value = mysqli_fetch_array($result)){
            $products = array(
                'ID' => $value['ID'],
                'SIRKET_ADI' => $value['SIRKET_ADI'],
                'AD' => $value['AD'],
                'SOYAD' => $value['SOYAD'],
                'E_POSTA' => $value['E_POSTA'],
                'KULLANICI_ADI' => $value['KULLANICI_ADI'],
                'SIFRE' => $value['SIFRE'],
                'DURUM' => $value['DURUM']    
                );
            array_push($response, $products);
        }
        return $response;
    }

    function getLogs(){
        $sql = "SELECT * FROM test.logs ORDER BY ID DESC";
        $response = array();
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        while($value = mysqli_fetch_array($result)){
            $products = array(
                'ID' => $value['ID'],
                'URUN_KODU' => $value['URUN_KODU'],
                'URUN_ADI' => $value['URUN_ADI'],
                'URETIM_TARIHI' => $value['URETIM_TARIHI'],
                'SON_TUKETIM_TARIHI' => $value['SON_TUKETIM_TARIHI'],
                'ADET' => $value['ADET'],
                'BIRIM_FIYAT' => $value['BIRIM_FIYAT'],
                'KAYIT' => $value['KAYIT'],
                'TARIH' => $value['TARIH']
                );
            array_push($response, $products);
        }

        return $response;
    }

    function addProduct(){
        $sql = "INSERT INTO products (URUN_KODU,URUN_ADI,URETIM_TARIHI,SON_TUKETIM_TARIHI,ADET,BIRIM_FIYAT) VALUES('$this->URUN_KODU','$this->URUN_ADI','$this->URETIM_TARIHI','$this->SON_TUKETIM_TARIHI','$this->ADET','$this->BIRIM_FIYAT')";

        $result = mysqli_query($this->DB_CONNECTION,$sql);
    }

    function addProductControl(){
        $sql = "SELECT * FROM products WHERE URUN_KODU = '".$this->URUN_KODU."'";
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        if(mysqli_num_rows($result) > 0){
            return false;
        }else{
            return true;
        }
    }

    function deleteProduct(){
        $sql = "DELETE FROM products WHERE ID = '".$this->ID."'";
        $result = mysqli_query($this->DB_CONNECTION,$sql);

    }

    function deleteProductControl(){
        $sql = "SELECT * FROM products WHERE ID = '".$this->ID."'";
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        if(mysqli_num_rows($result) > 0){
            return false;
        }else{
            return true;
        }
    }

    function updateProduct(){
        $sql = "UPDATE products SET URUN_KODU = '".$this->URUN_KODU."', URUN_ADI = '".$this->URUN_ADI."', URETIM_TARIHI = '".$this->URETIM_TARIHI."', SON_TUKETIM_TARIHI = '".$this->SON_TUKETIM_TARIHI."', ADET = '".$this->ADET."', BIRIM_FIYAT = '".$this->BIRIM_FIYAT."' WHERE URUN_KODU = '".$this->URUN_KODU."' ";
        $result = mysqli_query($this->DB_CONNECTION,$sql);

    }

    function getUpdateProductInfo(){
        $sql = "SELECT URUN_KODU,URUN_ADI,URETIM_TARIHI,SON_TUKETIM_TARIHI,ADET,BIRIM_FIYAT FROM products WHERE URUN_KODU = '".$this->URUN_KODU."'";
        $result = mysqli_query($this->DB_CONNECTION,$sql);
        $response = array();

        while($value = mysqli_fetch_array($result)){
            $products = array(
                'URUN_KODU' => $value['URUN_KODU'],
                'URUN_ADI' => $value['URUN_ADI'],
                'URETIM_TARIHI' => $value['URETIM_TARIHI'],
                'SON_TUKETIM_TARIHI' => $value['SON_TUKETIM_TARIHI'],
                'ADET' => $value['ADET'],
                'BIRIM_FIYAT' => $value['BIRIM_FIYAT']    
                );
            array_push($response, $products);
        }

        return $response;

    }

    function orderProduct(){
        $sql = "INSERT INTO orders (URUN_KODU,URUN_ADI,ADET,BIRIM_FIYAT,DURUM) VALUES ('$this->URUN_KODU','$this->URUN_ADI','$this->ADET','$this->BIRIM_FIYAT','$this->DURUM')";
        $result = mysqli_query($this->DB_CONNECTION,$sql);

    }

    function deleteOrder(){
        $sql = "DELETE FROM orders WHERE ID = '".$this->ID."'";
        $result = mysqli_query($this->DB_CONNECTION,$sql);
    }

    function deleteOrderControl(){
        $sql = "SELECT * FROM orders WHERE ID = '".$this->ID."'";
        $result = mysqli_query($this->DB_CONNECTION,$sql);

        if(mysqli_num_rows($result) > 0){
            return false;
        }else{
            return true;
        }
    }

    function acceptRequest(){
        $sql = "INSERT INTO users (SIRKET_ADI,AD,SOYAD,E_POSTA,KULLANICI_ADI,SIFRE) VALUES('$this->SIRKET_ADI','$this->AD','$this->SOYAD','$this->E_POSTA','$this->KULLANICI_ADI','$this->SIFRE')";
        $result = mysqli_query($this->DB_CONNECTION,$sql);


    }

    function declineRequest(){
        $sql = "UPDATE requests SET DURUM = 'Reddedildi' WHERE ID = '$this->ID2' ";

        $result = mysqli_query($this->DB_CONNECTION,$sql);
    }
    

    

}

