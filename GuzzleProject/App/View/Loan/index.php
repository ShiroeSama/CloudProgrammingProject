<?php
	use ShirOSBundle\Config;
	use ShirOSBundle\Utils\Url\Url;
	use ShirOSBundle\View\HTML\ShirOSForm;
	
	/** @var Url $UrlModule */
	/** @var ShirOSForm $form */
	/** @var Config $ConfigModule */
?>


<section class="item ptb-50 plr-100">
	<div class="post">
		<div class="post-header">Demande de crédit</div>
		
		<div class="post-content">
			
			<div class="d-center w-80">
				<form method="POST">
					
					<!-- ACCOUNT -->
						
						<div>
							<div class="d-inline w-20">Compte :</div>
							
							<?= $form->select($ConfigModule->get('Fields.Name.AccountId'), $accounts); ?>
							
							<?php if(isset($errors[$ConfigModule->get('Fields.Name.AccountId')])) : ?>
								<?php if(!empty($errors[$ConfigModule->get('Fields.Name.AccountId')])): ?>
									<div class="alert alert-danger-revert text-size-14"><?= $errors[$ConfigModule->get('Fields.Name.AccountId')] ?></div>
								<?php endif; ?>
							<?php endif; ?>
						</div>
					
					
					<!-- AMOUNT -->
					
					<div>
						<div class="d-inline w-20">Solde :</div>
						
						<?php if(isset($errors[$ConfigModule->get('Fields.Name.Amount')])) : ?>
							<?= $form->field($ConfigModule->get('Fields.Name.Amount'), 'Montant', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70'], true); ?>
							<?php if(!empty($errors[$ConfigModule->get('Fields.Name.Amount')])): ?>
                                <div class="alert alert-danger-revert text-size-14"><?= $errors[$ConfigModule->get('Fields.Name.Amount')] ?></div>
							<?php endif; ?>
						<?php else: ?>
							<?= $form->field($ConfigModule->get('Fields.Name.Amount'), 'Montant', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70']); ?>
						<?php endif; ?>
					</div>
     
					<?= $form->submit('Envoyer', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline-center mt-20']); ?>
				</form>
			</div>
		
		</div>
	</div>
</section>