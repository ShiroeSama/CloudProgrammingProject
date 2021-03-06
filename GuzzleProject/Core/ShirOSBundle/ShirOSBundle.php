<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : ShirOSBundle.php
	 *   @Created_at : 03/12/2017
	 *   @Update_at : 03/12/2017
	 * --------------------------------------------------------------------------
	 */
	
	/**
	 * Gére le Bundle ShirOS
	 */
	class ShirOSBundle
	{
		public static function register()
		{
			define('SHIROS_ROOT', __DIR__);
			define('SHIROS_CSS', SHIROS_ROOT . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'css');
			define('SHIROS_WEB_CSS', strstr(SHIROS_CSS, 'Core/ShirOSBundle'));
			
			define('SHIROS_ROUTES', 'Config/route.php');

			/* ---- Inclusion du Bundle ---- */
				require_once 'src' . DIRECTORY_SEPARATOR . 'Autoloader.php';
				ShirOSBundle\Autoloader::register();
		}
	}
?>