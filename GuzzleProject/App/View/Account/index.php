<?php
	use ShirOSBundle\Utils\Url\Url;
	use ShirOSBundle\View\HTML\ShirOSForm;
	
	/** @var Url $UrlModule */
	/** @var ShirOSForm $form */
?>


<section class="item ptb-50 plr-100">
	<div class="post">
		<div class="post-header d-inline-vcenter text-left">
			<a class="d-inline w-80 mtb-auto">Comptes</a>
			
			<div class="d-inline w-20 text-end">
				<a href="<?= $UrlModule->getUrl('Account-Add') ?>" class="btn btn-third"><span class="glyphicon glyphicon-plus m-0"></span></a>
			</div>
		</div>
		
		<div class="post-content">
			
			<div class="overflow">
				<table>
					<thead>
					<tr id="header">
						<th id="Id">#</th>
						<th id="AccountName">Nom de Compte</th>
						<th id="Lastname">Nom</th>
						<th id="Firstname">Prénom</th>
						<th id="Amount">Solde</th>
						<th></th>
					</tr>
					</thead>
					
					<tbody class="mh-400PX">
					<?php /** @var \App\Model\Account $account */?>
					<?php foreach ($accounts as $account): ?>
						<tr>
							<td><?= $account->getId(); ?></td>
							<td><?= $account->getName(); ?></td>
							<td><?= $account->getLastname(); ?></td>
							<td><?= $account->getFirstname(); ?></td>
							<td><?= $account->getAmount(); ?></td>
							<td>
								<a href="<?= $UrlModule->getUrl('Account-Delete', ['id' => $account->getId()]) ?>" class="btn btn-danger"><span class="glyphicon glyphicon-remove m-0"></span></a>
							</td>
						</tr>
					<?php endforeach; ?>
					</tbody>
				</table>
			</div>
		
		</div>
	</div>
</section>