<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : AppController.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 25/05/2018
	 * --------------------------------------------------------------------------
	 */

	namespace App\Controller;

	use \App;
	
	use Guzzle\Http\Client;
	use ShirOSBundle\Utils\Url\Url;
	use ShirOSBundle\Controller\Controller;

	class AppController extends Controller
	{
		public const BASE_URL_GAE = '';
		public const BASE_URL_HEROKU = 'https://arcane-lake-71736.herokuapp.com';
		
		public const API_URL = '';
		
		/**
		 * Client Guzzle for GAE (Google Apps Engine)
		 * @var Client
		 */
		protected $GuzzleClientGAE;
		
		/**
		 * Client Guzzle for Heroku
		 * @var Client
		 */
		protected $GuzzleClientHeroku;
		
		
		/**
		 * AppController constructor.
		 */
		public function __construct()
		{
			parent::__construct();
			
			$this->ApplicationModule = App::getInstance();
			$this->UrlModule = new Url();
			
			$this->GuzzleClientGAE = new Client(self::BASE_URL_GAE . static::API_URL);
			$this->GuzzleClientHeroku = new Client(self::BASE_URL_HEROKU . static::API_URL);
		}
	}
?>