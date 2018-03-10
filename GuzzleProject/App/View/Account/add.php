<?php
	use ShirOSBundle\Config;
	use ShirOSBundle\Utils\Url\Url;
	use ShirOSBundle\View\HTML\ShirOSForm;
	
	/** @var Url $UrlModule */
	/** @var ShirOSForm $form */
	/** @var Config $ConfigModule */
?>


<section class="item ptb-50 plr-100">
	
	<section class="post">
		<div class="post-header d-inline-vcenter text-left">
			<a class="d-inline w-80 mtb-auto">Ajout d'un comptes</a>
			
			<form method="POST" class="d-inline w-20 text-end">
				<?= $form->submitWithId($ConfigModule->get('Url.Key.Return'), 'Retour', NULL, 'btn-third'); ?>
			</form>
		</div>
		
		<div class="post-content">
			<div class="d-center w-80">
				<form method="POST">
					
					<!-- NAME ACCOUNT -->
					
						<div>
							<div class="d-inline w-20">Nom du Compte :</div>
							
							<?php if(isset($errors[$ConfigModule->get('Fields.Name.AccountName')])) : ?>
								<?= $form->field($ConfigModule->get('Fields.Name.AccountName'), 'Nom du Compte', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70'], true); ?>
							<?php else: ?>
								<?= $form->field($ConfigModule->get('Fields.Name.AccountName'), 'Nom du Compte', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70']); ?>
							<?php endif; ?>
						</div>
					
					
					
					<!-- LASTNAME -->
					
						<div>
							<div class="d-inline w-20">Nom :</div>
							
							<?php if(isset($errors[$ConfigModule->get('Fields.Name.Lastname')])) : ?>
								<?= $form->field($ConfigModule->get('Fields.Name.Lastname'), 'Nom', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70'], true); ?>
							<?php else: ?>
								<?= $form->field($ConfigModule->get('Fields.Name.Lastname'), 'Nom', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70']); ?>
							<?php endif; ?>
						</div>
					
					
					
					<!-- FIRSTNAME -->
					
						<div>
							<div class="d-inline w-20">Prénom :</div>
							
							<?php if(isset($errors[$ConfigModule->get('Fields.Name.Firstname')])) : ?>
								<?= $form->field($ConfigModule->get('Fields.Name.Firstname'), 'Prénom', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70'], true); ?>
							<?php else: ?>
								<?= $form->field($ConfigModule->get('Fields.Name.Firstname'), 'Prénom', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70']); ?>
							<?php endif; ?>
						</div>
					
					
					
					<!-- AMOUNT -->
					
						<div>
							<div class="d-inline w-20">Solde de départ :</div>
							
							<?php if(isset($errors[$ConfigModule->get('Fields.Name.Amount')])) : ?>
								<?= $form->field($ConfigModule->get('Fields.Name.Amount'), 'Solde de départ', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70'], true); ?>
							<?php else: ?>
								<?= $form->field($ConfigModule->get('Fields.Name.Amount'), 'Solde de départ', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline w-70']); ?>
							<?php endif; ?>
						</div>
					
					<?= $form->submit('Enregistrer', NULL, [ShirOSForm::OPTIONS_SURROUND => 'd-inline-center mt-20']); ?>
				</form>
			</div>
		</div>
	</section>
		
	<?php if(!$valid): ?>
		<div class="alert alert-danger mtb-20"><a>Nous avons rencontrés des difficultés</a></div>
	<?php endif; ?>

</section>