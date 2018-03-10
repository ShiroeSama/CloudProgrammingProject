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
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */

	namespace App\Controller;

	use \App;
	
	use Guzzle\Http\Client;
	use ShirOSBundle\Utils\Url\Url;
	use ShirOSBundle\Controller\Controller;

	class AppController extends Controller
	{
		protected const BASE_URL_GAE = '';
		protected const BASE_URL_HEROKU = '';
		
		protected const GAE_API_URL = '';
		protected const HEROKU_API_URL = '';
		
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
			
			$this->SessionModule->navSet();
			$this->ApplicationModule = App::getInstance();
			$this->UrlModule = new Url();
			
			$this->GuzzleClientGAE = new Client(self::BASE_URL_GAE . static::GAE_API_URL);
			$this->GuzzleClientHeroku = new Client(self::BASE_URL_HEROKU . static::HEROKU_API_URL);
		}
	}
?>