<?php
	
	/**
	 * --------------------------------------------------------------------------
	 *   @Copyright : License MIT 2017
	 *
	 *   @Author : Alexandre Caillot
	 *   @WebSite : https://www.shiros.fr
	 *
	 *   @File : ApprovalService.php
	 *   @Created_at : 10/03/2018
	 *   @Update_at : 26/05/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Core\Service\Approval;
	
	use App\Core\Service\Account\AccountService;
	use App\Core\Service\Response\ResponseService;
	use App\Core\Service\AppService;
	use App\Model\Approval;
	use ShirOSBundle\Utils\Exception\ShirOSException;
	
	class ApprovalService extends AppService
	{
		/**
		 * @var AccountService
		 */
		protected $accountService;
		
		/**
		 * @var ResponseService
		 */
		protected $responseService;
		
		/**
		 * ApprovalService constructor.
		 */
		public function __construct()
		{
			parent::__construct();
			$this->accountService = new AccountService();
			$this->responseService = new ResponseService();
		}
		
		/**
		 * @param array $body
		 * @return array
		 * @throws ShirOSException
		 */
		public function responseProcessing(array $body): array
		{
			$approvals = [];
			
			foreach ($body as $objectId => $element) {
				try {
					$approval = $this->createApproval($element);
					array_push($approvals, $approval);
				} catch (ShirOSException $shirOSException) {
					throw new ShirOSException("Clé manquant pour l'objet {$objectId} dans le flux /api/approvals", 500, $shirOSException);
				}
			}
			
			return $approvals;
		}
		
		
		/**
		 * @param array $approval
		 * @return Approval
		 * @throws ShirOSException
		 */
		public function createApproval($approval): Approval
		{
			$idField = $this->ConfigModule->get('Fields.Name.Id');
			$dateField = $this->ConfigModule->get('Fields.Name.Date');
			$accountField = $this->ConfigModule->get('Fields.Name.Account');
			$responseField = $this->ConfigModule->get('Fields.Name.Response');
			
			if (property_exists($approval,$idField)
				&& property_exists($approval,$dateField)
				&& property_exists($approval,$accountField)
				&& property_exists($approval,$responseField)
			) {
				$approvalModel = new Approval();
				$account = $this->accountService->createAccount($approval->$accountField);
				$response = $this->responseService->createResponse($approval->$responseField);
				
				$approvalModel->setId($approval->$idField);
				$approvalModel->setDate($approval->$dateField);
				$approvalModel->setAccount($account);
				$approvalModel->setResponse($response);
				
				return $approvalModel;
			} else {
				throw new ShirOSException();
			}
		}
	}
?>