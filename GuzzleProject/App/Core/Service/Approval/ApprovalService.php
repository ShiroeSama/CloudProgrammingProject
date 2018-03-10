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
	 *   @Update_at : 10/03/2018
	 * --------------------------------------------------------------------------
	 */
	
	namespace App\Core\Service\Approval;
	
	use App\Core\Service\Account\AccountService;
	use App\Core\Service\AppService;
	use App\Model\Approval;
	use App\Model\Response;
	use ShirOSBundle\Utils\Exception\ShirOSException;
	
	class ApprovalService extends AppService
	{
		/**
		 * @var AccountService
		 */
		protected $accountService;
		
		/**
		 * ApprovalService constructor.
		 */
		public function __construct()
		{
			parent::__construct();
			$this->accountService = new AccountService();
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
		public function createApproval(array $approval): Approval
		{
			if (isset($approval[$this->ConfigModule->get('Fields.Name.Id')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Date')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Account')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Account')][$this->ConfigModule->get('Fields.Name.Id')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Account')][$this->ConfigModule->get('Fields.Name.AccountName')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Account')][$this->ConfigModule->get('Fields.Name.Lastname')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Account')][$this->ConfigModule->get('Fields.Name.Firstname')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Account')][$this->ConfigModule->get('Fields.Name.Amount')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Response')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Response')][$this->ConfigModule->get('Fields.Name.Id')])
				&& isset($approval[$this->ConfigModule->get('Fields.Name.Response')][$this->ConfigModule->get('Fields.Name.ResponseValue')])
			) {
				$approvalModel = new Approval();
				$account = $this->accountService->createAccount($approval[$this->ConfigModule->get('Fields.Name.Account')]);
				$response = new Response();
				
				$response->setId($approval[$this->ConfigModule->get('Fields.Name.Response')][$this->ConfigModule->get('Fields.Name.Id')]);
				$response->setName($approval[$this->ConfigModule->get('Fields.Name.Response')][$this->ConfigModule->get('Fields.Name.ResponseValue')]);
				
				$approvalModel->setId($approval[$this->ConfigModule->get('Fields.Name.Id')]);
				$approvalModel->setDate($approval[$this->ConfigModule->get('Fields.Name.Date')]);
				$approvalModel->setAccount($account);
				$approvalModel->setResponse($response);
				
				return $approvalModel;
			} else {
				throw new ShirOSException();
			}
		}
	}
?>