<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : index.php
	 *   @Created_at : 24/11/2016
	 *   @Update_at : 30/12/2017
	 * --------------------------------------------------------------------------
	 */
	
	
	/*
	|--------------------------------------------------------------------------
	| Constante
	|--------------------------------------------------------------------------
	|
	*/
	
	define('ROOT', __DIR__);
	define('VIEW_PATH', ROOT . '/App/View');
	
	
	/*
	|--------------------------------------------------------------------------
	| Autoload
	|--------------------------------------------------------------------------
	|
	*/
	
	require ROOT . '/Core/ShirOSBundle/src/ApplicationKernel.php';
	require ROOT . '/App/App.php';
	require ROOT . '/Core/guzzle.phar';
	App::load();
	
	
	/*
	|--------------------------------------------------------------------------
	| Pre-Load Instance
	|--------------------------------------------------------------------------
	|
	*/
	
	use ShirOSBundle\Config;
	use ShirOSBundle\Router;
	use ShirOSBundle\Utils\Session\Session;
	
	App::getInstance();
	Config::getInstance();
	$Session = Session::getInstance();
	$Router = Router::getInstance();
	
	
	/*
	|--------------------------------------------------------------------------
	| Create Navigation Session
	|--------------------------------------------------------------------------
	|
	*/
	
	$Session->navInit();
	
	
	/*
	|--------------------------------------------------------------------------
	| Router
	|--------------------------------------------------------------------------
	|
	*/
	
	$Router->init();
?>