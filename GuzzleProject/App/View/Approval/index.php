<?php
	use ShirOSBundle\Utils\Url\Url;
	use ShirOSBundle\View\HTML\ShirOSForm;
	
	/** @var Url $UrlModule */
	/** @var ShirOSForm $form */
?>


<section class="item ptb-50 plr-100">
    <div class="post">
        <div class="post-header">Approbation de crédit</div>

        <div class="post-content">

            <div class="overflow">
                <table>
                    <thead>
                    <tr id="header">
                        <th id="Id">#</th>
                        <th id="Account">Compte</th>
                        <th id="Response">Réponse</th>
                        <th id="Date">Date</th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody class="mh-400PX">
					<?php /** @var \App\Model\Approval $approval */?>
					<?php foreach ($approvals as $approval): ?>
                        <tr>
                            <td><?= $approval->getId(); ?></td>
                            <td><?= $approval->getAccount()->getName(); ?></td>
                            <td><?= $approval->getResponse()->getName(); ?></td>
                            <td><?= $approval->getDate(); ?></td>
                            <td>
                                <a href="<?= $UrlModule->getUrl('Approval-Delete', ['id' => $approval->getId()]) ?>" class="btn btn-danger"><span class="glyphicon glyphicon-remove m-0"></span></a>
                            </td>
                        </tr>
					<?php endforeach; ?>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</section>