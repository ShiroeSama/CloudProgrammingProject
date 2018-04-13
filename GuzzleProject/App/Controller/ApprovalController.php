<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : ApprovalController.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Controller;
	
	
	use App\Core\Service\Approval\ApprovalService;
	use Guzzle\Http\Exception\RequestException;
	use ShirOSBundle\Utils\Exception\ShirOSException;
	
	class ApprovalController extends AppController
	{
		protected const HEROKU_API_URL = '/api/approvals';
		
		/**
		 * @var ApprovalService
		 */
		protected $approvalService;
		
		/**
		 * AccountController constructor.
		 */
		public function __construct()
		{
			parent::__construct();
			$this->approvalService = new ApprovalService();
		}
		
		
		/* ------------------------ Route ------------------------ */
		
			/**
			 * Homepage
			 * @throws ShirOSException
			 */
			public function index()
			{
				$request = $this->GuzzleClientHeroku->get();
				$response = $request->send();
				
				$body = json_decode($response->getBody());
				
				if (is_null($body)) {
					throw new ShirOSException("Problème avec le flux. Les données renvoyées ne sont pas sous format JSON", 500);
				}
				
				$approvals = $this->approvalService->responseProcessing($body);
				
				$vars = compact('approvals');
				$this->render('Approval.index', $vars);
			}
		
			/**
			 * Delete
			 * @throws ShirOSException
			 */
			public function delete(int $id)
			{
				$request = $this->GuzzleClientHeroku->delete(self::BASE_URL_HEROKU . self::HEROKU_API_URL . "/{$id}");
				$response = $request->send();
				
				if ($response->getStatusCode() == 204) {
					$this->UrlModule->goTo('Approval');
				} else {
					throw new ShirOSException($response->getBody(), $response->getStatusCode());
				}
			}
	}
?>