<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : AccountController.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 25/05/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Controller;
	
	
	use App\Core\Service\Account\AccountService;
	use App\Core\Validation\Account\AccountValidation;
	use App\Core\Validation\Loan\LoanValidation;
	use ShirOSBundle\Utils\Exception\ShirOSException;
	use ShirOSBundle\View\HTML\ShirOSForm;
	
	class LoanController extends AppController
	{
		public const API_URL = '/api/approvals';
		
		/**
		 * @var AccountService
		 */
		protected $accountService;
		
		/**
		 * Instance de la Classe de gestion de Validation de champs
		 * @var AccountValidation
		 */
		private $validationModule;
		
		/**
		 * AccountController constructor.
		 */
		public function __construct()
		{
			parent::__construct();
			$this->accountService = new AccountService();
			$this->validationModule = new LoanValidation();
		}
		
		
		/* ------------------------ Route ------------------------ */
		
		/**
		 * Homepage
		 * @throws ShirOSException
		 */
		public function index()
		{
			$request = $this->GuzzleClientHeroku->get(self::BASE_URL_HEROKU . AccountController::API_URL);
			$response = $request->send();
			
			$body = json_decode($response->getBody());
			if (is_null($body)) {
				throw new ShirOSException("Problème avec le flux. Les données renvoyées ne sont pas sous format JSON", 500); // I'm a Teapot :)
			}
			
			$accounts = $this->accountService->responseProcessingForLoan($body);
			
			$values = $this->RequestModule->getPostRequest();
			$errors = [];
			$valid = true;
			
			if($this->RequestModule->isPostRequest()) {
				$post = $this->RequestModule->getPostRequest();
				
				if(isset($post[$this->ConfigModule->get('Url.Key.Return')]))
					$this->SessionModule->navBack();
				else {
					$this->validationModule->validateForm($post);
					$valid  = $this->validationModule->isValid();
					$values = $this->validationModule->getValues();
					
					if ($valid) {
						$request = $this->GuzzleClientHeroku->post();
						foreach ($values as $key => $value) {
							$request->setPostField($key, $value);
						}
						
						$response = $request->send();
						
						if ($response->getStatusCode() == 201) {
							$this->UrlModule->goTo('Homepage');
						} else {
							throw new ShirOSException($response->getBody(), $response->getStatusCode());
						}
					} else {
						$errors = $this->validationModule->getErrors();
					}
				}
			}
			
			$form = new ShirOSForm($values);
			$var = compact('form','accounts', 'valid', 'errors');
			
			$this->render('Loan.index', $var);
		}
	}
?>